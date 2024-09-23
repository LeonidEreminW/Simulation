package simulation;

import entity.Herbivore;
import entity.Predator;
import entity.Rock;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
    private int worldWidth = 9;
    private int worldLength = 9;
    private Renderer renderer;
    private WorldMap worldMap;
    public void initialize(){
        worldMap = new WorldMap(worldWidth,worldLength);
//        worldMap.spawnObject(new Rock(),3,3);
//        worldMap.spawnObject(new Predator());
//        worldMap.spawnObject(new Herbivore());
        renderer = new Renderer(worldMap);
    }
    public void nextTurn(){

    }
    public void startSimulation(){
        renderer.renderWorld();
        System.out.println(breadthFirstSearch(new Coordinates(1,1),"Rock"));

    }
    public void pauseSimulation(){

    }
    public boolean breadthFirstSearch(Coordinates startNode, String className){
        int[][] visited = new int[worldWidth+1][worldLength+1];
        int[][] distance = new int[worldWidth][worldLength];
        for (var i = 0; i < worldWidth; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        Queue<Coordinates> queue = new LinkedList<>();

        Queue<Coordinates> usedNodes = new LinkedList<>();

        queue.add(startNode);
        while(!queue.isEmpty()){
            var current_node = queue.remove();
            var currentNodeObj =worldMap.map.get(current_node);
            if (currentNodeObj!=null&& currentNodeObj.getClass().getSimpleName().equals(className)) {
                return true;
            }
            usedNodes.add(current_node);
            var nextNode = new Coordinates(current_node.x+1,current_node.y);
            if(isNotUsedNode(nextNode, usedNodes) &&isOnWorldPlane(nextNode)){
                queue.add(nextNode);
            }
            nextNode = new Coordinates(current_node.x-1,current_node.y);
            if(isNotUsedNode(nextNode, usedNodes) &&isOnWorldPlane(nextNode)){
                queue.add(nextNode);
            }
            nextNode = new Coordinates(current_node.x,current_node.y+1);
            if(isNotUsedNode(nextNode, usedNodes) &&isOnWorldPlane(nextNode)){
                queue.add(nextNode);
            }
            nextNode = new Coordinates(current_node.x,current_node.y-1);
            if(isNotUsedNode(nextNode, usedNodes) &&isOnWorldPlane(nextNode)){
                queue.add(nextNode);
            }


        }
        return false;

    }
    public boolean isNotUsedNode(Coordinates node, Queue<Coordinates> usedNodes){
        if(usedNodes.contains(node)){
            return false;
        }
        return true;
    }
    public boolean isOnWorldPlane(Coordinates node){
        if(node.x+1 <= worldWidth&&node.y+1 <= worldLength&&node.x>=0&&node.y>=0){
            return true;
        }
        return false;
    }

}
