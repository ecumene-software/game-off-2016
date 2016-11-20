package ecu.silicon.events;

// Generally, for anything removed.
// For anything added, the object is just sent as an event
public class RemovedEvent {
    public Object object;

    public RemovedEvent(Object obj){
        this.object = obj;
    }

    public Object getObject() {
        return object;
    }
}
