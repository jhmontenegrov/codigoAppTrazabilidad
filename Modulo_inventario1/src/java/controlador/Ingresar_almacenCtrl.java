/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import beans.Almacen;
import beans.Conexion;
import dao.Ingresar_almacenDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "Ingresar_almacenCtrl", urlPatterns = {"/Ingresar_almacenCtrl"})
public class Ingresar_almacenCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String ubica = new String(request.getParameter("ubica").getBytes("ISO-8859-1"), "UTF-8");
        int ciudadID = new Integer(request.getParameter("id_ciudad"));
        int establecimientoID = new Integer(request.getParameter("id_establecimiento"));
        Connection conn;
        PreparedStatement stmt = null;
        conn = (Connection) Conexion.crearConexion();
        Statement st = (Statement) conn.createStatement();
        String sql = "select *  from almacen alm where alm.CiudadID='"+ciudadID+"' AND alm.Ubicacion='"+ubica+"'";
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
        } else {
            int asignaID = 111;
            int contador = 0;
            Connection conn1 = null;
            PreparedStatement stmt1 = null;
            conn1 = (Connection) Conexion.crearConexion();
            Statement st1 = (Statement) conn1.createStatement();
            String sql1 = "select COUNT(*) AS conta from almacen";
            ResultSet rs1 = st.executeQuery(sql1);
            while (rs1.next()) {
                contador = rs1.getInt("conta");
            }
            contador = contador + 1;
            asignaID = asignaID + contador;

            HttpSession sesion_cli = request.getSession(true);
            int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
            Almacen almacen = new Almacen(ubica, asignaID, ciudadID, establecimientoID, idUsuario);
            Ingresar_almacenDAO ingresar_almacenDAO = new Ingresar_almacenDAO();
            ingresar_almacenDAO.CrearAlmacen(almacen);
            request.getRequestDispatcher("/lista_almacen.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_almacenCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}