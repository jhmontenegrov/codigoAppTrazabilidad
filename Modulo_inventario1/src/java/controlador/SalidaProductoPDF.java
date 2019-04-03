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
import java.sql.ResultSet;
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
@WebServlet(name = "SalidaProductoPDF", urlPatterns = {"/SalidaProductoPDF"})
public class SalidaProductoPDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = Conexion.crearConexion();
                st = con.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
            Integer salProdId = Integer.parseInt(request.getParameter("Idsal"));
            try {
                String path = "C:\\ReportesTrazabildad\\SalidaProducto1.jasper";
                File reportFile = new File(path);
                Map parameters = new HashMap();
                parameters.put("salProdId", salProdId);
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, con);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                try (ServletOutputStream ouputStream = response.getOutputStream()) {
                    ouputStream.write(bytes, 0, bytes.length);
                    ouputStream.flush();
                }
            } catch (IOException | JRException e) {
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalidaProductoPDF.class.getName()).log(Level.SEVERE, null, ex);
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
