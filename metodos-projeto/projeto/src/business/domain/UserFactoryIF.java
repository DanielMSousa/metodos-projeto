package domain;

import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public interface UserFactoryIF {
    UsuarioIF getSystemUser(String nome, String login, String senha);
    UsuarioProjeto getProjectUser(String userType,String loginUsuario, ProjetoIF projeto) throws TipoUsuarioInvalidoException;
}