package src.AnalistaSistemas.Listas;

import java.util.ArrayList;
import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class AnalistaSistemasEmProjeto extends UsuarioListaModel<AnalistaSistemasModel> {
    private static AnalistaSistemasEmProjeto instance;
    private AnalistaSistemasEmProjeto(List<AnalistaSistemasModel> listaGerentes) {
        super(listaGerentes);
    }

    public static synchronized AnalistaSistemasEmProjeto getInstance(List<AnalistaSistemasModel> listaGerentes) {
        if (instance == null) {
            if (listaGerentes == null) {
                listaGerentes = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new AnalistaSistemasEmProjeto(listaGerentes);
        }
        return instance;
    }
}
