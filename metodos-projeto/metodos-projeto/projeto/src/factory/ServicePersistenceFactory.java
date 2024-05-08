package factory;

import Service.JdbcServicePersistence;
import Service.ServicePersistence;

public class ServicePersistenceFactory {
    public static ServicePersistence criarServicePersistence(String tipo,String loginDb,String passwordDb) {
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