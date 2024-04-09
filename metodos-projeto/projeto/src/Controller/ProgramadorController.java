package src.Controller;

public class ProgramadorController  {
    private static ProgramadorController instance;

    public synchronized static ProgramadorController getInstance(){
        if(instance == null){
            instance = new ProgramadorController();
        }
        return instance;
    }
}
