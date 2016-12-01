package ecu.silicon.models.tiles;

public class TileMapEvent {
    public Object object;
    public String message;

    public TileMapEvent(String message, Object object){
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}
