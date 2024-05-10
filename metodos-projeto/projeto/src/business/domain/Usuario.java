package domain;

public class Usuario implements usuarioIF{
    private String login;
    private String nome;
    private String senha;

    public Usuario(String nome,String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    public String getLogin(){
        return this.login;
    }
    public String getSenha(){
        return this.senha;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    

    }
        
