import simulation.Coordinates;
import simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        var simulation = new Simulation();
        simulation.initialize();
        simulation.startSimulation();
//3,3 3,1 2,1 1,1
        //2,3 2,1 1,1
        //8,8 8,6 8,5 8,4 8,3 8,2 8,1 7,1 6,1 5,1 4,1 3,1 2,1 1,1
    }
}