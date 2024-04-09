package src.Interfaces.Dao;

import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;

public interface UsuarioPersistenceStrategy {
    void criar(UsuarioAbstrato usuario);
    void atualizar(UsuarioAbstrato usuario);
    void excluir(int id);
    UsuarioAbstrato recuperar(int id);
}
