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
public class ActualizarInfoPerfilDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String actualizarUsuario(Usuario usuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("UPDATE usuario SET tel_usuario=? , direccion=? , email=? , ciudadID=? WHERE usuarioID=?");
            stmt.setString(1, usuario.getTelusuario());
            stmt.setString(2, usuario.getDireccion());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getCiudadID());
            stmt.setInt(5, usuario.getUsuarioID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarInfoPerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;

    }

    public String actualizarPass(Usuario usuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("UPDATE usuario SET contrasena=? WHERE usuarioID=?");
            stmt.setString(1, usuario.getContrasena());
            stmt.setInt(2, usuario.getUsuarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarInfoPerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;

    }
}
