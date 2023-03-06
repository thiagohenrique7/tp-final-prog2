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
 * @author Lucas Emanuel
 */
public class PainelPrincipalController {

    private PainelPrincipalView tela;
    private ArrayList<AlunoDTO> alunos;

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
                // clona dados preservando original
                ArrayList<AlunoDTO> listaAlunos = (ArrayList<AlunoDTO>) alunos.clone();
                boolean isChecked = tela.getRadioBtnOrdenar().isSelected();

                // se estiver selecionado ele ordena em ordem alfabetica
                if (isChecked) {

                    Collections.sort(listaAlunos, new Comparator<AlunoDTO>() {
                        @Override
                        public int compare(AlunoDTO o1, AlunoDTO o2) {
                            return o1.getNome().toUpperCase().compareTo(o2.getNome().toUpperCase());
                        }
                    });
                } else {

                }

                mostrarAlunos(listaAlunos);

                System.out.println("ordenado");
            }
        });

        tela.getBtnBuscar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String pesquisa = tela.getTxtFieldPesquisa().getText();
                ArrayList<AlunoDTO> listaAlunos = new ArrayList<AlunoDTO>(pesquisar(pesquisa));

                mostrarAlunos(listaAlunos);
                ;
                System.out.println("pesquisa feita");
            }
        });

        tela.getBtnRemover().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor Selecione um Aluno!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int resposta = JOptionPane.showConfirmDialog(null, "tem certeza que deseja deletar aluno", "Alerta", JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    // deleta
                    AlunoDTO aluno = alunos.get(alunoSelecionado - 1);
                    AlunoDAO objAlunoDAO = new AlunoDAO();

                    objAlunoDAO.deleteAluno(aluno);
                    // pesquisa 
                    alunos = objAlunoDAO.getAlunos();

                    mostrarAlunos(alunos);
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
                    AlunoDTO aluno = alunos.get(alunoSelecionado - 1);

                    FormularioAlunoController form = new FormularioAlunoController(aluno.getId(), aluno.getNome(), aluno.getData_nascimento(), aluno.getEmail(), aluno.getTelefone());

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

    public ArrayList<AlunoDTO> pesquisar(String pesquisa) {
        ArrayList<AlunoDTO> alunosFiltrados = new ArrayList<AlunoDTO>();

        for (AlunoDTO aluno : alunos) {
            if (aluno.getNome().contains(pesquisa)) {
                alunosFiltrados.add(aluno);
            }
        }

        return alunosFiltrados;

    }

    private void mostrarAlunos(ArrayList<AlunoDTO> listaAlunos) {
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
