package ecu.silicon.models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import ecu.silicon.models.alerts.Alert;
import org.apache.commons.io.FileUtils;

// Serializable class for saving values to disk, contains save-specific info
public class STSaveState {
    private static Json json = new Json();
    public String saveName;

    public Date lastLoaded;
    public List<Alert> alerts;
    public String username;

    // Yes, this is the field you're looking for. You dirty cheater ;)
    public int money;

    public float stepTime;
    public float stepMul;
    public boolean quickTime;
    public boolean pauseTime;

    @Deprecated
    public STSaveState(){
        alerts = new ArrayList<Alert>();
    }

    public STSaveState(String username, String saveName){
        this();
        // TODO: Set lastDate to current date
        this.username = username;
        // TODO: Test write permissions to saveName
        this.saveName = saveName;
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

    public STSaveState timestamp(){
        this.lastLoaded = new Date();
        return this;
    }

    public static STSaveState fromJSON(String data){
        return json.fromJson(STSaveState.class, data);
    }

    public static STSaveState fromJSON(File file) throws IOException {
        return fromJSON(FileUtils.readFileToString(file, Charset.forName("UTF-8")));
    }

    // TODO: Test write permissions to saveName

    public static void save(STSaveState state) throws IOException {
        STSaveWriter writer = new STSaveWriter(state.saveName);
        writer.write(state);
    }
}
