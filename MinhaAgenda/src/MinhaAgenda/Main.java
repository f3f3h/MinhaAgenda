package MinhaAgenda;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tarefas> tarefas = new ArrayList<>(); // Lista que armazena todas as tarefas

        tarefas.add(new Personal("Marcar consulta check up" , "Pendente" , LocalDate.of(2025, 8,4)));
        tarefas.add(new Study("Finalizar projeto Java" , "Concluido", LocalDate.of(2025, 7, 23)));
        tarefas.add(new Work("Relatorio de vendas", "Pendente", LocalDate.of(2025, 7,10)));


        int opcao; // Variável para armazenar a opção do menu

        // Estrutura de repetição principal do programa (menu)
        do {
            // Exibe o menu de opções
            System.out.println("\nBem-vindo à sua agenda!");
            System.out.println("--------------------------------");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Marcar tarefa como concluída");
            System.out.println("4 - Tarefas pendentes");
            System.out.println("5 - Tarefas em andamento");
            System.out.println("6 - Tarefas concluídas");
            System.out.println("0 - Sair");
            System.out.println("---------------");
            System.out.print("Escolha uma opção: ");

            // Validação da entrada numérica do usuário
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // descarta a entrada inválida
            }
            opcao = scanner.nextInt(); // lê a opção escolhida
            scanner.nextLine(); // limpa o buffer do scanner

            // Estrutura de decisão principal (switch de acordo com a opção escolhida)
            switch (opcao) {
                case 1:
                    System.out.println("--- Adicionar nova tarefa ---");

                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = scanner.nextLine();

                    while (true) { // fica nesse laço até o usuário acertar tudo
                        System.out.print("Digite a data de entrega (formato: AAAA-MM-DD): ");
                        String dataStr = scanner.nextLine();
                        LocalDate deadline;

                        try {
                            deadline = LocalDate.parse(dataStr);
                        } catch (DateTimeParseException e) {
                            System.out.println("❌ Data inválida. Tente novamente.");
                            continue;
                        }

                        System.out.println("Qual o tipo da tarefa?");
                        System.out.println("1 - Pessoal");
                        System.out.println("2 - Trabalho");
                        System.out.println("3 - Estudo");
                        System.out.print("Escolha: ");
                        String tipo = scanner.nextLine();

                        Tarefas novaTarefa = null;

                        switch (tipo) {
                            case "1" -> novaTarefa = new Personal(descricao, "Pendente", deadline);
                            case "2" -> novaTarefa = new Work(descricao, "Pendente", deadline);
                            case "3" -> novaTarefa = new Study(descricao, "Pendente", deadline);
                            default -> {
                                System.out.println("❌ Opção inválida. Tente novamente.");
                                continue;
                            }
                        }

                        // Adiciona à lista
                        tarefas.add(novaTarefa);

                        // Formata e exibe a data de entrega
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        System.out.println("✅ Tarefa adicionada com sucesso!");
                        System.out.println("📅 Data de entrega: " + deadline.format(formatter));
                        break;
                    }

                    break; // fim do case 1

                case 2:
                    // Exibe todas as tarefas
                    System.out.println("\n--- Todas as tarefas ---");
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        for (int i = 0; i < tarefas.size(); i++) {
                            System.out.println("[" + i + "]\n" + tarefas.get(i).getResumoTarefa());
                        }
                    }
                    break; //parar repitição

                case 3:
                    // Atualizar o status de uma tarefa
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                        break;
                    }

                    // Lista as tarefas com índice e status atual
                    System.out.println("\nTarefas cadastradas:");
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.println("[" + i + "] " + tarefas.get(i).getDescricao() + " (" + tarefas.get(i).getStatus() + ")");
                    }

                    System.out.print("Digite o número da tarefa para atualizar o status: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida.");
                        scanner.nextLine(); // limpa entrada inválida
                        break;
                    }
                    int indice = scanner.nextInt();
                    scanner.nextLine(); // limpa o buffer

                    if (indice >= 0 && indice < tarefas.size()) {
                        // Submenu para escolher o novo status
                        System.out.println("Escolha o novo status:");
                        System.out.println("1 - Em andamento");
                        System.out.println("2 - Concluída");
                        System.out.println("3 - Cancelada");

                        int escolhaStatus = scanner.nextInt();
                        scanner.nextLine(); // limpa o buffer

                        // Converte a escolha numérica em uma string de status
                        String novoStatus = switch (escolhaStatus) {
                            case 1 -> "Em andamento";
                            case 2 -> "Concluída";
                            case 3 -> "Cancelada";
                            default -> "Pendente";
                        };

                        tarefas.get(indice).setStatus(novoStatus); // atualiza o status da tarefa
                        System.out.println("Status atualizado para: " + novoStatus);
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 4:
                    // Lista tarefas com status "Pendente"
                    System.out.println("\n--- Tarefas pendentes ---");
                    for (Tarefas t : tarefas) {
                        if (t.getStatus().equalsIgnoreCase("Pendente")) {
                            System.out.println(t.getResumoTarefa());
                        }
                    }
                    break;

                case 5:
                    // Lista tarefas com status "Em andamento"
                    System.out.println("\n--- Tarefas em andamento ---");
                    for (Tarefas t : tarefas) {
                        if (t.getStatus().equalsIgnoreCase("Em andamento")) {
                            System.out.println(t.getResumoTarefa());
                        }
                    }
                    break;

                case 6:
                    // Lista tarefas com status "Concluída"
                    System.out.println("\n--- Tarefas concluídas ---");
                    for (Tarefas t : tarefas) {
                        if (t.getStatus().equalsIgnoreCase("Concluída")) {
                            System.out.println(t.getResumoTarefa());
                        }
                    }
                    break;

                case 0:
                    // Finaliza o programa
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    // Caso o usuário digite uma opção inválida
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0); // repete até que o usuário escolha sair

        scanner.close(); // encerra o scanner
    }
}


