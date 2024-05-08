package Service;

import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;

public interface ServicePersistence {
    void criarUsuario(Usuario usuario) throws LoginExisteException;
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(String login) throws LoginExisteException;
    Usuario buscarUsuarioPorLogin(String Login);
    void criarProjeto(Usuario usuario,String nomeProjeto);
    Usuario getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(Usuario usuario, int idProjeto);
    void adionarUsuarioProjeto(Usuario usuario,int idProjeto,int idFuncao);
    void atribuirTarefa(Usuario usuario, int idTarefa);
    //Tem mais ainda
}

