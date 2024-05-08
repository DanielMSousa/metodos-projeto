package Usuarios;

import java.util.List;


public interface ControllersInterface {
    UsuarioAbstrato criarNovoUsuario(String nome, String login, String senha);
    void deletarUsuario(String login);
    UsuarioAbstrato updateUsuario(String login);
    List<? extends UsuarioAbstrato> getUsuarios() ;
    void alterarDiponibilidade(UsuarioAbstrato usuario);
}