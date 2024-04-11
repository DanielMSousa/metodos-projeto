package src.Equipe;

import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.Gerente.GerenteModel;
import src.Programador.ProgramadorModel;

public class EquipeController {
    private static EquipeController instance;
    private static EquipeLista equipeLista;

    private EquipeController(){
        equipeLista =EquipeLista.getInstance();
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
        equipeLista.adicionarEquipe(equipe);
        return equipe;
    }

    public List<EquipeModel> obterEquipes(){
        return equipeLista.getListaEquipes();
    }

}
