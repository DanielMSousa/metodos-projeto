package infra.utils.Exception.CriacaoLoginSenha;

public class LoginExisteException extends Exception {
    public LoginExisteException(String message){
        super(message);
    }
}
