import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph 
{
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;

    public Graph(String fileName) 
    {
        if(fileName == "")
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
                numVertices = file.nextInt();
                numEdges = 0;
                vertices = new String[numVertices];
                adjacencyMatrix = new Integer[numVertices][numVertices];
                for(int i = 0; i < numVertices; i++)
                {
                    vertices[i] = file.next();
                }
                for(int i = 0; i < numVertices; i++)
                {
                    for(int j = 0; j < numVertices; j++)
                    {
                        adjacencyMatrix[i][j] = file.nextInt();
                        if(adjacencyMatrix[i][j] != 0)
                            numEdges++;
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
        if(name == "")
            return;
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
                if(i < numVertices && j < numVertices)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                else
                    tempAdjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void insertEdge(String start, String end, int weight) 
    {
        if(weight == 0 || start.equals(end))
            return;
        int startIndex = 0;
        int endIndex = 0;
        boolean foundStart = false;
        boolean foundEnd = false;
        for(int i = 0; i < vertices.length; i++)
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
        int index = 0;
        boolean found = false;
        for(int i = 0; i < vertices.length; i++)
        {
            if(vertices[i].equals(name))
            {
                index = i;
                found = true;
            }
        }
        if(!found)
            return;
        numVertices--;
        String[] tempVertices = new String[numVertices];
        for(int i = 0; i < index; i++)
        {
            tempVertices[i] = vertices[i];
        }
        for(int i = index + 1; i < numVertices-1; i++)
        {
            tempVertices[i - 1] = vertices[i];
        }
        vertices = tempVertices;
        Integer[][] tempAdjacencyMatrix = new Integer[numVertices][numVertices];
        for(int i = 0; i < numVertices; i++)
        {
            for(int j = 0; j < numVertices; j++)
            {
                if(i < index && j < index)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                else if(i < index && j >= index)
                {
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
                    if(tempAdjacencyMatrix[i][j] != 0)
                        numEdges--;
                }
                else if(i >= index && j < index)
                {
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
                    if(tempAdjacencyMatrix[i][j] != 0)
                        numEdges--;
                }
                else
                {
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
                    if(tempAdjacencyMatrix[i][j] != 0)
                        numEdges--;
                }
            }
        }
    }

    public void removeEdge(String start, String end) 
    {
        int startIndex = 0;
        int endIndex = 0;
        boolean foundStart = false;
        boolean foundEnd = false;
        for(int i = 0; i < vertices.length; i++)
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
        String output = "(" + numVertices + "," + numEdges + ")\t";
        for(int i = 0; i < numVertices; i++)
        {
            output += vertices[i] + "\t";
        }
        output += "\n";
        for(int i = 0; i < numVertices; i++)
        {
            output += vertices[i] + "\t";
            for(int j = 0; j < numVertices; j++)
            {
                output += adjacencyMatrix[i][j] + "\t";
            }
            output += "\n";
        }
        return output;
    }
//     depthFirst()
//   for all vertices v
//     num(v) = 0;
//   edges = null;
//   i = 1; // global counter
//   while there is a vertex v such that num(v) is 0
//     DFS(v); // initiate recursion
//   output edges;

// DFS(v) // recursive function
//   num(v) = i++; // visit
//   for all vertices u adjacent to v
//     if num(u) is 0 //unvisited
//       attach edge(uv) to edges;
//       DFS(u); // recursion
    private int num[] = new int[numVertices];
    int visited = 0;
    String output = "";
    public String depthFirstTraversal() 
    {
        for(int i = 0; i < numVertices; i++)
        {
            num[i] = 0;
        }
        visited = 1;
        while(true)
        {
            int v = -1;
            for(int i = 0; i < numVertices; i++)
            {
                if(num[i] == 0)
                {
                    v = i;
                    break;
                }
            }
            if(v == -1)
                break;
            DFS(v);
        }
        return output;
    }
    private void DFS(int v)
    {
        num[v] = visited++;
        for(int i = 0; i < vertices.length; i++)
        {
            if(adjacencyMatrix[v][i] != 0)
            {
                if(num[i] == 0)
                {
                    DFS(i);
                }
            }
        }
        output += "[" + vertices[v] + "]";
    }
    public String breadthFirstTraversal() 
    {
        return "";
    }

    public Double[][] shortestPaths() 
    {
        return null;
    }

    public Double shortestPath(String start, String end) 
    {
        return null;

    }

    public boolean cycleDetection() 
    {
        return false;
    }

    public String stronglyConnectedComponents() 
    {
        return "";
    }
}