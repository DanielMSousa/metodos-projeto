package src.Controle;
import java.util.ArrayList;
import java.util.List;

import modelos.Usuario;
public class UsuarioManager {
    private List<Usuario> usuarios;

    public UsuarioManager(){
        this.usuarios = new ArrayList<>();
    }
    //metodo para adicionar usuario
    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
        System.out.println("Usuário adicionado " + usuario.getNome());
    }
    //metodo para listar usuarios
    public void listarUsuarios(){
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Nível de Acesso: " + usuario.getNivelAcesso());
            System.out.println("--------------------------");
        }
    }
}
