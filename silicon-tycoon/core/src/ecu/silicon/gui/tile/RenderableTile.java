package ecu.silicon.gui.tile;

import ecu.silicon.models.tiles.image.TileTextureSource;

public class RenderableTile {
    public TileTextureSource floor;
    public TileTextureSource construction;

    public RenderableTile(TileTextureSource construction, TileTextureSource floor){
        this.construction = construction;
        this.floor = floor;
    }

    public TileTextureSource getConstruction() {
        return construction;
    }

    public TileTextureSource getFloor() {
        return floor;
    }
}
