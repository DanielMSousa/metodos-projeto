package Loggers;

public class TerminalLogger {
    public void escreveLog(String text) {
        System.out.println(text);
    }
    
    public void escreveInfo(String text) {
        System.out.println("[ INFO ]" + text);
    }

    public void escreveWarn(String text) {
        System.out.println("[ WARN ]" + text);
    }

    public void escreveError(String text) {
        System.err.println(text);
    }
}
