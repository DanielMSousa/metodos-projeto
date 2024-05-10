package domain;
import java.util.List;
import java.util.Map;

public interface ProjetoIF {
    int getId();

    String getNome();

    Map<String, List<FuncaoIF>> getUsuariosPorProjeto();

    void adicionarUsuario(FuncaoIF usuario, String tipo);

    List<FuncaoIF> getUsuariosPorTipo(String tipo);
}
