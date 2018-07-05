package training.spring.logger;

import training.spring.Event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CombinedEventLogger implements EventLogger{

    private List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(@Value("consoleEventLogger, fileEventLogger") List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(EventLogger logger : loggers){
            logger.logEvent(event);
        }
    }
}