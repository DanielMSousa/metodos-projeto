package src.Gerente;

import java.util.List;

import src.Gerente.Listas.ControllerGerenteEmProjeto;
import src.Gerente.Listas.ControllerGerentesDisponiveis;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import src.Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import src.Utils.Validacoes.UsuarioValidator;

public class GerenteController implements UsuarioController {
    private static GerenteController instance;
    private static ControllerGerentesDisponiveis controllerGerentesDisponiveis;
    private static ControllerGerenteEmProjeto controllerGerenteEmProjeto;

    public synchronized static GerenteController getInstance(){
        if(instance == null){
            instance = new GerenteController();
        }
        return instance;
    } 
    private GerenteController(){
        controllerGerentesDisponiveis = ControllerGerentesDisponiveis.getInstance();
        controllerGerenteEmProjeto = ControllerGerenteEmProjeto.getInstance();
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
        controllerGerentesDisponiveis.adicionarUsuario(novoUsuario);
        return novoUsuario;
    }

    public void alterarDiponibilidade(GerenteModel usuario){
        if(usuario.getDisponivel()){
            usuario.setDisponivel(false);
            controllerGerentesDisponiveis.removerUsuario(usuario);
            controllerGerenteEmProjeto.adicionarUsuario(usuario);
        }else{
            usuario.setDisponivel(true);
            controllerGerenteEmProjeto.removerUsuario(usuario);
            controllerGerentesDisponiveis.adicionarUsuario(usuario);
        }
    }
    public List<GerenteModel> obterGerentes(){
        return null;
    }

    public GerenteModel recuperarGerente(){
        obterGerentes();
        return null;
    }
}
