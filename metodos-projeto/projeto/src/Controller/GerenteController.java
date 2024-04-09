package src.Controller;
public class GerenteController extends UsuarioController {
    private static GerenteController instance;

    public synchronized static GerenteController getInstance(){
        if(instance == null){
            instance = new GerenteController();
        }
        return instance;
    } 
    
}
