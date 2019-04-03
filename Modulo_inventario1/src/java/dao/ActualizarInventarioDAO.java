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
public class ActualizarInventarioDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarCantidadInv(Inventario inventario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("UPDATE inventario SET Cantidad=? WHERE InventarioID=?");
            stmt.setInt(1, inventario.getCantidad());
            stmt.setInt(2, inventario.getInventarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarInventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;

    }
}
