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
            if(head.next == null)
                return;
            nodePtr = head;
            while(nodePtr != null)
            {
                Node<T> nodePtrTemp = nodePtr.next;
                Node<T> checkingNode = nodePtr;
                Node<T> prevNode = nodePtr;
                while(nodePtrTemp != null && checkingNode.accessCount > nodePtrTemp.accessCount)
                {
                    prevNode = nodePtrTemp;
                    nodePtrTemp = nodePtrTemp.next;
                }
                if(checkingNode != nodePtrTemp.next)
                {
                    if(checkingNode.prev != null)
                    {
                        checkingNode.prev.next = checkingNode.next;
                    }
                    else
                    {
                        head = checkingNode.next;
                    }
                    if(checkingNode.next != null)
                    {
                        checkingNode.next.prev = checkingNode.prev;
                    }
                    if(nodePtrTemp != null)
                    {
                        nodePtrTemp.prev = checkingNode;   
                    }
                    checkingNode.next = nodePtrTemp;
                    checkingNode.prev = prevNode;
                    prevNode.next = checkingNode;
                }
                nodePtr = nodePtr.next;
            }
        }
    }
}
