package domain;

import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class OperadorSistemaFactory {
    public static userIF GetUsuario(String tipo, String login, String nome, String senha) throws TipoUsuarioInvalidoException{
        if ("gerente".equalsIgnoreCase(tipo)) {
            return new Gerente(nome,login,senha);
        } else if ("programador".equalsIgnoreCase(tipo)) {
            return new Programador(nome,login,senha);
        } else if ("analista".equalsIgnoreCase(tipo)) {
            return new AnalistaSistemas(nome,login,senha);
        } else if ("criar".equalsIgnoreCase(tipo)) {
            // Se nenhum tipo válido for fornecido, criar um usuário comum com nome e senha
            return new Usuario(nome,login, senha);
        }
        else{
            throw new TipoUsuarioInvalidoException("Tipo de usuario inválido");
        }
    }
}