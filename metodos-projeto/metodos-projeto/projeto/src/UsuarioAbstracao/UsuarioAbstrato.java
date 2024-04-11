package src.UsuarioAbstracao;

public abstract class UsuarioAbstrato implements UsuarioInterface{
    
    private String nome;
    private String nivelAcesso;
    private String login;
    private String senha;
    private boolean disponivel;

    //array para armazenar os usuários criados

    public UsuarioAbstrato(String nome, String nivelAcesso){
        this.login = null;
        this.senha = null;
        this.nome = nome;
        this.disponivel = true;
        this.nivelAcesso = nivelAcesso;

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
    public void setLogin(String login){
        this.login = login;
       
    }
    @Override
    public void setSenha(String senha){
        this.senha = senha;
    }
    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getSenha() {
        return senha;
    }
    @Override
    public boolean getDisponivel() {
        return disponivel ;   }
    @Override
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
        
}