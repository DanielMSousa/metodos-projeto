package Service;

import java.util.List;

import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;

public interface ServicePersistence {
    void criarUsuario(Usuario usuario) throws LoginExisteException;
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(String login) throws LoginExisteException;
    Usuario buscarUsuarioPorLogin(String Login);
    void criarProjeto(String nomeProjeto);
    String getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(Usuario usuario, int idProjeto);
    void adionarUsuarioProjeto(Usuario usuario,int idProjeto,String nomeFuncao);
    void removerProjeto(int idProjeto);
    void alterarNomeProjeto(int idProjeto, String novoNome);
    String getUsuarios();

    //Tem mais ainda
}

