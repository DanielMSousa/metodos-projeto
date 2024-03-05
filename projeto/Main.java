import src.usuarios.*;
import java.util.List;

public class Main{
    public static void main(String[] args){

        // Criando novos usuários
        Usuario usuario1 = Usuario.criarNovoUsuario(1,"Daniel","Gerente");
        Usuario usuario2 = Usuario.criarNovoUsuario(2,"Melo","Programador");
        
        // Acessando a lista de usuários
        List<Usuario> listaUsuarios = Usuario.getUsuarios();

        // Exibindo informações dos usuários
        for (Usuario usuario : listaUsuarios){
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Nivel de Acesso: " + usuario.getNivelAcesso());
            System.out.println("--------------------------");

        }
    }
}