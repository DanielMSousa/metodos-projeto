package infra.service;


import domain.ProjetoIF;
import domain.Usuario;
import domain.userIF;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public interface ServicePersistenceIF {
    void criarUsuario(userIF novoUsuario) throws LoginExisteException, TipoUsuarioInvalidoException;
    void atualizarUsuario(userIF usuario);
    void excluirUsuario(String login) throws LoginExisteException,TipoUsuarioInvalidoException;
    userIF buscarUsuarioPorLogin(String Login) throws TipoUsuarioInvalidoException;
    void criarProjeto(String nomeProjeto);
    String getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(Usuario usuario, int idProjeto);
    void adionarUsuarioProjeto(Usuario usuario,int idProjeto,String nomeFuncao);
    void removerProjeto(int idProjeto);
    void alterarNomeProjeto(int idProjeto, String novoNome);
    String getUsuarios();
    ProjetoIF buscarProjetoPorId(int idProjeto);

    //Tem mais ainda
}

