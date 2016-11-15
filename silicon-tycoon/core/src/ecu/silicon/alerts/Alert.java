package ecu.silicon.alerts;

public class Alert {
    public String message;

    @Deprecated
    public Alert(){}

    public Alert(String message){
        this.message = message;
    }

    public enum AlertLevel{
        BIG, MEDIUM, SMALL
    }

    @Override
    public String toString() {
        return message;
    }
}
