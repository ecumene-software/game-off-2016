package ecu.silicon.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.widget.VisWindow;
import ecu.silicon.util.Easing;

public class AdviceWindow extends VisWindow {

    public AdviceWindow(String title, Vector2 starting, Vector2 ending){
        super(title);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(Gdx.graphics.getWidth()/2 - getWidth()/2, Gdx.graphics.getHeight() - getHeight());
    }
}
