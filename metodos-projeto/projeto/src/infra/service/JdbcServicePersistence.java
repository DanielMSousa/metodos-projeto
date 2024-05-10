package infra.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import domain.UserFactory;
import domain.UserFactoryIF;
import domain.Cartao;
import domain.CartaoUsuario;
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
    public int criarProjeto(String nomeProjeto) {
        String sql = "INSERT INTO projeto (nome) VALUES (?)";
        int projetoId = -1; // Valor padrão para indicar falha na inserção
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, nomeProjeto);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 1) {
                // Se a inserção for bem-sucedida, obtenha o ID gerado
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        projetoId = generatedKeys.getInt(1);
                    } else {
                        // Caso o ID não seja gerado, imprima um aviso
                        System.err.println("Falha ao obter o ID do projeto gerado.");
                    }
                }
            } else {
                // Caso nenhuma linha seja afetada, imprima um aviso
                System.err.println("Nenhuma linha afetada ao inserir o projeto.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }  
        
        return projetoId; // Retornar o ID do projeto (ou -1 se falhar)
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
    public void removerUsuarioProjeto(UsuarioProjeto usuario, int idProjeto) {
        String sql = "DELETE FROM usuarioProjeto WHERE usuario = ? AND projeto = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuarioLogin());
            stmt.setInt(2, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }
   
    @Override
    public UsuarioProjeto adionarUsuarioProjeto(UsuarioIF usuario, int idProjeto, String nomeFuncao) throws TipoUsuarioInvalidoException {
        UserFactory factoryUser = UserFactory.getInstance();
        UsuarioProjeto usuarioProjeto = null;

        try {
            // Consulta SQL para obter o ID da função com base no nome fornecido
            String sqlConsultaFuncao = "SELECT id FROM funcao WHERE nome = ?";

            // Conexão com o banco de dados
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmtConsultaFuncao = conn.prepareStatement(sqlConsultaFuncao)) {
                
                // Definindo o nome da função na consulta SQL
                stmtConsultaFuncao.setString(1, nomeFuncao);

                // Executando a consulta e obtendo o resultado
                try (ResultSet rs = stmtConsultaFuncao.executeQuery()) {
                    if (rs.next()) {
                        int idFuncao = rs.getInt("id");

                        // Se a função for encontrada, execute a operação SQL para adicionar o usuário ao projeto
                        String sqlAdicionarUsuarioProjeto = "INSERT INTO usuarioProjeto (usuario, projeto, funcao) VALUES (?, ?, ?)";
                        try (PreparedStatement stmtAdicionarUsuarioProjeto = conn.prepareStatement(sqlAdicionarUsuarioProjeto)) {
                            stmtAdicionarUsuarioProjeto.setString(1, usuario.getLogin());
                            stmtAdicionarUsuarioProjeto.setInt(2, idProjeto);
                            stmtAdicionarUsuarioProjeto.setInt(3, idFuncao);
                            
                            // Executando a inserção
                            stmtAdicionarUsuarioProjeto.executeUpdate();

                            // Criando o objeto UsuarioProjeto após a inserção bem-sucedida
                           usuarioProjeto = factoryUser.getProjectUser(nomeFuncao, usuario.getLogin(), idProjeto);

                           return usuarioProjeto;
                        }
                    } else {
                        // Lidar com a situação em que a função não é encontrada
                        throw new TipoUsuarioInvalidoException("Função não encontrada: " + nomeFuncao);
                    }
                }
            }
        } catch (SQLException e) {
            // Lidar com exceções de SQL, se necessário
            e.printStackTrace();
        }

        return usuarioProjeto;
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
    public void addUsuarioCartao(Cartao idCartao, UsuarioProjeto atribuinte) {
        String sql = "INSERT INTO cartaoUsuario (cartao, usuario) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCartao.getId());
            stmt.setInt(2, atribuinte.getId()); // Suponha que você tenha um método getId() na classe UsuarioProjeto para obter o ID do usuário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lide com exceções de SQL aqui, se necessário
        }
    }
    @Override
    public void updateStatusCartao(int idCartao, String novoStatus) {
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
    public void createCartao(Cartao novCartao) {
        String sql = "INSERT INTO cartoes (nome, texto, status, kanban) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novCartao.getNome());
            stmt.setString(2, novCartao.getTexto());
            stmt.setString(3, "Em andamento");
            stmt.setInt(4, novCartao.getKanban()); // Supondo que você tenha um método getId() na classe Kanban para obter o ID do kanban
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

    @Override
    public void removeCartaoUsuario(UsuarioProjeto usuarioProjeto, CartaoUsuario cartaoUsuario) {
        String sql = "DELETE FROM cartaoUsuario WHERE usuario = ? AND cartao = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioProjeto.getId()); // Supondo que você tenha um método getUsuario() na classe UsuarioProjeto para obter o usuário associado
            stmt.setInt(2, cartaoUsuario.getId()); // Supondo que você tenha um método getId() na classe CartaoUsuario para obter o ID do cartão de usuário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
    }

    @Override
    public UsuarioProjeto getUsuarioProjetoPorId(String idUsuarioProjeto, int idProjeto) throws TipoUsuarioInvalidoException {
        String sql = "SELECT * FROM usuarioProjeto WHERE id = ? AND projeto = ?";
        UsuarioProjeto usuarioProjeto = null;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuarioProjeto);
            stmt.setInt(2, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se o usuário do projeto for encontrado, crie um objeto UsuarioProjeto com os dados do ResultSet e retorne
                    String login = rs.getString("usuario");
                    int idFuncao = rs.getInt("funcao");
                    String nomeFuncao = consultarNomeFuncaoPorId(idFuncao);

                    UserFactoryIF userFactory = UserFactory.getInstance();
                    usuarioProjeto = userFactory.getProjectUser(nomeFuncao, login, idProjeto);
                } else {
                    // Se nenhum usuário for encontrado, você pode lançar uma exceção ou retornar um valor padrão
                    throw new TipoUsuarioInvalidoException("Nenhum usuário encontrado com o ID fornecido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }

        return usuarioProjeto;
    }

    @Override
    public String consultarNomeFuncaoPorId(int idFuncao) {
        String nomeFuncao = null;
        String sqlConsultaFuncao = "SELECT nome FROM funcao WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sqlConsultaFuncao)) {
            stmt.setInt(1, idFuncao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeFuncao = rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
        
        return nomeFuncao;
    }

    @Override
    public UsuarioIF getUsuarioPorId(String idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        UsuarioIF usuario = null;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se o usuário for encontrado, crie um objeto Usuario com os dados do ResultSet e retorne
                    String nome = rs.getString("nome");
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    UserFactoryIF userFactory = UserFactory.getInstance(); 
                    usuario = userFactory.getSystemUser(nome, login, senha);
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções de SQL, se necessário
        }
        
        return usuario;
    }

}
