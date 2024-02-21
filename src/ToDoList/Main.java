package src.ToDoList;
import src.ToDoList.GUI.ToDoListGUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static ArrayList<String> categorias = new ArrayList<>(Arrays.asList("Lazer", "Trabalho", "Estudos"));
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nomeUsuario = input.nextLine();

        Usuario usuario = new Usuario(nomeUsuario);

        boolean exibirMenu = true;

        while (true) {
            if (exibirMenu) {
                exibirMenu(nomeUsuario);
            }

            int escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 1:
                    ArrayList<Tarefa> tasks =  usuario.getAllTarefas();

                    if(tasks.isEmpty()){
                        System.out.println("Você ainda não tem tarefas.");
                    }else{
                        System.out.println("Todas as tarefas de " + nomeUsuario + ":");
                        tasks.forEach((tarefa) ->{
                            int index = tasks.indexOf(tarefa) + 1;
                            System.out.println(index + ". " + tarefa.getTitulo());
                            System.out.println("\t" + tarefa.getDescricao());
                            System.out.println("\t(Status: " + (tarefa.status() ? "Concluída" : "Pendente") +
                                                    ". Criada em: " + tarefa.getDataCriacao() +
                                                    ". Prazo: " + tarefa.getPrazo() + ")");
                            }

                        );
                    }

                    continuarOuSair(input, nomeUsuario);
                    break;
                case 2:
                    System.out.println("Como quer consultar a tarefa?");
                    System.out.println("1. Por status");
                    System.out.println("2. Por categoria");
                    int opcao = input.nextInt();
                    if(opcao == 1){

                    }else if(opcao == 2){

                    }
                    input.nextLine();
                    continuarOuSair(input, nomeUsuario);
                    break;
                case 3:
                    System.out.println("Selecione a categoria da nova tarefa:");
                    for (int i = 0; i<categorias.size() ; i++){
                        System.out.println(i + 1 + ". " +  categorias.get(i));
                    }
                    int selected_category = input.nextInt();
                    input.nextLine();

                    System.out.println("Insira o título da nova tarefa:");
                    String title = input.nextLine();

                    System.out.println("Insira a descrição da nova tarefa:");
                    String description = input.nextLine();

                    System.out.println("Até quando pretende realizar essa tarefa?");
                    Date prazo = obterPrazo(input);

                    Tarefa nova_task = new Tarefa(categorias.get(selected_category), title, description, prazo);
                    usuario.adicionarTarefa(nova_task);

                    System.out.println("Tarefa adicionada com sucesso!");
                    continuarOuSair(input, nomeUsuario);
                    break;
                case 4:
                    System.out.println("Digite o índice da tarefa para marcar ou desmarcar:");
                    int indice = input.nextInt();
                    input.nextLine();
                    usuario.marcarOuDesmarcar(indice);
                    continuarOuSair(input, nomeUsuario);
                    break;
                case 5:
                    System.out.println("Digite o índice da tarefa para deletar:");
                    int indiceDeletar = input.nextInt();
                    input.nextLine(); // Consumir a quebra de linha após o número
                    //toDoList.deletarTarefa(indiceDeletar);
                    continuarOuSair(input, nomeUsuario);
                    break;
                case 6:
                    ToDoListGUI gui = new ToDoListGUI(usuario);
                    gui.exibir();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma das opções listadas.");
            }

            exibirMenu = true;
        }
    }

    public static void consultarPorCategoria(){

    }

    public static void consultarPorStatus(){

    }

    public static void continuarOuSair(Scanner input, String nomeUsuario) {
        System.out.println("1. Voltar ao menu principal");
        System.out.println("2. Sair do programa");

        int escolha = input.nextInt();
        input.nextLine();

        switch (escolha) {
            case 1:
                break;
            case 2:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Voltando ao menu principal.");
        }
    }

    public static void exibirMenu(String nomeUsuario) {
        System.out.println("To-Do-List\n\n");
        System.out.println("Seja bem-vindo(a), " + nomeUsuario + "!\n");
        System.out.println("Insira um número para cada funcionalidade:");
        System.out.println("1. Listar todas as tarefas");
        System.out.println("2. Consultar tarefas");
        System.out.println("3. Criar nova tarefa");
        System.out.println("4. Marcar ou desmarcar tarefa");
        System.out.println("5. Deletar tarefa");
        System.out.println("6. Abrir GUI");
        System.out.println("7. Sair");
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
