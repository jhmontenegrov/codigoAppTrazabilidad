/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.Devolucion;
import beans.Inventario;
import dao.ActualizarInventarioDAO;
import dao.IngresarDevolucionDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "IngresarDevolucionCtrl", urlPatterns = {"/IngresarDevolucionCtrl"})
public class IngresarDevolucionCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //fecha, cantidad e inventario
        try {
            response.setContentType("text/html;charset=UTF-8");
            int asignaID = 111;
            int contador = 0, cantidad = 0;
            Connection conn = null;
            PreparedStatement stmt = null;
            conn = Conexion.crearConexion();
            //Contador
            Statement st = (Statement) conn.createStatement();
            String sql = "select COUNT(*) AS conta from devolucion";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                contador = rs.getInt("conta");
            }
            contador = contador + 1;
            asignaID = asignaID + contador;
            HttpSession sesion_cli = request.getSession(true);
            int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
            Integer inventarioID = new Integer(request.getParameter("select_inventario"));
            String sql1 = "select cantidad AS cant FROM inventario WHERE inventarioID='" + inventarioID + "'";
            ResultSet rs1 = st.executeQuery(sql1);
            while (rs1.next()) {
                cantidad = rs1.getInt("cant");
            }
            String trasladoP = request.getParameter("fech");
            java.sql.Date entradaD;
            entradaD = java.sql.Date.valueOf(trasladoP);
            Integer cantdevolucion = new Integer(request.getParameter("cant"));
            Integer select_estado = new Integer(request.getParameter("estID"));
            cantidad = cantidad + cantdevolucion;
            Devolucion devolucion = new Devolucion(asignaID, cantdevolucion, entradaD, idUsuario, inventarioID, select_estado);
            IngresarDevolucionDAO ingresarDevolucionDAO = new IngresarDevolucionDAO();
            ingresarDevolucionDAO.ingresar_Devolucion(devolucion);
            ActualizarInventarioDAO actualizarInventarioDAO = new ActualizarInventarioDAO();
            Inventario inv = new Inventario(inventarioID);
            inv.setCantidad(cantidad);
            actualizarInventarioDAO.actualizarCantidadInv(inv);
            request.getRequestDispatcher("/listaDevoluciones.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IngresarDevolucionCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
