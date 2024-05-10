package infra.service;


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
    void criarProjeto(String nomeProjeto);
    String getUsuariosProjeto(int idProjeto);
    void removerUsuarioProjeto(Usuario usuario, int idProjeto);
    void adionarUsuarioProjeto(Usuario usuario,int idProjeto,String nomeFuncao);
    void removerProjeto(int idProjeto);
    void alterarNomeProjeto(int idProjeto, String novoNome);
    String getUsuarios();
    ProjetoIF buscarProjetoPorId(int idProjeto);
    Kanban getKanban(int idKanban);
    // tem que ter um kanban inexistente
    void addUsuarioCartao(int idCartao,UsuarioProjeto gerente, UsuarioProjeto atribuinte);
    void createCartao(Kanban kanbanAssociado,String nome,String texto);
    CartaoUsuario getCartoes(String loger);
    void updateStatusCartao(UsuarioProjeto gerente, int idCartao,UsuarioProjeto solicitante,String novoStatus);
    void removeCartao(UsuarioProjeto gerente,int idCartao);
    void createKanban(int idProjeto,String nomeKanban);
    void updateNomeKanban(int idKanban,String nome);
    // todos os metodos podem ter sua logica implementada na fachada
    //Tem mais ainda
}

