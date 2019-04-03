/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Usuario;
import dao.ActualizarInfoPerfilDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
@WebServlet(name = "ActualizarInfoPerfiCtrol", urlPatterns = {"/actualizarInfoPerfiCtrol"})
public class ActualizarInfoPerfiCtrol extends HttpServlet {
         protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        HttpSession sesion_cli = request.getSession(true);
        int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
        String telefono = new String(request.getParameter("telefonoAC").getBytes("ISO-8859-1"), "UTF-8");
        String direccion = new String(request.getParameter("direccionAC").getBytes("ISO-8859-1"), "UTF-8");    
        String correo = new String(request.getParameter("correoAC").getBytes("ISO-8859-1"), "UTF-8");
        int id_ciudad = new Integer(request.getParameter("id_ciudadAC"));
        Usuario usuario = new Usuario(idUsuario);
        usuario.setTelusuario(telefono);
        usuario.setDireccion(direccion);
        usuario.setEmail(correo);
        usuario.setCiudadID(id_ciudad);
        ActualizarInfoPerfilDAO actualizarInfoPerfilDAO = new ActualizarInfoPerfilDAO();
        actualizarInfoPerfilDAO.actualizarUsuario(usuario);
        request.getRequestDispatcher("/perfil_usuario.jsp").forward(request, response);
    }

         @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(ActualizarInfoPerfiCtrol.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

         @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
