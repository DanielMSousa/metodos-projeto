package factory;

import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;

public class UsuarioFactory {
    public static Usuario criarUsuario(String nome, String login, String senha) throws LoginInvalidoException,SenhaInvalidaException{
        Usuario novoUsuario = new Usuario(nome);
        return novoUsuario;
    }
}
