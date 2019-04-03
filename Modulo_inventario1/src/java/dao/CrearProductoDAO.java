/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Producto;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhona
 */
public class CrearProductoDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String crearProducto(Producto producto) {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO producto VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, producto.getNomproducto());
            stmt.setString(2, producto.getDescproducto());
            stmt.setDate(3, (Date) producto.getFechlaboracion());
            stmt.setDate(4, (Date) producto.getFechvencimiento());
            stmt.setDate(5, (Date) producto.getFechnotificacion());
            stmt.setString(6, producto.getMarca());
            stmt.setString(7, producto.getLote());
            stmt.setDouble(8, producto.getVlorproducto());
            stmt.setDouble(9, producto.getProducNo());
            stmt.setString(10, producto.getProductoID());
            stmt.setInt(11, producto.getTipoproductoID());
            stmt.setInt(12, producto.getUsuarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrearProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }

}
