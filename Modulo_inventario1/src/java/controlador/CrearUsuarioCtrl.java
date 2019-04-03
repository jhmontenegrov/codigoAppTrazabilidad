/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import beans.Usuario;
import dao.CrearUsuarioDAO;
import beans.Conexion;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jhona
 */
@WebServlet(name = "CrearUsuarioCtrl", urlPatterns = {"/CrearUsuarioCtrl"})
public class CrearUsuarioCtrl extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer cid = Integer.parseInt(request.getParameter("cid"));
            String user = request.getParameter("user");
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from usuario usu where usu.UsuarioID='"+cid+"' or usu.user='"+user+"'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            }else{
            response.setContentType("text/html;charset=UTF-8");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String pass = request.getParameter("pass");
            String clave = DigestUtils.sha1Hex(pass);
            String correo = request.getParameter("correo");
            Integer id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
            Integer t_usuario = Integer.parseInt(request.getParameter("t_usuario"));
            Boolean activ_usuario= Boolean.getBoolean(request.getParameter("activ_usuario"));
            Usuario usuario= new Usuario(nombre, apellido, telefono, direccion, user, clave, correo, activ_usuario, cid, id_ciudad, t_usuario);
            CrearUsuarioDAO crearUsuarioDAO = new CrearUsuarioDAO();
            crearUsuarioDAO.crearUsuario(usuario);
            request.getRequestDispatcher("/lista_usuario.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrearUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
