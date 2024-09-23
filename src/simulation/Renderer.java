package simulation;


import java.util.HashMap;

public class Renderer {
    private WorldMap worldMap;
    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    public void renderWorld(){
        for(var i = 0;i<worldMap.width;i++){
            for(int j = 0;j<worldMap.height;j++){
                var pos = new Coordinates(i,j);
                if(worldMap.map.get(pos)==null){
                    System.out.print(" "+i+j+" ");
                }else{
                    System.out.print(worldMap.map.get(pos).image);
                };
            }
            System.out.println();
        }
    }

}
