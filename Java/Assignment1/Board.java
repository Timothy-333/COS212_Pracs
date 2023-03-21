public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString() {
        String res = "";
        for (int r = 0; r < numRows * numCols; r++) {
            if (r % numRows == 0) {
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++) {
                if (c % numCols == 0) {
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine() {
        String res = "";
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++) {
            res += "-";
        }
        return res;
    }

    public void testLinks() {
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++) {
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++) {
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++) {
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells() {
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells) {
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c) {
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++) {
            if (cells[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c) {
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols) {
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
     */

    public Board(int r, int c, String boardString) 
    {
        numRows = r;
        numCols = c;
        cells = new Cell[numRows * numCols * numRows * numCols];
        String[] board = boardString.split(" ");
        for(int i = 0; i < numRows * numCols * numRows * numCols; i++)
        {
            cells[i] = new Cell(r,c,board[i]);
        }
        setLinks();
    }

    public void setLinks() 
    {
        rows = new Cell[numRows * numCols];
        cols = new Cell[numRows * numCols];
        blocks = new Cell[numRows * numCols];
        int blockCounter = 0;
        for(int i = 0; i < numRows * numCols; i++)
        {
            cols[i] = cellAt(0, i);
            rows[i] = cellAt(i, 0);
            for(int j = 0; j < numCols * numRows; j++)
            {
                if(j < numCols * numRows - 1)
                {
                    cellAt(i, j).right = cellAt(i, j + 1);
                }
                if(i < numCols * numRows - 1)
                {
                    cellAt(i, j).below = cellAt(i + 1, j);
                }
                if(j % numCols == 0 && i % numRows == 0)
                {
                    blocks[blockCounter] = cellAt(i, j);
                    for(int k = 0; k < numRows; k++)
                    {
                        for(int l = 0; l < numCols; l++)
                        {
                            if(l < numCols - 1)
                            {
                                cellAt(i + k, j + l).block = cellAt(i + k, j + l + 1);
                            }
                            else if(k < numRows - 1)
                            {
                                cellAt(i + k, j + l).block = cellAt(i + k + 1, j);
                            }
                            cellAt(i+ k, j + l).b = blockCounter;
                        }
                    }
                    blockCounter++;
                }
                cellAt(i, j).r = i;
                cellAt(i, j).c = j;
            }
        }
    }

    public void fullProp() 
    {
        for(int i = 0; i < numRows * numCols * numRows * numCols; i++)
        {
            propCell(cells[i]);
        }
    }

    public void propCell(Cell cell) 
    {
        if(cell.value == null)
        {
            return;
        }
        Cell rowPtr = rows[cell.r];
        Cell colPtr = cols[cell.c];
        Cell blockPtr = blocks[cell.b];
        while(rowPtr != null)
        {
            if(rowPtr.possibleValues != null)
            {
                rowPtr.possibleValues.remove(cell.value);
            }
            rowPtr = rowPtr.right;
        }
        while(colPtr != null)
        {
            if(colPtr.possibleValues != null)
            {
                colPtr.possibleValues.remove(cell.value);
            }
            colPtr = colPtr.below;
        }
        while(blockPtr != null)
        {
            if(blockPtr.possibleValues != null)
            {
                blockPtr.possibleValues.remove(cell.value);
            }
            blockPtr = blockPtr.block;
        }
    }

    public void solve() 
    {
        int counter = 0;
        while(soleCandidate() || uniqueCandidate() || duplicateCells())
        {
            counter++;
        }
        System.out.println("Number of moves: " + counter );
    }

    public boolean soleCandidate() 
    {
        for(int i = 0; i < numRows * numCols * numRows * numCols; i++)
        {
            if(cells[i].possibleValues != null && cells[i].possibleValues.length == 1)
            {
                cells[i].setVal(cells[i].possibleValues.head.data);
                propCell(cells[i]);
                return true;
            }
        }
        return false;
    }

    public boolean uniqueCandidate() 
    { 
        for(int row = 0; row < numRows * numCols; row++)
        {
            int counts[] = new int[numRows * numCols];
            Cell rowPtr = rows[row];
            while(rowPtr != null)
            {
                if(rowPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    while(nodePtr != null)
                    {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for(int i = 0; i < numRows * numCols; i++)
            {
                if(counts[i] == 1)
                {
                    rowPtr = rows[row];
                    while(rowPtr != null)
                    {
                        if(rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1))
                        {
                            rowPtr.setVal(i + 1);
                            propCell(rowPtr);
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }
        for(int col = 0; col < numRows * numCols; col++)
        {
            int counts[] = new int[numRows * numCols];
            Cell colPtr = cols[col];
            while(colPtr != null)
            {
                if(colPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = colPtr.possibleValues.head;
                    while(nodePtr != null)
                    {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for(int i = 0; i < numRows * numCols; i++)
            {
                if(counts[i] == 1)
                {
                    colPtr = cols[col];
                    while(colPtr != null)
                    {
                        if(colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1))
                        {
                            colPtr.setVal(i + 1);
                            propCell(colPtr);
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }
        for(int block = 0; block < numRows * numCols; block++)
        {
            int counts[] = new int[numRows * numCols];
            Cell blockPtr = blocks[block];
            while(blockPtr != null)
            {
                if(blockPtr.possibleValues != null)
                {
                    Node<Integer> nodePtr = blockPtr.possibleValues.head;
                    while(nodePtr != null)
                    {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for(int i = 0; i < numRows * numCols; i++)
            {
                if(counts[i] == 1)
                {
                    blockPtr = blocks[block];
                    while(blockPtr != null)
                    {
                        if(blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1))
                        {
                            blockPtr.setVal(i + 1);
                            propCell(blockPtr);
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells() 
    {
        for(int row = 0; row < numRows * numCols; row++)
        {
            Cell rowPtr = this.rows[row];
            while(rowPtr != null)
            {
                if(rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = rowPtr.right;
                    while(secondPtr != null)
                    {
                        if(rowPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = this.rows[row];
                            Boolean change = false;
                            while(thirdPtr != null)
                            {
                                if(thirdPtr != secondPtr && thirdPtr != rowPtr && thirdPtr.possibleValues != null)
                                {
                                    change = change || thirdPtr.possibleValues.remove(rowPtr.possibleValues);
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if(change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.right;
                    }
                }
                rowPtr = rowPtr.right;
            }
        }
        for(int col = 0; col < numRows * numCols; col++)
        {
            Cell colPtr = this.cols[col];
            while(colPtr != null)
            {
                if(colPtr.possibleValues != null && colPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = colPtr.below;
                    while(secondPtr != null)
                    {
                        if(colPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = this.cols[col];
                            Boolean change = false;
                            while(thirdPtr != null)
                            {
                                if(thirdPtr != secondPtr && thirdPtr != colPtr && thirdPtr.possibleValues != null)
                                {
                                    change = change || thirdPtr.possibleValues.remove(colPtr.possibleValues);
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if(change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.below;
                    }
                }
                colPtr = colPtr.below;
            }
        }
        for(int block = 0; block < numRows * numCols; block++)
        {
            Cell blockPtr = this.blocks[block];
            while(blockPtr != null)
            {
                if(blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2)
                {
                    Cell secondPtr = blockPtr.block;
                    while(secondPtr != null)
                    {
                        if(blockPtr.possibleValues.equals(secondPtr.possibleValues))
                        {
                            Cell thirdPtr = this.blocks[block];
                            Boolean change = false;
                            while(thirdPtr != null)
                            {
                                if(thirdPtr != secondPtr && thirdPtr != blockPtr && thirdPtr.possibleValues != null)
                                {
                                    change = change || thirdPtr.possibleValues.remove(blockPtr.possibleValues);
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if(change)
                            {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.block;
                    }
                }
                blockPtr = blockPtr.block;
            }
        }
        return false;
    }
}