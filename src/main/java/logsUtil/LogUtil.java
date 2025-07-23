package logsUtil;

import org.apache.logging.log4j.*;
//import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;

import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;

public class LogUtil {
    private static final String LOG_DIR = "logs";
    private static final ConcurrentHashMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public static Logger getTestCaseLogger(String className, String methodName) {
        String loggerKey = className + "." + methodName;

        if (loggerMap.containsKey(loggerKey)) return loggerMap.get(loggerKey);

        try {
            Files.createDirectories(Paths.get(LOG_DIR));
        } catch (Exception e) {
            System.err.println("Failed to create log directory: " + e.getMessage());
        }

        String logFile = LOG_DIR + "/" + className + "_" + methodName + ".log";

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();

        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{HH:mm:ss} %-5p %c{1} - %m%n")
                .withConfiguration(config)
                .build();

        FileAppender fileAppender = FileAppender.newBuilder()
                .setName("FileAppender_" + loggerKey)
                .withFileName(logFile)
                .setLayout(layout)
                .setConfiguration(config)
                .build();
        fileAppender.start();

        ConsoleAppender consoleAppender = ConsoleAppender.newBuilder()
                .setName("ConsoleAppender_" + loggerKey)
                .setLayout(layout)
                .setConfiguration(config)
                .build();
        consoleAppender.start();

        AppenderRef fileRef = AppenderRef.createAppenderRef(fileAppender.getName(), null, null);
        AppenderRef consoleRef = AppenderRef.createAppenderRef(consoleAppender.getName(), null, null);

        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, loggerKey,
                "true", new AppenderRef[]{fileRef, consoleRef}, null, config, null);
        loggerConfig.addAppender(fileAppender, null, null);
        loggerConfig.addAppender(consoleAppender, null, null);

        config.addLogger(loggerKey, loggerConfig);
        ctx.updateLoggers();

        Logger logger = (Logger) LogManager.getLogger(loggerKey);
        loggerMap.put(loggerKey, logger);
        return logger;
    }
}
