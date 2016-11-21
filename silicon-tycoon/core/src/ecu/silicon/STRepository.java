package ecu.silicon;

import com.badlogic.gdx.graphics.Texture;

public class STRepository {
    public Texture logo;
    public Texture server_background;
    public Texture buildings_background;
    public Texture play_button;
    public Texture quit_button;
    public Texture saves_button;
    public Texture advisor_legal;
    public Texture advisor_buisness;
    public Texture advisor_tech;
    public Texture silicoin;
    public Texture whitedot;
    public Texture greydot;
    public Texture slow;
    public Texture quick;
    public Texture pause;
    public Texture temp_tile_test;

    public void loadAll(){
        logo                  = new Texture("gui/logo.png");
        server_background     = new Texture("gui/menu/server-rack.png");
        buildings_background  = new Texture("gui/menu/buildings.png");
        play_button           = new Texture("gui/menu/play.png");
        saves_button          = new Texture("gui/menu/saves.png");
        quit_button           = new Texture("gui/menu/quit.png");
        advisor_legal         = new Texture("advisors/legal.png");
        advisor_buisness      = new Texture("advisors/buisness.png");
        advisor_tech          = new Texture("advisors/tech.png");
        silicoin              = new Texture("silicoin.png");
        whitedot              = new Texture("white-dot.png");
        greydot               = new Texture("grey-dot.png");
        slow                  = new Texture("slow.png");
        quick                 = new Texture("quick.png");
        pause                 = new Texture("pause.png");
        temp_tile_test        = new Texture("tiles/test.png");

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
        advisor_buisness.dispose();
        advisor_tech.dispose();
        advisor_legal.dispose();
        silicoin.dispose();
        whitedot.dispose();
        greydot.dispose();
        temp_tile_test.dispose();
    }
}
