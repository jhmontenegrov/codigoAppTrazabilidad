/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Conexion;
import beans.Requisicion;
import dao.CrearRequisicionDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
@WebServlet(name = "CrearRequisicionCtrl", urlPatterns = {"/CrearRequisicionCtrl"})
public class CrearRequisicionCtrl extends HttpServlet {
    ServletContext sc = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //nombre, fecha
            String nombre_producto = request.getParameter("nombre_producto");
            String fecha_solicutud = request.getParameter("fecha_solicutud");
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from requisicion rq where rq.Nombre_producto='" + nombre_producto + "' AND rq.Fecha_solicitud='" + fecha_solicutud + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                try {
                    response.setContentType("text/html;charset=UTF-8");
                    int asignaID = 131;
                    int contador = 0;
                    Connection conn1;
                    PreparedStatement stmt1 = null;
                    conn1 = (Connection) Conexion.crearConexion();
                    Statement st1 = (Statement) conn1.createStatement();
                    String sql1 = "select COUNT(*) AS conta from requisicion";
                    ResultSet rs1 = st1.executeQuery(sql1);
                    while (rs1.next()) {
                        contador = rs1.getInt("conta");
                    }
                    contador = contador + 1;
                    asignaID = asignaID + contador;
                    String objservacion = request.getParameter("objservacion");
                    Integer cantidadre = Integer.parseInt(request.getParameter("cantidadre"));
                    Integer estadoID = Integer.parseInt(request.getParameter("estadoID"));
                    java.sql.Date Datesolicitud;
                    Datesolicitud = java.sql.Date.valueOf(fecha_solicutud);
                    Integer proveedorID = Integer.parseInt(request.getParameter("proveedorID"));
                    HttpSession sesion_cli = request.getSession(true);
                    int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
                    Requisicion requisicion = new Requisicion(asignaID, nombre_producto, Datesolicitud, objservacion, cantidadre, estadoID, proveedorID, idUsuario);
                    CrearRequisicionDAO crearRequisicionDAO = new CrearRequisicionDAO();
                    crearRequisicionDAO.crearPedido(requisicion);
                    sc.getRequestDispatcher("/listaPedidos.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CrearRequisicionCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrearRequisicionCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
    }

}
