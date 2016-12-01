package ecu.silicon.gui.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import ecu.silicon.models.tiles.TileMap;

public interface TileMapRenderable {
    void render(Batch batch, int x, int y, float tileSize, TileMap tileMap);
}
