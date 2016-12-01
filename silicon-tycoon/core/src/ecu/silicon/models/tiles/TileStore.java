package ecu.silicon.models.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TileStore {
    public Texture tiles_construction;

    public Map regions;

    public TextureRegion[] na_beigeWalls;
    public TextureRegion[] na_greyWalls;
    public TextureRegion[] na_brickWalls;
    public TextureRegion[] t_glassDoor;
    public TextureRegion[] t_greenFloor;
    public TextureRegion[] t_blueFloor;
    public TextureRegion[] t_tileFloor;
    public TextureRegion[] t_greyFloor;
    public TextureRegion[] t_empty;

    public TileStore(){
        tiles_construction = new Texture("tiles/tiles_construction.png");

        tiles_construction.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        tiles_construction.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        na_beigeWalls = new TextureRegion[16];
        for(int i = 0; i < na_beigeWalls.length; i++) na_beigeWalls[i] = new TextureRegion(tiles_construction, 1, 1+(32 * i), 30, 30);
        na_greyWalls  = new TextureRegion[16];
        for(int i = 0; i < na_greyWalls.length; i++) na_greyWalls[i] = new TextureRegion(tiles_construction, 33, 1+(32 * i), 30, 30);
        na_brickWalls = new TextureRegion[16];
        for(int i = 0; i < na_brickWalls.length; i++) na_brickWalls[i] = new TextureRegion(tiles_construction, 65, 1+(32 * i), 30, 30);
        t_glassDoor  = new TextureRegion[]{new TextureRegion(tiles_construction, 481, 1, 30, 30)};
        t_greenFloor = new TextureRegion[]{new TextureRegion(tiles_construction, 449, 33, 30, 30)};
        t_blueFloor  = new TextureRegion[]{new TextureRegion(tiles_construction, 449, 1, 30, 30)};
        t_tileFloor  = new TextureRegion[]{new TextureRegion(tiles_construction, 449, 65, 30, 30)};
        t_greyFloor  = new TextureRegion[]{new TextureRegion(tiles_construction, 449, 97, 30, 30)};
        t_empty      = new TextureRegion[]{new TextureRegion(tiles_construction, 481, 481, 30, 30)};

        regions = new HashMap<String, TextureRegion[]>();
        regions.put("na_wall_beige", na_beigeWalls);
        regions.put("na_wall_grey",  na_greyWalls);
        regions.put("na_wall_brick", na_brickWalls);
        regions.put("t_greenFloor",  t_greenFloor);
        regions.put("t_blueFloor",   t_blueFloor);
        regions.put("t_tileFloor",   t_tileFloor);
        regions.put("t_greyFloor",   t_greyFloor);
        regions.put("",              t_empty);
    }

    public TextureRegion[] getRegionByName(String name){
        return (TextureRegion[]) regions.get(name);
    }

    public Map getRegions() {
        return regions;
    }

    public void dispose(){

    }

}
