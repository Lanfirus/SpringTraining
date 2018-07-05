package training.spring.logger;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import training.spring.Event;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

@Component
public class FileEventLogger implements EventLogger{

    private String fileName;
    private File file;

    @Autowired
    public FileEventLogger(@Value("logger.log") String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event){
        try {
            FileUtils.writeStringToFile(file, event.toString(), "UTF-8", true);
        }
        catch (IOException e){}
    }

    @PostConstruct
    private void init() throws IOException{
        this.file = new File(fileName);
        if(!this.file.canWrite()){
            throw new IOException();
        }
    }
}
