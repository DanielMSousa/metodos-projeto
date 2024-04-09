import src.Controller.UsuarioController;
import src.View.*;

public class Main{
    public static void main(String[] args){
        UsuarioController usuarioController = new UsuarioController();
        UsuarioFronteira usuarioFronteira = new UsuarioFronteira(usuarioController);

        usuarioFronteira.cadastrarNovoUsuario();
        usuarioFronteira.listarUsuarios();
    }
}