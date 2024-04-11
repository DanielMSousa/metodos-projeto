package src.Programador.Listas;

import java.util.ArrayList;
import java.util.List;

import src.Programador.ProgramadorModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class ProgramadoresEmProjeto extends UsuarioListaModel<ProgramadorModel> {
    private static ProgramadoresEmProjeto instance;

    private ProgramadoresEmProjeto(List<ProgramadorModel> listaProgramadores) {
        super(listaProgramadores);
    }

    public static synchronized ProgramadoresEmProjeto getInstance(List<ProgramadorModel> listaProgramadores) {
        if (instance == null) {
            if (listaProgramadores == null) {
                listaProgramadores = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new ProgramadoresEmProjeto(listaProgramadores);
        }
        return instance;
    }

}
