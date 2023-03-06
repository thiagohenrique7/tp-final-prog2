/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mac
 */
public class ProfessorDTO {
    private static ProfessorDTO instancia = new ProfessorDTO();

    private int id;
    private String nome, data_nascimento, email, telefone, materia;

    public int getId() {
        return id;
    }
    public static ProfessorDTO getInstancia(){
        return instancia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AlunoDTO{" + "id=" + id + ", nome=" + nome + ", data_nascimento=" + data_nascimento + ", email=" + email + ", telefone=" + telefone + '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
