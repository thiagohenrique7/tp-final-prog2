/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AlunoDAO;
import DTO.AlunoDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.PainelAlunoView;
import view.PainelPrincipalView;

/**
 *
 * @author mac
 */
public class PainelAlunoController {

    private PainelAlunoView tela;
    private ArrayList<AlunoDTO> alunos;

    private int alunoSelecionado; // index aluno selecionado

    public PainelAlunoController(AlunoDTO aluno) {
        tela = new PainelAlunoView();

        preencherDadosIniciais(aluno);

        tela.setVisible(true);

    }

    private void preencherDadosIniciais(AlunoDTO aluno) {

        tela.getTxtNome().setText(aluno.getNome());
        tela.getTxtEmail().setText(aluno.getEmail());
        tela.getTxtMatricula().setText(Integer.toString(aluno.getId()));
        tela.getTxtTelefone().setText(aluno.getTelefone());
       tela.getTxtDataNasc().setText(aluno.getData_nascimento());
    }

}
