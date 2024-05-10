package controller;

public class GerenteController {
    
    private static GerenteController instance;
    private String type;
    private GerenteController(String bdType){
        this.type = bdType;
    }

    public static synchronized GerenteController getInstance(String bdType){
        if(instance == null){
            instance = new GerenteController(bdType);
        }
        return instance;
    }

    // public Projeto adicionarUsuarioAoProjeto(Projeto projeto,UsuarioIF usuario,String tipo){
    //     projeto.adicionarUsuario(usuarioTipo, tipo);
    //     return projeto;
    // }
  

}
