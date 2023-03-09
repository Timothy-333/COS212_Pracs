public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() 
    {
        return new TransposeList<>();
    }

    @Override
    public void access(T data) 
    {
        if(head != null)
        {
            Node<T> nodePtr = head.next;
            Node<T> prevNode = head;
            while(nodePtr != null)
            {
                if(nodePtr.data.compareTo(data) == 0)
                {
                    if(prevNode.prev != null)
                    {
                        prevNode.prev.next = nodePtr;
                    }
                    else
                    {
                        head = nodePtr;
                    }
                    nodePtr.next.prev = prevNode;
                    nodePtr.prev = prevNode.prev;
                    prevNode.next = nodePtr.next;
                    nodePtr.next = prevNode;
                    prevNode.prev = nodePtr;
                    return;
                }
                prevNode = nodePtr;
                nodePtr = nodePtr.next;
            }
        }
    }
}
