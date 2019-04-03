/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author jhona
 */
@WebServlet(name = "productoPdf", urlPatterns = {"/productoPdf"})
public class productoPdf extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con = null;
        Statement st = null;

        try {
            con = Conexion.crearConexion();
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("classnotfound");
        }

        try {
            String path = "C:\\ReportesTrazabildad\\ListaProductos.jasper";
            File reportFile = new File(path);
            Map parameters = new HashMap();
            parameters.put("", "");
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), null, con);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            try (ServletOutputStream ouputStream = response.getOutputStream()) {
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
            }

        } catch (IOException | JRException e) {
            System.out.println(e);
        }
        con.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(productoPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(productoPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
