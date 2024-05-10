package infra.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.UserFactory;
import domain.Kanban;
import domain.ProjectFactory;
import domain.ProjetoIF;
import domain.Usuario;
import domain.UsuarioIF;
import domain.UsuarioProjeto;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class JdbcServicePersistence implements ServicePersistenceIF {
    private  final static String URL = "jdbc:mysql://localhost/Trellotion";
    private  final UserFactory userFactory = UserFactory.getInstance();
    private  final String USER;
    private  final String PASSWORD;

    public JdbcServicePersistence(String user, String password){
        this.USER = user;
        this.PASSWORD = password;
    }

    @Override
    public UsuarioIF buscarUsuarioPorLogin(String login) throws TipoUsuarioInvalidoException {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se o usuário for encontrado, crie um objeto Usuario com os dados do ResultSet e retorne
                    String nome = rs.getString("nome");
                    String senha = rs.getString("senha");
                    return userFactory.getSystemUser(login, nome, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }

        return null; // Retorna null se o usuário não for encontrado ou se ocorrer algum erro
    }
    

    @Override
    public void criarUsuario(UsuarioIF usuario) throws LoginExisteException, TipoUsuarioInvalidoException {
        try{
        if (buscarUsuarioPorLogin(usuario.getLogin()) != null) {
            throw new LoginExisteException("Login já existe");
        }}catch(TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }

        String sql = "INSERT INTO usuarios (login, nome, senha) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lida com a exceção de SQL conforme necessário
        }
    }

    @Override
    public void atualizarUsuario(UsuarioIF usuario) {
        if(usuario instanceof Usuario){
        String sql = "UPDATE usuarios SET nome = ?, senha = ? WHERE login = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    }

    @Override
    public void excluirUsuario(String login) throws LoginExisteException, TipoUsuarioInvalidoException {

        try{
            if (buscarUsuarioPorLogin(login) == null) {
                throw new LoginExisteException("Usuario nao encontrado");
            }}catch(TipoUsuarioInvalidoException e){
                throw new TipoUsuarioInvalidoException(e.getMessage());
            }

        String sql = "DELETE INTO usuarios WHERE login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }       
    }

    @Override
    public void criarProjeto(String nomeProjeto) {
        String sql = "INSERT INTO projeto (nome) VALUE(?) ";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }  
        
    }

    @Override
    public String getUsuariosProjeto(int idProjeto) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"projetoId\": ").append(idProjeto).append(",");
        jsonBuilder.append("\"usuarios\": [");

        String sql = "SELECT u.login, u.nome, up.funcao " +
                    "FROM usuarios u " +
                    "JOIN usuariosprojeto up ON u.login = up.usuario " +
                    "WHERE up.projeto = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first) {
                        jsonBuilder.append(",");
                    } else {
                        first = false;
                    }
                    jsonBuilder.append("{");
                    jsonBuilder.append("\"login\": \"").append(rs.getString("login")).append("\",");
                    jsonBuilder.append("\"nome\": \"").append(rs.getString("nome")).append("\",");
                    jsonBuilder.append("\"funcao\": \"").append(rs.getString("funcao")).append("\"");
                    jsonBuilder.append("}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
            return null;
        }

        jsonBuilder.append("]");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }

    @Override
    public void removerUsuarioProjeto(Usuario usuario, int idProjeto) {
        String sql = "DELETE FROM usuarioProjeto WHERE usuario = ? AND projeto = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setInt(2, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    @Override
    public void adionarUsuarioProjeto(Usuario usuario, int idProjeto, String nomeFuncao) {
    // Consulta SQL para obter o ID da função com base no nome fornecido
    String sqlConsultaFuncao = "SELECT id FROM funcao WHERE nome = ?";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmtConsultaFuncao = conn.prepareStatement(sqlConsultaFuncao)) {
        stmtConsultaFuncao.setString(1, nomeFuncao);
        try (ResultSet rs = stmtConsultaFuncao.executeQuery()) {
            if (rs.next()) {
                int idFuncao = rs.getInt("id");
                
                // Se a função for encontrada, execute a operação SQL para adicionar o usuário ao projeto
                String sqlAdicionarUsuarioProjeto = "INSERT INTO usuarioProjeto (usuario, projeto, funcao) VALUES (?, ?, ?)";
                try (PreparedStatement stmtAdicionarUsuarioProjeto = conn.prepareStatement(sqlAdicionarUsuarioProjeto)) {
                    stmtAdicionarUsuarioProjeto.setString(1, usuario.getLogin());
                    stmtAdicionarUsuarioProjeto.setInt(2, idProjeto);
                    stmtAdicionarUsuarioProjeto.setInt(3, idFuncao);
                    stmtAdicionarUsuarioProjeto.executeUpdate();
                }
            } else {
                System.out.println("Função não encontrada: " + nomeFuncao);
                // Lidar com a situação em que a função não é encontrada
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Lidar com exceções de SQL, se necessário
    }
        
    }

    @Override
    public String getUsuarios() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            boolean first = true;
            while (rs.next()) {
                if (!first) {
                    jsonBuilder.append(",");
                } else {
                    first = false;
                }
                jsonBuilder.append("{");
                jsonBuilder.append("\"login\": \"").append(rs.getString("login")).append("\",");
                jsonBuilder.append("\"nome\": \"").append(rs.getString("nome")).append("\",");
                jsonBuilder.append("\"senha\": \"").append(rs.getString("senha")).append("\"");
                jsonBuilder.append("}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
            return null;
        }
    
        jsonBuilder.append("]");
    
        return jsonBuilder.toString();
    }

    @Override
    public void removerProjeto(int idProjeto) {
    String sql = "DELETE FROM projeto WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idProjeto);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Lidar com exceções de SQL, se necessário
    }
    }

    @Override
    public void alterarNomeProjeto(int idProjeto, String novoNome) {
        String sql = "UPDATE projeto SET nome = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }

    @Override
    public ProjetoIF buscarProjetoPorId(int idProjeto) {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        ProjetoIF projeto = null;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nomeProjeto = rs.getString("nome");
                    projeto = ProjectFactory.getProjeto("Criar", idProjeto, nomeProjeto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return projeto;
    }

    @Override
    public String getCartoesUsuario(String loger) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        String sql = "SELECT c.id, c.status, c.nome, c.texto " +
                    "FROM cartoes c " +
                    "JOIN cartaoUsuario cu ON c.id = cu.cartao " +
                    "WHERE cu.usuario = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loger);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first) {
                        jsonBuilder.append(",");
                    } else {
                        first = false;
                    }
                    jsonBuilder.append("{");
                    jsonBuilder.append("\"id\": ").append(rs.getInt("id")).append(",");
                    jsonBuilder.append("\"status\": \"").append(rs.getString("status")).append("\",");
                    jsonBuilder.append("\"nome\": \"").append(rs.getString("nome")).append("\",");
                    jsonBuilder.append("\"texto\": \"").append(rs.getString("texto")).append("\"");
                    jsonBuilder.append("}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
            return null;
        }

        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }
    @Override
    public String getKanban(int idKanban) {
        StringBuilder jsonBuilder = new StringBuilder();
        
        String sql = "SELECT * FROM kanban WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idKanban);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    jsonBuilder.append("{");
                    jsonBuilder.append("\"id\": ").append(idKanban).append(",");
                    jsonBuilder.append("\"nome\": \"").append(rs.getString("nome")).append("\"");
                    jsonBuilder.append("}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
            return null;
        }

        return jsonBuilder.toString();
    }
    @Override
    public void addUsuarioCartao(int idCartao, UsuarioProjeto gerente, UsuarioProjeto atribuinte) {
        String sql = "INSERT INTO cartaoUsuario (cartao, usuario) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCartao);
            stmt.setInt(2, atribuinte.getId()); // Suponha que você tenha um método getId() na classe UsuarioProjeto para obter o ID do usuário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lide com exceções de SQL aqui, se necessário
        }
    }
    @Override
    public void updateStatusCartao(UsuarioProjeto gerente, int idCartao, UsuarioProjeto solicitante, String novoStatus) {
        String sql = "UPDATE cartoes SET status = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idCartao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    @Override
    public void removeCartao(UsuarioProjeto gerente, int idCartao) {
        String sql = "DELETE FROM cartoes WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCartao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    @Override
    public void createCartao(Kanban kanbanAssociado, String nome, String texto) {
        String sql = "INSERT INTO cartoes (nome, texto, status, kanban) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, texto);
            stmt.setString(3, "Em andamento");
            stmt.setInt(4, kanbanAssociado.getId()); // Supondo que você tenha um método getId() na classe Kanban para obter o ID do kanban
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    @Override
    public void createKanban(Kanban kanban) {
        String nomeKanban = kanban.getNome();
        int idProjeto = kanban.getidProjeto();
        String sql = "INSERT INTO kanban (nome, projeto) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeKanban);
            stmt.setInt(2, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
    @Override
    public void updateNomeKanban(int idKanban, String nome) {
        String sql = "UPDATE kanban SET nome = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idKanban);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }

    @Override
    public String getCartoesProjeto(int idProjeto) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        String sql = "SELECT c.id, c.status, c.nome, c.texto " +
                    "FROM cartoes c " +
                    "JOIN kanban k ON c.kanban = k.id " +
                    "WHERE k.projeto = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first) {
                        jsonBuilder.append(",");
                    } else {
                        first = false;
                    }
                    jsonBuilder.append("{");
                    jsonBuilder.append("\"id\": ").append(rs.getInt("id")).append(",");
                    jsonBuilder.append("\"status\": \"").append(rs.getString("status")).append("\",");
                    jsonBuilder.append("\"nome\": \"").append(rs.getString("nome")).append("\",");
                    jsonBuilder.append("\"texto\": \"").append(rs.getString("texto")).append("\"");
                    jsonBuilder.append("}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
            return null;
        }

        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }

}
