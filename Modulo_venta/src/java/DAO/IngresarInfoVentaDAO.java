/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Conexion;
import Modelo.InformacionVenta;
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
public class IngresarInfoVentaDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String Crearinformacion_venta(InformacionVenta infoVenta) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO informacion_venta VALUES (?,?,?,?,?)");
            stmt.setInt(1, infoVenta.getCantidad());
            stmt.setInt(2, infoVenta.getInformacionventaID());
            stmt.setInt(3, infoVenta.getVentaID());            
            stmt.setString(4, infoVenta.getProductoID());
            stmt.setInt(5, infoVenta.getInventarioID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(IngresarInfoVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}

