package src.ToDoList;

import src.ToDoList.GUI.ToDoListGUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nomeUsuario = input.nextLine();

        Usuario usuario = new Usuario(nomeUsuario);
        Tarefa tarefa1 = new Tarefa("Geral", "Fazer compras", "Comprar itens essenciais");
        usuario.adicionarTarefa(tarefa1);

        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Tarefas:");
        usuario.getTarefas().forEach(tarefa ->
                System.out.println("- " + tarefa.getTitulo() + " (Status: " + (tarefa.isStatus() ? "Concluída" : "Pendente") + ")")
        );

        ToDoListGUI gui = new ToDoListGUI(usuario);
        gui.exibir();
    }
}
