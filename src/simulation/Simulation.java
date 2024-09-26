package simulation;

import entity.Herbivore;
import entity.Predator;
import entity.Rock;


import java.util.*;

public class Simulation {
    private int worldWidth = 9;
    private int worldLength = 9;
    private Renderer renderer;
    private WorldMap worldMap;

    public void initialize() {
        worldMap = new WorldMap(worldWidth, worldLength);
        worldMap.spawnObject(new Rock(), 8, 8);
//        worldMap.spawnObject(new Predator());
//        worldMap.spawnObject(new Herbivore());
        renderer = new Renderer(worldMap);
    }

    public void nextTurn() {

    }

    public void startSimulation() {
        renderer.renderWorld();
        renderer.renderDeque(breadthFirstSearch(new Coordinates(1, 1), "Rock"));

    }

    public void pauseSimulation() {

    }

    public ArrayDeque<Coordinates> breadthFirstSearch(Coordinates startNode, String className) {
        int[][] visited = new int[worldWidth + 2][worldLength + 2];
        int[][] distance = new int[worldWidth][worldLength];
        var from = new HashMap<Coordinates, Coordinates>();
        for (var i = 0; i < worldWidth; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        for (var i = 1; i < worldWidth+1; i++) {
            for (var j = 1; j < worldLength+1; j++) {
                visited[i][j] = 1;
            }
        }


        Queue<Coordinates> queue = new LinkedList<>();
        visited[startNode.x+1][startNode.y+1] = 0;
        distance[startNode.x][startNode.y] = 0;
        queue.add(startNode);
        from.put(startNode, new Coordinates(-1,-1));

        while (!queue.isEmpty()) {
            //TODO удалить тестовые выводы в методе
//            renderer.renderArray(visited, worldWidth + 2, worldLength + 2);
//            renderer.renderArray(distance, worldWidth, worldLength);
            var current_node = queue.remove();
            var currentNodeObj = worldMap.map.get(current_node);

            //проверка на то что ячейка содержит искомый класс
            if (currentNodeObj != null && currentNodeObj.getClass().getSimpleName().equals(className)) {
                return getPath(from,current_node,from.get(current_node));
            }
            for (var i : new int[]{-1, 1}) {
                var nextX = current_node.x + i;
                var nextY = current_node.y;
                if (visited[nextX + 1][nextY + 1] != 0) {
                    var nextNode = new Coordinates(nextX, nextY);
                    visited[nextX + 1][nextY + 1] = 0;
                    distance[nextX][nextY] = distance[current_node.x][current_node.y] + 1;
                    from.put(nextNode, current_node);
                    queue.add(nextNode);
                }
            }
            for (var i : new int[]{-1, 1}) {
                var nextX = current_node.x;
                var nextY = current_node.y+i;
                if (visited[nextX + 1][nextY + 1] != 0) {
                    var nextNode = new Coordinates(nextX, nextY);
                    visited[nextX + 1][nextY + 1] = 0;
                    distance[nextX][nextY] = distance[current_node.x][current_node.y] + 1;
                    from.put(nextNode, current_node);
                    queue.add(nextNode);
                }
            }

        }
        return null;

    }
    public ArrayDeque<Coordinates> getPath(HashMap<Coordinates,Coordinates> from, Coordinates finish, Coordinates finishFrom) {
        var path = new ArrayDeque<Coordinates>();
        var mark = new Coordinates(-1,-1);
        var flag = true;
        path.add(finish);
        var previousNode = from.get(finishFrom);
        while (flag){

            if (previousNode.equals(mark)) {
                flag = false;
                break;
            }
            path.add(previousNode);
            previousNode=from.get(previousNode);
        }
        return path;
    }

    public boolean isNotUsedNode(Coordinates node, Queue<Coordinates> usedNodes) {
        if (usedNodes.contains(node)) {
            return false;
        }
        return true;
    }

    public boolean isOnWorldPlane(Coordinates node) {
        if (node.x + 1 <= worldWidth && node.y + 1 <= worldLength && node.x >= 0 && node.y >= 0) {
            return true;
        }
        return false;
    }

}
