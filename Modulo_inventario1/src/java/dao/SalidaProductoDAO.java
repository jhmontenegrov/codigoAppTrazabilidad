/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.SalidaProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhona
 */
public class SalidaProductoDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_salidaProducto(SalidaProducto salidaProducto) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO salida_producto VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, salidaProducto.getCanttraslado());
            stmt.setDate(2, (Date) salidaProducto.getFechsalidap());
            stmt.setInt(3, salidaProducto.getSalidaproductoID());
            stmt.setInt(4, salidaProducto.getEstadoID());
            stmt.setInt(5, salidaProducto.getMediostransporteID());
            stmt.setInt(6, salidaProducto.getInventarioID());
            stmt.setInt(7, salidaProducto.getUsuarioID());
            
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(SalidaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }

    private void setDate(int i, java.util.Date fechsalidap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
