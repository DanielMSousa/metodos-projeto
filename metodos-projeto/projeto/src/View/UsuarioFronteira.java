package src.View;

import java.util.Scanner;
import src.Facade.UserEquipeFacade;

public class UsuarioFronteira {
    private UserEquipeFacade facade;
    private Scanner scanner;

    public UsuarioFronteira() {
        this.facade = UserEquipeFacade.getInstance();
        this.scanner = new Scanner(System.in);
    }

    // Método para coletar dados de um novo usuário do usuário
    public void cadastrarNovoUsuario() {
        System.out.println("Cadastro de Novo Usuário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Nível de Acesso (analista/gerente/programador): ");
        String nivelAcesso = scanner.nextLine().toLowerCase();
        // Obter o tipo de usuário com base no nível de acesso

        // Chamar o método da fachada para criar um novo usuário
        try {
            facade.criarUsuario(nivelAcesso, nome, login, senha);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar novo usuário: " + e.getMessage());
        }
    }

    // Método para obter o tipo de usuário com base no nível de acesso inserido pelo usuário

    // Outros métodos para interagir com os usuários, se necessário
}
