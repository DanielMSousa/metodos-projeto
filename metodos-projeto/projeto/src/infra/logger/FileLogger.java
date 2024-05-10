package infra.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FileLogger {
    private static final String LOG_FILE_PATH = "log.txt";

    public void escreveLog(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escreveInfo(String text) {
        escreveLog("[ INFO ]" + text);
    }

    public void escreveWarn(String text) {
        escreveLog("[ WARN ]" + text);
    }

    public void escreveError(String text){
        escreveLog("[ ERRO ] | "+text);
    }
}
