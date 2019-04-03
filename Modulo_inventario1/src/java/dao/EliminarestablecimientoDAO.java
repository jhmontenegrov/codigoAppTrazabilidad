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
public class EliminarestablecimientoDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String eliminarEstablecimiento(Establecimiento establecimiento) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
                stmt = conn.prepareStatement("DELETE FROM establecimiento WHERE EstablecimientoID=?");
                stmt.setInt(1, establecimiento.getEstablecimientoID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarestablecimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return regusuario;

        }
}
