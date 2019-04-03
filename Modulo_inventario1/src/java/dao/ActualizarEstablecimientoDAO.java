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
public class ActualizarEstablecimientoDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarEstablecimiento(Establecimiento establecimiento) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
                stmt = conn.prepareStatement("UPDATE establecimiento SET Ubicaciontienda=? WHERE EstablecimientoID=?");
                stmt.setString(1, establecimiento.getUbicaciontienda());
                stmt.setInt(2, establecimiento.getEstablecimientoID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarEstablecimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return regusuario;

        }
}
