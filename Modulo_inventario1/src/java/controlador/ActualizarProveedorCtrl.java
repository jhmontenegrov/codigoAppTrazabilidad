/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Proveedor;
import dao.ActualizarProveedorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhona
 */
@WebServlet(name = "ActualizarProveedorCtrl", urlPatterns = {"/ActualizarProveedorCtrl"})
public class ActualizarProveedorCtrl extends HttpServlet {

    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.setContentType("text/html;charset=UTF-8");
            int idt = new Integer(request.getParameter("idpr"));
            String direccionProv = new String(request.getParameter("direccionProv").getBytes("ISO-8859-1"), "UTF-8");
            String telProv = new String(request.getParameter("telProv").getBytes("ISO-8859-1"), "UTF-8");
            String mailProv = new String(request.getParameter("mailProv").getBytes("ISO-8859-1"), "UTF-8");
            Integer id_ciudadAC= new Integer(request.getParameter("id_ciudadAC"));
            Proveedor proveedor = new Proveedor(idt);
            proveedor.setDirproveedor(direccionProv);
            proveedor.setTelproveedor(telProv);
            proveedor.setMailproveedor(mailProv);
            proveedor.setCiudadID(id_ciudadAC);
            ActualizarProveedorDAO actualizarProveedorDAO = new ActualizarProveedorDAO();
            actualizarProveedorDAO.actualizarProveedor(proveedor);
            sc.getRequestDispatcher("/lista_proveedores.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarestablecimientoCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
