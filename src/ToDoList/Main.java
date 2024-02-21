package src.ToDoList;

import src.ToDoList.GUI.ToDoListGUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nomeUsuario = input.nextLine();

        Usuario usuario = new Usuario(nomeUsuario);

        System.out.print("Digite a meta: ");
        String meta = input.nextLine();

        Date dataCriacao = new Date();

        Date prazo = obterPrazo(input);

        Tarefa tarefa1 = new Tarefa("Geral", meta, "Descrição da meta", dataCriacao, prazo);
        usuario.adicionarTarefa(tarefa1);

        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Tarefas:");
        usuario.getTarefas().forEach(tarefa ->
                System.out.println("- " + tarefa.getTitulo() + " (Status: " +
                        (tarefa.isStatus() ? "Concluída" : "Pendente") +
                        ", Criada em: " + tarefa.getDataCriacao() +
                        ", Prazo: " + tarefa.getPrazo() + ")")
        );

        ToDoListGUI gui = new ToDoListGUI(usuario);
        gui.exibir();
    }

    private static Date obterPrazo(Scanner input) {
        while (true) {
            try {
                System.out.print("Digite o prazo (formato dd/MM/yyyy): ");
                String prazoStr = input.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date prazo = dateFormat.parse(prazoStr);

                if (prazo.after(new Date())) {
                    return prazo;
                } else {
                    System.out.println("O prazo deve ser uma data futura.");
                }
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
    }
}
