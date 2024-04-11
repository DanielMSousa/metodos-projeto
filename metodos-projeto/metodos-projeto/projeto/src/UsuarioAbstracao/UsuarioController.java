package src.UsuarioAbstracao;

public interface UsuarioController {
    UsuarioAbstrato criarNovoUsuario(String nome, String login, String senha);
}