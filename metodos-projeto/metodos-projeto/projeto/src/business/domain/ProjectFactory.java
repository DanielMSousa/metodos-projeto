package domain;

public class ProjectFactory {
    public static ProjetoIF getProjeto(String type, int id, String nome) {
        switch (type) {
            case "Criar":
                return new Projeto(id, nome);
            
            default:
                throw new IllegalArgumentException("Tipo de projeto não suportado: " + type);
        }
    }
}