/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Ciudad;
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
public class Ingresa_ciudadDAO {
     Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String IngresarCiudad(Ciudad ciudad) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO ciudad VALUES (?,?,?)");
            stmt.setString(1, ciudad.getCiudadnombre());
            stmt.setString(2, ciudad.getPais());
            stmt.setInt(3, ciudad.getCiudadID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresa_ciudadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
