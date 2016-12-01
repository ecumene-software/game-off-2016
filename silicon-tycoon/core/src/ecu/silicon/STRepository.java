package ecu.silicon;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.models.TextureDB;
import ecu.silicon.models.tiles.TileStore;

import java.util.HashMap;

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
    public Texture silicoin_small;
    public Texture whitedot;
    public Texture greydot;
    public Texture slow;
    public Texture quick;
    public Texture pause;

    public Texture construction;
    public Texture enterprise;
    public Texture decor;

    public Texture road_middle;
    public Texture road_crossing;
    public Texture road_corner;

    public TileStore tile_store;

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
        silicoin_small        = new Texture("silicoin-small.png");
        whitedot              = new Texture("white-dot.png");
        greydot               = new Texture("grey-dot.png");
        slow                  = new Texture("slow.png");
        quick                 = new Texture("quick.png");
        pause                 = new Texture("pause.png");

        construction          = new Texture("gui/construction.png");
        enterprise            = new Texture("gui/enterprise.png");
        decor                 = new Texture("gui/decor.png");

        tile_store = new TileStore();

        road_middle           = new Texture("roads/road-straight.png");
        road_corner           = new Texture("roads/road-cross.png");
        road_crossing         = new Texture("roads/road-crossing.png");

        server_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        buildings_background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        road_middle.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
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

        tile_store.dispose();

        road_middle.dispose();
        road_corner.dispose();
        road_crossing.dispose();
    }
}
