package src.AnalistaSistemas.Listas;


import java.util.Collection;
import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.UsuarioAbstracao.Listas.UsuariosNaoDisponivelLista;

public class ControllerAnalistaSistemasEmProjeto extends UsuariosNaoDisponivelLista<AnalistaSistemasModel> {
    private static ControllerAnalistaSistemasEmProjeto instance;
    private AnalistaSistemasDisponivel lista; // Variável para armazenar a instância da lista

    private ControllerAnalistaSistemasEmProjeto() {
        lista = AnalistaSistemasDisponivel.getInstance(null); // Inicializa a lista, se ainda não estiver inicializada
    }

    public static synchronized ControllerAnalistaSistemasEmProjeto getInstance() {
        if (instance == null) {
            instance = new ControllerAnalistaSistemasEmProjeto();
        }
        return instance;
    }

    @Override
    public void adicionarUsuario(AnalistaSistemasModel usuario) {
        lista.getListaUsuarios().add(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }

    @Override
    public void removerUsuario(AnalistaSistemasModel usuario) {
        lista.getListaUsuarios().remove(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }
    @Override
    public List<AnalistaSistemasModel> getListaUsuarios() {
        return lista.getListaUsuarios();
    }

    public AnalistaSistemasModel getUsuarioPorNome(String nome) {
        for (AnalistaSistemasModel programador : lista.getListaUsuarios()) {
            if (programador.getNome().equals(nome)) {
                return programador;
            }
        }
        return null;
    }

    public void listarAnalistaSistemasDisponivel() {
        Collection<AnalistaSistemasModel> programadores = lista.getListaUsuarios();
        for (AnalistaSistemasModel programador : programadores) {
            System.out.println(programador.getNome());
        }
    }
}

