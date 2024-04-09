package src.Interfaces.Controller;

import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;

public interface UsuariosControllerInterface {
    void criarUsuario(int id, String nome, String nivelAcesso, String login, String senha);
    void deletarUsuario(UsuarioAbstrato usuario);
}
