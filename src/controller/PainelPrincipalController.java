/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Aluno;
import repository.Alunos;
import view.PainelPrincipalView;
import DAO.AlunoDAO;
import DTO.AlunoDTO;
import java.sql.ResultSet;

/**
 *
 * @author mac
 */
public class PainelPrincipalController {

    private PainelPrincipalView tela;
    private  ArrayList <AlunoDTO> alunos;

    private int alunoSelecionado; // index aluno selecionado

    public void RunPainelPrincipalController() {
        tela = new PainelPrincipalView();
        AlunoDAO objAlunoDAO = new AlunoDAO();
        alunos = objAlunoDAO.getAlunos();
        System.out.println(alunos);
        // adiciona eventos de callback
        addCallbacks();

        // carrega dados iniciais
      //ArrayList<Aluno> listaAlunos = new ArrayList<>(this.alunos.getTreeSet());
        // mostra na view
       mostrarAlunos(alunos);
       tela.setVisible(true);

    }

    private void addCallbacks() {

        tela.getRadioBtnOrdenar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ArrayList<Aluno> listaAlunos = alunos.getTreeSet();
                boolean isChecked = tela.getRadioBtnOrdenar().isSelected();
                
                // se estiver selecionado ele ordena em ordem alfabetica
                if (isChecked) {
                    Collections.sort(listaAlunos, new Comparator<Aluno>() {
                        @Override
                        public int compare(Aluno o1, Aluno o2) {
                            return o1.getNome().toUpperCase().compareTo(o2.getNome().toUpperCase());
                        }
                    });
                }

                mostrarAlunos(listaAlunos);

                System.out.println("ordenado");
            }
        });

        tela.getBtnBuscar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String pesquisa = tela.getTxtFieldPesquisa().getText();
                ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>(alunos.pesquisar(pesquisa));

                mostrarAlunos(listaAlunos);
                ;
                System.out.println("pesquisa feita");
            }
        });

        tela.getBtnRemover().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor Selecione um Aluno!", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    // deleta
              
                    alunos.removerAluno(alunoSelecionado - 1);
                    
                    mostrarAlunos(alunos.getTreeSet());
                }
            }
        });

        tela.getBtnEditar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor Selecione um Aluno!", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    // obtem o aluno selecionado e abre edicao de aluno
                    tela.dispose();
                    Aluno aluno = alunos.getAluno(alunoSelecionado - 1);
                    FormularioAlunoController form = new FormularioAlunoController(aluno.getNome(), aluno.getDataNascimento(), aluno.getEmail(), aluno.getTelefone());

                }
            }
        });

        tela.getBtnAdd().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tela.dispose();
                FormularioAlunoController janela = new FormularioAlunoController();
            }
        });
    }

    private void mostrarAlunos(ArrayList <AlunoDTO> listaAlunos) {
        String[] lista = new String[listaAlunos.size()];

        int contador = 0;
        for (AlunoDTO obj : listaAlunos) {
            lista[contador] = obj.getNome();
            contador++;
        }
        System.out.println("" + lista);
        JList<String> listaDeNomes = new JList<>(lista);
        listaDeNomes.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    alunoSelecionado = listaDeNomes.getSelectedIndex() + 1;
                    System.out.println("Item selecionado: " + alunoSelecionado);
  
                }
            }
        });
        // mostra na view
        tela.getjScrollPane2().setViewportView(listaDeNomes);
        alunoSelecionado = 0;
    }

}
