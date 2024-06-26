package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Projeto implements ProjetoIF {
    private int id;
    private String nome;
    private Map<String, List<UsuarioProjeto>> usuariosPorProjeto;

    public Projeto(int id,String nome) {
        this.id = id;
        this.nome = nome;
        this.usuariosPorProjeto = new HashMap<>();
    }
    @Override
    public int getId(){
        return this.id;
    }
    @Override
    public String getNome() {
        return this.nome;
    }
    @Override
    public Map<String, List<UsuarioProjeto>> getUsuariosPorProjeto() {
        return this.usuariosPorProjeto;
    }
    @Override
    public void adicionarUsuario(UsuarioProjeto usuario, String tipo) {
        // Verifica se já existe uma lista de usuários para o tipo especificado
        if (!usuariosPorProjeto.containsKey(tipo)) {
            usuariosPorProjeto.put(tipo, new ArrayList<>());
        }
        // Adiciona o usuário à lista correspondente ao tipo
        usuariosPorProjeto.get(tipo).add(usuario);
    }
    @Override
    public List<UsuarioProjeto> getUsuariosPorTipo(String tipo) {
        return usuariosPorProjeto.getOrDefault(tipo, new ArrayList<>());
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
}
