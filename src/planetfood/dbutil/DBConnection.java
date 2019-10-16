/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Win7
 */
public class DBConnection {
    private static Connection conn;
    static {
        try {
             Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Win7-PC:1521/XE","planetfood","student");
            System.out.println("Connection opened!"); 
        }
        catch(Exception ex) {
           JOptionPane.showMessageDialog(null,"DB Error in opening connection in DBConnection","Error!",JOptionPane.ERROR_MESSAGE);
           ex.printStackTrace();
        }
    } 
    public static Connection getConnection() {
        return conn;
    } 
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null,"DB Error in closing connection in DBConnection","Error!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            
        }
    }
    
}
