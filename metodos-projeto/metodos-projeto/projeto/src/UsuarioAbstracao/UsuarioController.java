package src.UsuarioAbstracao;

import java.util.List;

import src.Programador.ProgramadorModel;

public interface UsuarioController {
    UsuarioAbstrato criarNovoUsuario(String nome, String login, String senha);
    void deletarUsuario(String login);
    UsuarioAbstrato updateUsuario(String login);
    List<? extends UsuarioAbstrato> getUsuarios() ;
    void alterarDiponibilidade(UsuarioAbstrato usuario);
}