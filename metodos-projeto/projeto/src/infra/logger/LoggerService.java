package infra.logger;

import infra.logger.Adapters.LoggerAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import infra.logger.Adapters.DatabaseLoggerAdapter;
import infra.logger.Adapters.FileLoggerAdapter;
import infra.logger.Adapters.TerminalLoggerAdapter;

import infra.utils.Exception.Logging.CategoriaLogInvalida;
import infra.utils.Exception.Logging.LoggerInvalido;


public final class LoggerService {
    private static volatile LoggerService instance;

    private DatabaseLoggerAdapter db_logger;
    private FileLoggerAdapter file_logger;
    private TerminalLoggerAdapter terminal_logger;

    //lista de loggers dividos por tipo
    private List<LoggerAdapter> logAdapters;
    private List<LoggerAdapter> infoAdapters;
    private List<LoggerAdapter> warnAdapters;
    private List<LoggerAdapter> errorAdapters;


    private LoggerService(){
        db_logger = new DatabaseLoggerAdapter();
        file_logger = new FileLoggerAdapter();
        terminal_logger = new TerminalLoggerAdapter();

        this.logAdapters = new ArrayList<>();
        this.infoAdapters = new ArrayList<>();
        this.warnAdapters = new ArrayList<>();
        this.errorAdapters = new ArrayList<>();
    }

    public static LoggerService getInstance(){
        LoggerService result = instance;
        if(result != null){
            return result;
        }
        synchronized(LoggerService.class){
            if(instance == null){
                instance = new LoggerService();
            }
            return instance;
        }
        
    }

    public void addLogType(String tipo, String logger) throws CategoriaLogInvalida, LoggerInvalido{
        List<LoggerAdapter> lista;

        switch (tipo) {
            case "LOG":
                lista = logAdapters;
                break;
            
            case "INFO":
                lista = infoAdapters;
                break;

            case "WARN":
                lista = warnAdapters;
                break;

            case "ERROR":
                lista = errorAdapters;
                break;

            default:
                error("Categoria de log desconhecida: "+tipo);
                throw new LoggerInvalido("Log desconhecido");
        }

        switch (logger) {
            case "db":
                lista.add(db_logger);
                break;
            
            case "file":
                lista.add(file_logger);
                break;

            case "terminal":
                lista.add(terminal_logger);
                break;

            default:
                error("Logger não listado: "+logger);
                throw new CategoriaLogInvalida("Log desconhecido");     
        }
    }

    public void makeLog(String mensagem, List<LoggerAdapter> adapterList, String level){
        LocalDateTime dataHora = LocalDateTime.now();
        for (LoggerAdapter adapter : adapterList) {
            switch (level) {
                case "LOG":
                    adapter.log(mensagem, dataHora);
                    break;
                
                case "INFO":
                    adapter.info(mensagem, dataHora);
                    break;
    
                case "WARN":
                    adapter.warn(mensagem, dataHora);
                    break;
    
                case "ERROR":
                    adapter.error(mensagem, dataHora);
                    break;
    
                default:
                    //lançar exceção
                    adapter.log(mensagem, dataHora);
                    break;
            }
        }
    }

    public void log(String mensagem){
        makeLog(mensagem, logAdapters, "LOG");
    }

    public void info(String mensagem){
        makeLog(mensagem, infoAdapters, "INFO");
    }

    public void warn(String mensagem){
        makeLog(mensagem, warnAdapters, "WARN");
    }

    public void error(String mensagem){
        makeLog(mensagem, errorAdapters, "ERROR");
    }
}
