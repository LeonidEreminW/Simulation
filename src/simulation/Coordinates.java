package simulation;

public class Coordinates {
    public final int x;
    public final int y;
    public Coordinates( int x1, int y1) {

        this.x = x1;
        this.y = y1;
    }
    @Override
    public boolean equals(Object obj) {
        return obj == this|| (obj instanceof Coordinates) && (x==((Coordinates) obj).x && y==((Coordinates) obj).y);
    }
    @Override
    public int hashCode() {
        return (x+""+y).hashCode();
    }
}
