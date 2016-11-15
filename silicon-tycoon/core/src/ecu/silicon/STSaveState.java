package ecu.silicon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ecu.silicon.alerts.Alert;

// Serializable class for saving values to disk, contains save-specific info
public class STSaveState {
    public Date lastLoaded;
    public List<Alert> alerts;
    public String username;

    private boolean fromJSON;

    public STSaveState(String username){
        // TODO: Set lastDate to current date
        alerts = new ArrayList<Alert>();
        this.username = username;
        fromJSON = false;
    }

    public boolean isFromJSON() {
        return fromJSON;
    }

    public String getUsername() {
        return username;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
