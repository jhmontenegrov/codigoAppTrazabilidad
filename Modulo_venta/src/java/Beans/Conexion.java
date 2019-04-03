/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhona
 */
public class Conexion {

    public static String usuario = "root";
    public static String clave = "";
    public static String servidor = "localhost";
    public static String BD = "db_proyecto_trazabilidad";

    public static Connection crearConexion() {
        Connection con = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            String URL = "jdbc:mysql://" + servidor + "/" + BD;
            con = DriverManager.getConnection(URL, usuario, clave);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("classnotfound");
        } catch (SQLException e) {
            System.out.println("error de enlace canal");
        }
        return con;
    }
}
