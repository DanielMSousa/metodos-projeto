package domain;
import java.util.HashMap;

public abstract class FuncaoSistema implements FuncaoIF {
    private String login;
    private String nome;
    private HashMap<Integer, String> tarefas; // HashMap de tarefas

    public FuncaoSistema(String login, String nome) {
        this.login = login;
        this.nome = nome;
        this.tarefas = new HashMap<>(); // Inicializa o HashMap de tarefas
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

    @Override
    public HashMap<Integer, String> getTarefas() {
        return this.tarefas;
    }

    @Override
    public void adicionarTarefa(int idTarefa, String descricao) {
        this.tarefas.put(idTarefa, descricao);
    }
}
