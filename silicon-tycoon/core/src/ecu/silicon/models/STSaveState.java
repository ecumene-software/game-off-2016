package ecu.silicon.models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import ecu.silicon.alerts.Alert;
import org.apache.commons.io.FileUtils;

// Serializable class for saving values to disk, contains save-specific info
public class STSaveState {
    private static Json json = new Json();

    public Date lastLoaded;
    public List<Alert> alerts;
    public String username;

    @Deprecated
    public STSaveState(){
        alerts = new ArrayList<Alert>();
    }

    public STSaveState(String username){
        this();
        // TODO: Set lastDate to current date
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public String toJSON(){
        return json.prettyPrint(this);
    }

    public static STSaveState fromJSON(String data){
        return json.fromJson(STSaveState.class, data);
    }

    public static STSaveState fromJSON(File file) throws IOException {
        return fromJSON(FileUtils.readFileToString(file, Charset.forName("UTF-8")));
    }
}
