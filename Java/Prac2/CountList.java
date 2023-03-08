public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() 
    {
        return new CountList<>();
    }

    @Override
    public void access(T data) 
    {
        Node<T> nodePtr = head;
        while(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                nodePtr.accessCount++;
                return;
            }
            nodePtr = nodePtr.next;
        }
    }
}
