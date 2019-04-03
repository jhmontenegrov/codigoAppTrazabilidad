/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Mediostransporte;
import dao.EliminarMedioTransDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhona
 */
public class EliminarMedioTransCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Integer idMediot = Integer.parseInt(request.getParameter("Id"));
            Mediostransporte mediostransporte = new Mediostransporte(idMediot);
            EliminarMedioTransDAO eliminarMedioTransDAO = new EliminarMedioTransDAO();
            eliminarMedioTransDAO.eliminarMedioTrans(mediostransporte);
            request.getRequestDispatcher("/lista_medioTrans.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarMedioTransCtrl.class.getName()).log(Level.SEVERE, null, ex);
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


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
