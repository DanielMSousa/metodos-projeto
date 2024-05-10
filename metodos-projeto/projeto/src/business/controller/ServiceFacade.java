package controller;


import domain.Cartao;
import domain.Gerente;
import domain.ProjectFactory;
import domain.ProjetoIF;
import domain.UserFactory;
import domain.Usuario;
import domain.UsuarioIF;
import domain.UsuarioProjeto;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class ServiceFacade {
    private UsuarioController usuarioController;
    private CartaoController cartaoController;
    private KanbanController kanbanController;
    private CartaoUsuarioController cartaoUsuarioController;
    private ProjetoController projetoController;

    ServiceFacade instance;

    private ServiceFacade(String type){};
    public ServiceFacade getInstance(String type){
        if(instance == null){
            instance = new ServiceFacade(type);
        }
        return instance;
    }

    public void criarProjeto(Usuario usuario,String nomeProjeto)throws TipoUsuarioInvalidoException{
        ProjetoIF projeto = ProjectFactory.getProjeto("Criar", 0, nomeProjeto);
        int idProjeto = projetoController.criarProjeto(nomeProjeto);
        projeto.setId(idProjeto);
        try 
        {
        projetoController.adionarUsuarioProjeto(usuario, projeto.getId(), "Gerente");

        }catch( TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }

    public UsuarioProjeto adicionarUsuarioProjeto(String loginUsuario,int idProjeto,String novoUsuarioId,String funcaoUsuario) throws TipoUsuarioInvalidoException{
        UsuarioProjeto usuario = projetoController.buscarUsuarioProjetoPorLogin(loginUsuario,idProjeto);
        UsuarioIF novoUsuario = usuarioController.getUsuarioId(loginUsuario);
        if(usuario.getFuncao() instanceof Gerente){
            try{
                projetoController.adionarUsuarioProjeto(novoUsuario, idProjeto, funcaoUsuario);
                UsuarioProjeto novoUsuarioProjeto = UserFactory.getInstance().getProjectUser(funcaoUsuario, novoUsuario.getLogin(), idProjeto);
                return novoUsuarioProjeto;
            }catch(TipoUsuarioInvalidoException e){throw new TipoUsuarioInvalidoException(e.getMessage());}
        }
        return null; //adicionar exception
    }
    

    public void adicionarCartaoUsuario(Cartao cartao, String gerenteLogin,String destinatarioLogin,int idProjeto )throws TipoUsuarioInvalidoException{
        try{
            UsuarioProjeto gerente = projetoController.buscarUsuarioProjetoPorLogin(gerenteLogin, idProjeto);
            if(gerente.getFuncao().equals("Gerente"))
            {
                UsuarioProjeto detinatario = projetoController.buscarUsuarioProjetoPorLogin(destinatarioLogin, idProjeto);
                cartaoUsuarioController.adicionarCartaoUsuario(cartao, detinatario);
            }
        }catch(TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }




}
