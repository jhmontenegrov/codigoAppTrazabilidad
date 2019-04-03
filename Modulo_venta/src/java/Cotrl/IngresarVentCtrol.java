/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotrl;

import Beans.Conexion;
import DAO.ActualizarInventarioDAO;
import DAO.IngresarInfoVentaDAO;
import DAO.IngresarVentaDAO;
import Modelo.Venta;
import Modelo.InformacionVenta;
import Modelo.Inventario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "IngresarVentCtrol", urlPatterns = {"/IngresarVentCtrol"})
public class IngresarVentCtrol extends HttpServlet {

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
            HttpSession session = request.getSession();
            List idIlista = new ArrayList();
            idIlista = (List) session.getAttribute("idIlista");
            //System.out.println(idIlista);
            session.setAttribute("idIlista", null);
            session.setAttribute("bLista", null);
            int asignaID = 131;
            int contador = 0;
            Connection conn;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select COUNT(*) AS conta from venta";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                contador = rs.getInt("conta");
            }
            contador = contador + 1;
            asignaID = asignaID + contador;
            Date date = new Date();
            java.sql.Date date2 = new java.sql.Date(date.getTime());
            int idUsuario = (int) session.getAttribute("idUsuario");
            int estab = (int) session.getAttribute("estab");
            Venta venta = new Venta(date2, asignaID, estab, idUsuario);
            IngresarVentaDAO ingVenta = new IngresarVentaDAO();
            ingVenta.CrearVenta(venta);

            int asignID = 138;
            int contado = 0;
            int i = 0, j = 1, k = 2;
            conn = (Connection) Conexion.crearConexion();
            Statement st1 = (Statement) conn.createStatement();
            String sql1 = "select COUNT(*) AS conta from informacion_venta";
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {
                contado = rs1.getInt("conta");
            }
            contado = contado + 1;
            if (idIlista == null) {
                request.getRequestDispatcher("/listaCompra.jsp").forward(request, response);
            } else {

                while (idIlista.size() > i && idIlista.size() > j && idIlista.size() > k) {
                    asignID = asignID + contado;
                    Connection conn1;
                    conn1 = (Connection) Conexion.crearConexion();
                    Statement st3 = (Statement) conn1.createStatement();
                    String sql3 = "SELECT cantidad FROM producto p, inventario i WHERE p.ProductoID=i.ProductoID AND p.ProductoID='" + (String) idIlista.get(k) + "' AND i.InventarioID='" + (int) idIlista.get(i) + "'";
                    ResultSet rs3 = st3.executeQuery(sql3);
                    int cantidad = 0;
                    while (rs3.next()) {
                        cantidad = rs3.getInt("cantidad");
                    }
                    int resta= (int) idIlista.get(j);
                    int cantida = cantidad - resta;
                    ActualizarInventarioDAO actualizarInventarioDAO = new ActualizarInventarioDAO();
                    Inventario inv = new Inventario((int) idIlista.get(i));
                    inv.setCantidad(cantida);
                    actualizarInventarioDAO.actualizarCantidadInv(inv);
                    InformacionVenta infoVenta = new InformacionVenta((int) idIlista.get(j), asignID, (int) idIlista.get(i), (String) idIlista.get(k), asignaID);
                    IngresarInfoVentaDAO infoVentaDAO = new IngresarInfoVentaDAO();
                    infoVentaDAO.Crearinformacion_venta(infoVenta);
                    i = i + 3;
                    j = j + 3;
                    k = k + 3;
                    conn.close();
                }//pdf
                request.setAttribute("asignaID", asignaID);

                request.getRequestDispatcher("/Recibo").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresarVentCtrol.class.getName()).log(Level.SEVERE, null, ex);
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
