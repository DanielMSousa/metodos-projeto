package src.UsuarioAbstracao.Listas;

import java.util.List;

import src.UsuarioAbstracao.UsuarioAbstrato;

public interface UsuarioListaModelInterface<UsuarioAbstrato>{
    List<UsuarioAbstrato> getListaUsuarios();
    void setListaUsuarios(List<UsuarioAbstrato> listaUsuarios);
    int getTamanhoLista();
    void setTamanhoLista(int tamanho);
}
