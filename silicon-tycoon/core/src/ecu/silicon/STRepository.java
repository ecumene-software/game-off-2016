package ecu.silicon;

import com.badlogic.gdx.graphics.Texture;

public class STRepository {
    public Texture logo;
    public Texture server_background;
    public Texture grass_background;

    public void loadAll(){
        logo = new Texture("logo.png");
        server_background = new Texture("server-rack.png");
        grass_background = new Texture("grass.png");
    }

    public void dispose(){
        logo.dispose();
        server_background.dispose();
        grass_background.dispose();
    }
}
