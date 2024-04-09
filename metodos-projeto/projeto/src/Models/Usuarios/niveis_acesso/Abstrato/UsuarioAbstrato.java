package src.Models.Usuarios.niveis_acesso.Abstrato;
import src.Interfaces.Model.UsuarioInterface;
import src.Utils.UsuarioValidator;;

public abstract class UsuarioAbstrato implements UsuarioInterface{
    private int id;
    private String nome;
    private String nivelAcesso;
    private String login;
    private String senha;

    //array para armazenar os usuários criados

    public UsuarioAbstrato(int id,String nome, String nivelAcesso){
        this.login = null;
        this.senha = null;
        this.id=id;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
    }

    
    //Getters and Setters
    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void setId(int id){
        this.id = id;
    }
    @Override
    public String getNome(){
        return this.nome;
    }
    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    @Override
    public String getNivelAcesso(){
        return this.nivelAcesso;
    }
    @Override
    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }
    @Override
    public boolean setLogin(String login){
        if(UsuarioValidator.validarLogin(login)){
            this.login = login;
            return true;
        }
        return false;
    }
    @Override
    public boolean setSenha(String senha){
        if(UsuarioValidator.validarSenha(senha)){
            this.senha = senha;
            return true;
        }
        return false;
    }
    @Override
    public String getLogin(){
        if(login != null){
            return login;
        }
        return "Erro";
    }
    @Override
    public String getSenha(){
        if(senha != null){
            return senha;
        }
        return "Erro";
    }
}