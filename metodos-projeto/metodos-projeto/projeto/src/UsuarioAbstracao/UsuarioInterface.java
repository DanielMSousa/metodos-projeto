package src.UsuarioAbstracao;

public interface UsuarioInterface {
    String getNome();
    void setNome(String nome);
    String getNivelAcesso();
    void setNivelAcesso(String NivelAcesso);
    String getLogin();
    void setLogin(String login);
    String getSenha();
    void setSenha(String senha);
    boolean getDisponivel();
    void setDisponivel(boolean diponivel);

}
