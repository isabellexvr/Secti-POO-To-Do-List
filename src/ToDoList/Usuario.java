package src.ToDoList;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Tarefa> tarefas;
    private int indiceAtual;

    public Usuario(String nome) {
        this.nome = nome;
        this.tarefas = new ArrayList<>();
        this.indiceAtual = 1;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefa.setIndice(indiceAtual++);
        tarefas.add(tarefa);
    }

    // Adição do método getIndiceAtual()
    public int getIndiceAtual() {
        return indiceAtual;
    }

    public ArrayList<Tarefa> getallTarefas() {
        return tarefas;
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

    // Adição do método para remover tarefa por índice
    public void removerTarefa(int indexTarefa) {
        if (indexTarefa >= 0 && indexTarefa < tarefas.size()) {
            Tarefa tarefaRemovida = tarefas.remove(indexTarefa);
            System.out.println("Tarefa \"" + tarefaRemovida.getTitulo() + "\" removida com sucesso!");
        } else {
            System.out.println("Índice inválido, nenhuma tarefa removida.");
        }
    }
}
