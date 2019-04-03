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
public class Ingresar_medioTransDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_medioTrans(Mediostransporte mediostransporte) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO mediostransporte VALUES (?,?,?,?)");
            stmt.setString(1, mediostransporte.getMarca());
            stmt.setString(2, mediostransporte.getModelo());
            stmt.setString(3, mediostransporte.getPlaca());
            stmt.setInt(4, mediostransporte.getMediostransporteID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresar_medioTransDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
