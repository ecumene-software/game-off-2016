package ecu.silicon;

import com.badlogic.gdx.graphics.Texture;

public class STRepository {
    public Texture logo;
    public Texture server_background;
    public Texture grass_background;
    public Texture play_button;
    public Texture continue_button;
    public Texture quit_button;

    public void loadAll(){
        logo = new Texture("logo.png");

        server_background = new Texture("server-rack.png");
        grass_background  = new Texture("grass.png");
        play_button       = new Texture("play.png");
        continue_button   = new Texture("continue.png");
        quit_button       = new Texture("quit.png");


        server_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        grass_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void dispose(){
        logo.dispose();
        server_background.dispose();
        grass_background.dispose();
    }
}
