package domain;

import java.util.HashMap;

public interface FuncaoIF {
    String getNome();
    String getLogin();
    
    void setNome(String nome);

    HashMap<Integer, String> getTarefas();
    void adicionarTarefa(int idTarefa, String descricao);


    void setLogin(String login);
}

