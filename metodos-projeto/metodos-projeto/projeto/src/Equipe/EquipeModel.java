package src.Equipe;

import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.Gerente.GerenteModel;
import src.Programador.ProgramadorModel;

public class EquipeModel {
    private GerenteModel gerente;
    private AnalistaSistemasModel analistaSistemas;
    private List<ProgramadorModel> listaProgramador;

    public EquipeModel(GerenteModel gerente, List<ProgramadorModel> listaProgramador, AnalistaSistemasModel analistaSistemas) {
        this.gerente = gerente;
        this.listaProgramador = listaProgramador;
        this.analistaSistemas = analistaSistemas;
    }

    // Getter and setter for Gerente
    public GerenteModel getGerente() {
        return gerente;
    }

    public void setGerente(GerenteModel gerente) {
        this.gerente = gerente;
    }

    // Getter and setter for AnalistaSistemas
    public AnalistaSistemasModel getAnalistaSistemas() {
        return analistaSistemas;
    }

    public void setAnalistaSistemas(AnalistaSistemasModel analistaSistemas) {
        this.analistaSistemas = analistaSistemas;
    }

    // Getter and setter for listaProgramador
    public List<ProgramadorModel> getListaProgramador() {
        return listaProgramador;
    }

    public void setListaProgramador(List<ProgramadorModel> listaProgramador) {
        this.listaProgramador = listaProgramador;
    }

}
