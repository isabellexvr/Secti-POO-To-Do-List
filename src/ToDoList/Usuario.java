package src.ToDoList;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Tarefa> tarefas;

    public Usuario(String nome) {
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public ArrayList<Tarefa> getAllTarefas() {
        return tarefas;
    }

    public void marcarOuDesmarcar(int indexTarefa) {
        if (indexTarefa >= 0 && indexTarefa < tarefas.size()) {
            Tarefa tarefa = tarefas.get(indexTarefa);
            tarefa.toggleStatus();
            String str = tarefa.status() ? "marcada" : "desmarcada";
            System.out.println("Tarefa de título \\\"" + tarefa.getTitulo() + "\\\"" + str + " com sucesso!");
        }
    }

    public void removerTarefa(int indexTarefa) {
        if (indexTarefa >= 0 && indexTarefa < tarefas.size()) {
            tarefas.remove(indexTarefa);
        }
    }
}
