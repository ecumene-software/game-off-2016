package ecu.silicon.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class TileMap {
    private ITile[][] tileMap;
    public Bus mapBus;

    public TileMap(int sizeX, int sizeY){
        tileMap = new ITile[sizeX][sizeY];
        mapBus = new Bus(ThreadEnforcer.ANY);
    }

    public void render(Batch batch, int screenspaceTileSize, int mapx, int mapy){
        batch.begin();
            for(int x = 0; x < tileMap.length; x++)
                for(int y = 0; y < tileMap[0].length; y++)
                    batch.draw(tileMap[x][y].getTexture(), mapx+(x*screenspaceTileSize), mapy+(y*screenspaceTileSize), screenspaceTileSize, screenspaceTileSize);
        batch.end();
    }

    public void setAt(int x, int y, ITile tile){
        if(tileMap[x][y] != null) mapBus.post(new TileMapEvent("TILE-REMOVED", tileMap[x][y]));
        tileMap[x][y] = tile;
        mapBus.post(new TileMapEvent("TILE-SET", tile));
    }

    public ITile[][] getTiles(){
        return tileMap;
    }
}
