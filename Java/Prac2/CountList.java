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
        if(head != null)
        {
            while(nodePtr != null)
            {
                if(nodePtr.data.compareTo(data) == 0)
                {
                    nodePtr.accessCount++;
                    break;
                }
                nodePtr = nodePtr.next;
            }
            nodePtr = head;
            while(nodePtr != null)
            {
                Node<T> nodePtrTemp = nodePtr;
                Node<T> checkingNode = nodePtr;
                while(nodePtrTemp != null && nodePtrTemp.accessCount > nodePtr.next.accessCount)
                {

                }
                if(nodePtrTemp != null)
                {
                    nodePtrTemp.prev.next = nodePtrTemp.next;
                    nodePtrTemp.next.prev = nodePtrTemp.prev;
                    nodePtrTemp.next = nodePtr;
                    nodePtrTemp.prev = nodePtr.prev;
                    nodePtr.prev = nodePtrTemp;
                    nodePtr = nodePtrTemp;
                }
            }
        }
    }
}
