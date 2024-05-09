package infra.logger.Adapters;

import java.time.LocalDateTime;

public interface LoggerAdapter {
    public void log(String text, LocalDateTime dataHora);
    public void info(String text, LocalDateTime dataHora);
    public void warn(String text, LocalDateTime dataHora);
    public void error(String text, LocalDateTime dataHora);
}
