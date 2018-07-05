package training.spring.logger;

import org.springframework.stereotype.Component;

import training.spring.Event;

@Component
public class ConsoleEventLogger implements EventLogger{

    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}