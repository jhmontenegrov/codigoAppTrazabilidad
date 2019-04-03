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

public class EliminarUsuarioDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;

    public String eliminarUsuasrio(Usuario usuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
                stmt = conn.prepareStatement("UPDATE usuario SET Check_bloquear_usuario=? WHERE UsuarioID=?");
                stmt.setBoolean(1, usuario.getCheckbloquearusuario());
                stmt.setInt(2, usuario.getUsuarioID());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return regusuario;

        }
}
