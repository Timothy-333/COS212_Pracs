public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() 
    {
        return new NaturalOrderList<>();
    }

    @Override
    public void access(T data) 
    {
    }

    @Override
    public void insert(T data) 
    {
        Node<T> newNode = new Node<T>(data);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            Node<T> nodePtr = head;
            Node<T> prevNode = null;
            while(nodePtr != null && nodePtr.data.compareTo(data) > 0)
            {
                prevNode = nodePtr;
                nodePtr = nodePtr.next;
            }
            if(prevNode == null)
            {
                head = newNode;
            }
            else
            {
                prevNode.next = newNode;
            }
            newNode.next = nodePtr;
            newNode.prev = prevNode;
            if(nodePtr != null)
            {
                nodePtr.prev = newNode;
            }
        }
    }
}
