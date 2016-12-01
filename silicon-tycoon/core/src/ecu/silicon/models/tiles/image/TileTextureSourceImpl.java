package ecu.silicon.models.tiles.image;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.models.tiles.TileMap;

public class TileTextureSourceImpl implements TileTextureSource {
    public TextureRegion region;

    public TileTextureSourceImpl(TextureRegion region){
        this.region = region;
    }

    @Override
    public TextureRegion get(TileMap map, int tileX, int tileY) {
        return region;
    }
}
