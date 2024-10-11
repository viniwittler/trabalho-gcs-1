import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Usuario;

public class App {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioAtual;

    public static void main(String[] args) {
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
}
