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
        if(startNode == null)
            return false;
        if(exitNodes.toArray().length == 0)
            return false;
        for(Object n: nodes.toArray())
        {
            Boolean canReachExit = false;
            for(Object exitNode: exitNodes.toArray())
            {
                if(isReachable((Node)n, (Node)exitNode))
                {
                    canReachExit = true;
                    break;
                }
            }
            if(!canReachExit)
                return false;
        }
        return true;
    }

    public boolean isSESE()
    {
        if(exitNodes.toArray().length > 1)
            return false;
        if(!isValid())
            return false;
        return true;
    }

    public CFG[] splitGraph()
    {
        if(isSESE())
        {
            CFG[] res = new CFG[1];
            res[0] = this;
            return res;
        }
        CFG[] res = new CFG[exitNodes.toArray().length];
        for(int i = 0; i < exitNodes.toArray().length; i++)
        {
            Node exitNode = (Node)exitNodes.toArray()[i];
            res[i] = new CFG();
            res[i].addStartNode(startNode);
            res[i].addExitNode(exitNode);
            for(Object n: nodes.toArray())
            {
                if(isReachable((Node)n, exitNode))
                {
                    res[i].addNode((Node)n);
                    for(Object e: ((Node)n).getEdges())
                    {
                        if(isReachable(((Edge)e).getNext(), exitNode))
                            res[i].addEdge(((Edge)e).getAnnotation(), (Node)n, ((Edge)e).getNext(), ((Edge)e).getCompTime());
                    }
                }
            }
        }
        return res;
    }
    Boolean visited[];
    Boolean isReachable = false;
    public boolean isReachable(Node startNode, Node goalNode)
    {
        if(startNode == null || goalNode == null)
            return false;
        isReachable = false;
        visited = new Boolean[nodes.toArray().length];
        for(int i = 0; i < nodes.toArray().length; i++)
            visited[i] = false;
        DFS(startNode, goalNode);
        return isReachable;
    }
    private void DFS(Node startNode, Node goalNode)
    {
        if(startNode == goalNode)
            isReachable = true;
        int v = nodes.indexOf(startNode);
        visited[v] = true;
        for(int i = 0; i < startNode.getEdges().length; i++)
        {
            Node nextNode = startNode.getEdges()[i].getNext();
            if(!visited[nodes.indexOf(nextNode)])
                DFS(nextNode, goalNode);
        }
    }
    public int compTimeRequired(Path p)
    {
        if(p == null)
            return -1;
        return p.computationalCostOfPath();
    }
    int[] compTime;
    Node[] prevNode;
    Node[] unvisiteNodes;
    public Path shortestCompTimePath(Node sN, Node gN)
    {
        if(sN == null || gN == null)
            return null;
        compTime = new int[nodes.toArray().length];
        prevNode = new Node[nodes.toArray().length];
        unvisiteNodes = new Node[nodes.toArray().length];
        for(int i = 0; i < nodes.toArray().length; i++)
        {
            compTime[i] = Integer.MAX_VALUE;
            prevNode[i] = null;
        }
        
        compTime[nodes.indexOf(sN)] = 0;

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
        addNode(n);
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
