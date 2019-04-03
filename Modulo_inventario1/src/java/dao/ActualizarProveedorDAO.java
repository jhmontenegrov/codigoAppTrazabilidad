/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Proveedor;
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
public class ActualizarProveedorDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarProveedor(Proveedor proveedor) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("UPDATE proveedor SET Dir_proveedor=?, Tel_proveedor=?, Mail_proveedor=?, ciudadID=? WHERE ProveedorID=?");
            stmt.setString(1, proveedor.getDirproveedor());
            stmt.setString(2, proveedor.getTelproveedor());
            stmt.setString(3, proveedor.getMailproveedor());
            stmt.setInt(4, proveedor.getCiudadID());
            stmt.setInt(5, proveedor.getProveedorID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;

    }
}
