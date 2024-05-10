package domain;

public abstract class FuncaoSistema implements FuncaoIF {
    private int idFuncao;
    private String nome;

    public FuncaoSistema(int idFuncao, String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getId() {
        return this.idFuncao;
    }

   
}
