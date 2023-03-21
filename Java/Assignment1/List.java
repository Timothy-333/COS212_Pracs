public class List<T> 
{
    public int length;
    public Node<T> head;

    public List() 
    {
        length = 0;
        head = null;
    }

    public String toString() 
    {
        String out = "";
        if(head != null)
        {
            Node<T> nodePtr = head;
            while(nodePtr.next != null)
            {
                out += nodePtr.toString() + ",";
                nodePtr = nodePtr.next;
            }
            out += nodePtr.toString();
        }
        return out;
    }

    public void append(T val) 
    {
        Node<T> newNode = new Node<T>(val);
        if(head == null)
        {
            length++;
            head = newNode;
            return;
        }
        Node<T> nodePtr = head;
        while(nodePtr.next != null)
        {
            nodePtr = nodePtr.next;
        }
        nodePtr.next = newNode;
        length++;
    }

    public boolean remove(T val) 
    {
        if(head == null)
        {
            return false;
        }
        Node<T> nodePtr = head;
        Node<T> prevNode = head;
        if(nodePtr.data.equals(val))
        {
            head = nodePtr.next;
            length--;
            return true;
        }
        while(nodePtr != null && !nodePtr.data.equals(val))
        {
            prevNode = nodePtr;
            nodePtr = nodePtr.next;
        }
        if(nodePtr == null)
        {
            return false;
        }
        else
        {
            prevNode.next = nodePtr.next;
            length--;
            return true;
        }
    }

    public boolean remove(List<T> val) 
    {
        boolean flag = false;
        Node<T> nodePtr = val.head;
        while(nodePtr != null)
        {
            if(!flag)
                flag = remove(nodePtr.data);
            else
                remove(nodePtr.data);
            nodePtr = nodePtr.next;
        }
        return flag;
    }

    public boolean contains(T search) 
    {
        if(head == null)
        {
            return false;
        }
        Node<T> nodePtr = head;
        while(nodePtr != null && !nodePtr.data.equals(search))
        {
            nodePtr = nodePtr.next;
        }
        if(nodePtr == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean equals(List<T> other) 
    {
        if(other == null)
            return false;
        Node<T> nodePtr = head;
        Node<T> nodePtrOther = other.head;
        while(nodePtr != null && nodePtrOther != null)
        {
            if(!nodePtr.data.equals(nodePtrOther.data))
                return false;
            nodePtr = nodePtr.next;
            nodePtrOther = nodePtrOther.next;
        }
        if((nodePtr == null && nodePtrOther != null) || (nodePtr != null && nodePtrOther == null))
            return false;
        return true;
    }

}
