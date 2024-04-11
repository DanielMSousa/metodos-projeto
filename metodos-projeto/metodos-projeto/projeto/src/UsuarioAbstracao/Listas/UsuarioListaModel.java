package src.UsuarioAbstracao.Listas;


import java.util.List;



public class UsuarioListaModel<T> implements UsuarioListaModelInterface<T> {
    private List<T> listaProgramadores;
    private int tamanhoLista;

    protected UsuarioListaModel(List<T> listaProgramadores) {
        this.listaProgramadores = listaProgramadores;
        this.tamanhoLista = listaProgramadores.size();
    }
    @Override
    public List<T> getListaUsuarios() {
        return listaProgramadores;
    }

    @Override
    public void setListaUsuarios(List<T> listaUsuarios) {
        this.listaProgramadores = listaUsuarios;
    }

    @Override
    public int getTamanhoLista() {
        return this.tamanhoLista;
    }

    @Override
    public void setTamanhoLista(int tamanho) {
        this.tamanhoLista = tamanho;
    }
}
