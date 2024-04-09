package src.Controller;

import java.util.List;

import src.Models.Usuarios.Conjunto.Equipe;
import src.Models.Usuarios.niveis_acesso.AnalistaSistemas;
import src.Models.Usuarios.niveis_acesso.Gerente;
import src.Models.Usuarios.niveis_acesso.Programador;

public class EquipeController {
    private static EquipeController instance;

    public synchronized static  EquipeController getInstance() {
        if(instance == null){
            instance = new EquipeController();
        }
        return instance;
    }

    // Método para criar uma nova equipe
    public Equipe criarEquipe(Gerente gerente, List<Programador> listaProgramador, AnalistaSistemas analistaSistemas) {
        Equipe equipe = new Equipe(gerente, listaProgramador, analistaSistemas);
        return equipe;
    }

}
