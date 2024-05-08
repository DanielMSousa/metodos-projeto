package Usuarios;

import java.util.HashMap;
import java.util.Map;
enum Funcao {
    ADMINISTRADOR,
    MEMBRO,
    VISITANTE
}

public class Usuario{
    private String login;
    private String nome;
    private String senha;
    private Map<String,Funcao> funcoesPorProjeto;

    public Usuario(String nome){
        this.nome = nome;
        this.funcoesPorProjeto = new HashMap<>();
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
        
