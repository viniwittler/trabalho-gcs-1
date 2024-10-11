import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Administrador;
import dados.Usuario;

public class App {

    private static List<Usuario> usuarios = new ArrayList<>();
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

        usuarios.add(new Administrador("admin01", "Carlos Lacerda"));

        usuarioAtual = null;
    }
}
