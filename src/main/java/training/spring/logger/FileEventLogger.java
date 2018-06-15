package training.spring.logger;

import org.apache.commons.io.FileUtils;
import training.spring.Event;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger{

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event){
        try {
            FileUtils.writeStringToFile(file, event.getMsg(), "UTF-8", true);
        }
        catch (IOException e){}
    }

    private void init() throws IOException{
        this.file = new File(fileName);
        if(!this.file.canWrite()){
            throw new IOException();
        }
    }
}
