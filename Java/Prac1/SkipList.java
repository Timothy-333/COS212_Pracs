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
                SkipListNode<T> nodePtr = root[i];
                SkipListNode<T> prevNode = root[i];
                if(key.compareTo(prevNode.key) < 0)
                {
                    newNode.next[i] = prevNode;
                    root[i] = newNode;
                }
                else
                {
                    while(nodePtr != null && key.compareTo(nodePtr.key) >= 0)
                    {
                        prevNode = nodePtr;
                        nodePtr = nodePtr.next[i];
                    }
                    newNode.next[i] = nodePtr;
                    prevNode.next[i] = newNode;
                }
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
                    if(nodePtr != null && nodePtr.key.equals(key))
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
        String[] levels = new String[maxLevel];
        SkipListNode<T> nodePtr = root[0];
        for(int i = maxLevel-1; i >= 0;i--)
        {
            levels[i] = "[Lvl " + i + "]";
        }
        while(nodePtr != null)
        {
            for(int i = maxLevel-1; i >= 0;i--)
            {
                if(i < nodePtr.next.length)
                {
                    levels[i] += "->" + nodePtr.toString();
                }
                else
                {
                    levels[i] += "--" + nodePtr.emptyString();
                }
            }
            nodePtr = nodePtr.next[0];
        }
        for(int i = maxLevel-1; i > 0;i--)
        {
            out += levels[i].substring(0, 1+levels[i].lastIndexOf("]")) + "\n";
        }
        out += levels[0].substring(0, 1+levels[0].lastIndexOf("]"));
        return out;
    }

    public boolean delete(T key) 
    {
        boolean flag = false;
        SkipListNode<T> deleteNode = search(key);
        if(!isEmpty() && deleteNode != null)
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
                    while(nodePtr != null && nodePtr != deleteNode)
                    {
                        prevNode = nodePtr;
                        nodePtr = nodePtr.next[i];
                    }
                    if(nodePtr == root[i] && nodePtr == deleteNode)
                    {
                        root[i] = nodePtr.next[i];
                    }
                    if(nodePtr != null && nodePtr == deleteNode)
                    {
                        prevNode.next[i] = nodePtr.next[i];
                        flag = true;
                    }
                }
                
            }
        }
        return flag;
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
                    if(nodePtr != null && nodePtr.key.compareTo(key) == 0)
                    {
                        System.out.println(out);
                        return;
                    }
                }
                
            }
        }
    }
}
