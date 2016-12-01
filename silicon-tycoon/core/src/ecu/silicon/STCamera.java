package ecu.silicon;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class STCamera extends OrthographicCamera {
    public int x, y;

    public STCamera(int width, int height){
        super(width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
