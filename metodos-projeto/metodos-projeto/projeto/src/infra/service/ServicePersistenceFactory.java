package infra.service;

public class ServicePersistenceFactory {
    private static final String loginDb = "SEU LOGIN";
    private static final String passwordDb = "SUA SENHA";
    public static ServicePersistenceIF criarServicePersistence(String tipo) {
        if ("jdbc".equals(tipo)) {
            return new JdbcServicePersistence(loginDb,passwordDb);}
        // } else if ("hibernate".equals(tipo)) {
        //     return new HibernateServicePersistence();
        // } 
        else {
            throw new IllegalArgumentException("Tipo de persistência desconhecido: " + tipo);
        }
    }
}