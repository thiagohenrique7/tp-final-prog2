/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AlunoDTO;
import DTO.UsuarioDTO;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author THIAGO
 */
public class UsuarioDAO {

    Connection conexao;

    public ResultSet authUser(UsuarioDTO objUsuarioDto) {
        conexao = new Conection().conectaBD();
        try {
            String sql = "SELECT * FROM users WHERE user = ? and password = ?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            pstm.setString(1, objUsuarioDto.getUser());
            pstm.setString(2, objUsuarioDto.getPassword());
            
            ResultSet rs = pstm.executeQuery();
            System.out.println(pstm);
            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e.getMessage());
            return null;
        }
    }
    
    
    public void cadastrarUsuario(UsuarioDTO objUsuario) {
        conexao = new Conection().conectaBD();
        try {
            System.out.println("cadastrando");
            String sql = "INSERT INTO users (user,password,type,user_id) VALUES(?,?,?,?)";
            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setString(1, objUsuario.getUser());
            pstm.setString(2, objUsuario.getPassword());
            pstm.setString(3, objUsuario.getType());
            pstm.setInt(4, objUsuario.getUserId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }
    }
    


}
