package ecu.silicon.models.tile;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class TileMap {
    private Tile[][] tileMap;
    public Bus mapBus;

    public TileMap(){
    }

    public TileMap(int sizeX, int sizeY){
        tileMap = new Tile[sizeX][sizeY];
        mapBus = new Bus(ThreadEnforcer.ANY);
    }

    public void setAt(int x, int y, Tile tile){
        if(tileMap[x][y] != null) mapBus.post(new TileMapEvent("TILE-REMOVED", tileMap[x][y]));
        tileMap[x][y] = tile;
        mapBus.post(new TileMapEvent("TILE-SET", tile));
    }

    public Tile[][] getTiles(){
        return tileMap;
    }
}
