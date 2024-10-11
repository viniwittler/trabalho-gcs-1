import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Administrador;
import dados.Departamento;
import dados.Funcionario;
import dados.Item;
import dados.Pedido;
import dados.Usuario;

public class App {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Usuario usuarioAtual;

    public static void main(String[] args) {
        inicializarDados();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println(getFuncionarioPorId("func01"));

        while (opcao != 0 && usuarioAtual == null) {
            exibirMenuInicial();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    selecionarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }

        while (usuarioAtual instanceof Funcionario) {
            exibirMenuFuncionario();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarPedido((Funcionario) usuarioAtual);
                    break;
                case 9:
                    selecionarUsuario();
                default:
                    break;
            }
        }

        while (usuarioAtual instanceof Administrador) {
            exibirMenuAdministrador();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarPedido();
                    break;
                case 3:
                    buscarPedidoPorFuncionario();
                default:
                    break;
            }
        }
    }

    private static void inicializarDados() {
        departamentos.add(new Departamento("Financeiro", 5000));
        departamentos.add(new Departamento("RH", 3000));
        departamentos.add(new Departamento("Engenharia", 8000));
        departamentos.add(new Departamento("Manutenção", 4000));
        departamentos.add(new Departamento("Compras", 6000));

        usuarios.add(new Administrador("admin01", "Carlos Lacerda"));
        usuarios.add(new Funcionario("func01", "Pedro Cardozo", departamentos.get(0)));
        usuarios.add(new Funcionario("func02", "Maria Souza", departamentos.get(1)));

        usuarioAtual = null;
    }

    private static void exibirMenuInicial() {
        System.out.println("----- Sistema de Controle de Aquisições -----");
        System.out.println("1. Selecionar Usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static Funcionario getFuncionarioPorId(String id) {
        return (Funcionario) usuarios.stream().filter(f -> f.getId().equals(id)).findFirst().get();
    }

    private static void buscarPedidoPorFuncionario() {
        Scanner scanner = new Scanner(System.in);
        Funcionario solicitante = null;

        while (true) {
            System.out.print("\nInforme o ID do funcionário solicitante: ");
            String id = scanner.nextLine();
            try {
                solicitante = getFuncionarioPorId(id);
                listarPedidosFuncionario(solicitante).stream().forEach(p -> System.out.println(p));
                break;
            } catch (Exception e) {
                System.out.println("Funcionário não encontrado.");
            }
        }
    }

    private static List<Pedido> listarPedidosFuncionario(Funcionario solicitante) {
        return pedidos.stream().filter(p -> p.getSolicitante().equals(solicitante)).toList();
    }

    private static void selecionarUsuario() {
        System.out.println("\n # [Selecionar Usuário] #");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
        }
        System.out.print("Escolha o usuário: ");
        int escolha = scanner.nextInt() - 1;
        if (escolha >= 0 && escolha < usuarios.size()) {
            usuarioAtual = usuarios.get(escolha);
            if (usuarioAtual instanceof Funcionario) {
                System.out.println("\nFuncionário " + usuarioAtual.getNome() + " selecionado.");
            }
            if (usuarioAtual instanceof Administrador) {
                System.out.println("\nAdministrador " + usuarioAtual.getNome() + " selecionado.");
            }
        } else {
            System.out.println("Usuário inválido.");
        }
    }

    private static void exibirMenuFuncionario() {
        System.out.println("\n1. Registrar Novo Pedido");
        System.out.println("9. Trocar de Usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirMenuAdministrador() {
        System.out.println("\n1. Registrar Novo Pedido");
        System.out.println("2. Listar Pedidos");
        System.out.println("3. Buscar Pedido(s) por Funcionário");
        System.out.println("4. Buscar Pedidos(s) por Descrição");
        System.out.println("5. Ver Relatórios e Estatísticas");
        System.out.print("Escolha uma opção: ");
    }

    private static void registrarPedido() {
        Scanner scanner = new Scanner(System.in);
        Funcionario solicitante = null;

        while (true) {
            System.out.print("\nInforme o ID do funcionário solicitante: ");
            String id = scanner.nextLine();
            try {
                solicitante = getFuncionarioPorId(id);
                System.out.println("\nFuncionário " + solicitante.getNome() + " definido como solicitante.");
                registrarPedido(solicitante);
                break;
            } catch (Exception e) {
                System.out.println("Funcionário não encontrado.");
            }
        }
    }

    private static void registrarPedido(Funcionario solicitante) {
        System.out.println("\n# [Registrar Novo Pedido] #");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Data do pedido (dd/MM/yyyy): ");
        String dataPedido = scanner.nextLine();

        Pedido pedido = new Pedido(solicitante, dataPedido);
        System.out.print("Quantos itens deseja adicionar? ");
        int numItens = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numItens; i++) {
            System.out.print("Descrição do item: ");
            String descricao = scanner.nextLine();
            System.out.print("Valor unitário: ");
            double valorUnitario = scanner.nextDouble();
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            Item item = new Item(descricao, valorUnitario, quantidade);
            pedido.adicionarItem(item);
        }

        if (pedido.getValorTotal() <= solicitante.getDepartamento().getValorMaximoPedido()) {
            pedidos.add(pedido);
            System.out.println("\nPedido registrado com sucesso.");
        } else {
            System.out.println("Valor total excede o limite do departamento.");
        }
    }
}
