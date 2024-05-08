package Usuarios;

import java.util.List;


public interface ControllersInterface {
    Usuario criarNovoUsuario(String nome, String login, String senha);
    void deletarUsuario(String login);
    Usuario updateUsuario(String login);
    List<? extends Usuario> getUsuarios() ;
    void alterarDiponibilidade(Usuario usuario);
}