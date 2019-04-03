
package controlador;

import beans.Conexion;
import beans.Inventario;
import beans.SalidaProducto;
import dao.ActualizarInventarioDAO;
import dao.SalidaProductoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SalidaProductoCtrl", urlPatterns = {"/SalidaProductoCtrl"})
public class SalidaProductoCtrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        int asignaID = 111;
        int contador = 0, cantidad = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = Conexion.crearConexion();
        //Contador
        Statement st = (Statement) conn.createStatement();
        String sql = "select COUNT(*) AS conta from salida_producto";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            contador = rs.getInt("conta");
        }
        contador = contador + 1;
        asignaID = asignaID + contador;

        HttpSession sesion_cli = request.getSession(true);
        int idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
        Integer select_inventario = new Integer(request.getParameter("select_inventario"));
        String sql1 = "select cantidad AS cant FROM inventario WHERE inventarioID='" + select_inventario + "'";
        ResultSet rs1 = st.executeQuery(sql1);
        while (rs1.next()) {
            cantidad = rs1.getInt("cant");
        }
        String trasladoP = request.getParameter("trasladoP");
        java.sql.Date startDate =  java.sql.Date.valueOf(trasladoP);
        Integer cantidadPtras = new Integer(request.getParameter("cantidadPtras"));
        Integer select_estado = new Integer(request.getParameter("select_estado"));
        Integer select_medioTransporte = new Integer(request.getParameter("select_medioTransporte"));
        if (cantidadPtras > cantidad) {
            request.getRequestDispatcher("/errorCantidad.jsp").forward(request, response);
        } else if(cantidadPtras <= cantidad){
            cantidad=cantidad-cantidadPtras;
            ActualizarInventarioDAO actualizarInventarioDAO= new ActualizarInventarioDAO();
            Inventario inv=new Inventario (select_inventario);
            inv.setCantidad(cantidad);
            actualizarInventarioDAO.actualizarCantidadInv(inv);
            SalidaProducto salidaProducto = new SalidaProducto(asignaID, cantidadPtras, startDate, select_estado, select_medioTransporte, select_inventario, idUsuario);
            SalidaProductoDAO salidaProductoDAO = new SalidaProductoDAO();
            salidaProductoDAO.ingresar_salidaProducto(salidaProducto);
            request.getRequestDispatcher("/listaSalidaProd.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Ingresa_ciudadCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SalidaProductoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}