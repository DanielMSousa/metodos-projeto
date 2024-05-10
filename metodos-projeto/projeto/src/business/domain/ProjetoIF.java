package domain;
import java.util.List;
import java.util.Map;

public interface ProjetoIF {
    int getId();

    String getNome();

    Map<String, List<funcaoIF>> getUsuariosPorProjeto();

    void adicionarUsuario(funcaoIF usuario, String tipo);

    List<funcaoIF> getUsuariosPorTipo(String tipo);
}
