package controller;


import java.sql.Connection;
import java.util.List;

import AnalistaSistemas.AnalistaSistemasModel;
import Usuarios.UsuarioAbstrato;
import infra.utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import infra.utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import infra.utils.Validacoes.UsuarioValidator;
import Usuarios.ControllersInterface;

public class AnalistaSistemasController implements ControllersInterface{

    private static AnalistaSistemasController instance;
    private Connection connection;
    private AnalistaSistemasController(Connection connection){
        this.connection = connection;
    }


    public synchronized static AnalistaSistemasController getInstance(Connection connection){
        if(instance == null){
            instance = new AnalistaSistemasController(connection);
        }
        return instance;
    }
    @Override
    public AnalistaSistemasModel criarNovoUsuario(String nome,String login, String senha){
        AnalistaSistemasModel novoUsuario = new AnalistaSistemasModel(nome);
        if(novoUsuario != null){
            
                try{
                    UsuarioValidator.validarLogin(login);
                    novoUsuario.setLogin(login);
                     
                }catch(LoginInvalidoException e){
                        System.err.println("Erro ao criar novo login: "+ e.getMessage());
                }
                try{
                    UsuarioValidator.validarSenha(senha);
                    novoUsuario.setSenha(senha);
                }catch(SenhaInvalidaException e){
                    System.err.println("Erro ao criar senha: "+ e.getMessage());
                }
        }
        return novoUsuario;
    }
    @Override
    public void alterarDiponibilidade(UsuarioAbstrato usuario){
        if(usuario.getDisponivel()){
            usuario.setDisponivel(false);
            
        }else{
            usuario.setDisponivel(true);
           
        }
    }

    @Override
    public void deletarUsuario(String login) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AnalistaSistemasModel updateUsuario(String login) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AnalistaSistemasModel> getUsuarios() {
        // TODO Auto-generated method stub
        return null;
    }
}
