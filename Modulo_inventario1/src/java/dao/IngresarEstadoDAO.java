/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Estado;
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
public class IngresarEstadoDAO {

    /**
     *
     * @author jhona
     */

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_estado(Estado estado) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO estado VALUES (?,?,?)");
            stmt.setString(1, estado.getEstado());
            stmt.setString(2, estado.getDescripcion());
            stmt.setInt(3, estado.getEstadoID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IngresarEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }
}
