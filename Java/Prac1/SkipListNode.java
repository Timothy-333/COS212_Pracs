// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> 
{
    public T key;
    public SkipListNode<T>[] next;

    public SkipListNode(T key, int levels) 
    {
        this.key = key;
        next = new SkipListNode[levels];
        for(int i = 0; i < levels; i++)
            next[i] = null;
    }

    @Override
    public String toString() 
    {
        String result = "[" + key.toString() + "]";
        return result;
    }

    public String emptyString() 
    {
        String result = "--";
        for(int i = 0; i < key.toString().length(); i++) 
        {
            result += "-";
        }
        return result;
    }
}