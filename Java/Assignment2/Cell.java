public class Cell implements Comparable<Cell> 
{
    int databaseRow;
    String value;

    public Cell(String value, int databaseRow) 
    {
        this.value = value;
        this.databaseRow = databaseRow;
    }
    @Override
    public int compareTo(Cell o) 
    {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) 
    {
        return value.equals(((Cell) obj).value);
    }

    @Override
    public String toString() 
    {
        return value + "{" + databaseRow + "}";
    }
    public int getRow()
    {
        return databaseRow;
    }
}
