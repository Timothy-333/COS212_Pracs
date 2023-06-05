public class Node 
{
    private final String annotation;
    private final Array edges;
    
    public Node(String annot)
    {
        annotation = annot;
        edges = new Array();
    }
    public Node(Node other, boolean deepCopy)
    {
        annotation = other.annotation;
        if(deepCopy)
            edges = new Array(other.edges, true);
        else
            edges = other.edges;
    }
    public void addEdge(Node nextNode, String annotation, int compTime)
    {
        edges.add(new Edge(annotation, nextNode, compTime));
    }

    public String getAnnotation()
    {
        return annotation;
    }

    public Edge[] getEdges()
    {
        return (Edge[])edges.toArray();
    }

    public String toString()
    {
        //Provided function, do not alter!!!
        String res = annotation + ":\n";
        if(edges.toArray().length == 0)
            res += "\t" + "No out going edges" + "\n";
        else
            for(Object e: edges.toArray()){
                res += "\t" + e.toString() + "\n";
            } 
        return res;
    }
}
