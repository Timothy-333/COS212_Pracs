@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) 
    {
        database = new String[maxSize][cols.length];
        for(int i = 0; i < maxSize; i++)
        {
            for(int j = 0; j < cols.length; j++)
            {
                database[i][j] = null;
            }
        }
        columnNames = new String[cols.length];
        for(int i = 0; i < columnNames.length; i++)
        {
            columnNames[i] = cols[i];
        }
        indexes = new Treap[cols.length];
        for(int i = 0; i < indexes.length; i++)
        {
            indexes[i] = null;
        }
    }

    public void insert(String[] newRowDetails) throws DatabaseException 
    {
        if(newRowDetails.length != columnNames.length)
        {
            throw DatabaseException.invalidNumberOfColums();
        }
        for(int i = 0; i < newRowDetails.length; i++)
        {
            if(newRowDetails[i] == null)
            {
                throw DatabaseException.invalidColumnName(newRowDetails[i]);
            }
        }
        for(int i = 0; i < columnNames.length; i++)
        {
            Cell newCell = new Cell(newRowDetails[i], database.length - 1);
            if(indexes[i] != null && indexes[i].access(newCell) != null)
            {
                throw DatabaseException.duplicateInsert(newRowDetails[i]);
            }
        }
        boolean full = true;
        for(int i = 0; i < database.length; i++)
        {
            if(database[i][0] == null)
            {
                for(int j = 0; j < newRowDetails.length; j++)
                {
                    Cell newCell = new Cell(newRowDetails[j], i);
                    if(indexes[j] != null)
                    {
                        try
                        {
                            indexes[j].insert(newCell);
                            database[i][j] = newRowDetails[j];
                        }
                        catch(Exception e)
                        {
                            for(int k = 0; k < j; k++)
                            {
                                indexes[k].remove(new Cell(newRowDetails[k], i));
                                database[i][k] = null;
                            }
                            throw e;
                        }
                    }
                    else
                    {
                        database[i][j] = newRowDetails[j];
                    }
                }
                full = false;
                break;
            }
        }
        if(full)
        {
            throw DatabaseException.databaseFull();
        }
    }
    public String[] removeFirstWhere(String col, String data) throws DatabaseException 
    {
        String[] removedRow = new String[0];
        int colIndex = 0;
        while(!columnNames[colIndex].equals(col) && colIndex < columnNames.length)
        {
            colIndex++;
        }
        if(colIndex == columnNames.length)
        {
            throw DatabaseException.invalidColumnName(col);
        }
        int row = 0;
        if(indexes[colIndex] != null)
        {
            Node<Cell> delNode = indexes[colIndex].remove(new Cell(data, 0));
            if(delNode != null)
            {
                row = delNode.getData().getRow();
                removedRow = new String[columnNames.length];
            }
        }
        else
        {
            for(int i = 0; i < database.length; i++)
            {
                if(database[i][colIndex] != null && database[i][colIndex].equals(data))
                {
                    row = i;
                    removedRow = new String[columnNames.length];
                    break;
                }
            }
        }
        if(removedRow.length == 0)
        {
            return removedRow;
        }
        for(int i = 0; i < columnNames.length; i++)
        {
            removedRow[i] = database[row][i];
        }
        for(int i = 0; i < columnNames.length; i++)
        {
            String delData = database[row][i];
            if(delData != null && indexes[i] != null)
            {
                indexes[i].remove(new Cell(delData, 0));
            }
            database[row][i] = null;
        }
        return removedRow;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException 
    {
        while(removeFirstWhere(col, data).length != 0)
        {
            removeFirstWhere(col, data);
        }
        return null;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException 
    {
        String[] foundRow = new String[columnNames.length];
        int colIndex = 0;
        while(!columnNames[colIndex].equals(col) && colIndex < columnNames.length)
        {
            colIndex++;
        }
        if(colIndex == columnNames.length)
        {
            throw DatabaseException.invalidColumnName(col);
        }
        if(indexes[colIndex] != null)
        {
            Node<Cell> foundNode = indexes[colIndex].access(new Cell(data, 0));
            if(foundNode != null)
            {
                int row = foundNode.getData().getRow();
                for(int i = 0; i < columnNames.length; i++)
                {
                    foundRow[i] = database[row][i];
                }
                return foundRow;
            }
        }
        else
        {
            for(int i = 0; i < database.length; i++)
            {
                if(database[i][colIndex].equals(data))
                {
                    for(int j = 0; j < columnNames.length; j++)
                    {
                        foundRow[j] = database[i][j];
                    }
                    return foundRow;
                }
            }
        }
        return foundRow;
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException 
    {
        return null;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException 
    {
        return null;
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException 
    {
        return null;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException 
    {
        int index = 0;
        while(!columnNames[index].equals(col) && index < columnNames.length)
        {
            index++;
        }
        if(index == columnNames.length)
        {
            throw DatabaseException.invalidColumnName(col);
        }
        if(indexes[index] != null)
        {
            return indexes[index];
        }
        Treap<Cell> newTreap = new Treap<>();
        for(int i = 0; i < database.length; i++)
        {
            if(database[i][index] != null)
            {
                Cell newCell = new Cell(database[i][index], i);
                try
                {
                    newTreap.insert(newCell);
                }
                catch(Exception e)
                {
                    newTreap = null;
                    throw e;
                }
            }
        }
        indexes[index] = newTreap;
        return newTreap;
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException 
    {
        for(int i = 0; i < columnNames.length; i++)
        {
            try
            {
                generateIndexOn(columnNames[i]);
            }
            catch(Exception e)
            {
            }
        }
        return indexes;
    }
    public int countOccurences(String col, String data) throws DatabaseException 
    {
        return 0;
    }
}
