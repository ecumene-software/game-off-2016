package ecu.silicon.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import ecu.silicon.SiliconTycoon;

import java.util.HashMap;

public class TextureDB {
    private static Json json = new Json();

    public HashMap<String, TextureRegion> database;

    public TextureDB(){
        this.database = new HashMap<String, TextureRegion>();
        this.database.put("construction", new TextureRegion(SiliconTycoon.getInstance().repository.tile_store.tiles_construction));
        this.database.put("decoration",   new TextureRegion(SiliconTycoon.getInstance().repository.tile_store.tiles_construction));
        this.database.put("enterprise",   new TextureRegion(SiliconTycoon.getInstance().repository.tile_store.tiles_construction));
    }

    public HashMap<String, TextureRegion> getDatabase() {
        return database;
    }

}
