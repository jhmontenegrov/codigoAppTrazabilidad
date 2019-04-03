/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.Usuario;
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
public class CrearUsuarioDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String crearUsuario(Usuario usuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getTelusuario());
            stmt.setString(4, usuario.getDireccion());
            stmt.setString(5, usuario.getUser());
            stmt.setString(6, usuario.getContrasena());
            stmt.setString(7, usuario.getEmail());
            stmt.setBoolean(8, usuario.getCheckbloquearusuario());
            stmt.setInt(9, usuario.getUsuarioID());
            stmt.setInt(10, usuario.getPerfilusuarioID());
            stmt.setInt(11, usuario.getCiudadID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrearUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }
}
