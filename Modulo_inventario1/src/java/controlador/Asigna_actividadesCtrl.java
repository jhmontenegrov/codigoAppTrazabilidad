/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.AsignadaPerfilUsuario;
import beans.Conexion;
import dao.Asigna_actividadesDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 *
 */
@WebServlet(name = "Asigna_actividadesCtrl", urlPatterns = {"/Asigna_actividadesCtrl"})
public class Asigna_actividadesCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){

        try {
            int AsignaID = 211;
            int contador = 0;
            Connection conn = null;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            //Contador
            int usuarioID = new Integer(request.getParameter("select_user"));
            int actividades = new Integer(request.getParameter("select_activ"));
            Statement st = (Statement) conn.createStatement();
            String sql = "select * from asignada_perfil_usuario where UsuarioID='" + usuarioID + "' AND Actividades_perfil_usuarioID='" + actividades + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                Statement st1 = (Statement) conn.createStatement();
                String sql1 = "select COUNT(*) AS conta from asignada_perfil_usuario";
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    contador = rs1.getInt("conta");
                }
                contador = contador + 20;
                AsignaID = AsignaID + contador;
                HttpSession sesion_cli = request.getSession(true);
                int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
                int activ = 1;
                AsignadaPerfilUsuario asignadaPerfilUsuario = new AsignadaPerfilUsuario(idUsuario, AsignaID, usuarioID, activ, actividades);
                Asigna_actividadesDAO asigna_actividadesDAO = new Asigna_actividadesDAO();
                asigna_actividadesDAO.AsignarActividades(asignadaPerfilUsuario);
                request.getRequestDispatcher("/asigna_actividades.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Asigna_actividadesCtrl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                Logger.getLogger(Asigna_actividadesCtrl.class.getName()).log(Level.SEVERE, null, ex1);
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
