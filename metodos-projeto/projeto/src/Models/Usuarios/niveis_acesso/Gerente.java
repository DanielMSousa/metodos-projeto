package src.Models.Usuarios.niveis_acesso;
import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;
public class Gerente extends UsuarioAbstrato {
    public Gerente(int id, String nome){
        super(id,nome,"Gerente");
    }

    //métodos para a classe gerente
}