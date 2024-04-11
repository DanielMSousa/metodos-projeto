package src.Programador.Listas;

import java.util.Collection;
import java.util.List;

import src.Programador.ProgramadorModel;
import src.UsuarioAbstracao.Listas.UsuariosNaoDisponivelLista;

public class ControllerProgramadoresEmProjeto extends UsuariosNaoDisponivelLista<ProgramadorModel> {
    private static ControllerProgramadoresEmProjeto instance;
    private ProgramadoresEmProjeto lista; // Variável para armazenar a instância da lista

    private ControllerProgramadoresEmProjeto() {
        lista = ProgramadoresEmProjeto.getInstance(null); // Inicializa a lista, se ainda não estiver inicializada
    }

    public static synchronized ControllerProgramadoresEmProjeto getInstance() {
        if (instance == null) {
            instance = new ControllerProgramadoresEmProjeto();
        }
        return instance;
    }

    @Override
    public void adicionarUsuario(ProgramadorModel usuario) {
        lista.getListaUsuarios().add(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }

    @Override
    public void removerUsuario(ProgramadorModel usuario) {
        lista.getListaUsuarios().remove(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }

    @Override
    public List<ProgramadorModel> getListaUsuarios() {
        // TODO Auto-generated method stub
        return lista.getListaUsuarios();
    }
    public ProgramadorModel getUsuarioPorNome(String nome) {
        for (ProgramadorModel programador : lista.getListaUsuarios()) {
            if (programador.getNome().equals(nome)) {
                return programador;
            }
        }
        return null;
    }
    public void listarProgramadoresEmProjeto() {
        Collection<ProgramadorModel> programadores = lista.getListaUsuarios();
        for (ProgramadorModel programador : programadores) {
            System.out.println(programador.getNome());
        }
    }
}

