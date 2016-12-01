package ecu.silicon.gui.tile;

import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.tiles.TileMap;
import ecu.silicon.models.tiles.image.NATextureSource;
import ecu.silicon.models.tiles.image.TileTextureSource;
import ecu.silicon.models.tiles.image.TileTextureSourceImpl;

public class RenderableTileMap {
    public RenderableTile[][] map;
    public TileMap parent;

    public RenderableTileMap(TileMap parent){
        this.parent = parent;
        rebuildAll();
    }

    public void updateTile(int x, int y){
        TileTextureSource construction;
        TileTextureSource floor;
        if(parent.getTiles()[x][y].constructionName.contains("na")){
            construction = new NATextureSource(SiliconTycoon.getInstance().repository.tile_store.getRegionByName(parent.getTiles()[x][y].constructionName));

            int neighbor = 0;
            if (parent.safeGet(x - 1, y) != null && parent.safeGet(x - 1, y).constructionName.contains("na")) neighbor += 8;
            if (parent.safeGet(x + 1, y) != null && parent.safeGet(x + 1, y).constructionName.contains("na")) neighbor += 2;
            if (parent.safeGet(x, y - 1) != null && parent.safeGet(x, y - 1).constructionName.contains("na")) neighbor += 4;
            if (parent.safeGet(x, y + 1) != null && parent.safeGet(x, y + 1).constructionName.contains("na")) neighbor += 1;
            ((NATextureSource)construction).neighbors = neighbor;
        }
        else                                                 construction = new TileTextureSourceImpl(SiliconTycoon.getInstance().repository.tile_store.getRegionByName(parent.getTiles()[x][y].constructionName)[0]);
        if(parent.getTiles()[x][y].floorName.contains("na")) floor        = new NATextureSource      (SiliconTycoon.getInstance().repository.tile_store.getRegionByName(parent.getTiles()[x][y].floorName));
        else                                                 floor        = new TileTextureSourceImpl(SiliconTycoon.getInstance().repository.tile_store.getRegionByName(parent.getTiles()[x][y].floorName)[0]);
        map[x][y] = new RenderableTile(construction, floor);
    }

    public void rebuildAll(){
        map = new RenderableTile[parent.getTiles().length][parent.getTiles()[0].length];
        for(int x = 0; x < parent.getTiles().length; x++)
            for(int y = 0; y < parent.getTiles()[0].length; y++)
                if(parent.getTiles()[x][y] != null)
                    updateTile(x, y);
    }
}
