/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.Establecimiento;
import dao.Ingresar_establecimientoDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhona
 */
@WebServlet(name = "Ingresar_establecimientoCtrl", urlPatterns = {"/Ingresar_establecimientoCtrl"})
public class Ingresar_establecimientoCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String ubi_tienda = new String(request.getParameter("ubi_tienda").getBytes("ISO-8859-1"), "UTF-8");
        Integer ciudad = new Integer(request.getParameter("id_ciudad"));
        Connection conn;
        PreparedStatement stmt = null;
        conn = (Connection) Conexion.crearConexion();
        Statement st = (Statement) conn.createStatement();
        String sql = "select *  from establecimiento est where est.Ubicaciontienda='" + ubi_tienda + "' AND est.CiudadID='" + ciudad + "'";
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
        } else {
            int asignaID = 111;
            int contador = 0;
            Connection conn1 = null;
            PreparedStatement stmt1 = null;
            conn1 = (Connection) Conexion.crearConexion();
            //Contador
            Statement st1 = (Statement) conn1.createStatement();
            String sql1 = "select COUNT(*) AS conta from establecimiento";
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {
                contador = rs1.getInt("conta");
            }
            contador = contador + 1;
            asignaID = asignaID + contador;
            HttpSession sesion_cli = request.getSession(true);
            int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
            Establecimiento establecimiento = new Establecimiento(ubi_tienda, asignaID, idUsuario, ciudad);
            Ingresar_establecimientoDAO ingresar_establecimientoDAO = new Ingresar_establecimientoDAO();
            ingresar_establecimientoDAO.ingresar_establecimientoD(establecimiento);
            request.getRequestDispatcher("/lista_establecimiento.jsp").forward(request, response);
        }
    }
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_establecimientoCtrl.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        }

        @Override
        public void doGet
        (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            doPost(req, res);
        }
    }
