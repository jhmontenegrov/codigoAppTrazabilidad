/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.AsignaInventarioProveedor;
import beans.Conexion;
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
public class Asigna_proveedorDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;
    public String asignaProveedor(AsignaInventarioProveedor asignaInventarioProveedor) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO asigna_inventario_proveedor VALUES (?,?,?,?)");
            stmt.setInt(1, asignaInventarioProveedor.getAsignainventarioproveedorID());
            stmt.setInt(2, asignaInventarioProveedor.getProveedorID());
            stmt.setInt(3, asignaInventarioProveedor.getUsuarioID());
            stmt.setInt(4, asignaInventarioProveedor.getInventarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Asigna_actividadesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }
}
