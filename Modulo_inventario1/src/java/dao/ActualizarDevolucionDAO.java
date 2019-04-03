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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhona
 */
public class ActualizarDevolucionDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarDevolucion(Devolucion devolucion) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
                stmt = conn.prepareStatement("UPDATE devolucion SET EstadoID=? WHERE DevolucionID=?");
                stmt.setInt(1, devolucion.getEstadoID());
                stmt.setInt(2, devolucion.getDevolucionID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarEstablecimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return regusuario;

        }
}
