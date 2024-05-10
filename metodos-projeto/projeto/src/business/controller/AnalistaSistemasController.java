package controller;



public class AnalistaSistemasController{

    private static AnalistaSistemasController instance;
    private String type;
    private AnalistaSistemasController(String bdType){
        this.type = bdType;
    }

    public static synchronized AnalistaSistemasController getInstance(String bdType){
        if(instance == null){
            instance = new AnalistaSistemasController(bdType);
        }
        return instance;
    }
    
}
