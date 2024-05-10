package controller;


public class ProgramadorController  {
    private static ProgramadorController instance;
    private String type;
    private ProgramadorController(String bdType){
        this.type = bdType;
    }

    public static synchronized ProgramadorController getInstance(String bdType){
        if(instance == null){
            instance = new ProgramadorController(bdType);
        }
        return instance;
    }
}
