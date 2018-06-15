package training.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import training.spring.logger.EventLogger;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, String msg){
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = eventLogger;
        }
        Event event = (Event)ctx.getBean(Constants.EVENT);
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext(Constants.SPRING_FILE);
        App app = (App) ctx.getBean(Constants.APP);
        app.logEvent(EventType.INFO, "Some event for user 1");
        app.logEvent(EventType.ERROR, "Some event for user 2");
        ctx.close();
    }
}
