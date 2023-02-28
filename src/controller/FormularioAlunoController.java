/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.Aluno;
import repository.Alunos;
import view.FormularioAlunoView;
import view.PainelPrincipalView;

/**
 *
 * @author mac
 */
public class FormularioAlunoController {

    private FormularioAlunoView tela;
    private boolean isEdit = false;

    //private Alunos alunos;
    public FormularioAlunoController() {
        tela = new FormularioAlunoView();
        addBtnCallbacks();
        tela.setVisible(true);
    }

    // preenche formulario com os dados
    public FormularioAlunoController(String nome, String dataNascimento, String email, String telefone) {
        tela = new FormularioAlunoView();
        // adiciona os valores iniciais
        setDefaultComponentsValues(nome, dataNascimento, email, telefone);
        addBtnCallbacks();

        tela.setVisible(true);
    }

    // adiciona os eventos de callbacks dos btn do formulario
    private void addBtnCallbacks() {

        tela.getBtnSalvar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("salvar");
                String nome = tela.getTxtFieldNome().getText();
                String dataNasc = tela.getTxtFieldDataNasc().getText();
                String email = tela.getTxtFieldEmail().getText();
                String telefone = tela.getTxtFieldTelefone().getText();

                if (!isValidFields()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // se for edicao de aluno faz update se nao cria um novo aluno
                Alunos alunos = new Alunos().getInstance();
                alunos.add(new Aluno(nome, dataNasc, email, telefone));

                tela.dispose();
                PainelPrincipalController painel = new PainelPrincipalController();
            }
        });

        tela.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                tela.dispose();
                PainelPrincipalController painel = new PainelPrincipalController();

            }
        });

    }

    private void setDefaultComponentsValues(String nome, String dataNascimento, String email, String telefone) {
        tela.getTxtFieldNome().setText(nome);
        tela.getTxtFieldDataNasc().setText(dataNascimento);
        tela.getTxtFieldEmail().setText(email);
        tela.getTxtFieldTelefone().setText(telefone);
        // seta como edicao de dados
        isEdit = true;
    }

    // verifica se existem campos vazios ou no formato invalido
    private boolean isValidFields() {
        String nome = tela.getTxtFieldNome().getText();
        String dataNasc = tela.getTxtFieldDataNasc().getText();
        String email = tela.getTxtFieldEmail().getText();
        String telefone = tela.getTxtFieldTelefone().getText();

        if ("".equals(nome) || "".equals(dataNasc) || "".equals(telefone) || "".equals(email)) {
            return false;
        }
        // valida os formatos 

        return true;

    }

    public static boolean isValidEmail(String email) {
        String check = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "("
                + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
