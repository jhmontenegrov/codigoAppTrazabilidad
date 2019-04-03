/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotrl;

import Beans.Conexion;
import Modelo.ListaProductos;
import Modelo.Producto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "RegistroVenta", urlPatterns = {"/RegistroVenta"})
public class RegistroVenta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession();
        ListaProductos datosP = null;
        List idIlista = null;
        String idProducto = request.getParameter("idProducto");
        int cant = Integer.parseInt(request.getParameter("cantidad"));
        Producto prod = new Producto();
        Connection conn = null;
        String vencid = "nVencido";
        Integer estab = (Integer) session.getAttribute("estab");
        conn = Conexion.crearConexion();
        Statement st = (Statement) conn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT * FROM producto p, inventario i, almacen a, Establecimiento es WHERE p.productoID='" + idProducto + "' AND p.productoID= i.ProductoID AND i.AlmacenID=a.AlmacenID AND a.EstablecimientoID=es.EstablecimientoID and es.EstablecimientoID='" + estab + "'";
        rs = st.executeQuery(sql);
        if (session.getAttribute("bLista") == null) {
            datosP = new ListaProductos();
        } else {
            datosP = (ListaProductos) session.getAttribute("bLista");
        }
        //PrintWriter out = response.getWriter();
        while (rs.next()) {
            String idP = rs.getString("p.productoID");
            String nomPro = rs.getString("p.Nom_producto");
            String marca = rs.getString("p.Marca");
            String lote = rs.getString("p.Lote");
            Double vlorproducto = rs.getDouble("p.Vlor_producto");
            Date fechvencimiento = rs.getDate("p.Fech_vencimiento");
            Integer idI = rs.getInt("i.inventarioID");
            Integer cantidad = rs.getInt("i.cantidad");
            int cantidadResta = cantidad - cant;
            if (cantidadResta >= 0) {
                vlorproducto = cant * vlorproducto;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateS = sdf.format(date);
                String fecha2 = sdf.format(fechvencimiento);
                Date date2 = sdf.parse(dateS);
                Date fechvencimiento2 = sdf.parse(fecha2);
                if (fechvencimiento2.after(date2)) {
                    prod.setProductoID(idP);
                    prod.setVlorproducto(vlorproducto);
                    prod.setNomproducto(nomPro);
                    prod.setLote(lote);
                    prod.setMarca(marca);
                    prod.setFechvencimiento(fechvencimiento);
                    datosP.addProducto(prod);

                } else {
                    vencid = "vencido";
                }
                if (session.getAttribute("idIlista") == null) {
                    idIlista = new ArrayList();
                } else {
                    idIlista = (List) session.getAttribute("idIlista");
                }
                idIlista.add(idI);
                idIlista.add(cant);
                idIlista.add(idP);
                session.setAttribute("idIlista", idIlista);
                session.setAttribute("ven", vencid);
                session.setAttribute("bLista", datosP);
                response.sendRedirect("inicio.jsp");

            } else if (cantidadResta < 0) {
                request.getRequestDispatcher("/errorCantidad.jsp").forward(request, response);
            }
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
        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(RegistroVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("slsls");
            Logger.getLogger(RegistroVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(RegistroVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(RegistroVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
