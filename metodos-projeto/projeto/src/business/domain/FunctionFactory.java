package domain;

import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class FunctionFactory {
    public static FuncaoIF getUserFunction(String tipo) throws TipoUsuarioInvalidoException {
        if ("gerente".equalsIgnoreCase(tipo)) {
            return new Gerente(1);
        } else if ("programador".equalsIgnoreCase(tipo)) {
            return new Programador(2);
        } else if ("analista".equalsIgnoreCase(tipo)) {
            return new AnalistaSistemas(3);
        } else {
            throw new TipoUsuarioInvalidoException("Tipo de usuário inválido");
        }
    }
}
