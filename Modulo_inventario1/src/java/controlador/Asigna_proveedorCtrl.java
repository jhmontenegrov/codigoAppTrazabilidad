/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.AsignaInventarioProveedor;
import beans.Conexion;
import dao.Asigna_proveedorDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhona
 */
public class Asigna_proveedorCtrl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int AsignaID = 111;
            int contador = 0;
            Connection conn = null;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            //Contador
            Statement st = (Statement) conn.createStatement();
            String sql = "select COUNT(*) AS conta from asignada_perfil_usuario";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                contador = rs.getInt("conta");
            }
            contador = contador + 1;
            AsignaID = AsignaID + contador;
            HttpSession sesion_cli = request.getSession(true);
            int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
            int select_prov = Integer.parseInt(request.getParameter("select_prov"));
            int select_inventario = new Integer(request.getParameter("select_inventario"));
            AsignaInventarioProveedor asignaInventarioProveedor = new AsignaInventarioProveedor(AsignaID, select_inventario, select_prov, idUsuario);
            Asigna_proveedorDAO asigna_proveedorDAO = new Asigna_proveedorDAO();
            asigna_proveedorDAO.asignaProveedor(asignaInventarioProveedor);
            request.getRequestDispatcher("/asigna_proveedor.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Asigna_proveedorCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
