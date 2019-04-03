/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Mediostransporte;
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
public class ActualizarmediosTransporteDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarmediosTransporte(Mediostransporte mediostransporte) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
                stmt = conn.prepareStatement("UPDATE mediostransporte SET marca=? , modelo=? WHERE MediostransporteID=?");
                stmt.setString(1, mediostransporte.getMarca());
                stmt.setString(2, mediostransporte.getModelo());
                stmt.setInt(3, mediostransporte.getMediostransporteID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarmediosTransporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return regusuario;

        }
}
