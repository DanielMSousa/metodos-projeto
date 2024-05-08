package projetos;

import Service.ServicePersistence;
import Usuarios.Usuario;
import factory.ServicePersistenceFactory;

public class ProjetoController {
private final String type ;
private final String loginUsuario ;
private final String password ;
private static ProjetoController instance;

private ProjetoController(String bdType,String login,String password){
    this.type = bdType;
    this.loginUsuario = login;
    this.password = password;
}

public static synchronized ProjetoController getInstance(String bdType,String login, String password){
    if(instance == null){
        instance = new ProjetoController(bdType, login, password);
    }
    return instance;
}

public void criarProjeto(String nome){
    //adicionar catch
    ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
    servicePersistence.criarProjeto(nome);
}

public String getUsuariosProjeto(int idProjeto){
    ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
    return servicePersistence.getUsuariosProjeto(idProjeto);
}

public void removerUsuarioProjeto(Usuario usuario, int idProjeto){
    ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
    servicePersistence.removerUsuarioProjeto(usuario, idProjeto);
}

public void adionarUsuarioProjeto(Usuario usuario, int idProjeto, String nomeFuncao){
    ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
    servicePersistence.adionarUsuarioProjeto(usuario, idProjeto, nomeFuncao);
}

}
