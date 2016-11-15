package ecu.silicon;

import com.badlogic.gdx.graphics.Texture;

public class STRepository {
    public Texture logo;
    public Texture server_background;
    public Texture buildings_background;
    public Texture play_button;
    public Texture quit_button;
    public Texture saves_button;

    public void loadAll(){
        logo                  = new Texture("gui/logo.png");
        server_background     = new Texture("gui/menu/server-rack.png");
        buildings_background  = new Texture("gui/menu/buildings.png");
        play_button           = new Texture("gui/menu/play.png");
        saves_button          = new Texture("gui/menu/saves.png");
        quit_button           = new Texture("gui/menu/quit.png");

        server_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        buildings_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void dispose(){
        logo.dispose();
        server_background.dispose();
        buildings_background.dispose();
        play_button.dispose();
        quit_button.dispose();
        saves_button.dispose();
    }
}
