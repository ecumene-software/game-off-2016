package ecu.silicon.models.tiles.image;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.models.tiles.TileMap;

public class NATextureSource implements TileTextureSource {
    public TextureRegion[] textures;
    public int             neighbors;

    public NATextureSource(TextureRegion[] textures){
        this.textures = textures;
    }

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    public int getNeighbors(){
        return this.neighbors;
    }

    @Override
    public TextureRegion get(TileMap map, int tileX, int tileY) {
        return textures[neighbors];
    }
}
