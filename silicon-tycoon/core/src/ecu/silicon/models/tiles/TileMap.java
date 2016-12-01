package ecu.silicon.models.tiles;

import ecu.silicon.SiliconTycoon;

public class TileMap {
    private TileData[][] tileMap;

    public TileMap(){
    }

    public TileMap(int sizeX, int sizeY){
        this();
        tileMap = new TileData[sizeX][sizeY];
    }

    public void setAt(boolean announce, int x, int y, TileData tile){
        if(tileMap[x][y] != null) SiliconTycoon.getInstance().gameScreen.getBus().post(new TileMapEvent("TILE-REMOVED", tileMap[x][y]));
        tileMap[x][y] = tile;
        if(announce) SiliconTycoon.getInstance().gameScreen.getBus().post(new TileMapEvent("TILE-SET", tile));
    }

    public void setAt(int x, int y, TileData tile){
        setAt(false, x, y, tile);
    }

    public TileData safeGet(int x, int y){
        try{
            return tileMap[x][y];
        } catch (Exception e){}
        return null;
    }

    public TileData[][] getTiles(){
        return tileMap;
    }
}
