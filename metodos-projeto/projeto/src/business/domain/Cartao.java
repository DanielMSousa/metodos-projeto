package domain;

public class Cartao {
    private int id = 0; // integer pk
    private String status; // integer
    private int kanban; // integer (foreign key referencing kanban.id)
    private String nome; // string
    private String texto; // text

    // Constructor
    private Cartao( String status, int kanban, String nome, String texto) {
        this.status = status;
        this.kanban = kanban;
        this.nome = nome;
        this.texto = texto;
    }

    public static Cartao getCartao(String status, int idKanban,String nome,String texto){
        return new Cartao(status, idKanban, nome, texto);
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
    public String getStatus() {
        return status;
    }
    public String getTexto() {
        return texto;
    }
    public void setId(int id) {
        this.id = id;
    }
}