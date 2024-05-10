package infra.service;


import domain.Cartao;
import domain.CartaoUsuario;
import domain.Kanban;
import domain.ProjetoIF;
import domain.Usuario;
import domain.UsuarioIF;
import domain.UsuarioProjeto;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public interface ServicePersistenceIF {
    void criarUsuario(UsuarioIF novoUsuario) throws LoginExisteException, TipoUsuarioInvalidoException;
    void atualizarUsuario(UsuarioIF usuario);
    void excluirUsuario(String login) throws LoginExisteException,TipoUsuarioInvalidoException;
    UsuarioIF buscarUsuarioPorLogin(String Login) throws TipoUsuarioInvalidoException;
    int criarProjeto(String nomeProjeto);
    String getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(UsuarioProjeto usuario, int idProjeto);
    UsuarioProjeto adionarUsuarioProjeto(UsuarioIF usuario,int idProjeto,String nomeFuncao) throws TipoUsuarioInvalidoException;
    void removerProjeto(int idProjeto);
    void alterarNomeProjeto(int idProjeto, String novoNome);
    String getUsuarios();
    ProjetoIF buscarProjetoPorId(int idProjeto);
    String getKanban(int idKanban);
    // tem que ter um kanban inexistente
    void addUsuarioCartao(Cartao idCartao,UsuarioProjeto atribuinte);
    void createCartao(Cartao cartao);
    String getCartoesUsuario(String loger);
    String getCartoesProjeto(int idProjeto);
    void updateStatusCartao(int idCartao,String novoStatus);
    void removeCartao(UsuarioProjeto gerente,int idCartao);
    void createKanban(Kanban kanban);
    void updateNomeKanban(int idKanban,String nome);
    void removeCartaoUsuario(UsuarioProjeto id,CartaoUsuario idCartaoUsuario);
    UsuarioProjeto getUsuarioProjetoPorId(String idUsuarioProjeto,int idProjeto) throws TipoUsuarioInvalidoException;
    String consultarNomeFuncaoPorId(int idFuncao);
    UsuarioIF getUsuarioPorId(String idUsuario);
    // todos os metodos podem ter sua logica implementada na fachada
    //Tem mais ainda
}

