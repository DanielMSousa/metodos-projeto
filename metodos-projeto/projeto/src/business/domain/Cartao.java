package domain;

public class Cartao {
    private int id; // integer pk
    private int status; // integer
    private int kanban; // integer (foreign key referencing kanban.id)
    private String nome; // string
    private String texto; // text

    // Constructor
    public Cartao(int id, int status, int kanban, String nome, String texto) {
        this.id = id;
        this.status = status;
        this.kanban = kanban;
        this.nome = nome;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }
    public int getKanban() {
        return kanban;
    }
    public String getNome() {
        return nome;
    }
    public int getStatus() {
        return status;
    }
    public String getTexto() {
        return texto;
    }
}