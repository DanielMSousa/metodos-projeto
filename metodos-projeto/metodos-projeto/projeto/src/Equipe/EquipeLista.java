package src.Equipe;

import java.util.ArrayList;
import java.util.List;

public class EquipeLista {
    private static EquipeLista instance;
    private List<EquipeModel> listaEquipes;

    private EquipeLista() {
        this.listaEquipes = new ArrayList<>();
    }

    // Método para obter a instância única da classe EquipeLista
    public static synchronized EquipeLista getInstance() {
        if (instance == null) {
            instance = new EquipeLista();
        }
        return instance;
    }

    // Método para adicionar uma equipe à lista
    public void adicionarEquipe(EquipeModel equipe) {
        listaEquipes.add(equipe);
    }

    // Método para remover uma equipe da lista
    public void removerEquipe(EquipeModel equipe) {
        listaEquipes.remove(equipe);
    }

    // Método para obter a lista de equipes
    public List<EquipeModel> getListaEquipes() {
        return listaEquipes;
    }

    // Método para definir a lista de equipes
    public void setListaEquipes(List<EquipeModel> listaEquipes) {
        this.listaEquipes = listaEquipes;
    }
}
