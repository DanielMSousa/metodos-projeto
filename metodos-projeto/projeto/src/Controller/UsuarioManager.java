package src.Controller;
import java.util.ArrayList;
import java.util.List;

import src.Models.Usuarios.niveis_acesso.AnalistaSistemas;
import src.Models.Usuarios.niveis_acesso.Gerente;
import src.Models.Usuarios.niveis_acesso.Programador;
import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;
import src.Service.UsuarioService;
public class UsuarioManager {
    private List<UsuarioAbstrato> usuarios;

    public UsuarioManager(){
        this.usuarios = new ArrayList<>();
    }
    //metodo para adicionar usuario
    public void adicionarUsuario(UsuarioAbstrato usuario){
        UsuarioService conexao  = new UsuarioService("localhost:3306", "metodosProj_DB", "root", "Youngmull4!");
        if(usuario instanceof Gerente){

        }
        else if( usuario instanceof Programador){

        }
        else if(usuario instanceof AnalistaSistemas){
            
        }
        usuarios.add(usuario);
        conexao.inserirUsuario(usuario.getNome(), usuario.getNivelAcesso(), usuario.getLogin(), usuario.getSenha());
        System.out.println("Usuário adicionado " + usuario.getNome());
    }
    //metodo para listar usuarios
    public void listarUsuarios(){
        for (UsuarioAbstrato usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Nível de Acesso: " + usuario.getNivelAcesso());
            System.out.println("--------------------------");
        }
    }
}
