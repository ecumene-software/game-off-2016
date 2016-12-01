package ecu.silicon.gui.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.tiles.TileData;
import ecu.silicon.models.tiles.TileMap;

public class TileMapRenderer implements InputProcessor, TileMapRenderable {

    private TileMap map;
    private RenderableTileMap renderableMap;

    private int mouseX, mouseY;
    private int mapMouseX, mapMouseY;
    private boolean mouseDown;

    public BuyableTile selectedBuyable;

    public TileMapRenderer(TileMap map){
        this.map = map;
        mouseDown = false;
        renderableMap = new RenderableTileMap(map);
    }

    @Override
    public void render(Batch batch, int mapx, int mapy, float tileSize, TileMap tileMap) {
        mapMouseX = ((int)(mouseX * SiliconTycoon.getInstance().gameScreen.getCamera().zoom) - mapx) / getMapControl().tileSize;
        mapMouseY = ((int)(mouseY * SiliconTycoon.getInstance().gameScreen.getCamera().zoom) - mapy) / getMapControl().tileSize;

        batch.begin();
        for(int x = 0; x < map.getTiles().length; x++)
            for(int y = 0; y < map.getTiles()[0].length; y++) {
                if (map.getTiles()[x][y] != null) {
                    batch.draw(renderableMap.map[x][y].floor       .get(map, x, y), mapx + (x * tileSize), mapy + (y * tileSize), tileSize, tileSize);
                    batch.draw(renderableMap.map[x][y].construction.get(map, x, y), mapx + (x * tileSize), mapy + (y * tileSize), tileSize, tileSize);
                    if(selectedBuyable != null){
                        if(selectedBuyable.getConstructionPreview() != null){
                            Sprite sprite = null;
                            if (mapMouseX == x && mapMouseY == y) {
                                sprite = new Sprite(selectedBuyable.getConstructionPreview());
                                sprite.setColor(0.9f, 0.9f, 0.9f, 0.5f);
                                sprite.setX(mapx + (x * tileSize));
                                sprite.setY(mapy + (y * tileSize));
                            }
                            if (sprite != null) sprite.draw(batch);
                        } else if(selectedBuyable.getFloorPreview() != null){
                            Sprite sprite = null;
                            if (mapMouseX == x && mapMouseY == y) {
                                sprite = new Sprite(selectedBuyable.getFloorPreview());
                                sprite.setColor(0.9f, 0.9f, 0.9f, 0.5f);
                                sprite.setX(mapx + (x * tileSize));
                                sprite.setY(mapy + (y * tileSize));
                            }
                            if (sprite != null) sprite.draw(batch);
                        }
                    }

                    Sprite sprite = null;
                    if (mapMouseX == x && mapMouseY == y) {
                        sprite = new Sprite(renderableMap.map[x][y].construction.get(map, x, y));
                        sprite.setColor(0.9f, 0.9f, 0.9f, 1f);
                        sprite.setX(mapx + (x * tileSize));
                        sprite.setY(mapy + (y * tileSize));
                    }
                    if (sprite != null) sprite.draw(batch);
                }
            }
        batch.end();

        mapMouseX = MathUtils.clamp(mapMouseX, 0, map.getTiles().length-1);
        mapMouseY = MathUtils.clamp(mapMouseY, 0, map.getTiles()[0].length-1);
    }

    private void buyAtMouse(){
        if(mouseDown) {
            if(map.getTiles()[mapMouseX][mapMouseY] == null) map.getTiles()[mapMouseX][mapMouseY] = new TileData();
            if(selectedBuyable != null) selectedBuyable.buyAndApply(map.getTiles()[mapMouseX][mapMouseY]);
        }
        this.renderableMap.rebuildAll();
    }

    private void destroyAtMouse(){
        if(mouseDown) map.getTiles()[mapMouseX][mapMouseY].constructionName = "";
        renderableMap.rebuildAll();
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseX = screenX - (Gdx.graphics.getWidth()/2);
        mouseY = (Gdx.graphics.getHeight()/2) - screenY;

        return false;
    }

    private TileMapControl getMapControl(){
        return SiliconTycoon.getInstance().gameScreen.getTileMapControl();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    int button;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.button = button;
        mouseDown = true;
        if(button == Input.Buttons.RIGHT)
            buyAtMouse();
        if(button == Input.Buttons.LEFT)
            destroyAtMouse();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseX = screenX - (Gdx.graphics.getWidth()/2);
        mouseY = (Gdx.graphics.getHeight()/2) - screenY;
        if(button == Input.Buttons.RIGHT)
            buyAtMouse();
        if(button == Input.Buttons.LEFT)
            destroyAtMouse();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.button = button;
        if(button == Input.Buttons.RIGHT)
            buyAtMouse();
        if(button == Input.Buttons.LEFT)
            destroyAtMouse();
        mouseDown = false;
        return false;
    }
}
