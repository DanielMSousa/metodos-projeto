import src.Controle.UsuarioController;
import src.Fronteira.*;

public class Main{
    public static void main(String[] args){
        UsuarioController usuarioController = new UsuarioController();
        UsuarioFronteira usuarioFronteira = new UsuarioFronteira(usuarioController);

        usuarioFronteira.cadastrarNovoUsuario();
        usuarioFronteira.listarUsuarios();
    }
}