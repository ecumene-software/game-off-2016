package ecu.silicon.gui.tile;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.tiles.TileMap;

import java.util.ArrayList;
import java.util.List;

public class TileMapControl extends InputAdapter {
    public TileMap map;
    public List<TileMapRenderable> renderables;
    public int x, y;
    public int tileSize = 30;

    public TileMapControl(TileMap controllable, TileMapRenderable ... renderables){
        this.map = controllable;
        this.renderables = new ArrayList<TileMapRenderable>();
        for(TileMapRenderable renderable : renderables) this.renderables.add(renderable);
    }

    public void render(Batch batch){
        x = SiliconTycoon.getInstance().gameScreen.getCamera().x + (-map.getTiles().length*tileSize)/2;
        y = SiliconTycoon.getInstance().gameScreen.getCamera().y + (-map.getTiles()[0].length*tileSize)/2;

        for(TileMapRenderable renderable : renderables)
            renderable.render(batch, x, y, tileSize, map);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}
