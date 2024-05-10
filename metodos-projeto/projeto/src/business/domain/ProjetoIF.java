package domain;
import java.util.List;
import java.util.Map;

public interface ProjetoIF {
    int getId();

    String getNome();

    Map<String, List<UsuarioProjeto>> getUsuariosPorProjeto();

    void adicionarUsuario(UsuarioProjeto usuario, String tipo);

    List<UsuarioProjeto> getUsuariosPorTipo(String tipo);
}
