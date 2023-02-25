/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mac
 */


import java.io.Serializable;

import java.io.Serializable;

public class Aluno implements Comparable<Aluno>, Serializable{
    private String nome;
    private String dataNascimento;
    private String email;
    private String telefone;

    public Aluno(String nome, String dataNascimento, String email, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome=" + nome + ", dataNascimento=" + dataNascimento + ", email=" + email + ", telefone=" + telefone + '}';
    }


    public int compareTo(Aluno o) {
        return nome.compareTo(o.nome);
    }
}  
