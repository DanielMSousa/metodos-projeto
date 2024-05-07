package Loggers.Adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Loggers.FileLogger;

//O fileLogger espera receber um texto, mas recebe texto e DateTime
public class FileLoggerAdapter extends FileLogger implements LoggerAdapter{
    String formataDataHora(LocalDateTime dataHora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataFormatada = dataHora.format(formatter);
        return dataFormatada;
    }

    public void log(String text, LocalDateTime dataHora){
        String data = formataDataHora(dataHora);

        escreveLog("[ " + data + " ]" + text);
    }

    public void info(String text, LocalDateTime dataHora){
        String data = formataDataHora(dataHora);

        escreveInfo("[ " + data + " ]" + text);
    }

    public void warn(String text, LocalDateTime dataHora){
        String data = formataDataHora(dataHora);

        escreveWarn("[ " + data + " ]" + text);
    }

    public void error(String text, LocalDateTime dataHora){
        String data = formataDataHora(dataHora);

        escreveError("[ " + data + " ]" + text);
    }
}
