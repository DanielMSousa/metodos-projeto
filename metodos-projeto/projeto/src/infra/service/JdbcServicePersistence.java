package infra.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.UserFactory;
import domain.ProjectFactory;
import domain.ProjetoIF;
import domain.Usuario;
import domain.UsuarioIF;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class JdbcServicePersistence implements ServicePersistenceIF {
    private  final static String URL = "jdbc:mysql://localhost/Trellotion";
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
                    return UserFactory.getSystemUser(login, nome, senha);
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
                     "JOIN usuarioProjeto up ON u.login = up.usuario " +
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
}
