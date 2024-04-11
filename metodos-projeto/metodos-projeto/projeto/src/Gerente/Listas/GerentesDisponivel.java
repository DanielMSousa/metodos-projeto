package src.Gerente.Listas;

import java.util.ArrayList;
import java.util.List;

import src.Gerente.GerenteModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class GerentesDisponivel extends UsuarioListaModel<GerenteModel> {
    private static GerentesDisponivel instance;
    private GerentesDisponivel(List<GerenteModel> listaGerentes) {
        super(listaGerentes);
    }

    public static synchronized GerentesDisponivel getInstance(List<GerenteModel> listaGerentes) {
        if (instance == null) {
            if (listaGerentes == null) {
                listaGerentes = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new GerentesDisponivel(listaGerentes);
        }
        return instance;
    }
}
