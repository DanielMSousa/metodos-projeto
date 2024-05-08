package Gerente;

import java.sql.Connection;
import java.util.List;

import Usuarios.UsuarioAbstrato;
import Usuarios.ControllersInterface;
import Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import Utils.Validacoes.UsuarioValidator;

public class GerenteController implements ControllersInterface {
    private static GerenteController instance;
    private Connection connection;

 

    public synchronized static GerenteController getInstance(Connection connection){
        if(instance == null){
            instance = new GerenteController(connection);
        }
        return instance;
    } 
    private GerenteController(Connection connection){
        this.connection = connection;
    }
    @Override
    public GerenteModel criarNovoUsuario(String nome,String login, String senha){
        GerenteModel novoUsuario = new GerenteModel(nome);
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
    public GerenteModel updateUsuario(String login) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GerenteModel> getUsuarios() {
        // TODO Auto-generated method stub
        return null;
    }
}
