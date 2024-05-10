package domain;

public class Kanban {
    private int id = 0;
    private int idProjeto; // integer (foreign key referencing idProjeto.id)
    private String nome; // string
    
    // Constructor
    private Kanban( int idProjeto, String nome) {
        this.idProjeto = idProjeto;
        this.nome = nome;
    }

    public static Kanban createKanban(int idProjeto,String nome){
        return new Kanban(idProjeto, nome);
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getidProjeto() {
        return idProjeto;
    }

    public void setId(int id) {
        this.id = id;
    }
}