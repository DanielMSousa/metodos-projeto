package src.Equipe;

import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.Gerente.GerenteModel;
import src.Programador.ProgramadorModel;

public class EquipeController {
    private static EquipeController instance;

    private EquipeController(){
    }
    public synchronized static  EquipeController getInstance() {
        if(instance == null){
            instance = new EquipeController();
        }
        return instance;
    }

    // Método para criar uma nova equipe
    public EquipeModel criarEquipe(GerenteModel gerente, List<ProgramadorModel> listaProgramador, AnalistaSistemasModel analistaSistemas) {
        EquipeModel equipe = new EquipeModel(gerente, listaProgramador, analistaSistemas);
        return equipe;
    }

    // public List<EquipeModel> obterEquipes(){
    //     //todo
    // }

}
