package src.Gerente.Listas;

import java.util.ArrayList;
import java.util.List;

import src.Gerente.GerenteModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class GerentesEmProjeto extends UsuarioListaModel<GerenteModel> {
    private static GerentesEmProjeto instance;
    private GerentesEmProjeto(List<GerenteModel> listaGerentes) {
        super(listaGerentes);
    }

    public static synchronized GerentesEmProjeto getInstance(List<GerenteModel> listaGerentes) {
        if (instance == null) {
            if (listaGerentes == null) {
                listaGerentes = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new GerentesEmProjeto(listaGerentes);
        }
        return instance;
    }
}
