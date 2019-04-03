/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Establecimiento;
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
public class Ingresar_establecimientoDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_establecimientoD(Establecimiento establecimiento) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO establecimiento VALUES (?,?,?,?)");
            stmt.setString(1, establecimiento.getUbicaciontienda());
            stmt.setInt(2, establecimiento.getEstablecimientoID());
            stmt.setInt(3, establecimiento.getUsuarioID());
            stmt.setInt(4, establecimiento.getCiudadID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresar_establecimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
