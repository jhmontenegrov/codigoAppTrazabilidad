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
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author jhona
 */
@WebServlet(name = "DevolucionPDF", urlPatterns = {"/DevolucionPDF"})
public class DevolucionPDF extends HttpServlet {

    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                System.out.println("classnotfound");
            }
            Integer devProdId = Integer.parseInt(request.getParameter("Id"));
            Map parametros = new HashMap();
            parametros.put("devProdId", devProdId);
            sc = this.getServletContext();
            try {
                File reportFile = new File("C:\\ReportesTrazabildad\\DevolucionProd.jasper");
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile); //se ejecuta el reporte
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, con);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                try (ServletOutputStream ouputStream = response.getOutputStream()) {
                    ouputStream.write(bytes, 0, bytes.length);
                    ouputStream.flush();
                }
            } catch (JRException | IOException e) {
                throw new ServletException(e);
            } finally {
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalidaProductoPDF.class.getName()).log(Level.SEVERE, null, ex);
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
