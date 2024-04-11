package src.AnalistaSistemas;

import src.AnalistaSistemas.Listas.ControllerAnalistaSistemasDisponivel;
import src.AnalistaSistemas.Listas.ControllerAnalistaSistemasEmProjeto;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import src.Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import src.Utils.Validacoes.UsuarioValidator;

public class AnalistaSistemasController implements UsuarioController{

    private static AnalistaSistemasController instance;
    private static ControllerAnalistaSistemasDisponivel controllerAnalistaSistemasDisponivel;
    private static ControllerAnalistaSistemasEmProjeto controllerAnalistaSistemasEmProjeto;
    private AnalistaSistemasController(){
        controllerAnalistaSistemasDisponivel = ControllerAnalistaSistemasDisponivel.getInstance();
        controllerAnalistaSistemasEmProjeto = ControllerAnalistaSistemasEmProjeto.getInstance();
    }


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

    public void alterarDiponibilidade(AnalistaSistemasModel usuario){
        if(usuario.getDisponivel()){
            usuario.setDisponivel(false);
            controllerAnalistaSistemasDisponivel.removerUsuario(usuario);
            controllerAnalistaSistemasEmProjeto.adicionarUsuario(usuario);
        }else{
            usuario.setDisponivel(true);
            controllerAnalistaSistemasEmProjeto.removerUsuario(usuario);
            controllerAnalistaSistemasDisponivel.adicionarUsuario(usuario);
        }
    }
}
