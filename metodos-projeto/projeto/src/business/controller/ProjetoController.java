package controller;

import domain.UserFactory;
import domain.ProjetoIF;
import domain.Usuario;
import domain.UsuarioIF;
import domain.UsuarioProjeto;
import infra.service.ServicePersistenceIF;
import infra.service.ServicePersistenceFactory;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;


public class ProjetoController {
private final String type ;

private static ProjetoController instance;
private final UserFactory userFactory = UserFactory.getInstance();
private ProjetoController(String bdType){
    this.type = bdType;
}

public static synchronized ProjetoController getInstance(String bdType){
    if(instance == null){
        instance = new ProjetoController(bdType);
    }
    return instance;
}

public int criarProjeto(String nome){
    //adicionar catch
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    int idProjeto = servicePersistence.criarProjeto(nome);
    return idProjeto;
}

public String getUsuariosProjeto(int idProjeto){
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    return servicePersistence.getUsuariosProjeto(idProjeto);

}

public ProjetoIF getProjetoById(int idProjeto)throws TipoUsuarioInvalidoException{
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    ProjetoIF projeto = servicePersistence.buscarProjetoPorId(idProjeto);
    try{
        populateProject(projeto);
        return projeto;
    }
    catch(TipoUsuarioInvalidoException e){
        throw new TipoUsuarioInvalidoException(e.getMessage());
    }

}

private void populateProject(ProjetoIF projeto)throws TipoUsuarioInvalidoException{
    String usuarios = getUsuariosProjeto(projeto.getId());
    try{getUsers(projeto, usuarios);}
    catch(TipoUsuarioInvalidoException e){
        throw new TipoUsuarioInvalidoException(e.getMessage());
    }
}

private void getUsers(ProjetoIF projeto, String userJson) throws TipoUsuarioInvalidoException {
    if (userJson != null && !userJson.isEmpty()) {
        String[] registros = userJson.split(",");

        for (String registro : registros) {
            String idStr = extrairCampo(registro, "id");
            int id = Integer.parseInt(idStr);
            String login = extrairCampo(registro, "login");
            String funcao = extrairCampo(registro, "funcao");
            try {

                UsuarioProjeto usuarioProjeto = userFactory.getProjectUser(funcao, login,projeto.getId());
                usuarioProjeto.setId(id);
                projeto.adicionarUsuario(usuarioProjeto, funcao);
            } catch (TipoUsuarioInvalidoException e) {
                throw new TipoUsuarioInvalidoException(e.getMessage());
            }
        }
    }
}


private String extrairCampo(String registro,String campo){
    String[] partes = registro.split(":");
    return partes[1].trim().replace("\"", "");
}


public void removerUsuarioProjeto(UsuarioProjeto usuario, int idProjeto){
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    servicePersistence.removerUsuarioProjeto(usuario, idProjeto);
}

public void adionarUsuarioProjeto(UsuarioIF usuario, int idProjeto, String nomeFuncao)throws TipoUsuarioInvalidoException{
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    try{servicePersistence.adionarUsuarioProjeto(usuario, idProjeto, nomeFuncao);}
    catch(TipoUsuarioInvalidoException e){throw new TipoUsuarioInvalidoException(e.getMessage());}
}

public UsuarioProjeto buscarUsuarioProjetoPorLogin(String login,int idProjeto) throws TipoUsuarioInvalidoException{
    ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
    try{
        UsuarioProjeto usuario = servicePersistence.getUsuarioProjetoPorId(login,idProjeto);
        return usuario;
    }
    catch(TipoUsuarioInvalidoException e){throw new TipoUsuarioInvalidoException(e.getMessage());}
}

}
