package Usuarios;
import java.util.List;

import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import factory.UsuarioFactory;
import Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
public class UsuarioController{
    public Usuario criarNovoUsuario(String nome, String login, String senha) throws LoginInvalidoException,SenhaInvalidaException {
        Usuario novoUsario = UsuarioFactory.criarUsuario(nome, login, senha);
        
        return null;  
    }
    public void deletarUsuario(String login) {
        // TODO Auto-generated method stub
        
    }
    public UsuarioAbstrato updateUsuario(String login) {
        // TODO Auto-generated method stub
        return null;
    }
    public List<? extends UsuarioAbstrato> getUsuarios() {
        // TODO Auto-generated method stub
        return null;
    }
    public void alterarDiponibilidade(UsuarioAbstrato usuario) {
        // TODO Auto-generated method stub
        
    }

    public void verify()
}
