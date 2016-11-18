package ecu.silicon.events;

public class STEvent implements ISTEvent{
    private String message;

    public STEvent(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
