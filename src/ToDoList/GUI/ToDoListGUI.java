// ToDoListGUI.java
package src.ToDoList.GUI;

import src.ToDoList.Usuario;
import src.ToDoList.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

public class ToDoListGUI extends JFrame {
    private Usuario usuario;
    private JTextField campoTarefa;
    private JTextArea campoDescricao;
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JList<Tarefa> listaTarefas;
    private DefaultListModel<Tarefa> model;

    public ToDoListGUI(Usuario usuario) {
        this.usuario = usuario;
        initGUI();
    }

    public void exibir() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    private void initGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("To-Do List");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        campoTarefa = new JTextField(20);
        campoTarefa.setText("Digite Sua Tarefa:");
        campoTarefa.setForeground(Color.GRAY);
        campoTarefa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoTarefa.getText().equals("Digite Sua Tarefa:")) {
                    campoTarefa.setText("");
                    campoTarefa.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoTarefa.getText().isEmpty()) {
                    campoTarefa.setForeground(Color.GRAY);
                    campoTarefa.setText("Digite Sua Tarefa:");
                }
            }
        });

        campoDescricao = new JTextArea(5, 20);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);

        botaoAdicionar = new JButton("Adicionar Tarefa");
        botaoRemover = new JButton("Remover Tarefa");

        model = new DefaultListModel<>();
        listaTarefas = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(listaTarefas);

        panel.add(campoTarefa, BorderLayout.NORTH);
        panel.add(scrollDescricao, BorderLayout.CENTER);
        panel.add(botaoAdicionar, BorderLayout.SOUTH);

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new BoxLayout(botoesPanel, BoxLayout.Y_AXIS));
        botoesPanel.add(botaoRemover);
        botoesPanel.add(Box.createVerticalStrut(10));
        botoesPanel.add(scrollPane);
        panel.add(botoesPanel, BorderLayout.EAST);

        add(panel);

        botaoAdicionar.addActionListener(e -> adicionarTarefa());
        botaoRemover.addActionListener(e -> removerTarefa());

        setVisible(true);
    }

    // Dentro do método adicionarTarefa()
    private void adicionarTarefa() {
        SwingUtilities.invokeLater(() -> {
            String novaTarefa = campoTarefa.getText();
            String descricao = campoDescricao.getText();

            // Adição da nova tarefa apenas se a categoria não for nula
            if (!novaTarefa.isEmpty() && !novaTarefa.equals("Digite Sua Tarefa:")) {
                Date prazo = obterPrazo();

                 // Criação da nova tarefa com índice
                Tarefa tarefa = new Tarefa("Geral", novaTarefa, descricao, prazo, model.size() + 1);
                usuario.adicionarTarefa(tarefa);
                model.addElement(tarefa);
                campoTarefa.setText("Digite Sua Tarefa:");
                campoTarefa.setForeground(Color.GRAY);
                campoDescricao.setText("");
            }
        });
    }

    private Date obterPrazo() {
        // Adicione aqui a lógica para obter a data de prazo desejada
        return null;
    }

    private void removerTarefa() {
        SwingUtilities.invokeLater(() -> {
            int indiceSelecionado = listaTarefas.getSelectedIndex();

            // Remoção da tarefa e exibição da mensagem
            if (indiceSelecionado != -1) {
                usuario.removerTarefa(indiceSelecionado);
                model.remove(indiceSelecionado);
            }
        });
    }
}