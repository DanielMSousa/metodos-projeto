package src.Programador.Listas;

import java.util.ArrayList;
import java.util.List;

import src.Programador.ProgramadorModel;
import src.UsuarioAbstracao.Listas.UsuarioListaModel;

public class ProgramadoresDisponiveis extends UsuarioListaModel<ProgramadorModel> {
    private static ProgramadoresDisponiveis instance;

    private ProgramadoresDisponiveis(List<ProgramadorModel> listaProgramadores) {
        super(listaProgramadores);
    }

    public static synchronized ProgramadoresDisponiveis getInstance(List<ProgramadorModel> listaProgramadores) {
        if (instance == null) {
            if (listaProgramadores == null) {
                listaProgramadores = new ArrayList<>(); // Inicializa a lista, se ainda não estiver inicializada
            }
            instance = new ProgramadoresDisponiveis(listaProgramadores);
        }
        return instance;
    }
}
