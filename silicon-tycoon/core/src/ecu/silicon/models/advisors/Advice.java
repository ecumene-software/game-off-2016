package ecu.silicon.models.advisors;

public class Advice {
    public Advisor parent;
    public String message;

    public Advice(Advisor parent, String message){
        this.parent = parent;
        this.message = message;
    }
}
