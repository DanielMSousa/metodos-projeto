package Usuarios;

import userInterface.OperadorSistema;

public class Usuario implements OperadorSistema{
    private String login;
    private String nome;
    private String senha;

    public Usuario(String nome,String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    @Override
    public String getLogin(){
        return this.login;
    }
    @Override
    public String getSenha(){
        return this.senha;
    }
    @Override
    public String getNome(){
        return this.nome;
    }
    @Override
    public void setNome(String nome){
        this.nome = nome;
    }
    @Override
    public void setLogin(String login){
        this.login = login;
    }
    @Override
    public void setSenha(String senha){
        this.senha = senha;
    }
    

    }
        
