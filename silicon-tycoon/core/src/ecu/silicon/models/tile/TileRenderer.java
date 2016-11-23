package ecu.silicon.models.tile;

import com.badlogic.gdx.graphics.g2d.Batch;

public class TileRenderer {
    public TileMap map;
    public TileDB  tileDB;

    public TileRenderer(TileMap map, TileDB db){
        this.map = map;
        this.tileDB = db;
    }

    public void render(Batch batch, int screenspaceTileSize, int mapx, int mapy){
        batch.begin();
        for(int x = 0; x < map.getTiles().length; x++)
            for(int y = 0; y < map.getTiles()[0].length; y++)
                batch.draw(tileDB.getBy(map.getTiles()[x][y].getTexture()),
                        mapx+(x*screenspaceTileSize), // Tile X
                        mapy+(y*screenspaceTileSize), // Tile Y
                        screenspaceTileSize,          // Tile width
                        screenspaceTileSize);         // Tile height
        batch.end();
    }

}
