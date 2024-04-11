package src.Programador;

import java.util.List;

import src.Programador.Listas.ControllerProgramadoresDisponiveis;
import src.Programador.Listas.ControllerProgramadoresEmProjeto;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import src.Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import src.Utils.Validacoes.UsuarioValidator;

public class ProgramadorController implements UsuarioController  {
    private static ProgramadorController instance;
    private static ControllerProgramadoresDisponiveis listaProgramadoresDisponiveisController;
    private static ControllerProgramadoresEmProjeto listaProgramadoresEmProjetoController;

    public synchronized static ProgramadorController getInstance(){
        if(instance == null){
            instance = new ProgramadorController();
        }
        return instance;
    }

    private ProgramadorController(){
        listaProgramadoresDisponiveisController = ControllerProgramadoresDisponiveis.getInstance();
        listaProgramadoresEmProjetoController = ControllerProgramadoresEmProjeto.getInstance();
    }

    public ProgramadorModel criarNovoUsuario(String nome,String login, String senha){
        ProgramadorModel novoUsuario = new ProgramadorModel(nome);
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

        listaProgramadoresDisponiveisController.adicionarUsuario(novoUsuario);


        return novoUsuario;
    }

    public void alterarDiponibilidade(ProgramadorModel usuario){
        if(usuario.getDisponivel()){
            usuario.setDisponivel(false);
            listaProgramadoresDisponiveisController.removerUsuario(usuario);
            listaProgramadoresEmProjetoController.adicionarUsuario(usuario);
        }else{
            usuario.setDisponivel(true);
            listaProgramadoresEmProjetoController.removerUsuario(usuario);
            listaProgramadoresDisponiveisController.adicionarUsuario(usuario);
        }
    }

    public void removerUsuario(ProgramadorModel usuario) {
        listaProgramadoresDisponiveisController.removerUsuario(usuario);
    }


    public List<ProgramadorModel> obterProgramadoresDisponiveis(){
        return listaProgramadoresDisponiveisController.getListaUsuarios();
    }

}
