package infra.logger.Adapters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import infra.logger.DatabaseLogger;

//O fileLogger espera receber um texto, mas recebe texto e DateTime
public class DatabaseLoggerAdapter extends DatabaseLogger implements LoggerAdapter{
    Date formataDataHora(LocalDateTime dataHora){
        ZoneId zoneId = ZoneId.systemDefault();

        Date date = Date.from(dataHora.atZone(zoneId).toInstant());
        return date;
    }

    public void log(String text, LocalDateTime dataHora){
        Date data = formataDataHora(dataHora);

        escreveLog(text, data);
    }

    public void info(String text, LocalDateTime dataHora){
        Date data = formataDataHora(dataHora);

        escreveInfo(text, data);
    }

    public void warn(String text, LocalDateTime dataHora){
        Date data = formataDataHora(dataHora);

        escreveWarn(text, data);
    }

    public void error(String text, LocalDateTime dataHora){
        Date data = formataDataHora(dataHora);

        escreveError(text, data);
    }
}
