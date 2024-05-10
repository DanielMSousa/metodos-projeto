package domain;
import java.util.List;
import java.util.Map;

public interface ProjetoIF {
    int getId();

    String getNome();

    Map<String, List<UsuarioProjeto>> getUsuariosPorProjeto();

    void adicionarUsuario(UsuarioProjeto usuario, String tipo);
    void setId(int id);
    List<UsuarioProjeto> getUsuariosPorTipo(String tipo);
}
