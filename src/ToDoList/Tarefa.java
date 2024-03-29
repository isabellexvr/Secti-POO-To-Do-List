package src.ToDoList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarefa {
    private boolean status;
    private String categoria, titulo, descricao;
    private Date dataCriacao, prazo;
    private int indice;

    // Novo construtor com índice
    public Tarefa(String categoria, String titulo, String descricao, Date prazo, int indice) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = false;
        this.dataCriacao = new Date();
        this.prazo = prazo;
        this.indice = indice;
    }

    // Método para definir o índice
    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Date getPrazo() {
        return prazo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean status() {
        return status;
    }

    public void toggleStatus() {
        this.status = !this.status;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public int getIndice() {
        return indice;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String statusStr = status() ? "Concluída" : "Pendente";
        String prazoStr = (prazo != null) ? sdf.format(prazo) : "Sem prazo";
        return String.format("%s (Status: %s, Criada em: %s, Prazo: %s)", titulo, statusStr, sdf.format(dataCriacao),
                prazoStr);
    }
}
