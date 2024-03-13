package modelos;
import java.util.regex.*;


public abstract class Usuario{
    private int id;
    private String nome;
    private String nivelAcesso;
    private String login;
    private String senha;

    //array para armazenar os usuários criados

    public Usuario(int id,String nome, String nivelAcesso){
        this.login = null;
        this.senha = null;
        this.id=id;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
    }

    
    //Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNivelAcesso(){
        return this.nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }

    public boolean setLogin(String login){
        String regex = "^(?=[a-zA-Z]{1,12}$)[a-zA-Z].*$";
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(login);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("O login deve conter ao menos 1 caractere, com no máximo 12 e não pode conter números ou caracteres especiais");
            }
            this.login = login;
            System.out.println("Login válido: " + login);
        } catch (PatternSyntaxException e) {
            System.err.println("Erro ao compilar a expressão regular: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean setSenha(String senha){
        String regex = "^(?=.*\\d.*\\d)[A-Za-z\\d!@#$%^&*()-+=]{8,20}$";
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(senha);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("A senha deve conter letras e ao menos 2 números, pode conter caracteres especiais");
            }
            this.senha = senha;
            System.out.println("Senha válida: " + senha);
        } catch (PatternSyntaxException e) {
            System.err.println("Erro ao compilar a expressão regular: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public String getLogin(){
        if(login != null){
            return login;
        }
        return "Erro";
    }
    public String getSenha(){
        if(senha != null){
            return senha;
        }
        return "Erro";
    }
}