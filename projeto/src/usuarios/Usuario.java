package src.usuarios;
import java.util.ArrayList;
import java.util.List;
import src.usuarios.niveis_acesso.*;

public abstract class Usuario{
    private int id;
    private String nome;
    private String nivelAcesso;

    //array para armazenar os usuários criados
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(int id,String nome, String nivelAcesso){
        this.id=id;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;

        //Adiciona o usuário ao array quando criado
        usuarios.add(this);
    }

    //Método para manipulação do array de usuários 
    public static List<Usuario> getUsuarios(){
        return usuarios;
    }

    // Método para criar novos usuarios e adicioná-los no array
    public static Usuario criarNovoUsuario(int id, String nome, String nivel_acesso){
        Usuario novo_usuario = null;
        if(nivel_acesso.equals("Gerente")){
            novo_usuario = new Gerente(id,nome);
        }else if (nivel_acesso.equals("Programador")){
            novo_usuario = new Programador(id,nome);
        }
        return novo_usuario;
    }

    //Método para listar usuários
    public static void listarUsuarios(){
        for(Usuario usuario: usuarios){
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Nivel de Acesso: " + usuario.getNivelAcesso());
            System.out.println("--------------------------");

        }
    }
    //Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNivelAcesso(){
        return this.nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }
}