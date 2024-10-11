/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpusapp;

import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Fathi
 */
public class Connection {
    java.sql.Connection cn;
    public static java.sql.Connection BukaKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_perpus?autoReconnect=true&useSSL=false", "root", "");
            
            return cn;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
