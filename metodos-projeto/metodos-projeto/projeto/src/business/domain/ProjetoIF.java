package domain;
import java.util.List;
import java.util.Map;

public interface ProjetoIF {
    int getId();

    String getNome();

    Map<String, List<userIF>> getUsuariosPorProjeto();

    void adicionarUsuario(userIF usuario, String tipo);

    List<userIF> getUsuariosPorTipo(String tipo);
}
