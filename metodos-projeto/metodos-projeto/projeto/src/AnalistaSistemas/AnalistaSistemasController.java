package src.AnalistaSistemas;


import java.util.List;

import src.UsuarioAbstracao.UsuarioAbstrato;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import src.Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import src.Utils.Validacoes.UsuarioValidator;

public class AnalistaSistemasController implements UsuarioController{

    private static AnalistaSistemasController instance;
    private AnalistaSistemasController(){}


    public synchronized static AnalistaSistemasController getInstance(){
        if(instance == null){
            instance = new AnalistaSistemasController();
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
