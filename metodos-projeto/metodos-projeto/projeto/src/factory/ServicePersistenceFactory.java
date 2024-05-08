package factory;

import Service.JdbcServicePersistence;
import Service.ServicePersistence;

public class ServicePersistenceFactory {
    public static ServicePersistence criarServicePersistence(String tipo,String squemaName,String loginDb,String passwordDb) {
        if ("jdbc".equals(tipo)) {
            return new JdbcServicePersistence(squemaName,loginDb,passwordDb);
        } else {
            throw new IllegalArgumentException("Tipo de persistência desconhecido: " + tipo);
        }

        //implementar coisas novas
    }
}