package src.AnalistaSistemas.Listas;

import java.util.ArrayList;
import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class AnalistaSistemasDisponivel extends UsuarioListaModel<AnalistaSistemasModel> {
    private static AnalistaSistemasDisponivel instance;
    private AnalistaSistemasDisponivel(List<AnalistaSistemasModel> listaGerentes) {
        super(listaGerentes);
    }

    public static synchronized AnalistaSistemasDisponivel getInstance(List<AnalistaSistemasModel> listaGerentes) {
        if (instance == null) {
            if (listaGerentes == null) {
                listaGerentes = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new AnalistaSistemasDisponivel(listaGerentes);
        }
        return instance;
    }
}
