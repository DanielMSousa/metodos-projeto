package src.Models.Usuarios.Conjunto;

import java.util.List;

import src.Models.Usuarios.niveis_acesso.AnalistaSistemas;
import src.Models.Usuarios.niveis_acesso.Gerente;
import src.Models.Usuarios.niveis_acesso.Programador;

public class Equipe {
    private Gerente gerente;
    private AnalistaSistemas analistaSistemas;
    private List<Programador> listaProgramador;

    public Equipe(Gerente gerente, List<Programador> listaProgramador, AnalistaSistemas analistaSistemas) {
        this.gerente = gerente;
        this.listaProgramador = listaProgramador;
        this.analistaSistemas = analistaSistemas;
    }

    // Getter and setter for Gerente
    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    // Getter and setter for AnalistaSistemas
    public AnalistaSistemas getAnalistaSistemas() {
        return analistaSistemas;
    }

    public void setAnalistaSistemas(AnalistaSistemas analistaSistemas) {
        this.analistaSistemas = analistaSistemas;
    }

    // Getter and setter for listaProgramador
    public List<Programador> getListaProgramador() {
        return listaProgramador;
    }

    public void setListaProgramador(List<Programador> listaProgramador) {
        this.listaProgramador = listaProgramador;
    }

}
