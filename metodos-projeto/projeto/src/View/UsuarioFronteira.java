package src.View;
import java.util.Scanner;

import src.UsuarioAbstracao.UsuarioController;

public class UsuarioFronteira {
    private UsuarioController usuarioController;
    private Scanner scanner;

    public UsuarioFronteira(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        this.scanner = new Scanner(System.in);
    }

    // Método para coletar dados de um novo usuário do usuário
    public void cadastrarNovoUsuario() {
        System.out.println("Cadastro de Novo Usuário:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nível de Acesso: ");
        String nivelAcesso = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chamar o método do controlador para criar um novo usuário
        try {
            usuarioController.criarNovoUsuario(id, nome, nivelAcesso, login, senha);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar novo usuário: " + e.getMessage());
        }
    }

    // Método para listar usuários
    public void listarUsuarios() {
        System.out.println("Lista de Usuários:");
        usuarioController.listarUsuarios();
    }

    // Outros métodos para interagir com os usuários, se necessário
}