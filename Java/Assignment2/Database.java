@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        return null;
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        return null;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        return 0;
    }
}
