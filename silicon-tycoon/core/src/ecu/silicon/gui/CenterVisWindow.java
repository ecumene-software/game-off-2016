package ecu.silicon.gui;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisWindow;

public class CenterVisWindow extends VisWindow {
    public CloseListener closeListener;
    public CenterVisWindow(String name){
        super(name);
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public CloseListener getCloseListener() {
        return closeListener;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(Gdx.graphics.getWidth()/2 - getWidth()/2, Gdx.graphics.getHeight()/2 - getHeight()/2);
    }

    @Override
    @Deprecated
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    @Override
    @Deprecated
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
    }

    @Override
    protected void close() {
        super.close();
        closeListener.onClose();
    }
}
