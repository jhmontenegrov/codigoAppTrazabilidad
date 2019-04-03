/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.*;
import beans.Conexion;
import beans.AsignadaPerfilUsuario;
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
public class Asigna_actividadesDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs;
    public String AsignarActividades(AsignadaPerfilUsuario asignadaPerfilUsuario) throws SQLException {
        conn = Conexion.crearConexion();
        String regusuario = "";
        try {
            stmt = conn.prepareStatement("INSERT INTO asignada_perfil_usuario VALUES (?,?,?,?,?)");
            stmt.setInt(1, asignadaPerfilUsuario.getIdusuarioasignado());
            stmt.setInt(2, asignadaPerfilUsuario.getAsignadaperfilusuarioID());
            stmt.setInt(3, asignadaPerfilUsuario.getUsuarioID());
            stmt.setInt(4, asignadaPerfilUsuario.getActivacionID());
            stmt.setInt(5, asignadaPerfilUsuario.getActividadesperfilusuarioID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Asigna_actividadesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regusuario;
    }
}
