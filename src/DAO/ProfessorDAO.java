/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AlunoDTO;
import DTO.ProfessorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mac
 */
public class ProfessorDAO {

    Connection conexao;
    
    

    public ProfessorDTO getProfessor(int id) {
        conexao = new Conection().conectaBD();
        ProfessorDTO professor = new ProfessorDTO();
        try {
            String sql = "SELECT * FROM professores WHERE id=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //Display values
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setData_nascimento(rs.getString("data_nascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setMateria(rs.getString("materia"));
                professor.setTelefone(rs.getString("telefone"));

            }

            return professor;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }
        return professor;
    }

}
