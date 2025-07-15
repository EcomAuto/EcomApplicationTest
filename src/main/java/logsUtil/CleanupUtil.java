package logsUtil;

import java.io.File;

public class CleanupUtil {
    public static void deleteOldLogs() {
        File logDir = new File("logs");
        if (logDir.exists() && logDir.isDirectory()) {
            for (File file : logDir.listFiles()) {
                if (file.isFile()) file.delete();
            }
        }
    }
}


