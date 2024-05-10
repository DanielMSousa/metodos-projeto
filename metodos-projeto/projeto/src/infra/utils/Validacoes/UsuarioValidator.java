package infra.utils.Validacoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infra.utils.TipoUsuarios;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import infra.utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class UsuarioValidator {

    public static void validarFormatoLogin(String novoLogin) throws LoginInvalidoException{
        String regex = "^(?=[a-zA-Z]{1,12}$)[a-zA-Z].*$";
        Pattern pattern;
        Matcher matcher;
        
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(novoLogin);
        if(!matcher.matches()){
            throw new LoginInvalidoException("O login deve conter ao menos 1 caractere, com no máximo 12 e não pode conter números ou caracteres especiais");
        }
        
    }
    public static void validarLogin(String novoLogin) throws LoginInvalidoException,LoginExisteException{
        try {validarFormatoLogin(novoLogin);}
        catch(LoginInvalidoException e){
            throw new LoginInvalidoException("Formato de Login inválido" + e);
        }
        
    }

    public static void validarSenha(String novaSenha) throws SenhaInvalidaException {
        String regex = "^(?=.*\\d.*\\d)[A-Za-z\\d!@#$%^&*()-+=]{8,20}$";
        Pattern pattern;
        Matcher matcher;
        
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(novaSenha);
            if (!matcher.matches()) {
                throw new SenhaInvalidaException("A senha deve conter letras e ao menos 2 números, pode conter caracteres especiais");
            }
            
    }

    public static TipoUsuarios mapearTipo(String tipo) throws TipoUsuarioInvalidoException {
        if (tipo == null || tipo.isEmpty()) {
            throw new TipoUsuarioInvalidoException("Tipo de usuário não pode ser vazio");
        }

        // Mapeamento da string para o TipoUsuarios correspondente
        switch (tipo.toLowerCase()) {
            case "analista":
                return TipoUsuarios.ANALISTA_DE_SISTEMAS;
            case "gerente":
                return TipoUsuarios.GERENTE;
            case "programador":
                return TipoUsuarios.PROGRAMADOR;
            default:
                throw new TipoUsuarioInvalidoException("Tipo de usuário inválido: " + tipo);
        }
    }
}
