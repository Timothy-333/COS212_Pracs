
public class Path {
    private final Node startNode;
    private Node endNode;
    private final Array nodes;
    private final Array edges;

    public Path(Node startNode, Node endNode, Node[] nodes, Edge[] edges)
    {
        this.startNode = startNode;
        this.endNode = endNode;
        if(nodes == null || nodes.length == 0 )
        {
            this.nodes = new Array(1);
            this.nodes.add(startNode);
        }
        else
        {
            this.nodes = new Array(nodes);
        }
        if(edges == null || edges.length == 0)
            this.edges = new Array(1);
        else
            this.edges = new Array(edges);
    }

    public Path(Path other)
    {
        startNode = other.startNode;
        endNode = other.endNode;
        nodes = new Array(other.nodes, false);
        edges = new Array(other.edges, false);
    }
    public Path(Path other, boolean deepCopy)
    {
        startNode = other.startNode;
        endNode = other.endNode;
        nodes = new Array(other.nodes, deepCopy);
        edges = new Array(other.edges, deepCopy);
    }
    public int computationalCostOfPath()
    {
        return edges.getCost();
    }

    @SuppressWarnings("unchecked")
    public void appendToPath(Path p)
    {
        if(p == null)
            return;
        if(p.startNode != endNode)
            return;
        endNode = p.endNode;
        for(Object n: p.nodes.toArray())
            nodes.add(n);
        for(Object e: p.edges.toArray())
            edges.add(e);
    }
    public void appendToPath(Node n, Edge e)
    {
        if(n == null || e == null)
            return;
        endNode = n;
        nodes.add(n);
        edges.add(e);
    }
    public void removeLastFromPath()
    {
        if(nodes.toArray().length == 0 || edges.toArray().length == 0)
            return;
        nodes.removeLast();
        edges.removeLast();
        endNode = (Node)nodes.toArray()[nodes.toArray().length-1];
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
    public boolean isSubPathOf(Path p)
    {
        if(p == null)
            return false;
        if(p.nodes.toArray().length < nodes.toArray().length)
            return false;
        return p.toString().contains(toString());
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
