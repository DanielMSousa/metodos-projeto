package factory;

import Service.JdbcServicePersistence;
import Service.ServicePersistence;

public class ServicePersistenceFactory {
    public static ServicePersistence criarServicePersistence(String tipo,String usuario,String senha) {
        if ("jdbc".equals(tipo)) {
            return new JdbcServicePersistence(usuario,senha);
        } else {
            throw new IllegalArgumentException("Tipo de persistência desconhecido: " + tipo);
        }

        //implementar coisas novas
    }
}