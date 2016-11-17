package ecu.silicon.models.advisors;

import com.badlogic.gdx.graphics.Texture;
import ecu.silicon.SiliconTycoon;

public interface Advisor {
    String getName();
    String getType();
    Texture getImage();

    // Called after constructor during screen open, used for subscribing to eventbusses
    void initSubscribe();
    // Called after constructor on screen close, used for unsubscribing
    void initUnsubscribe();

}
