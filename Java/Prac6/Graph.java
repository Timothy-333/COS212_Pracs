import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph 
{
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;
    private int num[];

    public Graph(String fileName) 
    {
        if(fileName == "" || fileName == null)
        {
            vertices = new String[0];
            adjacencyMatrix = new Integer[0][];
            numVertices = 0;
            numEdges = 0;
        }
        else
        {
            try
            {
                Scanner file = new Scanner(new File(fileName));
                if(file.hasNext())
                    numVertices = file.nextInt();
                numEdges = 0;
                vertices = new String[0];
                adjacencyMatrix = new Integer[0][];
                if(file.hasNext())
                {
                    vertices = new String[numVertices];
                }
                else
                {
                    numVertices = 0;
                    return;
                }
                for(int i = 0; i < numVertices; i++)
                {
                    if(file.hasNext())
                        vertices[i] = file.next();
                }
                if(file.hasNext())
                {
                    adjacencyMatrix = new Integer[numVertices][numVertices];
                    for(int i = 0; i < numVertices; i++)
                    {
                        for(int j = 0; j < numVertices; j++)
                        {
                            if(file.hasNext())
                            {
                                adjacencyMatrix[i][j] = file.nextInt();
                                if(adjacencyMatrix[i][j] != 0)
                                    numEdges++;
                            }
                            else
                                adjacencyMatrix[i][j] = 0;
                        }
                    }
                }
            }
            catch(FileNotFoundException e)
            {
                System.out.println("File not found");
            }
        }
    }
    public void insertVertex(String name) 
    {
        if(name == "" || name == null)
            return;
        for (int i = 0; i < numVertices; i++) 
        {
            if (vertices[i] == null || vertices[i] == "") 
            {
                vertices[i] = name;
                return;
            }
        }
        numVertices++;
        String[] tempVertices = new String[numVertices];
        for(int i = 0; i < numVertices - 1; i++)
        {
            tempVertices[i] = vertices[i];
        }
        tempVertices[numVertices - 1] = name;
        vertices = tempVertices;
        Integer[][] tempAdjacencyMatrix = new Integer[numVertices][numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            for(int j = 0; j < numVertices; j++)
            {
                if(i < numVertices - 1 && j < numVertices - 1)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                else
                    tempAdjacencyMatrix[i][j] = 0;
            }
        }
        adjacencyMatrix = tempAdjacencyMatrix;
    }
    public void insertEdge(String start, String end, int weight) 
    {
        if(weight == 0 || start.equals(end))
            return;
        int startIndex = 0;
        int endIndex = 0;
        boolean foundStart = false;
        boolean foundEnd = false;
        for(int i = 0; i < numVertices; i++)
        {
            if(vertices[i].equals(start))
            {
                startIndex = i;
                foundStart = true;
            }
            else if(vertices[i].equals(end))
            {
                endIndex = i;
                foundEnd = true;
            }
        }
        if(foundStart && foundEnd)
        {
            if(adjacencyMatrix[startIndex][endIndex] == 0)
                numEdges++;
            adjacencyMatrix[startIndex][endIndex] = weight;
        }
    }

    public void removeVertex(String name) 
    {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < numVertices; i++) 
        {
            if (vertices[i].equals(name)) 
            {
                index = i;
                found = true;
                break;
            }
        }
    
        if (!found) 
        {
            return;
        }
        numVertices--;
        String[] tempVertices = new String[numVertices];
        System.arraycopy(vertices, 0, tempVertices, 0, index);
        System.arraycopy(vertices, index + 1, tempVertices, index, numVertices - index);
        vertices = tempVertices;
        int edgeCount = 0;
        Integer[][] tempAdjacencyMatrix = new Integer[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) 
        {
            for (int j = 0; j < numVertices; j++) 
            {
                int originalRow = i < index ? i : i + 1;
                int originalCol = j < index ? j : j + 1;
                tempAdjacencyMatrix[i][j] = adjacencyMatrix[originalRow][originalCol];
                if (tempAdjacencyMatrix[i][j] != 0) 
                {
                    edgeCount++;
                }
            }
        }
        numEdges = edgeCount;
        adjacencyMatrix = tempAdjacencyMatrix;
    }    
    public void removeEdge(String start, String end) 
    {
        int startIndex = 0;
        int endIndex = 0;
        boolean foundStart = false;
        boolean foundEnd = false;
        for(int i = 0; i < numVertices; i++)
        {
            if(vertices[i].equals(start))
            {
                startIndex = i;
                foundStart = true;
            }
            else if(vertices[i].equals(end))
            {
                endIndex = i;
                foundEnd = true;
            }
        }
        if(foundStart && foundEnd)
        {
            if(adjacencyMatrix[startIndex][endIndex] != 0)
                numEdges--;
            adjacencyMatrix[startIndex][endIndex] = 0;
        }
    }

    @Override
    public String toString() 
    {
        String output = "(" + numVertices + "," + numEdges + ")";
        if(numVertices == 0)
            return output;
        output += "\t";
        for(int i = 0; i < numVertices; i++)
        {
            if(i < numVertices - 1)
                output += vertices[i] + "\t";
            else
                output += vertices[i];
        }
        output += "\n";
        for(int i = 0; i < numVertices; i++)
        {
            output += vertices[i] + "\t";
            for(int j = 0; j < numVertices; j++)
            {
                if(j < numVertices - 1)
                    output += adjacencyMatrix[i][j] + "\t";
                else
                    output += adjacencyMatrix[i][j];
            }
            if(i < numVertices - 1)
                output += "\n";
        }
        return output;
    }

    int visited = 0;
    String output = "";
    public String depthFirstTraversal() 
    {
        output = "";
        num = new int[numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            num[i] = 0;
        }
        visited = 1;
        for(int i = 0; i < numVertices; i++)
        {
            if(num[i] == 0)
            {
                DFS(i);
            }
        }
        return output;
    }
    private void DFS(int v)
    {
        output += "[" + vertices[v] + "]";
        num[v] = visited++;
        for(int i = 0; i < numVertices; i++)
        {
            if(adjacencyMatrix[v][i] != 0)
            {
                if(num[i] == 0)
                {
                    DFS(i);
                }
            }
        }
    }
    int queue[] = new int[numVertices];
    public String breadthFirstTraversal() 
    {
        queue = new int[numVertices];
        output = "";
        num = new int[numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            num[i] = 0;
        }
        visited = 1;
        for(int i = 0; i < numVertices; i++)
        {
            if(num[i] == 0)
            {
                BFS(i);
            }
        }
        return output;
    }
    private void BFS(int v)
    {
        output += "[" + vertices[v] + "]";
        num[v] = visited++;
        int front = 0;
        int back = 0;
        queue[back++] = v;
        while(front != back)
        {
            int u = queue[front++];
            for(int i = 0; i < numVertices; i++)
            {
                if(adjacencyMatrix[u][i] != 0 && num[i] == 0)
                {
                    output += "[" + vertices[i] + "]";
                    num[i] = visited++;
                    queue[back++] = i;
                }
            }
        }
    }
    public Double[][] shortestPaths() 
    {
        Double[][] out = new Double[numVertices][numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            for(int j = 0; j < numVertices; j++)
            {
                out[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for(int i = 0; i < numVertices; i++)
        {
            out[i][i] = 0.0;
        }
        for(int i = 0; i < numVertices; i++)
        {
            for(int j = 0; j < numVertices; j++)
            {
                if(adjacencyMatrix[i][j] != 0)
                {
                    out[i][j] = (double)adjacencyMatrix[i][j];
                }
            }
        }
        for(int i = 0; i < numVertices; i++)
        {
            for(int j = 0; j < numVertices; j++)
            {
                for(int k = 0; k < numVertices; k++)
                {
                    if(out[j][k] > out[j][i] + out[i][k])
                    {
                        out[j][k] = out[j][i] + out[i][k];
                    }
                }
            }
        }
        return out;
    }

    public Double shortestPath(String start, String end) 
    {
        int startIndex = 0;
        int endIndex = 0;
        boolean foundStart = false;
        boolean foundEnd = false;
        for(int i = 0; i < numVertices; i++)
        {
            if(vertices[i].equals(start))
            {
                startIndex = i;
                foundStart = true;
            }
            else if(vertices[i].equals(end))
            {
                endIndex = i;
                foundEnd = true;
            }
        }
        if(!foundStart || !foundEnd)
            return null;
        return shortestPaths()[startIndex][endIndex];
    }
    int[] pred = new int[numVertices];
    boolean result = false;
    public boolean cycleDetection()
    {
        result = false;
        num = new int[numVertices];
        pred = new int[numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            num[i] = 0;
        }
        visited = 1;
        for(int i = 0; i < numVertices; i++)
        {
            if(num[i] == 0)
            {
                cycleDetection(i);
            }
        }
        return result;
    }
    private void cycleDetection(int v)
    {
        num[v] = visited++;
        for(int i = 0; i < numVertices; i++)
        {
            if(adjacencyMatrix[v][i] != 0)
            {
                if(num[i] == 0)
                {
                    pred[i] = v;
                    cycleDetection(i);
                }
                else if(pred[v] != i)
                {
                    pred[i] = v;
                    result = true;
                    break;
                }
            }
        }
    }
    int[] stack = new int[numVertices];
    int top = 0;
    public String stronglyConnectedComponents() 
    {
        top = 0;
        stack = new int[numVertices];
        output = "";
        num = new int[numVertices];
        pred = new int[numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            num[i] = 0;
        }
        visited = 1;
        for(int i = 0; i < numVertices; i++)
        {
            if(num[i] == 0)
            {
                strongDFS(i);
            }
        }
        return output;
    }
    private void strongDFS(int v) 
    {
        pred[v] = num[v] = visited++;
        stack[top++] = v;
        for (int u = 0; u < numVertices; u++) 
        {
            if (adjacencyMatrix[v][u] != 0) 
            {
                if (num[u] == 0) 
                {
                    strongDFS(u);
                    pred[v] = Math.min(pred[v], pred[u]);
                } 
                else if (num[u] < num[v] && isOnStack(u)) 
                {
                    pred[v] = Math.min(pred[v], num[u]);
                }
            }
        }
        if (pred[v] == num[v]) 
        {
            int w = pop();
            while (w != v) {
                output += "[" + vertices[w] + "]";
                w = pop();
            }
            output += "[" + vertices[w] + "]\n";
        }
    }
    
    private boolean isOnStack(int vertex) 
    {
        for (int i = 0; i < top; i++) 
        {
            if (stack[i] == vertex) 
            {
                return true;
            }
        }
        return false;
    }
    
    private int pop() 
    {
        return stack[--top];
    }
    public String[] getVertices()
    {
        return vertices;
    }
}