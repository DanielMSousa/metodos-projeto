package infra.logger.Adapters;

import java.time.LocalDateTime;

import infra.logger.TerminalLogger;

//O TerminalLogger espera receber um texto, mas recebe texto e DateTime
public class TerminalLoggerAdapter extends TerminalLogger implements LoggerAdapter{
    public void log(String text, LocalDateTime dataHora){
        escreveLog(text);
    }

    public void info(String text, LocalDateTime dataHora){
        escreveInfo(text);
    }

    public void warn(String text, LocalDateTime dataHora){
        escreveWarn(text);
    }

    public void error(String text, LocalDateTime dataHora){
        escreveError(text);
    }
}
