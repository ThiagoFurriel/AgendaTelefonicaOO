import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Agenda minhaAgenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("=== SISTEMA DE AGENDA TELEFÔNICA ===");

        while (opcao != 0) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Inserir Contato");
            System.out.println("2 - Buscar Contato");
            System.out.println("3 - Remover Contato");
            System.out.println("4 - Listar todos os contatos");
            System.out.println("5 - Ver quantidade de contatos ");
            System.out.println("6 - Verificar se a agenda esta cheia");
            System.out.println("7 - Salvar em arquivo");
            System.out.println("8 - Carregar de arquivo");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Inserindo Contato ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String end = scanner.nextLine();

                    System.out.print("Relação: ");
                    String rel = scanner.nextLine();

                    Contato novoContato = new Contato(nome, tel, end, rel);
                    minhaAgenda.inserir(novoContato);
                    System.out.println("Operação realizada (Inserido ou Atualizado)!");
                    break;

                case 2:
                    System.out.println("\n--- Buscando Contato ---");
                    System.out.print("Digite o nome (ou parte dele): ");
                    String termo = scanner.nextLine();
                    Contato resultado = minhaAgenda.buscar(termo);

                    if (resultado != null) {
                        System.out.println("Contato encontrado:");
                        System.out.println(resultado);
                    } else {
                        System.out.println("Nenhum contato encontrado com esse nome.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Removendo Contato ---");
                    System.out.print("Digite o nome exato para remover: ");
                    String nomeRemover = scanner.nextLine();
                    minhaAgenda.remover(nomeRemover);
                    System.out.println("Se o contato existia, foi removido.");
                    break;

                case 4:
                    System.out.println("\n--- Lista de Contatos ---");
                    minhaAgenda.listar();
                    break;

                case 5: //
                    System.out.println("\n--- Status da Agenda ---");
                    int qtd = minhaAgenda.getQuantidade();
                    System.out.println("Existem " + qtd + " contatos cadastrados.");
                    break;

                case 6:
                    if (minhaAgenda.isCheia()) {
                        System.out.println("\nA agenda atingiu o limite de 1000 contatos.");
                    } else {
                        int restantes = 1000 - minhaAgenda.getQuantidade();
                        System.out.println("\nA agenda tem espaco disponível.");
                        System.out.println("Você ainda pode cadastrar mais " + restantes + " contatos.");
                    }
                    break;

                case 7:
                    System.out.print("\nDigite o nome do arquivo para salvar: ");
                    String arqSalvar = scanner.nextLine();
                    minhaAgenda.salvarArquivo(arqSalvar);
                    System.out.println("Arquivo salvo.");
                    break;

                case 8:
                    System.out.print("\nDigite o nome do arquivo para carregar: ");
                    String arqCarregar = scanner.nextLine();
                    minhaAgenda.carregarArquivo(arqCarregar);
                    System.out.println("Tentativa de carregamento finalizada. Verifique a lista.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        scanner.close();
    }
}