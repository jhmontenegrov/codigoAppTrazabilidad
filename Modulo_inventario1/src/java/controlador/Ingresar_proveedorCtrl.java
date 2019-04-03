/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.Proveedor;
import dao.Ingresar_proveedorDAO;
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
@WebServlet(name = "Ingresar_proveedorCtrl", urlPatterns = {"/Ingresar_proveedorCtrl"})
public class Ingresar_proveedorCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            String direccionProv = new String(request.getParameter("direccionProv").getBytes("ISO-8859-1"), "UTF-8");
            int id_ciudad = new Integer(request.getParameter("id_ciudadAC"));
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from proveedor pr where pr.Dir_proveedor='" + direccionProv + "' AND pr.CiudadID='"+id_ciudad+"'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                int asignaID = 133;
                int contador = 0;
                Connection conn1 = null;
                PreparedStatement stmt1 = null;
                conn1 = (Connection) Conexion.crearConexion();
                //Contador
                Statement st1 = (Statement) conn1.createStatement();
                String sql1 = "select COUNT(*) AS conta from proveedor ";
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    contador = rs1.getInt("conta");
                }
                contador = contador + 1;
                asignaID = asignaID + contador;
                String nombreProv = new String(request.getParameter("nombreProv").getBytes("ISO-8859-1"), "UTF-8");
                String telProv = new String(request.getParameter("telProv").getBytes("ISO-8859-1"), "UTF-8");
                String mailProv = new String(request.getParameter("mailProv").getBytes("ISO-8859-1"), "UTF-8");
                Proveedor proveedor = new Proveedor(asignaID, nombreProv, direccionProv, telProv, mailProv, id_ciudad);
                Ingresar_proveedorDAO ingresar_proveedorDAO = new Ingresar_proveedorDAO();
                ingresar_proveedorDAO.ingresar_proveedor(proveedor);
                request.getRequestDispatcher("/lista_proveedores.jsp").forward(request, response);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Ingresar_proveedorCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Ingresar_proveedorCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
