package src.Gerente;

import java.util.List;

import src.UsuarioAbstracao.UsuarioAbstrato;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import src.Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import src.Utils.Validacoes.UsuarioValidator;

public class GerenteController implements UsuarioController {
    private static GerenteController instance;
 

    public synchronized static GerenteController getInstance(){
        if(instance == null){
            instance = new GerenteController();
        }
        return instance;
    } 
    private GerenteController(){}
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
