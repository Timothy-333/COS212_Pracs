public class Graph 
{
    public Vertex[] vertices = new Vertex[0];
    public Edge[] edges = new Edge[0];
    public void addVertex(String v) 
    {
        if(v == null || v == "")
            return;
        Vertex newVertex = new Vertex(v);
        int i = 0;
        if(vertices.length == 0)
        {
            vertices = new Vertex[1];
            vertices[0] = newVertex;
        }
        while(i < vertices.length && vertices[i].compareTo(newVertex) < 0)
        {
            i++;
        }
        if (i < vertices.length && vertices[i].compareTo(newVertex) == 0)
            return;
        else
        {
            Vertex[] tempVertexs = new Vertex[vertices.length+1];
            for (int j = 0; j < i; j++) 
            {
                tempVertexs[j] = vertices[j];
            }
            for (int j = i+1; j < tempVertexs.length; j++) 
            {
                tempVertexs[j] = vertices[j-1];
            }
            tempVertexs[i] = newVertex;
            vertices = tempVertexs;
        }
    }

    public void removeVertex(String v) 
    {
        if(v == null || v == "")
            return;
        if(vertices.length == 0)
            return;
        int i = 0;
        Vertex delVertex = new Vertex(v);
        while(i < vertices.length-1 && vertices[i].compareTo(delVertex) < 0)
        {
            i++;
        }
        if (vertices[i].compareTo(delVertex) == 0)
        {
            Vertex[] tempVertexs = new Vertex[vertices.length-1];
            for (int j = 0; j < i; j++)
            {
                tempVertexs[j] = vertices[j];
            }
            for (int j = i; j < tempVertexs.length; j++) 
            {
                tempVertexs[j] = vertices[j+1];
            }
            vertices = tempVertexs;
        }
    }
    public void addEdge(String a, String b, int w) 
    {
        if(a == null || b == null || a == "" || b == "")
            return;
        Vertex vertexA = getVertex(a);
        Vertex vertexB = getVertex(b);
        if(vertexA == null || vertexB == null)
            return;
        Edge newEdge = new Edge(vertexA, vertexB, w);
        int i = 0;
        if(edges.length == 0)
        {
            edges = new Edge[1];
            edges[0] = newEdge;
        }
        while(i < edges.length && edges[i].compareTo(newEdge) < 0)
        {
            i++;
        }
        if (i < edges.length && edges[i].compareTo(newEdge) == 0)
            return;
        else
        {
            Edge[] tempEdges = new Edge[edges.length+1];
            for (int j = 0; j < i; j++) 
            {
                tempEdges[j] = edges[j];
            }
            for (int j = i+1; j < tempEdges.length; j++) 
            {
                tempEdges[j] = edges[j-1];
            }
            tempEdges[i] = newEdge;
            edges = tempEdges;
        }
    }
    public void removeEdge(String a, String b)
    {
        if(a == null || b == null || a == "" || b == "")
            return;
        if(edges.length == 0)
            return;
        Vertex vertexA = getVertex(a);
        Vertex vertexB = getVertex(b);
        if(vertexA == null || vertexB == null)
            return;
        Edge delEdge = new Edge(vertexA, vertexB, 0);
        int i = 0;
        while(i < edges.length-1 && edges[i].compareTo(delEdge) < 0)
        {
            i++;
        }
        if (edges[i].compareTo(delEdge) == 0)
        {
            Edge[] tempEdges = new Edge[edges.length-1];
            for (int j = 0; j < i; j++)
            {
                tempEdges[j] = edges[j];
            }
            for (int j = i; j < tempEdges.length; j++) 
            {
                tempEdges[j] = edges[j+1];
            }
            edges = tempEdges;
        }
    }

    public int[][] unionFind() 
    {
        int[] root = new int[vertices.length];
        int[] next = new int[vertices.length];
        int[] length = new int[vertices.length];
        int[] result = new int[vertices.length];
    
        for (int i = 0; i < vertices.length; i++) 
        {
            root[i] = i;
            next[i] = i;
            length[i] = 1;
            result[i] = 0;
        }
        for (Edge edge : edges) 
        {
            int v = getIndex(edge.vertexA);
            int u = getIndex(edge.vertexB);

            if (root[u] == root[v]) 
            {
                for (int i = 0; i < vertices.length; i++) 
                {
                    result[i] = 1;
                }
            } 
            else if (length[root[v]] < length[root[u]]) 
            {
                int rt = root[v];
                length[root[u]] += length[rt];
                root[rt] = root[u];
    
                for (int j = next[rt]; j != rt; j = next[j]) 
                {
                    root[j] = root[u];
                }
                int temp = next[rt];
                next[rt] = next[root[u]];
                next[root[u]] = temp;
            } 
            else 
            {
                int rt = root[u];
                length[root[v]] += length[rt];
                root[rt] = root[v];
    
                for (int j = next[rt]; j != rt; j = next[j]) {
                    root[j] = root[v];
                }
    
                int temp = next[rt];
                next[rt] = next[root[v]];
                next[root[v]] = temp;
            }
        }
        return new int[][] { root, next, length, result };
    }
    public boolean cycle() 
    {
        int[][] unionFindRes = unionFind();
        for (int i = 0; i < unionFindRes[3].length; i++) 
        {
            if(unionFindRes[3][i] != 0)
            {
                return true;
            }
        }
        return false;
    }
    public Graph minSpanningTree() 
    {
        if(vertices.length == 0 || edges.length == 0)
            return null;
        Graph result = new Graph();
        int treeSize = 0;
        Edge[] sortEdges = new Edge[edges.length];
        for (int j = 0; j < edges.length; j++) 
        {
            sortEdges[j] = edges[j];
        }
        for (int i = 0; i < sortEdges.length - 1; i++) 
        {
            for (int j = 0; j < sortEdges.length - i - 1; j++) 
            {
                if (sortEdges[j].weight > sortEdges[j + 1].weight) 
                {
                    Edge temp = sortEdges[j];
                    sortEdges[j] = sortEdges[j + 1];
                    sortEdges[j + 1] = temp;
                }
            }
        }
        int i = 0;
        while(treeSize < vertices.length - 1 && i < sortEdges.length)
        {
            Edge edge = sortEdges[i];
            result.addVertex(edge.vertexA.toString());
            result.addVertex(edge.vertexB.toString());
            result.addEdge(edge.vertexA.toString(), edge.vertexB.toString(), edge.weight);
            if(!result.cycle())
            {
                treeSize++;
            }
            else
            {
                result.removeEdge(edge.vertexA.toString(), edge.vertexB.toString());
            }
            i++;
        }
        return result;
    }

    public Vertex[][] brelazColouring() 
    {
        for (int i = 0; i < vertices.length; i++) 
        {
            vertices[i].saturationDeg = 0;
            vertices[i].uncoloredDeg = getNeighbours(vertices[i]).length;
        }
    
        String[] colors = new String[vertices.length];
        for (int i = 0; i < vertices.length; i++) 
        {
            colors[i] = "c" + i;
        }
    
        Vertex[][] colorVertices = new Vertex[vertices.length][];
        int[] colorVerticesLength = new int[vertices.length];
    
        Vertex[] uncoloredVertices = new Vertex[vertices.length];
        for (int i = 0; i < vertices.length; i++) 
        {
            uncoloredVertices[i] = vertices[i];
        }
    
        int uncoloredVerticesLength = uncoloredVertices.length;
        while (uncoloredVerticesLength > 0) 
        {
            Vertex v = getMaxVertex(uncoloredVertices);
            int j = 0;
    
            Vertex[] neighbors = getNeighbours(v);
            Boolean[] colorUsedByNeighbor = new Boolean[colors.length];
            for (Vertex u : neighbors) 
            {
                if (u.colorIndex > -1) 
                {
                    colorUsedByNeighbor[u.colorIndex] = true;
                }
            }
            for (int i = 0; i < colorUsedByNeighbor.length; i++) 
            {
                if (colorUsedByNeighbor[i] == null) 
                {
                    j = i;
                    break;
                }
            }
            for (Vertex u : neighbors) 
            {
                if (u.colorIndex > -1) 
                {
                    Vertex[] uNeighbors = getNeighbours(u);
                    boolean noAdjacentColor = true;
    
                    for (Vertex w : uNeighbors) 
                    {
                        if (w.colorIndex == j) 
                        {
                            noAdjacentColor = false;
                            break;
                        }
                    }
    
                    if (noAdjacentColor) 
                    {
                        u.saturationDeg++;
                    }
    
                    u.uncoloredDeg--;
                }
            }
    
            v.colorIndex = j;
            int colorIndex = v.colorIndex;
            int length = colorVerticesLength[colorIndex] + 1;
            Vertex[] verticesArray = new Vertex[length];
    
            for (int i = 0; i < colorVerticesLength[colorIndex]; i++) 
            {
                verticesArray[i] = colorVertices[colorIndex][i];
            }
            verticesArray[length - 1] = v;
            colorVertices[colorIndex] = verticesArray;
            colorVerticesLength[colorIndex]++;
    
            Vertex[] tempUncoloredVertices = new Vertex[uncoloredVerticesLength - 1];
            int tempIndex = 0;
    
            for (int i = 0; i < uncoloredVerticesLength; i++) 
            {
                if (uncoloredVertices[i] != v) 
                {
                    tempUncoloredVertices[tempIndex] = uncoloredVertices[i];
                    tempIndex++;
                }
            }
    
            uncoloredVertices = tempUncoloredVertices;
            uncoloredVerticesLength--;
        }
    
        return resizeColorVertices(colorVertices);
    }
    private Vertex[][] resizeColorVertices(Vertex[][] colorVertices) 
    {
        int length = 0;
        for (int i = 0; i < colorVertices.length; i++) 
        {
            if (colorVertices[i] != null) {
                length++;
            }
        }
        Vertex[][] result = new Vertex[length][];
        int index = 0;
        for (int i = 0; i < colorVertices.length; i++) 
        {
            if (colorVertices[i] != null) 
            {
                bubbleSort(colorVertices[i]);
                result[index] = colorVertices[i];
                index++;
            }
        }
        return result;
    }
    
    private void bubbleSort(Vertex[] vertices) 
    {
        int n = vertices.length;
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (vertices[j].compareTo(vertices[j + 1]) > 0) 
                {
                    Vertex temp = vertices[j];
                    vertices[j] = vertices[j + 1];
                    vertices[j + 1] = temp;
                }
            }
        }
    }
      
    @Override
    public String toString() 
    {
        String result = "\t";
    
        for (int i = 0; i < vertices.length; i++) {
            result += vertices[i].toString();
            if (i < vertices.length - 1) 
            {
                result += "\t";
            }
        }
        result += "\n";

        for (int i = 0; i < vertices.length; i++) 
        {
            result += vertices[i].toString() + "\t";
            for (int j = 0; j < vertices.length; j++) 
            {
                Vertex vertexA = vertices[i];
                Vertex vertexB = vertices[j];
                Edge edge = findEdge(vertexA, vertexB);
                if (edge != null) 
                {
                    result += edge.weight;
                } 
                else 
                {
                    result += "0";
                }
                if (j < vertices.length - 1) 
                {
                    result += "\t";
                }
            }
            if (i < vertices.length - 1) 
            {
                result += "\n";
            }
        }

        return result;
    }
    private Vertex getVertex(String v)
    {
        for (int i = 0; i < vertices.length; i++) 
        {
            if(vertices[i].compareTo(new Vertex(v)) == 0)
                return vertices[i];
        }
        return null;
    }
    private int getIndex(Vertex vertex) 
    {
        for (int i = 0; i < vertices.length; i++) 
        {
            if (vertices[i].compareTo(vertex) == 0) 
            {
                return i;
            }
        }
        return -1;
    }
    private Edge findEdge(Vertex vertexA, Vertex vertexB) 
    {
        for (int i = 0; i < edges.length; i++) 
        {
            if ((edges[i].vertexA.compareTo(vertexA) == 0 && edges[i].vertexB.compareTo(vertexB) == 0) || (edges[i].vertexA.compareTo(vertexB) == 0 && edges[i].vertexB.compareTo(vertexA) == 0))
            {
                return edges[i];
            }
        }
        return null;
    }
    public void printEdges() 
    {
        for (int i = 0; i < edges.length; i++) 
        {
            System.out.println(edges[i].toString());
        }
    }
    private Vertex[] getNeighbours(Vertex v)
    {
        Vertex[] neighbors = new Vertex[0];
        for (int i = 0; i < edges.length; i++) 
        {
            if(edges[i].vertexA.compareTo(v) == 0)
            {
                Vertex[] tempNeighbors = new Vertex[neighbors.length+1];
                for (int j = 0; j < neighbors.length; j++) 
                {
                    tempNeighbors[j] = neighbors[j];
                }
                tempNeighbors[neighbors.length] = edges[i].vertexB;
                neighbors = tempNeighbors;
            }
            else if(edges[i].vertexB.compareTo(v) == 0)
            {
                Vertex[] tempNeighbors = new Vertex[neighbors.length+1];
                for (int j = 0; j < neighbors.length; j++) 
                {
                    tempNeighbors[j] = neighbors[j];
                }
                tempNeighbors[neighbors.length] = edges[i].vertexA;
                neighbors = tempNeighbors;
            }
        }
        return neighbors;
    }
    private Vertex getMaxVertex(Vertex[] uncoloredVertexs)
    {
        Vertex maxSaturationVertex = uncoloredVertexs[0];
        for (int i = 1; i < uncoloredVertexs.length; i++) 
        {
            if(uncoloredVertexs[i].saturationDeg > maxSaturationVertex.saturationDeg)
            {
                maxSaturationVertex = uncoloredVertexs[i];
            }
            else if(uncoloredVertexs[i].saturationDeg == maxSaturationVertex.saturationDeg)
            {
                if(uncoloredVertexs[i].uncoloredDeg > maxSaturationVertex.uncoloredDeg)
                {
                    maxSaturationVertex = uncoloredVertexs[i];
                }
            }
        }
        return maxSaturationVertex;
    }
}

