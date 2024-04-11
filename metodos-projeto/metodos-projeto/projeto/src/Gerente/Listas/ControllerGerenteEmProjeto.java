package src.Gerente.Listas;

import java.util.Collection;
import java.util.List;

import src.Gerente.GerenteModel;
import src.UsuarioAbstracao.Listas.UsuariosNaoDisponivelLista;

public class ControllerGerenteEmProjeto extends UsuariosNaoDisponivelLista<GerenteModel> {
    private static ControllerGerenteEmProjeto instance;
    private GerentesDisponivel lista; // Variável para armazenar a instância da lista

    private ControllerGerenteEmProjeto() {
        lista = GerentesDisponivel.getInstance(null); // Inicializa a lista, se ainda não estiver inicializada
    }

    public static synchronized ControllerGerenteEmProjeto getInstance() {
        if (instance == null) {
            instance = new ControllerGerenteEmProjeto();
        }
        return instance;
    }

    @Override
    public void adicionarUsuario(GerenteModel usuario) {
        lista.getListaUsuarios().add(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }

    @Override
    public void removerUsuario(GerenteModel usuario) {
        lista.getListaUsuarios().remove(usuario);
        lista.setTamanhoLista(lista.getListaUsuarios().size());
    }

    public GerenteModel getUsuarioPorNome(String nome) {
        for (GerenteModel programador : lista.getListaUsuarios()) {
            if (programador.getNome().equals(nome)) {
                return programador;
            }
        }
        return null;
    }
    @Override
    public List<GerenteModel> getListaUsuarios() {
        return lista.getListaUsuarios();
    }

    public void listarGerentesDisponivel() {
        Collection<GerenteModel> programadores = lista.getListaUsuarios();
        for (GerenteModel programador : programadores) {
            System.out.println(programador.getNome());
        }
    }
}

