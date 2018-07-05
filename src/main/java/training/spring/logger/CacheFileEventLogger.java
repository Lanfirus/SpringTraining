package training.spring.logger;

import training.spring.Event;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheFileEventLogger extends FileEventLogger{

    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    @Autowired
    public CacheFileEventLogger(String fileName, @Value("4") int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache(){
    }
}
