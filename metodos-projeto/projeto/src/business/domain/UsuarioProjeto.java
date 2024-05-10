package domain;

public class UsuarioProjeto {
    private int id;
    private int projeto;
    private String loginUsuario;
    private FuncaoIF funcao;

    public UsuarioProjeto(int idProjeto,String loginUsuario,FuncaoIF funcao){
        this.projeto = idProjeto;
        this.loginUsuario = loginUsuario;
        this.funcao = funcao;
    }

    public FuncaoIF getFuncao() {
        return funcao;
    }
    public int getProjeto() {
        return projeto;
    }
    public String getUsuarioLogin() {
        return loginUsuario;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFuncao(FuncaoIF funcao) {
        this.funcao = funcao;
    }
}
