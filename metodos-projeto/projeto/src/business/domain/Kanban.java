package domain;

public class Kanban {
    private int id; // integer pk
    private int projeto; // integer (foreign key referencing projeto.id)
    private String nome; // string

    // Constructor
    public Kanban(int id, int projeto, String nome) {
        this.id = id;
        this.projeto = projeto;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getProjeto() {
        return projeto;
    }
}