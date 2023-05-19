public class Vertex implements Comparable<Vertex> {
    public String name;
    public int saturationDeg = 0;
    public int uncoloredDeg = 0;
    public int colorIndex = -1;
    public Vertex(String n) {
        name = n;
    }

    @Override
    public int compareTo(Vertex o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
