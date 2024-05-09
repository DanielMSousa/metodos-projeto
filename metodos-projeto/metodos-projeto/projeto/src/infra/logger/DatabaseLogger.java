package infra.logger;

import java.util.Date;

public class DatabaseLogger {
    public DatabaseLogger(){
        // this.database
    }

    public void escreveLog(String text, Date date) {
        //database.insert(text, date, 0)
    }
    
    public void escreveInfo(String text, Date date) {
        //database.insert(text, date, 1)
    }

    public void escreveWarn(String text, Date date) {
        //database.insert(text, date, 2)
    }

    public void escreveError(String text, Date date) {
        //database.insert(text, date, 3)
    }
}
