/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Almacen;
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
public class Ingresar_almacenDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String CrearAlmacen(Almacen almacen) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO almacen VALUES (?,?,?,?,?)");
            stmt.setString(1, almacen.getUbicacion());
            stmt.setInt(2, almacen.getAlmacenID());
            stmt.setInt(3, almacen.getEstablecimientoID());
            stmt.setInt(4, almacen.getUsuarioID());
            stmt.setInt(5, almacen.getCiudadID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresar_almacenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
