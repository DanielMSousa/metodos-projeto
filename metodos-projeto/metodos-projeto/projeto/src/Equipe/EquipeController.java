package Equipe;

import java.sql.Connection;
import java.util.List;

import AnalistaSistemas.AnalistaSistemasModel;
import Gerente.GerenteModel;
import Programador.ProgramadorModel;

public class EquipeController {
    private static EquipeController instance;
    private Connection connection;


    private EquipeController(Connection connection){
        this.connection = connection;
    }
    public synchronized static  EquipeController getInstance(Connection connection) {
        if(instance == null){
            instance = new EquipeController(connection);
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
