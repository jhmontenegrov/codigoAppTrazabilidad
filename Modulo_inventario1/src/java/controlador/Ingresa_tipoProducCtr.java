/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.TipoProducto;
import dao.Ingresa_tipoProductoDAO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
@WebServlet(name = "Ingresa_tipoProducCtr", urlPatterns = {"/Ingresa_tipoProducCtr"})
public class Ingresa_tipoProducCtr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String tipoP = new String(request.getParameter("tipoP").getBytes("ISO-8859-1"), "UTF-8");
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from tipo_producto tp where tp.Nom_tipoproducto='" + tipoP + "'";
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
                String sql1 = "select COUNT(*) AS conta from tipo_producto";
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    contador = rs1.getInt("conta");
                }
                contador = contador + 1;
                asignaID = asignaID + contador;
                String desc = new String(request.getParameter("desc").getBytes("ISO-8859-1"), "UTF-8");
                TipoProducto tipoProducto = new TipoProducto(asignaID, tipoP, desc);
                Ingresa_tipoProductoDAO ingresar_productoDAO = new Ingresa_tipoProductoDAO();
                ingresar_productoDAO.ingresar_tipoProducto(tipoProducto);
                request.getRequestDispatcher("/crear_producto.jsp").forward(request, response);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Ingresa_tipoProducCtr.class.getName()).log(Level.SEVERE, null, ex);
            try {
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                Logger.getLogger(Ingresa_tipoProducCtr.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Ingresa_tipoProducCtr.class.getName()).log(Level.SEVERE, null, ex);
            try {
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                Logger.getLogger(Ingresa_tipoProducCtr.class.getName()).log(Level.SEVERE, null, ex1);
            }
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