package src.Programador.Listas;

import java.util.Collection;
import java.util.List;

import src.Programador.ProgramadorModel;
import src.UsuarioAbstracao.Listas.UsuarioDisponivelLista;

public class ControllerProgramadoresDisponiveis extends UsuarioDisponivelLista<ProgramadorModel> {
    private static ControllerProgramadoresDisponiveis instance;
    private ProgramadoresDisponiveis lista; // Variável para armazenar a instância da lista

    private ControllerProgramadoresDisponiveis() {
        lista = ProgramadoresDisponiveis.getInstance(null); // Inicializa a lista, se ainda não estiver inicializada
    }

    public static synchronized ControllerProgramadoresDisponiveis getInstance() {
        if (instance == null) {
            instance = new ControllerProgramadoresDisponiveis();
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

    public ProgramadorModel getUsuarioPorNome(String nome) {
        for (ProgramadorModel programador : lista.getListaUsuarios()) {
            if (programador.getNome().equals(nome)) {
                return programador;
            }
        }
        return null;
    }

    @Override
    public List<ProgramadorModel> getListaUsuarios() {
        // TODO Auto-generated method stub
        return lista.getListaUsuarios();
    }

    public void listarProgramadoresDisponiveis() {
        Collection<ProgramadorModel> programadores = lista.getListaUsuarios();
        for (ProgramadorModel programador : programadores) {
            System.out.println(programador.getNome());
        }
    }
}

