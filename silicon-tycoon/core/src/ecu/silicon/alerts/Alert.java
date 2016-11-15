package ecu.silicon.alerts;

public class Alert {
    public String message;

    public Alert(String message){
        this.message = message;
    }

    public enum AlertLevel{
        BIG, MEDIUM, SMALL
    }
}
