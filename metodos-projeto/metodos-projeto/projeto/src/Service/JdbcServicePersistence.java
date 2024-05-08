package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Usuarios.Usuario;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;

public class JdbcServicePersistence implements ServicePersistence {
    private  final String URL;
    private  final String USER;
    private  final String PASSWORD;

    public JdbcServicePersistence(String squemaName, String user, String password){
        this.URL = "jdbc:mysql://localhost/"+squemaName;
        this.USER = user;
        this.PASSWORD = password;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String login) {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se o usuário for encontrado, crie um objeto Usuario com os dados do ResultSet e retorne
                    Usuario usuario = new Usuario(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    // Configure outros atributos conforme necessário
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // Retorna null se o usuário não for encontrado ou se ocorrer algum erro
    }
    

    @Override
    public void criarUsuario(Usuario usuario) throws LoginExisteException {

        if (buscarUsuarioPorLogin(usuario.getLogin()) != null) {
            throw new LoginExisteException("Login já existe");
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
    public void atualizarUsuario(Usuario usuario) {
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

    @Override
    public void excluirUsuario(String login) throws LoginExisteException {
        if(buscarUsuarioPorLogin(login) == null){
            throw new LoginExisteException("Usuário não encontrado");
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
    public void criarProjeto(Usuario usuario, String nomeProjeto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Usuario getUsuariosProjeto(int idProjeto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removerUsuarioProjeto(Usuario usuario, int idProjeto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void adionarUsuarioProjeto(Usuario usuario, int idProjeto, int idFuncao) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atribuirTarefa(Usuario usuario, int idTarefa) {
        // TODO Auto-generated method stub
        
    }
}
