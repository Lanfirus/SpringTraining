package training.spring.logger;

import training.spring.Event;

public class ConsoleEventLogger implements EventLogger{

    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
