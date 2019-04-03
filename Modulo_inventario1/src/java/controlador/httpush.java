/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author jhona
 */
@WebServlet(name = "httpush", urlPatterns = {"/httpush"})
public class httpush extends HttpServlet {

    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InterruptedException, ParseException {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = sdf.format(fecha);
        Connection conn=null;
        conn = Conexion.crearConexion();
        String sql = "SELECT Fech_notificacion FROM producto ORDER BY Fech_notificacion DESC LIMIT 1";
        Statement st=null;
        st = (Statement) conn.createStatement();
        ResultSet rs1 = null;
        Statement st1 = null;
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String timestamp = rs.getString("Fech_notificacion");
            Date fecha2 = sdf.parse(timestamp);
            fecha = sdf.parse(dateS);
            while (timestamp.equals(dateS)) {
                String query1 = "SELECT Fech_notificacion FROM producto ORDER BY Fech_notificacion DESC LIMIT 1";
                st1 = (Statement) conn.createStatement();
                rs1 = st1.executeQuery(query1);
                rs1.next();
                fecha2 = sdf.parse(rs.getString("Fech_notificacion"));
                fecha = new Date();
                dateS = sdf.format(fecha);
                //

            }
            String sql1 = "SELECT * FROM producto ORDER BY Fech_notificacion DESC LIMIT 1";
            Statement st2 = (Statement) conn.createStatement();
            ResultSet rs2;
            rs2 = st2.executeQuery(sql1);
            JSONArray lista = new JSONArray();
            while (rs2.next()) {
                Date timestamp1 = rs2.getDate("Fech_notificacion");
                //String mensaje = rs2.getString("valor");
                String id = rs2.getString("productoID");
                lista.put(timestamp1);
                //lista.put(mensaje);
                lista.put(id);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setCharacterEncoding("utf8");
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(lista);
                //
                
            }Thread.sleep(70000);
        }conn.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | InterruptedException | ParseException ex) {
            Logger.getLogger(httpush.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

}
