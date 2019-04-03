/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhona
 */
public class Ingresar_inventarioDAO {
     Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_salidaProducto(Inventario inventario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO inventario VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, inventario.getObservacion());
            stmt.setString(2, inventario.getZona());
            stmt.setString(3, inventario.getEstante());
            stmt.setInt(4, inventario.getCantidad());
            stmt.setInt(5, inventario.getInventarioID());
            stmt.setInt(6, inventario.getUsuarioID());
            stmt.setInt(7, inventario.getAlmacenID());
            stmt.setString(8, inventario.getProductoID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(SalidaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
