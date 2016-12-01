package ecu.silicon.models.tiles;

import ecu.silicon.SiliconTycoon;
import ecu.silicon.gui.tile.TileMapControl;

public abstract class TileMapObject {
    public TileMapObject(){
    }

    public TileMapControl getTileMapControl(){
        return SiliconTycoon.getInstance().gameScreen.getTileMapControl();
    }
}
