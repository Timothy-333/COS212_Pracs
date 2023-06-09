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
        if(nodes.indexOf(startNode) == -1 || nodes.indexOf(goalNode) == -1)
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
        {
            isReachable = true;
            return;
        }
        int v = nodes.indexOf(startNode);
        if(v == -1)
            return;
        visited[v] = true;
        for(int i = 0; i < startNode.getEdges().length; i++)
        {
            Node nextNode = startNode.getEdges()[i].getNext();
            if(nodes.indexOf(nextNode) == -1)
                continue;
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
    public Path shortestCompTimePath(Node sN, Node gN)
    {
        Path[] paths = getSimplePaths(nodes.indexOf(sN), nodes.indexOf(gN));
        Path shortestPath = null;
        for(int i = 0; i < paths.length; i++)
        {
            if(shortestPath == null)
                shortestPath = paths[i];
            else if(compTimeRequired(paths[i]) < compTimeRequired(shortestPath))
                shortestPath = paths[i];
        }
        return shortestPath;
    }
    public Path[] getPrimePaths()
    {
        Path[] simplePaths = getSimplePaths();
        Path[] primePaths = new Path[simplePaths.length];
        for(int i = 0; i < simplePaths.length; i++)
        {
            primePaths[i] = simplePaths[i];
            for(int j = 0; j < simplePaths.length; j++)
            {
                if(i != j && simplePaths[i].isSubPathOf(simplePaths[j]))
                {
                    primePaths[i] = null;
                    break;
                }
            }
        }
        int count = 0;
        for(int i = 0; i < primePaths.length; i++)
            if(primePaths[i] != null)
                count++;
        Path[] res = new Path[count];
        count = 0;
        for(int i = 0; i < primePaths.length; i++)
            if(primePaths[i] != null)
                res[count++] = primePaths[i];
        return res;
    }
    Array allSimplePaths;
    Array simplePaths;
    Path currentPath;
    public Path[] getSimplePaths()
    {
        allSimplePaths = new Array();
        for (int i = 0; i < nodes.toArray().length; i++) 
        {
            for (int j = 0; j < nodes.toArray().length; j++)
            {
                Path[] paths = getSimplePaths(i,j);
                for(int k = 0; k < paths.length; k++)
                    allSimplePaths.add(paths[k]);
            }
        }
        Path[] res = new Path[allSimplePaths.toArray().length];
        for(int i = 0; i < allSimplePaths.toArray().length; i++)
            res[i] = (Path)allSimplePaths.toArray()[i];
        return sort(res);
    }
    private Path[] sort(Path[] paths)
    {
        for(int i = 0; i < paths.length; i++)
        {
            for(int j = 0; j < paths.length - i - 1; j++)
            {
                if(paths[j].toString().length() > paths[j+1].toString().length())
                {
                    Path temp = paths[j];
                    paths[j] = paths[j+1];
                    paths[j+1] = temp;
                }
            }
        }
        return paths;
    }
    int startNodeIndex;
    public Path[] getSimplePaths(int startNode, int goalNode) 
    {
        startNodeIndex = startNode;
        visited = new Boolean[nodes.toArray().length];
        for (int i = 0; i < nodes.toArray().length; i++)
            visited[i] = false;
        simplePaths = new Array();
        currentPath = new Path((Node) nodes.toArray()[startNode], (Node) nodes.toArray()[goalNode], null, null);
        DFS(startNode, goalNode);
        Path[] res = new Path[simplePaths.toArray().length];
        for (int i = 0; i < simplePaths.toArray().length; i++)
            res[i] = (Path) simplePaths.toArray()[i];
        return res;
    }
    private void DFS(int startNode, int goalNode) 
    {
        visited[startNode] = true;
        if (startNode == goalNode) {
            simplePaths.add(new Path(currentPath, true));
            visited[startNode] = false;
            if(startNode != startNodeIndex)
                return;
        } 
        for (int i = 0; i < ((Node) nodes.toArray()[startNode]).getEdges().length; i++) 
        {
            Node nextNode = ((Node) nodes.toArray()[startNode]).getEdges()[i].getNext();
            if (visited[nodes.indexOf(nextNode)])
                continue;
            currentPath.appendToPath(nextNode, ((Node) nodes.toArray()[startNode]).getEdges()[i]);
            DFS(nodes.indexOf(nextNode), goalNode);
            currentPath.removeLastFromPath();
        }
        visited[startNode] = false;
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
