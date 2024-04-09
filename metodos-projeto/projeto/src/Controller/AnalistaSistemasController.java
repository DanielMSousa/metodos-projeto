package src.Controller;

public class AnalistaSistemasController extends UsuarioController{
    private static AnalistaSistemasController instance;

    public synchronized static AnalistaSistemasController getInstance(){
        if(instance == null){
            instance = new AnalistaSistemasController();
        }
        return instance;
    }
}
