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

public class Ingresar_proveedorDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_proveedor(Proveedor proveedor) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO proveedor VALUES (?,?,?,?,?,?)");
            stmt.setString(1, proveedor.getNomproveedor());
            stmt.setString(2, proveedor.getDirproveedor());
            stmt.setString(3, proveedor.getTelproveedor());
            stmt.setString(4, proveedor.getMailproveedor());
            stmt.setInt(5, proveedor.getProveedorID());
            stmt.setInt(6, proveedor.getCiudadID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresar_proveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
