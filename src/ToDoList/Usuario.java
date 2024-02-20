package src.ToDoList;

import java.util.ArrayList;

public class Usuario {
    String nome;

    Usuario(String nome){
        this.nome = nome;

    }

    void marcarOuDesmarcar(int indexTarefa, ArrayList<Tarefa> tarefas){
        Tarefa tarefaAntiga = tarefas.get(indexTarefa);
        tarefaAntiga.toggleStatus();
    }


}
