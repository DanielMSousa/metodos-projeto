package infra.service;


import domain.ProjetoIF;
import domain.Usuario;
import domain.funcaoIF;
import domain.usuarioIF;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public interface ServicePersistenceIF {
    void criarUsuario(usuarioIF novoUsuario) throws LoginExisteException, TipoUsuarioInvalidoException;
    void atualizarUsuario(usuarioIF usuario);
    void excluirUsuario(String login) throws LoginExisteException,TipoUsuarioInvalidoException;
    usuarioIF buscarUsuarioPorLogin(String Login) throws TipoUsuarioInvalidoException;
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

