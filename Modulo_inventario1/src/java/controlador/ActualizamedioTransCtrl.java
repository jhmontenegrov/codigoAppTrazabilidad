/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Mediostransporte;
import dao.ActualizarmediosTransporteDAO;
import java.io.IOException;
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
@WebServlet(name = "ActualizamedioTransCtrl", urlPatterns = {"/ActualizamedioTransCtrl"})
public class ActualizamedioTransCtrl extends HttpServlet {
    ServletContext sc = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        int idMediot = new Integer(request.getParameter("idt"));
        String marcar = new String(request.getParameter("marcar").getBytes("ISO-8859-1"), "UTF-8");
        String modelCar = new String(request.getParameter("modelCar").getBytes("ISO-8859-1"), "UTF-8");
        Mediostransporte mediosTransporte = new Mediostransporte(idMediot);
        mediosTransporte.setMarca(marcar);
        mediosTransporte.setModelo(modelCar);
        ActualizarmediosTransporteDAO actualizarmediosTransporteDAO = new ActualizarmediosTransporteDAO();
        actualizarmediosTransporteDAO.actualizarmediosTransporte(mediosTransporte);
        sc.getRequestDispatcher("/lista_medioTrans.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ActualizamedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            try {
                processRequest(request, response);
            } catch (ServletException | IOException ex) {
                try {
                    Logger.getLogger(ActualizamedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                } catch (ServletException | IOException ex1) {
                    Logger.getLogger(ActualizamedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex1);
                    request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            try {
                Logger.getLogger(ActualizamedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            } catch (ServletException | IOException ex1) {
                Logger.getLogger(ActualizamedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
