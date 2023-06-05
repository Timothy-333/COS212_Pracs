public class CFG {
    private Node startNode;
    private Array nodes;
    private Array edges;
    private Array exitNodes;

    public CFG()
    {
        startNode = null;
        nodes = new Array();
        edges = new Array();
        exitNodes = new Array();
    }

    public CFG(Node[] nodes, Edge[] edges, Node sNode, Node[] exitNodes)
    {
        this.nodes = new Array(nodes);
        this.edges = new Array(edges);
        this.startNode = sNode;
        this.exitNodes = new Array(exitNodes);
    }

    public CFG(CFG other)
    {
        this.nodes = new Array(other.nodes, true);
        this.edges = new Array(other.edges, true);
        this.startNode = other.startNode;
        this.exitNodes = new Array(other.exitNodes, true);
    }

    public boolean isValid()
    {
        return true;
    }

    public boolean isSESE()
    {
        return true;
    }

    public CFG[] splitGraph()
    {
        return null;
    }

    public boolean isReachable(Node startNode, Node goalNode)
    {
        return true;
    }

    public int compTimeRequired(Path p)
    {
        return 0;
    }

    public Path shortestCompTimePath(Node sN, Node gN)
    {
        return null;
    }

    public Path[] getPrimePaths()
    {
        return null;
    }
    public Path[] getSimplePaths()
    {
        return null;
    }

    public void addNode(String annotation)
    {
        Node newNode = new Node(annotation);
        if(nodes.contains(newNode))
            return;
        if(startNode == null)
            startNode = newNode;
        nodes.add(new Node(annotation));
    }

    public void addNode(Node node)
    {
        if(nodes.contains(node) || node == null)
            return;
        if(startNode == null)
            startNode = node;
        nodes.add(node);
    }

    public void addEdge(String annotation, Node fromNode, Node toNode, int computationalTime)
    {
        Edge newEdge = new Edge(annotation, toNode, computationalTime);
        if(edges.contains(newEdge) || computationalTime < 0)
            return;
        fromNode.addEdge(toNode, annotation, computationalTime);
        edges.add(newEdge);
    }

    public void addExitNode(Node n)
    {
        if(exitNodes.contains(n) || n == null)
            return;
        exitNodes.add(n);
        if(!nodes.contains(n))
            nodes.add(n);
    }

    public void addStartNode(Node n)
    {
        if(startNode == null)
            startNode = n;
        if(!nodes.contains(n))
            nodes.add(n);
    }

    public String toString() 
    {
        //Provided function, do not alter!!!
        String res = "";
        for(Object n: nodes.toArray()){
            res += n.toString();
        }
        return res;
    }

    public Node getNode(String annotation)
    {
        return (Node) nodes.getNode(annotation);
    }
}
