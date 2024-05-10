package domain;



public abstract class FuncaoSistema implements funcaoIF{
    private String login;
    private String nome;

    public FuncaoSistema(String login, String nome) {
        this.login = login;
        this.nome = nome;
    }
    @Override
    public String getNome() {

        return this.nome;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    

    @Override
    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
