/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.TipoProducto;
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
public class Ingresa_tipoProductoDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_tipoProducto(TipoProducto tipoProducto) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO tipo_Producto VALUES (?,?,?)");
            stmt.setString(1, tipoProducto.getNomtipoproducto());
            stmt.setString(2, tipoProducto.getDescripcion());
            stmt.setInt(3, tipoProducto.getTipoproductoID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresa_tipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
