package domain;

public interface usuarioIF {
    String getNome();
    String getLogin();
    String getSenha();
    void setNome(String nome);
    void setLogin(String login);
    void setSenha(String senha);
}
