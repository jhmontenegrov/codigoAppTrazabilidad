/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Ciudad;
import beans.Conexion;
import dao.Ingresa_ciudadDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.IOException;
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

/**
 *
 * @author jhona
 */
@WebServlet(name = "Ingresa_ciudadCtrl", urlPatterns = {"/Ingresa_ciudadCtrl"})
public class Ingresa_ciudadCtrl extends HttpServlet {

    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name_pais = new String(request.getParameter("name_pais").getBytes("ISO-8859-1"), "UTF-8");
        String name_ciudad = new String(request.getParameter("name_ciudad").getBytes("ISO-8859-1"), "UTF-8");
        Connection conn;
        PreparedStatement stmt = null;
        conn = (Connection) Conexion.crearConexion();
        Statement st = (Statement) conn.createStatement();
        String sql = "select *  from ciudad c where c.Ciudad_nombre='" + name_ciudad + "' AND c.Pais='" + name_pais + "'";
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            int asignaID = 111;
            int contador = 0;
            Connection conn1 = null;
            PreparedStatement stmt1 = null;
            conn1 = (Connection) Conexion.crearConexion();
            //Contador
            Statement st1 = (Statement) conn1.createStatement();
            String sql1 = "select COUNT(*) AS conta from ciudad";
            ResultSet rs1 = st1.executeQuery(sql);
            while (rs1.next()) {
                contador = rs1.getInt("conta");
            }
            contador = contador + 1;
            asignaID = asignaID + contador;
            Ciudad ciudad = new Ciudad(asignaID, name_ciudad, name_pais);
            Ingresa_ciudadDAO ingresar_almacenDAO = new Ingresa_ciudadDAO();
            ingresar_almacenDAO.IngresarCiudad(ciudad);
            request.getRequestDispatcher("/ingresar_establecimiento.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Ingresa_ciudadCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
