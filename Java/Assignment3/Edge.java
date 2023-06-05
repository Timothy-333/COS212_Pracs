public class Edge 
{
    private final String annotation;
    private final Node nextNode;
    private final int computationalTime;

    public Edge(String annot, Node nextNode, int compTime)
    {
        annotation = annot;
        this.nextNode = nextNode;
        computationalTime = compTime;
    }
    public Edge(Edge other, boolean deepCopy)
    {
        annotation = other.annotation;
        if(deepCopy)
            nextNode = new Node(other.nextNode, true);
        else
            nextNode = other.nextNode;
        computationalTime = other.computationalTime;
    }
    public Node getNext()
    {
        return nextNode;
    }
    public String getAnnotation()
    {
        return annotation;
    }

    public int getCompTime()
    {
        return computationalTime;
    }

    public String toString(){
        //Provided function, do not alter!!!
        String res = "-> " + annotation + " -[" + computationalTime + "]-"; 
        
        if(nextNode == null)
            res += "> NULL";
        else
            res += "> " + nextNode.getAnnotation();

        return res;
    }
}
