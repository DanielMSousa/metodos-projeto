import src.usuarios.*;

public class Main{
    public static void main(String[] args){

        // Criando novos usuários
        Usuario usuario1 = Usuario.criarNovoUsuario(1,"Daniel","Gerente");
        Usuario usuario2 = Usuario.criarNovoUsuario(2,"Melo","Programador");
        
        Usuario.listarUsuarios();
    }
}