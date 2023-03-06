/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AlunoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author THIAGO
 */
public class AlunoDAO {

    Connection conexao;

    public int cadastrarAluno(AlunoDTO objAlunoDto) {
        conexao = new Conection().conectaBD();
        try {
            System.out.println("cadastrando");
            String sql = "INSERT INTO alunos (nome,data_nascimento,email,telefone) VALUES(?,?,?,?)";
            PreparedStatement pstm = conexao.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, objAlunoDto.getNome());
            pstm.setString(2, objAlunoDto.getData_nascimento());
            pstm.setString(3, objAlunoDto.getEmail());
            pstm.setString(4, objAlunoDto.getTelefone());

            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }

        return 0;
    }

    public void updateAluno(AlunoDTO obj) {
        conexao = new Conection().conectaBD();
        try {
            String sql = "UPDATE alunos SET nome=?, data_nascimento=?, email=?, telefone=? WHERE id=?";

            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getData_nascimento());
            pstm.setString(3, obj.getEmail());
            pstm.setString(4, obj.getTelefone());
            pstm.setInt(5, obj.getId());

            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }

    }

    public void deleteAluno(AlunoDTO obj) {
        conexao = new Conection().conectaBD();
        try {
            String sql = "DELETE FROM alunos WHERE id=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, obj.getId());

            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }

    }

    public AlunoDTO getAluno(int id) {
        conexao = new Conection().conectaBD();
        AlunoDTO aluno = new AlunoDTO();
        try {
            System.out.println("cadastrando");
            String sql = "SELECT * FROM alunos WHERE id=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //Display values
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setData_nascimento(rs.getString("data_nascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));

            }

            return aluno;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AlunoDAO: " + e.getMessage());
        }
        return aluno;
    }

    public ArrayList<AlunoDTO> getAlunos() {
        conexao = new Conection().conectaBD();
        try {
            String sql = "SELECT * FROM alunos";
            PreparedStatement pstm = conexao.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            ArrayList<AlunoDTO> alunos = new ArrayList<>();

            while (rs.next()) {
                //Display values
                AlunoDTO aluno = new AlunoDTO();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setData_nascimento(rs.getString("data_nascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));

                alunos.add(aluno);

            }
            return alunos;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e.getMessage());
            return null;
        }
    }

}
