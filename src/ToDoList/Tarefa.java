package src.ToDoList;

public class Tarefa {
    boolean status;
    String categoria, titulo, descricao;

    public Tarefa(String categoria, String titulo, String descricao) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = false;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void toggleStatus() {
        this.status = !this.status;
    }
}
