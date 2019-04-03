/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.PerfilUsuario;
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
public class Ingresa_tipoPerfilDAO {
        Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String ingresar_tipoUsuario(PerfilUsuario perfilUsuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {// Validar orden
            stmt = conn.prepareStatement("INSERT INTO perfil_usuario VALUES (?,?)");
            stmt.setString(1, perfilUsuario.getNomperfilusuario());
            stmt.setInt(2, perfilUsuario.getPerfilusuarioID());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Ingresa_tipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return regusuario;
    }
}
