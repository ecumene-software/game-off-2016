package ecu.silicon.gui.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.tiles.TileData;

public abstract class BuyableTile {
    public int price;
    public String name;
    public String type;

    // TODO: Compare construction name to this name

    public BuyableTile(int price, String name, String type){
        this.price = price;
        this.name  = name;
        this.type  = type;
    }

    public void buyAndApply(TileData data){
        if(shouldApply(data)) {
            applyTo(data);
            buy();
        }
    }

    public abstract void applyTo(TileData data);
    public abstract boolean shouldApply(TileData data);

    public TextureRegion getConstructionPreview(){
        return null;
    }
    public TextureRegion getFloorPreview(){
        return null;
    }

    public void buy(){
        SiliconTycoon.getInstance().gameScreen.getState().money -= price;
    }
    public void sell() { SiliconTycoon.getInstance().gameScreen.getState().money += price; }
}
