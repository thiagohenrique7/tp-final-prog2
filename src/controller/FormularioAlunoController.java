/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AlunoDAO;
import DAO.UsuarioDAO;
import DTO.AlunoDTO;
import DTO.UsuarioDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import view.FormularioAlunoView;
import view.PainelPrincipalView;

/**
 *
 * @author Lucas Emanuel
 */
public class FormularioAlunoController {

    private FormularioAlunoView tela;
    private boolean isEdit = false;
    private int id = 0;

    //private Alunos alunos;
    public FormularioAlunoController() {
        tela = new FormularioAlunoView();
        addBtnCallbacks();

        tela.setVisible(true);
    }

    // preenche formulario com os dados
    public FormularioAlunoController(int id, String nome, String dataNascimento, String email, String telefone) {
        tela = new FormularioAlunoView();
        // adiciona os valores iniciais
        setDefaultComponentsValues(nome, dataNascimento, email, telefone);
        addBtnCallbacks();
        // seta como edicao de dados
        this.isEdit = true;
        this.id = id;
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
                AlunoDTO aluno = new AlunoDTO();
                aluno.setId(id);
                aluno.setNome(nome);
                aluno.setData_nascimento(dataNasc);
                aluno.setEmail(email);
                aluno.setTelefone(telefone);

                AlunoDAO objAlunoDAO = new AlunoDAO();
                if (isEdit) {
                    objAlunoDAO.updateAluno(aluno);
                } else {
                    // cria novo usuario e cadastra aluno
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    UsuarioDTO usuario = new UsuarioDTO();

                    usuario.setUser(email);
                   
                    int id = objAlunoDAO.cadastrarAluno(aluno);
                     // cria uma senha default para o usuario
                    usuario.setPassword("123");
                    usuario.setType("aluno");
                    usuario.setUserId(id);
                    usuarioDAO.cadastrarUsuario(usuario);
                    

                }

                tela.dispose();
                PainelPrincipalController painel = new PainelPrincipalController();
                painel.RunPainelPrincipalController();

            }
        });

        tela.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                tela.dispose();
                PainelPrincipalController painel = new PainelPrincipalController();
                painel.RunPainelPrincipalController();

            }
        });

    }

    private void setDefaultComponentsValues(String nome, String dataNascimento, String email, String telefone) {
        tela.getTxtFieldNome().setText(nome);
        tela.getTxtFieldDataNasc().setText(dataNascimento);
        tela.getTxtFieldEmail().setText(email);
        tela.getTxtFieldTelefone().setText(telefone);
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
