package simulation;

import entity.AbstractEntity;

import java.util.HashMap;

import java.util.Map;
import java.util.Random;

public class WorldMap {
    public HashMap<Coordinates, AbstractEntity> map;
    public int width;
    public int height;
    private Random random;
    public WorldMap(int x, int y) {
        random = new Random();
        map = new HashMap<Coordinates, AbstractEntity>();
        width = x;
        height = y;
        createWorld();
    }
    public void createWorld() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map.put(new Coordinates(x,y),null);
            }
        }
    }
    public void spawnObject(AbstractEntity entity) {
        var x = random.nextInt(width);
        var y = random.nextInt(height);
        var position = new Coordinates(x,y);
        if(map.get(position) != null){
            spawnObject(entity);
        }
        map.put(position,entity);
    }
    public void spawnObject(AbstractEntity entity,int X, int Y) {
        var x = X;
        var y = Y;
        var position = new Coordinates(x,y);
        if(map.get(position) != null){
            spawnObject(entity);
        }
        map.put(position,entity);
    }
    public void MoveEntity(AbstractEntity entity, Coordinates startPosition,Coordinates endPosition) {
        var placer1 = map.get(startPosition);
        var placer2 = map.get(endPosition);
        map.put(startPosition,entity);
        map.put(endPosition,entity);
    }

}
