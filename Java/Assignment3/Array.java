public class Array 
{
    private Object[] array;
    private int size;
    
    public Array()
    {
        array = new Object[0];
        size = 0;
    }

    public Array(int size)
    {
        if(size < 0)
            size = 0;
        array = new Object[size];
        this.size = 0;
    }
    public Array(Object[] array)
    {
        if(array == null)
            array = new Object[0];
        this.array = array;
        size = array.length;
    }
    public Array(Array other, boolean deepCopy)
    {
        if(other == null)
            other = new Array();
        if(deepCopy)
        {
            array = new Object[other.size];
            for(int i = 0; i < other.size; i++)
            {
                if(other.array[i] instanceof Edge)
                    array[i] = new Edge((Edge)other.array[i], true);
                else if(other.array[i] instanceof Node)
                    array[i] = new Node((Node)other.array[i], true);
                else
                    array[i] = other.array[i];
            }
        }
        else
            array = other.array;
        size = other.size;
    }
    public void add(Object o)
    {
        if(size == array.length)
        {
            Object[] temp = new Object[array.length + 1];
            for(int i = 0; i < array.length; i++)
                temp[i] = array[i];
            array = temp;
        }
        array[size] = o;
        size++;
    }
    public void remove(Object o)
    {
        int index = indexOf(o);
        if(index == -1)
            return;
        for(int i = index; i < size - 1; i++)
            array[i] = array[i+1];
        size--;
        Object[] temp = new Object[size];
        for(int i = 0; i < size; i++)
            temp[i] = array[i];
        array = temp;
    }
    public void removeLast()
    {
        if(size == 0)
            return;
        size--;
        Object[] temp = new Object[size];
        for(int i = 0; i < size; i++)
        {
            temp[i] = array[i];
        }
        array = temp;
    }
    public Object[] toArray()
    {
        Object[] res = new Object[size];
        for(int i = 0; i < size; i++)
            res[i] = array[i];
        return res;
    }
    public int getCost()
    {
        int res = 0;
        if(size == 0 || !(array[0] instanceof Edge))
            return res;
        for(int i = 0; i < size; i++)
            res += ((Edge)array[i]).getCompTime();
        return res;
    }
    public boolean contains(Object o)
    {
        for(int i = 0; i < size; i++)
        {
            if(array[i] == o)
                return true;
        }
        return false;
    }
    public Object getNode(String annotation)
    {
        for(int i = 0; i < size; i++)
            if(((Node)array[i]).getAnnotation().equals(annotation))
                return array[i];
        return null;
    }
    public int indexOf(Object o)
    {
        for(int i = 0; i < size; i++)
            if(array[i] == (o))
                return i;
        return -1;
    }
}
