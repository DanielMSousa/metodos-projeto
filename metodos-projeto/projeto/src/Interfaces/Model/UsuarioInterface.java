package src.Interfaces.Model;

public interface UsuarioInterface {
    int getId();
    void setId(int id);
    String getNome();
    void setNome(String nome);
    String getNivelAcesso();
    void setNivelAcesso(String NivelAcesso);
    String getLogin();
    boolean setLogin(String login);
    String getSenha();
    boolean setSenha(String senha);

}
