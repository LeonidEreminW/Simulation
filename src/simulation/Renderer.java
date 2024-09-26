package simulation;


import java.util.ArrayDeque;
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
    public void renderArray(int[][] array,int x,int y){
        for(var i = 0;i<x;i++){
            System.out.println();
            for(int j = 0;j<y;j++){
                System.out.print(array[i][j]+" ");
            }
        }
    }
    public void renderDeque(ArrayDeque<Coordinates> deq){
        System.out.println();
        while (!deq.isEmpty()){
            var elem = deq.pollLast();
            System.out.print(elem.x+","+elem.y+" ");
        }
    }

}
