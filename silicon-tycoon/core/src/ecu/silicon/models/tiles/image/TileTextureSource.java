package ecu.silicon.models.tiles.image;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.models.tiles.TileMap;

public interface TileTextureSource {
    TextureRegion get(TileMap map, int tileX, int tileY);
}
