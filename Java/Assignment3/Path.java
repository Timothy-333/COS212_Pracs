public class Path {
    private final Node startNode;
    private Node endNode;
    private final Array nodes;
    private final Array edges;

    public Path(Node startNode, Node endNode, Node[] nodes, Edge[] edges)
    {
        this.startNode = startNode;
        this.endNode = endNode;
        this.nodes = new Array(nodes);
        this.edges = new Array(edges);
    }

    public Path(Path other)
    {
        startNode = other.startNode;
        endNode = other.endNode;
        nodes = new Array(other.nodes, false);
        edges = new Array(other.edges, false);
    }

    public int computationalCostOfPath()
    {
        return edges.getCost();
    }

    @SuppressWarnings("unchecked")
    public void appendToPath(Path p)
    {
        //TODO: Implement the function
    }

    public boolean validPath()
    {
        Node[] nodes = (Node[])this.nodes.toArray();
        Edge[] edges = (Edge[])this.edges.toArray();
        if(nodes.length == 0)
            return false;
        int i = 0;
        while(nodes[i] != endNode && i < nodes.length - 1)
        {
            if(nodes[i+1] != edges[i].getNext())
                return false;
            i++;
        }
        return true;
    }

    public String toString()
    {
        //Provided function, do not alter!!!
        String str = "";
        str += ((Node)nodes.toArray()[0]).getAnnotation();
        for(Object e: edges.toArray()){
            str += e.toString();
        }
        return str;
    }
    
}
