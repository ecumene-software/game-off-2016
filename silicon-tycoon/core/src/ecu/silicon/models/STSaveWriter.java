package ecu.silicon.models;

import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

// Manages save-state mechanics, great for quicksaving
public class STSaveWriter {
    public FileHandle target;

    public STSaveWriter(File target){
        this.target = new FileHandle(target);
    }

    public STSaveWriter(FileHandle target){
        this.target = target;
    }

    public STSaveWriter(String targetPathAbsolute){
        this.target = new FileHandle(new File(targetPathAbsolute));
    }

    public void write(STSaveState state) throws IOException{
        if(target.isDirectory()) throw new IllegalArgumentException("Save file cannot be a directory");
        String realPath = target.path();
        if(!target.file().getName().toLowerCase().endsWith(".json")){
            realPath = realPath + ".json";
        }
        FileUtils.write(new File("saves/" + realPath), state.toJSON(), Charset.forName("UTF-8"));
    }

}
