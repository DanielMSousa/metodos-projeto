import src.View.UsuarioFronteira;

public class Main {
    public static void main(String[] args) {
        UsuarioFronteira usuarioFronteira = new UsuarioFronteira(); // Passa a fachada para o construtor

        usuarioFronteira.cadastrarNovoUsuario();
    }
}
