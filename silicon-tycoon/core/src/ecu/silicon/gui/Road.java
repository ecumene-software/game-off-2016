package ecu.silicon.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.gui.tile.TileMapRenderable;
import ecu.silicon.models.tiles.TileMap;

public class Road implements TileMapRenderable {

    public Texture straight = SiliconTycoon.getInstance().repository.road_middle;
    public Texture corner   = SiliconTycoon.getInstance().repository.road_corner;
    public Texture crossing = SiliconTycoon.getInstance().repository.road_crossing;
    private TextureRegion straightRegion;
    private TextureRegion cornerRegion;

    public Road(){
        straightRegion = new TextureRegion(straight);
        straight.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        straightRegion.setRegion(0, 0, straight.getWidth(), straight.getHeight());
        cornerRegion = new TextureRegion(corner);
        cornerRegion.setRegion(0, 0, corner.getWidth(), corner.getHeight());
        corner.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
    }

    @Override
    public void render(Batch batch, int x, int y, float tileSize, TileMap tileMap){
        batch.begin();

        float width  = tileSize * tileMap.getTiles().length + tileSize;
        float height = tileSize * tileMap.getTiles()[0].length + tileSize;

        float rx = x - tileSize;
        float ry = y - tileSize;

        batch.draw(corner, rx,         ry,          (int)tileSize, (int)tileSize, 0, 0, corner.getWidth(), corner.getHeight(), true, false);
        batch.draw(corner, rx + width, ry + height, (int)tileSize, (int)tileSize, 0, 0, corner.getWidth(), corner.getHeight(), false, true);
        batch.draw(corner, rx + width, ry,          (int)tileSize, (int)tileSize, 0, 0, corner.getWidth(), corner.getHeight(), false, false);
        batch.draw(corner, rx,         ry + height, (int)tileSize, (int)tileSize, 0, 0, corner.getWidth(), corner.getHeight(), true, true);

        for(int tx = 1; tx < tileMap.getTiles().length+1; tx++)  batch.draw(straight, (rx + tx*tileSize), (ry), tileSize, tileSize);
        for(int tx = 1; tx < tileMap.getTiles().length+1; tx++)  batch.draw(straight, (rx + tx*tileSize), (ry+height), tileSize, tileSize);
        //TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation
        for(int ty = 1; ty < tileMap.getTiles()[0].length+1; ty++) batch.draw(straightRegion,  (rx+tileSize), (ry + ty*tileSize),  0, 0, tileSize, tileSize, 1, 1, 90);
        for(int ty = 1; ty < tileMap.getTiles()[0].length+1; ty++) batch.draw(straightRegion,  (rx+width), (ry + (ty+1)*tileSize), 0, 0, tileSize, tileSize, 1, 1, -90);

        batch.end();
    }
}
