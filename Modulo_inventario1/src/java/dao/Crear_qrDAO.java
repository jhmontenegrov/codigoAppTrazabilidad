/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.CodigoQr;
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
public class Crear_qrDAO {
     Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String crearQR(CodigoQr codigoQr) {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO codigo_qr VALUES (?,?,?,?)");
            stmt.setBytes(1, codigoQr.getContenido());
            stmt.setInt(2, codigoQr.getCodigoqrID());
            stmt.setString(3, codigoQr.getProductoID());
            stmt.setInt(4, codigoQr.getUsuarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crear_qrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }   
}
