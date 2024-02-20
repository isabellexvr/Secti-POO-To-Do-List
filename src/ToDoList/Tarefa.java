package src.ToDoList;

public class Tarefa {
    boolean status;
    String categoria, titulo, descricao;

    Tarefa(String categoria, String titulo, String descricao){
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = false;
    }

}
