/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Devolucion;
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
public class IngresarDevolucionDAO {
     Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_Devolucion(Devolucion devolucion) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO devolucion VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, devolucion.getCantdevolucion());
            stmt.setDate(2, (Date) devolucion.getFechdevolucion());
            stmt.setInt(3, devolucion.getDevolucionID());
            stmt.setInt(4, devolucion.getEstadoID());
            stmt.setInt(5, devolucion.getUsuarioID());
            stmt.setInt(6, devolucion.getInventarioID());            
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(SalidaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
