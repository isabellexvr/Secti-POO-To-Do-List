package src.ToDoList.GUI;

import src.ToDoList.Usuario;
import src.ToDoList.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private void initGUI() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoTarefa = new JTextField();
        campoDescricao = new JTextArea();
        botaoAdicionar = new JButton("Adicionar");
        botaoRemover = new JButton("Remover");
        model = new DefaultListModel<>();
        listaTarefas = new JList<>(model);

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(listaTarefas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelAdicionar = new JPanel(new BorderLayout());

        JPanel painelBotoes = new JPanel(new FlowLayout());  // Alterado para FlowLayout
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);

        painelAdicionar.add(campoTarefa, BorderLayout.NORTH);
        painelAdicionar.add(campoDescricao, BorderLayout.CENTER);
        painelAdicionar.add(painelBotoes, BorderLayout.SOUTH);

        add(painelAdicionar, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerTarefa();
            }
        });

        listaTarefas.setCellRenderer(new TarefaListRenderer());
    }

    private void adicionarTarefa() {
        String novaTarefa = campoTarefa.getText();
        String descricao = campoDescricao.getText();
        if (!novaTarefa.isEmpty()) {
            Tarefa tarefa = new Tarefa("Geral", novaTarefa, descricao);
            usuario.adicionarTarefa(tarefa);
            model.addElement(tarefa);
            campoTarefa.setText("");
            campoDescricao.setText("");
        }
    }

    private void removerTarefa() {
        int indiceSelecionado = listaTarefas.getSelectedIndex();
        if (indiceSelecionado != -1) {
            usuario.removerTarefa(indiceSelecionado);
            model.remove(indiceSelecionado);
        }
    }

    private class TarefaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Tarefa) {
                Tarefa tarefa = (Tarefa) value;
                String status = tarefa.isStatus() ? "Concluída" : "Pendente";
                setText(tarefa.getTitulo() + " (Status: " + status + ")");
            }

            return this;
        }
    }

    public void exibir() {
        setVisible(true);
    }
}
