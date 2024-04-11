package src.UsuarioAbstracao.Listas;

import java.util.List;

public abstract class UsuariosNaoDisponivelLista<T> implements UsuarioListaModelInterface<T> {
    protected List<T> listaUsuarios;
    protected int tamanhoLista;

    @Override
    public List<T> getListaUsuarios() {
        return listaUsuarios;
    }

    @Override
    public void setListaUsuarios(List<T> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int getTamanhoLista() {
        return tamanhoLista;
    }

    @Override
    public void setTamanhoLista(int tamanho) {
        this.tamanhoLista = tamanho;
    }

    // Métodos específicos para listas de usuários não disponíveis
    public abstract void adicionarUsuario(T usuario);
    public abstract void removerUsuario(T usuario);
    // Outros métodos específicos, se necessário
}
