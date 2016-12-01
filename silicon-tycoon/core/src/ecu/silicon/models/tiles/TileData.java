package ecu.silicon.models.tiles;

//TODO: Flags
public class TileData {
    public String constructionName = "";
    public String floorName        = "";

    public TileData(){
    }

    public TileData(String constructionName, String floorName){
        this.constructionName = constructionName;
        this.floorName = floorName;
    }

    public String getConstruction(){
        return constructionName;
    }

    public String getFloor(){
        return floorName;
    }
}
