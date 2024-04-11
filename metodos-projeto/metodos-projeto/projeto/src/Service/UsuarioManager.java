package src.Service;
import java.util.ArrayList;
import java.util.List;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.Gerente.GerenteModel;
import src.Programador.ProgramadorModel;
import src.UsuarioAbstracao.UsuarioAbstrato;
public class UsuarioManager {
    private List<UsuarioAbstrato> usuarios;

    public UsuarioManager(){
        this.usuarios = new ArrayList<>();
    }
    //metodo para adicionar usuario
    public void adicionarUsuario(UsuarioAbstrato usuario){
        UsuarioService conexao  = new UsuarioService("localhost:3306", "metodosProj_DB", "root", "Youngmull4!");
        if(usuario instanceof GerenteModel){

        }
        else if( usuario instanceof ProgramadorModel){

        }
        else if(usuario instanceof AnalistaSistemasModel){
            
        }
        usuarios.add(usuario);
        conexao.inserirUsuario(usuario.getNome(), usuario.getNivelAcesso(), usuario.getLogin(), usuario.getSenha());
        System.out.println("Usuário adicionado " + usuario.getNome());
    }
    //metodo para listar usuarios
    public void listarUsuarios(){
        for (UsuarioAbstrato usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Nível de Acesso: " + usuario.getNivelAcesso());
            System.out.println("--------------------------");
        }
    }
}
