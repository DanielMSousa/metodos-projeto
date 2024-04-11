package src.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioService  {

    public static String status = "Não Conectou...";
    private Connection connection;
    private String serverName;
    private String myDataBase;
    private String username;
    private String password;

    public UsuarioService(String serverName, String myDataBase, String username, String password) {
        this.serverName = serverName;
        this.myDataBase = myDataBase;
        this.username = username;
        this.password = password;
        this.connection = getConexaoSQL();
    }

    public Connection getConexaoSQL() {
        Connection connection = null; // atributo Connection

        try {
            
            // Conectando ao JDBC padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            
            Class.forName(driverName);

            // Configurando conexão ao banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + myDataBase;
            connection = DriverManager.getConnection(url, username, password);
            status = "Conectado com sucesso!";
        } catch (ClassNotFoundException e) {
            status = "Driver não encontrado " + e.getMessage();
        } catch (SQLException e) {
            status = "Erro ao conectar: " + e.getMessage();
        }
        return connection;
    }

    public boolean inserirUsuario(String nome, String nivelAcesso,String login,String senha){
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO usuarios (nome,nivelAcesso,login,senha) VALUES (?,?,?,?)")){
           
            stmt.setString(1, nome);
            stmt.setString(2, nivelAcesso);
            stmt.setString(3, login);
            stmt.setString(4, senha);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected >0;
        }catch(SQLException e){
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }

}
