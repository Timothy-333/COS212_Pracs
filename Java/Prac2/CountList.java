public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() 
    {
        return new CountList<>();
    }

    @Override
    public void access(T data) 
    {
        if(head != null)
        {
            Node<T> nodePtr = head;
            while(nodePtr != null)
            {
                if(nodePtr.data.compareTo(data) == 0)
                {
                    nodePtr.accessCount++;
                    break;
                }
                nodePtr = nodePtr.next;
            }
            if(head.next == null)
                return;
            while(nodePtr != null && nodePtr.prev != null && nodePtr.accessCount > nodePtr.prev.accessCount)
            {
                if(nodePtr.prev.prev != null)
                {
                    nodePtr.prev.prev.next = nodePtr;
                }
                else
                {
                    head = nodePtr;
                }
                if(nodePtr.next != null)
                {
                    nodePtr.next.prev = nodePtr.prev;
                }
                nodePtr.prev.next = nodePtr.next;
                nodePtr.next = nodePtr.prev;
                nodePtr.prev = nodePtr.prev.prev;
                nodePtr.next.prev = nodePtr;
            }
        }
    }
}
