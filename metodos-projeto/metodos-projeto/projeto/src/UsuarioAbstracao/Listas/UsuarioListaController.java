package src.UsuarioAbstracao.Listas;

import java.util.Collection;

public interface UsuarioListaController<UsuarioAbstrato>{
    Collection<UsuarioAbstrato> getUsuarios();
    void adicionarUsuario(UsuarioAbstrato usuario);
    void removerItem(UsuarioAbstrato usuario);
    void atualizarItem(UsuarioAbstrato usuario);
    UsuarioAbstrato getUsuarioPorNome(String nome);
}
