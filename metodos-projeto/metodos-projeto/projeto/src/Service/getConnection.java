package Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class getConnection  {

    public static String status = "Não Conectou...";
    
    public static Connection getConexaoSQL(String serverName,String dataBaseName,String username, String password) {
        Connection connection = null; // atributo Connection

        try {
            // Conectando ao JDBC padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            
            Class.forName(driverName);

            // Configurando conexão ao banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + dataBaseName;
            connection = DriverManager.getConnection(url, username, password);
            status = "Conectado com sucesso!";
        } catch (ClassNotFoundException e) {
            status = "Driver não encontrado " + e.getMessage();
        } catch (SQLException e) {
            status = "Erro ao conectar: " + e.getMessage();
        }
        return connection;
    }

}
