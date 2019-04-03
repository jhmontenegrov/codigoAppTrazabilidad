/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.Estado;
import dao.IngresarEstadoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhona
 */
@WebServlet(name = "IngresaEstadoCtrl", urlPatterns = {"/IngresaEstadoCtrl"})
public class IngresaEstadoCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String tipoP = new String(request.getParameter("tipoP").getBytes("ISO-8859-1"), "UTF-8");
            Connection conn = null;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT * FROM estado est WHERE est.Estado='" + tipoP + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                response.setContentType("text/html;charset=UTF-8");
                int asignaID = 111;
                int contador = 0;
                Connection conn1 = null;
                PreparedStatement stmt1 = null;
                conn1 = (Connection) Conexion.crearConexion();
                //Contador
                Statement st1 = (Statement) conn1.createStatement();
                String sql1 = "select COUNT(*) AS conta from estado";
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    contador = rs1.getInt("conta");
                }
                contador = contador + 1;
                asignaID = asignaID + contador;
                String desc = new String(request.getParameter("desc").getBytes("ISO-8859-1"), "UTF-8");
                Estado estado = new Estado(asignaID, tipoP, desc);
                IngresarEstadoDAO ingresarEstadoDAO = new IngresarEstadoDAO();
                ingresarEstadoDAO.ingresar_estado(estado);
                request.getRequestDispatcher("/lista_estado.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresaEstadoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
