package training.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import training.spring.logger.EventLogger;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(String msg){
        Event event = (Event)ctx.getBean(Constants.EVENT);
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext(Constants.SPRING_FILE);
        App app = (App) ctx.getBean(Constants.APP);
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
        ctx.close();
    }
}
