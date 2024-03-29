abstract class SelfOrderingList<T extends Comparable<T>> 
{
    public Node<T> head = null;

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
            while(nodePtr.next != null)
            {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = newNode;
            newNode.prev = nodePtr;
        }
        
    }
    public void insert(T data, int accessCount)
    {
        Node<T> newNode = new Node<T>(data, accessCount);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            Node<T> nodePtr = head;
            while(nodePtr.next != null)
            {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = newNode;
            newNode.prev = nodePtr;
        }
        
    }
    public void remove(T data)
    {
        Node<T> nodePtr = head;
        Node<T> prevNode = null;
        while(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                if(nodePtr.next != null)
                {
                    nodePtr.next.prev = prevNode;
                }
                if(prevNode == null)
                {
                    head = nodePtr.next;
                }
                else
                {
                    prevNode.next = nodePtr.next;
                }
                return;
            }
            prevNode = nodePtr;
            nodePtr = nodePtr.next;
        }
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();
}
