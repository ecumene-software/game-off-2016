package ecu.silicon.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.SiliconTycoon;

public class Road {

    public Texture straight = SiliconTycoon.getInstance().repository.road_middle;
    public Texture corner   = SiliconTycoon.getInstance().repository.road_corner;
    public Texture crossing = SiliconTycoon.getInstance().repository.road_crossing;

    public Road(){
    }

    public void render(Batch batch, float x, float y, float width, float height){
        batch.begin();

        //Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY
        //batch.draw(corner, x, y, getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), true, true);
        //batch.draw(corner, x + (getHeight()*width), y + (getHeight()*height), getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), true, true);

        //for(int tx = 0; tx < ( (width>height) ? (width/getHeight())-2 : (width/getHeight()) ); tx++)
        //    for(int ty = 0; ty < ( (width>height) ? (height/getHeight()) : (height/getHeight())-2 ); ty++) {
        //        batch.draw(straight, x + ((tx+((width<height)?0:1)) * getHeight()), y + ((ty+((width>height)?0:1)) * getHeight()));
        //    }

        batch.draw(corner, x, y, getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), true, false);
        batch.draw(corner, x + width, y + height, getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), false, true);
        batch.draw(corner, x + width, y, getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), false, false);
        batch.draw(corner, x, y + height, getHeight(), getHeight(), 0, 0, getHeight(), getHeight(), true, true);

        for(int tx = 1; tx < (width/getHeight()); tx++)  batch.draw(straight, (x + tx*getHeight()), (y));
        for(int tx = 1; tx < (width/getHeight()); tx++)  batch.draw(straight, (x + tx*getHeight()), -(y+getHeight()));
        //TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation
        for(int ty = 1; ty < (height/getHeight()); ty++) batch.draw(new TextureRegion(straight),  (x+getHeight()), (y + ty*getHeight()), 0, 0, getHeight(), getHeight(), 1, 1, 90);
        for(int ty = 1; ty < (height/getHeight()); ty++) batch.draw(new TextureRegion(straight), -(x+getHeight()), (y + (ty+1)*getHeight()), 0, 0, getHeight(), getHeight(), 1, 1, -90);

        batch.end();
    }

     public int getHeight(){
         return straight.getHeight();
     }
}
