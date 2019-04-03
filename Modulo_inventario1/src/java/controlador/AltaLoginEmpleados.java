/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.ResultSet;
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
import javax.servlet.http.HttpSession;
import beans.Conexion;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jhona
 */
@WebServlet(name = "AltaLoginEmpleados", urlPatterns = {"/AltaLoginEmpleados"})
public class AltaLoginEmpleados extends HttpServlet {

    ResultSet resultado, resultado2;
    Connection con;
    Statement sentencia;
    String nusu = "";
    String idclave = "";
    int id;
    //int nrol;
    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession(true);
        try {
            String usu = null;
            String cla = null;
            usu = request.getParameter("u_login");
            cla = request.getParameter("p_login");
            String clave = DigestUtils.sha1Hex(cla);
            con = (Connection) Conexion.crearConexion();
            sentencia = (Statement) con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM usuario where user='" + usu + "' AND Contrasena = '" + clave + "' AND Check_bloquear_usuario=false");
            while (resultado.next()) {
                nusu = resultado.getString("user");
                idclave = resultado.getString("Contrasena");
                id = resultado.getInt("usuarioID");
            }
            if (usu == null || clave.equals(null)) {
                ses.setAttribute("mensaje", "Campos vacios.");
                sc.getRequestDispatcher("/Inicio_sesion.jsp");
                out.println("Campos Vacios");
            }
            if (usu.equals(nusu) && clave.equals(idclave)) {
//Captuara de datos para mantener la session.
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
                response.setHeader("Progma", "no-cache");
                response.setDateHeader("Expires", 0);
                HttpSession sesion_cli = request.getSession(true);
                sesion_cli.setAttribute("idUsuario", id);
                request.getRequestDispatcher("/generic.jsp").forward(request, response);
//Validación de usuario y contraseña
            } else if (!usu.equals(nusu) || !clave.equals(idclave)) {
                sc.getRequestDispatcher("/errorSesion.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            ses.setAttribute("mensaje", "Usuario no encontrado.");
            ses.setAttribute("exc", e.toString());
            sc.getRequestDispatcher("/Inicio_sesion.jsp");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
        sentencia.close();
        con.close();
        ses.setAttribute("titulo", "Control de Acceso");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AltaLoginEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
