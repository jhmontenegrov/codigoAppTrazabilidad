/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Conexion;
import beans.Requisicion;
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
public class CrearRequisicionDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String crearPedido(Requisicion requisicion) {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO requisicion VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, requisicion.getNombreproducto());
            stmt.setDate(2, (Date) requisicion.getFechasolicitud());
            stmt.setString(3, requisicion.getObservacion());
            stmt.setInt(4, requisicion.getCantidad());
            stmt.setInt(5, requisicion.getRequisicionID());
            stmt.setInt(6, requisicion.getEstadoID());
            stmt.setInt(7, requisicion.getProveedorID());
            stmt.setInt(8, requisicion.getUsuarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrearRequisicionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }
}
