package Service;


import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;
import Utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;
import userInterface.OperadorSistema;

public interface ServicePersistence {
    void criarUsuario(OperadorSistema novoUsuario) throws LoginExisteException, TipoUsuarioInvalidoException;
    void atualizarUsuario(OperadorSistema usuario);
    void excluirUsuario(String login) throws LoginExisteException,TipoUsuarioInvalidoException;
    OperadorSistema buscarUsuarioPorLogin(String Login) throws TipoUsuarioInvalidoException;
    void criarProjeto(String nomeProjeto);
    String getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(Usuario usuario, int idProjeto);
    void adionarUsuarioProjeto(Usuario usuario,int idProjeto,String nomeFuncao);
    void removerProjeto(int idProjeto);
    void alterarNomeProjeto(int idProjeto, String novoNome);
    String getUsuarios();

    //Tem mais ainda
}

