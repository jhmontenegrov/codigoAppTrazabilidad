/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CerrarSesionCtrl", urlPatterns = {"/CerrarSesionCtrl"})
public class CerrarSesionCtrl extends HttpServlet {
    ServletContext sc = null;
    /**
     * Initializes the servlet.
     * @param config
     * @throws javax.servlet.ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setHeader("Progma", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession ses = request.getSession();

        try {
            String vaciar="";
            HttpSession sesion_cli = request.getSession();
            sesion_cli.setAttribute("idUsuario", vaciar);
            sesion_cli.invalidate();

            sc.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            ses.setAttribute("mensaje", "Session Activa.");
            ses.setAttribute("exc", e.toString());
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
    }

}
