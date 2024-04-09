package src.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UsuarioValidator {

    public static boolean validarLogin(String novoLogin) {
        String regex = "^(?=[a-zA-Z]{1,12}$)[a-zA-Z].*$";
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(novoLogin);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("O login deve conter ao menos 1 caractere, com no máximo 12 e não pode conter números ou caracteres especiais");
            }
            return true;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Erro ao compilar a expressão regular: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static boolean validarSenha(String novaSenha) {
        String regex = "^(?=.*\\d.*\\d)[A-Za-z\\d!@#$%^&*()-+=]{8,20}$";
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(novaSenha);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("A senha deve conter letras e ao menos 2 números, pode conter caracteres especiais");
            }
            return true;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Erro ao compilar a expressão regular: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
