/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Asigna_proveedorDAO;
import beans.CodigoQr;
import beans.Conexion;
import beans.Producto;
import dao.CrearProductoDAO;
import dao.Crear_qrDAO;
import dao.Ingresar_inventarioDAO;
import beans.Almacen;
import beans.AsignaInventarioProveedor;
import beans.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import qr.QRGenerador;

/**
 *
 * @author jhona
 */
@WebServlet(name = "CrearProductoCtrol", urlPatterns = {"/CrearProductoCtrol"})
public class CrearProductoCtrol extends HttpServlet {

    ServletContext sc = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//Extraigo el contexto del servlet
        sc = this.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String codigoProd = request.getParameter("codigoPro");
        String nproducto = request.getParameter("nproducto");
        Connection conn;
        PreparedStatement stmt = null;
        conn = (Connection) Conexion.crearConexion();
        Statement st = (Statement) conn.createStatement();
        String sql = "select *  from producto p where p.ProductoID='" + codigoProd + "' or p.Nom_producto='"+nproducto+"'";
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            sc.getRequestDispatcher("/errorYaexiste.jsp").forward(request, response);
        } else {
            Connection conn8;
            PreparedStatement stmt8 = null;
            conn8 = (Connection) Conexion.crearConexion();
            Statement st8 = (Statement) conn8.createStatement();
            String sql8 = "select producNo as contador from producto order by producNo Desc Limit 1";
            ResultSet rs8 = st8.executeQuery(sql8);
            Integer producNo=0;
            while (rs8.next()){
                 producNo= rs8.getInt("contador");
                }
                int producN = producNo + 1;
            String dproducto = request.getParameter("dproducto");
            String fechaEla = request.getParameter("fechaEla");
            String fechaVcto = request.getParameter("fechaVcto");
            String fechaNtf = request.getParameter("fechaNtf");
            java.sql.Date DateEla, DateVto, DateNtf;
            DateVto = java.sql.Date.valueOf(fechaVcto);
            DateEla = java.sql.Date.valueOf(fechaEla);
            DateNtf = java.sql.Date.valueOf(fechaNtf);
            String marcaP = request.getParameter("marcaP");
            String nlote = request.getParameter("nlote");
            double vProducto = new Double(request.getParameter("vProducto"));
            int tip_producto = Integer.parseInt(request.getParameter("tip_producto"));
            HttpSession sesion_cli = request.getSession(true);
            int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
            Producto producto = new Producto(nproducto, dproducto, DateEla, DateVto, DateNtf, marcaP, nlote, vProducto, producN,codigoProd, tip_producto, idUsuario);
            CrearProductoDAO crearProductoDAO = new CrearProductoDAO();
            crearProductoDAO.crearProducto(producto);
            conn.close();
            Integer cantidadP = new Integer(request.getParameter("cantidadP"));
            Integer select_almacen = new Integer(request.getParameter("select_almacen"));
            //producto, cantidad y almacen -- campos nuevos para validar existencia
            String obserP = request.getParameter("obserP");
            String est = request.getParameter("est");
            String zonaIn = request.getParameter("zonaIn");

            Connection conn4;
            PreparedStatement stmt4 = null;
            conn4 = (Connection) Conexion.crearConexion();
            Statement st4 = (Statement) conn4.createStatement();
            String sql4 = "select *  from inventario iv where iv.ProductoID='" + codigoProd + "' AND iv.Cantidad='" + cantidadP + "' AND iv.AlmacenID='" + select_almacen + "'";
            ResultSet rs4 = st4.executeQuery(sql4);
            if (rs4.next()) {
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
                Inventario inventario = new Inventario(asignaID, obserP, cantidadP, zonaIn, est, select_almacen, codigoProd, idUsuario);
                Ingresar_inventarioDAO ingresar_inventarioDAO = new Ingresar_inventarioDAO();
                ingresar_inventarioDAO.ingresar_salidaProducto(inventario);
                conn4.close();
                response.setContentType("text/html;charset=UTF-8");
                int AsignaID2 = 111;
                int contador2 = 0;
                Connection conn2 = null;
                PreparedStatement stmt2 = null;
                conn2 = (Connection) Conexion.crearConexion();
                Statement st2 = (Statement) conn2.createStatement();
                String sql2 = "select COUNT(*) AS conta from asigna_inventario_proveedor";
                ResultSet rs2 = st2.executeQuery(sql2);
                while (rs2.next()) {
                    contador2 = rs2.getInt("conta");
                }
                contador2 = contador2 + 1;
                AsignaID2 = AsignaID2 + contador2;
                int select_prov = Integer.parseInt(request.getParameter("select_proveedor"));
                AsignaInventarioProveedor asignaInventarioProveedor = new AsignaInventarioProveedor(AsignaID2, asignaID, select_prov, idUsuario);
                Asigna_proveedorDAO asigna_proveedorDAO = new Asigna_proveedorDAO();
                asigna_proveedorDAO.asignaProveedor(asignaInventarioProveedor);
                conn2.close();
                Connection conn5 = null;
                PreparedStatement stmt5 = null;
                conn5 = (Connection) Conexion.crearConexion();
                Statement st5 = (Statement) conn5.createStatement();
                String sql5 = "select * from almacen WHERE AlmacenID='" + select_almacen + "'";
                ResultSet rs5 = st5.executeQuery(sql5);
                String ubic = null;
                int almacenID = 0, ciuID = 0, estaID = 0, idUs = 0;
                while (rs5.next()) {
                    ubic = rs5.getString("Ubicacion");
                    almacenID = rs5.getInt("AlmacenID");
                    ciuID = rs5.getInt("CiudadID");
                    estaID = rs5.getInt("EstablecimientoID");
                    idUs = rs5.getInt("UsuarioID");
                }
                Almacen almacen = new Almacen(ubic, almacenID, ciuID, estaID, idUs);
                QRGenerador qrgen = new QRGenerador();
                qrgen.qrGenera(producto, inventario, almacen);
                File archivoIma = new File("C:\\CodigoQR/" + codigoProd + "." + "png");
                byte[] fileContent = Files.readAllBytes(archivoIma.toPath());
                int contadorCqr = 200, asignaqr = 0;
                Statement st6 = (Statement) conn5.createStatement();
                String sql6 = "select COUNT(*) as conta FROM producto";
                ResultSet rs6 = st6.executeQuery(sql6);
                while (rs6.next()) {
                    contadorCqr = rs6.getInt("conta");
                }
                contadorCqr = contadorCqr + 1;
                asignaqr = asignaqr + contadorCqr;
                CodigoQr cqr = new CodigoQr(asignaqr, fileContent, codigoProd, idUsuario);
                Crear_qrDAO crear_qrDAO = new Crear_qrDAO();
                crear_qrDAO.crearQR(cqr);
                sc.getRequestDispatcher("/listaProductos.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(CrearProductoCtrol.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrearProductoCtrol.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
