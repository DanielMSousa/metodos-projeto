package src.Controller.Facade;

import src.Controller.AnalistaSistemasController;
import src.Controller.EquipeController;
import src.Controller.GerenteController;
import src.Controller.ProgramadorController;
import src.Controller.UsuarioController;

public class UserEquipeFacade {
    static UserEquipeFacade instace;
    AnalistaSistemasController analistaSistemasController;
    EquipeController equipeController;
    GerenteController gerenteController;
    ProgramadorController programadorController;
    UsuarioController usuarioController;

    private UserEquipeFacade(){
        this.analistaSistemasController = AnalistaSistemasController.getInstance();
        this.equipeController = EquipeController.getInstance();
        this.gerenteController = GerenteController.getInstance();
        this.programadorController = ProgramadorController.getInstance();
        this.usuarioController = UsuarioController.getInstance();
    }

    public synchronized UserEquipeFacade getInstance(){
        if(instace == null){
            instace = new UserEquipeFacade();
        }
        return instace;
    }

}
