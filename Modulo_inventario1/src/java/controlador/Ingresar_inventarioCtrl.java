/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Asigna_proveedorDAO;
import beans.Conexion;
import beans.Inventario;
import dao.Ingresar_inventarioDAO;
import beans.AsignaInventarioProveedor;
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
@WebServlet(name = "Ingresar_inventarioCtrl", urlPatterns = {"/Ingresar_inventarioCtrl"})
public class Ingresar_inventarioCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String select_producto = request.getParameter("select_producto");
        Integer cantidadP = new Integer(request.getParameter("cantidadP"));
        Integer select_almacen = new Integer(request.getParameter("select_almacen"));
        //producto, cantidad y almacen -- campos nuevos para validar existencia
        String obserP = request.getParameter("obserP");
        String est = request.getParameter("est");
        String zonaIn = request.getParameter("zonaIn");
        try {
            Connection conn;
            PreparedStatement stmt = null;
            conn = (Connection) Conexion.crearConexion();
            Statement st = (Statement) conn.createStatement();
            String sql = "select *  from inventario iv where iv.ProductoID='" + select_producto + "' AND iv.Cantidad='" + cantidadP + "' AND iv.AlmacenID='" + select_almacen + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                request.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
            } else {
                int asignaID = 111;
                int contador = 0;
                Connection conn1 = null;
                PreparedStatement stmt1 = null;
                conn1 = Conexion.crearConexion();
                //Contador
                Statement st1 = (Statement) conn1.createStatement();
                String sql1 = "select COUNT(*) AS conta from inventario";
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    contador = rs1.getInt("conta");
                }
                contador = contador + 1;
                asignaID = asignaID + contador;
                HttpSession sesion_cli = request.getSession(true);
                int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
                Inventario inventario = new Inventario(asignaID, obserP, cantidadP, zonaIn, est, select_almacen, select_producto, idUsuario);
                Ingresar_inventarioDAO ingresar_inventarioDAO = new Ingresar_inventarioDAO();
                ingresar_inventarioDAO.ingresar_salidaProducto(inventario);
                try {
                    response.setContentType("text/html;charset=UTF-8");
                    int AsignaID2 = 111;
                    int contador2 = 0;
                    Connection conn2 = null;
                    PreparedStatement stmt2 = null;
                    conn2 = (Connection) Conexion.crearConexion();
                    //Contador
                    Statement st2 = (Statement) conn2.createStatement();
                    String sql2 = "select COUNT(*) AS conta from asigna_inventario_proveedor";
                    ResultSet rs2 = st2.executeQuery(sql2);
                    while (rs2.next()) {
                        contador2 = rs2.getInt("conta");
                    }
                    contador2 = contador2 + 1;
                    AsignaID2 = AsignaID2 + contador2;
                    int select_prov = Integer.parseInt(request.getParameter("select_prov"));
                    int select_inventario = new Integer(request.getParameter("select_inventario"));
                    AsignaInventarioProveedor asignaInventarioProveedor = new AsignaInventarioProveedor(AsignaID2, select_inventario, select_prov, idUsuario);
                    Asigna_proveedorDAO asigna_proveedorDAO = new Asigna_proveedorDAO();
                    asigna_proveedorDAO.asignaProveedor(asignaInventarioProveedor);
                    request.getRequestDispatcher("/lista_inventario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Asigna_proveedorCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_inventarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
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
