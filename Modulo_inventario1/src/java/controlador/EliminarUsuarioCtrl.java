/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Usuario;
import dao.EliminarUsuarioDAO;
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
@WebServlet(name = "EliminarUsuarioCtrl", urlPatterns = {"/EliminarUsuarioCtrl"})
public class EliminarUsuarioCtrl extends HttpServlet {

    ServletContext sc = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            response.setContentType("text/html;charset=UTF-8");
            Integer idUsu = Integer.parseInt(request.getParameter("idUS"));
            Boolean activ_usuario = Boolean.parseBoolean(request.getParameter("activ_usuario"));
            Usuario usuario = new Usuario(idUsu); 
            usuario.setCheckbloquearusuario(activ_usuario);
            EliminarUsuarioDAO eliminarUsuarioDAO = new EliminarUsuarioDAO();
            eliminarUsuarioDAO.eliminarUsuasrio(usuario);
            sc.getRequestDispatcher("/lista_usuario.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarMedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
