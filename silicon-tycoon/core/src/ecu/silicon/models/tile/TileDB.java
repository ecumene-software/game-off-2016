package ecu.silicon.models.tile;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by mh on 11/21/16.
 */
public class TileDB {
    public HashMap<String, Texture> jsonToTexture;

    public TileDB(){
        jsonToTexture = new HashMap<String, Texture>();
    }

    public Texture getBy(String json){
        return jsonToTexture.get(json);
    }

    public HashMap<String, Texture> getJsonToTexture() {
        return jsonToTexture;
    }
}
