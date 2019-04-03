/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotrl;

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
import Beans.Conexion;
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
            int estab;
            usu = request.getParameter("u_login");
            cla = request.getParameter("p_login");
            estab = Integer.parseInt(request.getParameter("estab"));
            String clave = DigestUtils.sha1Hex(cla);
            con = (Connection) Conexion.crearConexion();
            sentencia = (Statement) con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.user='" + usu + "' AND u.Contrasena = '" + clave + "' AND u.Check_bloquear_usuario=false AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='inicio.jsp'");
            while (resultado.next()) {
                nusu = resultado.getString("user");
                idclave = resultado.getString("Contrasena");
                id = resultado.getInt("usuarioID");
            }
            if (usu == null || clave == null) {
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
                sesion_cli.setAttribute("idUsuario",id);
                sesion_cli.setAttribute("estab",estab);
                sc.getRequestDispatcher("/generic.jsp").forward(request, response);
//Validación de usuario y contraseña
            } else if (!usu.equals(nusu) || !clave.equals(idclave)) {
                sc.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            ses.setAttribute("mensaje", "Usuario no encontrado.");
            ses.setAttribute("exc", e.toString());
            sc.getRequestDispatcher("/Inicio_sesion.jsp");
            out.println("no se que pasa");
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
        }
    }
}
