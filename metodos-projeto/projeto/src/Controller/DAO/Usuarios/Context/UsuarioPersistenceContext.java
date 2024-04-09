package src.Controller.DAO.Usuarios.Context;
import src.Interfaces.Dao.UsuarioPersistenceStrategy;
import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;

public class UsuarioPersistenceContext {
    private UsuarioPersistenceStrategy strategy;

    public UsuarioPersistenceContext(UsuarioPersistenceStrategy strategy) {
        this.strategy = strategy;
    }

    public void criarUsuario(UsuarioAbstrato usuario) {
        strategy.criar(usuario);
    }

    public void atualizarUsuario(UsuarioAbstrato usuario) {
        strategy.atualizar(usuario);
    }

    public void excluirUsuario(int id) {
        strategy.excluir(id);
    }

    public UsuarioAbstrato recuperarUsuario(int id) {
        return strategy.recuperar(id);
    }
}
