/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conection {
    
    public Connection conectaBD(){
        Connection conexao = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/tp_prog2?user=root&password=santoss710";
            conexao = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conexao;
    }
    
}
