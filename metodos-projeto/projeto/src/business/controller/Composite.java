package controller;
import java.util.ArrayList;
import java.util.List;

// Component
interface ComponenteKanban {
    void adicionar(ComponenteKanban componente);
    void remover(ComponenteKanban componente);
    void exibir();
}

// Composite
class QuadroKanban implements ComponenteKanban {
    private final List<ComponenteKanban> colunas = new ArrayList<>();

    public void adicionar(ComponenteKanban componente) {
        colunas.add(componente);
    }

    public void remover(ComponenteKanban componente) {
        colunas.remove(componente);
    }

    public void exibir() {
        for (ComponenteKanban componente : colunas) {
            componente.exibir();
        }
    }
}

// Composite
class Coluna implements ComponenteKanban {
    private final String nome;
    private final List<ComponenteKanban> tarefas = new ArrayList<>();

    public Coluna(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteKanban componente) {
        tarefas.add(componente);
    }

    public void remover(ComponenteKanban componente) {
        tarefas.remove(componente);
    }

    public void exibir() {
        System.out.println("Coluna: " + nome);
        for (ComponenteKanban componente : tarefas) {
            componente.exibir();
        }
    }
}

// Leaf
class Tarefa implements ComponenteKanban {
    private final String descricao;

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public void adicionar(ComponenteKanban componente) {
        throw new UnsupportedOperationException("Não é possível adicionar a uma tarefa.");
    }

    public void remover(ComponenteKanban componente) {
        throw new UnsupportedOperationException("Não é possível remover de uma tarefa.");
    }

    public void exibir() {
        System.out.println("Tarefa: " + descricao);
    }
}