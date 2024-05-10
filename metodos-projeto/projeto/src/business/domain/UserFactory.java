package domain;

import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class UserFactory {
    public static FuncaoIF getUserFunction(String tipo, String login, String nome) throws TipoUsuarioInvalidoException {
        if ("gerente".equalsIgnoreCase(tipo)) {
            return new Gerente(nome, login);
        } else if ("programador".equalsIgnoreCase(tipo)) {
            return new Programador(nome, login);
        } else if ("analista".equalsIgnoreCase(tipo)) {
            return new AnalistaSistemas(nome, login);
        } else {
            throw new TipoUsuarioInvalidoException("Tipo de usuário inválido");
        }
    }
    
    public static UsuarioIF getSystemUser(String nome, String login, String senha) {
        return new Usuario(nome, login, senha);
    }
}
