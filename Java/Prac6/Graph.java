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
        String[] tempVertices = new String[vertices.length + 1];
        for(int i = 0; i < vertices.length - 1; i++)
        {
            tempVertices[i] = vertices[i];
        }
        tempVertices[vertices.length - 1] = name;
        vertices = tempVertices;
        Integer[][] tempAdjacencyMatrix = new Integer[adjacencyMatrix.length + 1][adjacencyMatrix.length + 1];
        for(int i = 0; i < tempAdjacencyMatrix.length; i++)
        {
            for(int j = 0; j < tempAdjacencyMatrix.length; j++)
            {
                if(i < adjacencyMatrix.length && j < adjacencyMatrix.length)
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
            adjacencyMatrix[startIndex][endIndex] = weight;
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
        String[] tempVertices = new String[vertices.length - 1];
        for(int i = 0; i < index; i++)
        {
            tempVertices[i] = vertices[i];
        }
        for(int i = index + 1; i < vertices.length; i++)
        {
            tempVertices[i - 1] = vertices[i];
        }
        vertices = tempVertices;
        Integer[][] tempAdjacencyMatrix = new Integer[tempVertices.length][tempVertices.length];
        for(int i = 0; i < tempAdjacencyMatrix.length; i++)
        {
            for(int j = 0; j < tempAdjacencyMatrix.length; j++)
            {
                if(i < index && j < index)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                else if(i < index && j >= index)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
                else if(i >= index && j < index)
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
                else
                    tempAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
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
            adjacencyMatrix[startIndex][endIndex] = 0;
    }

    @Override
    public String toString() 
    {
        return "";
    }

    public String depthFirstTraversal() 
    {
        return "";
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