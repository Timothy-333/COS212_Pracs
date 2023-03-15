public class Leaf<T extends Comparable<T>>{
    public Leaf<T> left;
    public Leaf<T> right;
    public T data;

    public Leaf(T data)
    {
        this.data = data;
        left = null;
        right = null;
    }
    public String toString(){
        String lStr;
        String rStr;
        
        if(left == null)
            lStr = "null";
        else 
            lStr = left.data.toString();

        if(right == null)
            rStr = "null";
        else
            rStr = right.data.toString();

        String res = "L[" + lStr + "]<-[" + data.toString() + "]->R[" + rStr + "]";
        return res; 
    }
    
}