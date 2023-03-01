import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> 
{
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) 
    {
        this.maxLevel = maxLevel;
        root = new SkipListNode[maxLevel];
        powers = new int[maxLevel];
        int n = (int) Math.pow(2,maxLevel) - 1;
        powers[0] = 1;
        root[0] = null;
        for(int i = 1; i < maxLevel; i++)
        {
            root[i] = null;
            powers[i] = powers[i-1] + (int) Math.round(n/Math.pow(2, i));
        }
    }

    public int chooseLevel() 
    {
        int n = (int) Math.pow(2,maxLevel);
        int random = Math.abs(randomGenerator.nextInt()) % powers[maxLevel-1] + 1;
        int counter = 0;
        while(counter < maxLevel-1 && random >= powers[counter+1])
        {
            counter++;
        }
        // System.out.println(counter + " " + random);
        return counter+1;
    }

    public void insert(T key) 
    {
        int level = chooseLevel();
        SkipListNode<T> newNode = new SkipListNode<T>(key, level);
        for(int i = level-1; i >=0; i--)
        {
            if(root[i] != null)
            {
                SkipListNode<T> nodePtr = root[i].next[i];
                SkipListNode<T> prevNode = root[i];
                if(key.compareTo(prevNode.key) < 0)
                {
                    newNode.next[i] = prevNode;
                    root[i] = newNode;
                }
                while(nodePtr != null && key.compareTo(nodePtr.key) >= 0)
                {
                    prevNode = nodePtr;
                    nodePtr = nodePtr.next[i];
                }
                if(nodePtr == null)
                {
                    newNode.next[i] = null;
                }
                else
                {
                    newNode.next[i] = nodePtr;
                }
                prevNode.next[i] = newNode;
            }
            else
                root[i] = newNode;
        }
        
    }
    public boolean isEmpty() 
    {
        return root[0] == null;
    }

    public SkipListNode<T> search(T key) 
    {
        if(!isEmpty())
        {
            Boolean hasStarted = false;
            SkipListNode<T> nodePtr = null;
            SkipListNode<T> prevNode = null;
            for(int i = maxLevel-1; i >= 0;i--)
            {
                if(root[i] != null)
                {
                    if(!hasStarted)
                    {
                        nodePtr = root[i];
                        prevNode = root[i];

                        hasStarted = key.compareTo(prevNode.key) > 0;
                    }
                    else
                    {
                        nodePtr = prevNode.next[i];
                    }
                    while(nodePtr != null && key.compareTo(nodePtr.key) > 0)
                    {
                        prevNode = nodePtr;
                        nodePtr = nodePtr.next[i];
                    }
                    if(nodePtr != null && nodePtr.key == key)
                    {
                        return nodePtr;
                    }
                }
                
            }
        }
        return null;
    }

    @Override
    public String toString() 
    {
        String out = "";
        for(int i = maxLevel-1; i >= 0;i--)
        {
            out += "[Lvl " + i + "]";
            SkipListNode<T> nodePtr = root[i];
            while(nodePtr != null)
            {
                out += nodePtr.toString();
                nodePtr = nodePtr.next[i];
            }
            out += "\n";
        }
        return out;
    }

    public boolean delete(T key) 
    {
        return false;
    }

    public void printSearchPath(T key) 
    {
        if(!isEmpty())
        {
            String out = "";
            Boolean hasStarted = false;
            SkipListNode<T> nodePtr = null;
            SkipListNode<T> prevNode = null;
            for(int i = maxLevel-1; i >= 0;i--)
            {
                if(root[i] != null)
                {
                    if(!hasStarted)
                    {
                        nodePtr = root[i];
                        prevNode = root[i];
                        hasStarted = key.compareTo(prevNode.key) > 0;
                        out+=nodePtr.toString();
                    }
                    else
                    {
                        nodePtr = prevNode.next[i];
                        if(nodePtr != null)
                            out+=nodePtr.toString();
                    }
                    while(nodePtr != null && key.compareTo(nodePtr.key) > 0)
                    {
                        prevNode = nodePtr;
                        nodePtr = nodePtr.next[i];
                        if(nodePtr != null) out+=nodePtr.toString();
                    }
                    if(nodePtr != null && nodePtr.key == key)
                    {
                        System.out.println(out);
                        return;
                    }
                }
                
            }
        }
    }
}
