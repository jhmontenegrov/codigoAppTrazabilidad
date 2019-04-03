/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Conexion;
import Modelo.Venta;
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
public class IngresarVentaDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String CrearVenta(Venta venta) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO venta VALUES (?,?,?,?)");
            stmt.setDate(1, (Date) venta.getFechventa());
            stmt.setInt(2, venta.getVentaID());
            stmt.setInt(3, venta.getEstablecimientoID());
            stmt.setInt(4, venta.getUsuarioID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(IngresarVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
