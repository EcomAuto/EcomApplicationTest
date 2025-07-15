package logsUtil;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;

public class LogUtil {
    private static final String logDir = "logs";
    private static final ConcurrentHashMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public static Logger getTestCaseLogger(String className, String methodName) {
        String loggerKey = className + "." + methodName;

        if (loggerMap.containsKey(loggerKey)) {
            return loggerMap.get(loggerKey);
        }

        try {
            Files.createDirectories(Paths.get(logDir));
        } catch (Exception e) {
            System.err.println("Failed to create log directory: " + e.getMessage());
        }

        String logFile = logDir + "/" + className + "_" + methodName + ".log";

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();

        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{HH:mm:ss} %-5p %c{1} - %m%n")
                .withConfiguration(config)
                .build();

        FileAppender appender = FileAppender.newBuilder()
                .setName(loggerKey + "Appender")
                .withFileName(logFile)
                .setLayout(layout)
                .setConfiguration(config)
                .build();
        appender.start();

        AppenderRef ref = AppenderRef.createAppenderRef(loggerKey + "Appender", null, null);
        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, loggerKey,
                "true", new AppenderRef[]{ref}, null, config, null);
        loggerConfig.addAppender(appender, null, null);

        config.addLogger(loggerKey, loggerConfig);
        ctx.updateLoggers();

        Logger logger = LogManager.getLogger(loggerKey);
        loggerMap.put(loggerKey, logger);
        return logger;
    }
}
