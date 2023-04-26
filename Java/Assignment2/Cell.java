public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(((Cell) obj).value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }
}
