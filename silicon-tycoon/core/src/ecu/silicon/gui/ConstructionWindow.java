package ecu.silicon.gui;

import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.widget.VisWindow;
import ecu.silicon.util.Easing;

/**
 * Created by mh on 11/29/16.
 */
public class ConstructionWindow extends VisWindow {

    public Vector2 starting, target;
    public float d;

    private float   time;
    private boolean easing;
    private Vector2 position;

    private boolean disabled; // To tween or not to tween, that is the question

    public ConstructionWindow(String title, Vector2 starting, Vector2 ending){
        super(title);
        this.starting = starting;
        this.target   = ending;
        easing = false;
        d = 0.2f;
    }

    public void bounce(){
        if(easing) time = 0;
        else       easing = true;
        position = new Vector2(starting);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(easing) {
            if(time > d) easing = false;
            time += delta;

            float easingFrame = Easing.easeInLinear(time, d);

            position.x = starting.x + ((target.x - starting.x) * easingFrame);
            position.y = starting.y + ((target.y - starting.y) * easingFrame);

            setX(position.x);
            setY(position.y);
        } else {
            time = 0;
        }
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Vector2 getStarting() {
        return starting;
    }

    public Vector2 getTarget() {
        return target;
    }

    public void setStarting(Vector2 starting) {
        this.starting = starting;
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }

}
