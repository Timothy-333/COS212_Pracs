public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() 
    {
        return new MoveToFrontList<>();
    }

    @Override
    public void access(T data) 
    {
        Node<T> nodePtr = head;
        if(head != null)
        {
            while(nodePtr != null)
            {
                if(nodePtr.data.compareTo(data) == 0)
                {
                    if(nodePtr.prev != null)
                    {
                        nodePtr.prev.next = nodePtr.next;
                        if(nodePtr.next != null)
                        {
                            nodePtr.next.prev = nodePtr.prev;
                        }
                        nodePtr.next = head;
                        head.prev = nodePtr;
                        head = nodePtr;
                        nodePtr.prev = null;
                    }
                    return;
                }
                nodePtr = nodePtr.next;
            }
        }
    }
}
