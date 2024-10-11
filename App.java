import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Administrador;
import dados.Departamento;
import dados.Funcionario;
import dados.Usuario;

public class App {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static Usuario usuarioAtual;

    public static void main(String[] args) {
        inicializarDados();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0 && usuarioAtual == null) {
            //exibirMenuInicial();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    //selecionarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
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
        usuarios.add(new Funcionario("func01", "João Silva", departamentos.get(0)));
        usuarios.add(new Funcionario("func02", "Maria Souza", departamentos.get(1)));

        usuarioAtual = null;
    }
}
