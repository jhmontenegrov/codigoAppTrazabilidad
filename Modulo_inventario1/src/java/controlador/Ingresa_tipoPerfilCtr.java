/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.PerfilUsuario;
import dao.Ingresa_tipoPerfilDAO;
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
@WebServlet(name = "Ingresa_tipoPerfilCtr", urlPatterns = {"/Ingresa_tipoPerfilCtr"})
public class Ingresa_tipoPerfilCtr extends HttpServlet {

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
            String tipoP = new String(request.getParameter("tipoPer").getBytes("ISO-8859-1"), "UTF-8");
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from perfil_usuario p where p.Nom_perfilusuario='" + tipoP + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                response.setContentType("text/html;charset=UTF-8");
                int asignaID = 311;
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
                PerfilUsuario perfilUsuario = new PerfilUsuario(asignaID, tipoP);
                Ingresa_tipoPerfilDAO perfilUsuarioDAO = new Ingresa_tipoPerfilDAO();
                perfilUsuarioDAO.ingresar_tipoUsuario(perfilUsuario);
                request.getRequestDispatcher("/lista_usuario.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresa_tipoPerfilCtr.class.getName()).log(Level.SEVERE, null, ex);
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
