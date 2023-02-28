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
        for(int i = 1; i < maxLevel; i ++)
        {
            root[i] = null;
            powers[i] = powers[i-1] + (int) Math.round(n/(i*2.0));
        }
    }

    public int chooseLevel() 
    {
        int n = (int) Math.pow(2,maxLevel);
        int random = randomGenerator.nextInt(n-1) + 1;
        int counter = 0;
        while(random > powers[counter+1])
        {
            counter++;
        }
        return counter;
    }

    public void insert(T key) 
    {
        int level = chooseLevel();
        SkipListNode<T> newNode = new SkipListNode<T>(key, level);
        for(int i = level; i >=0; i--)
        {
            if(root[i] != null)
            {
                SkipListNode<T> nodePtr = root[i].next[i];
                SkipListNode<T> prevNode = root[i];
                if(key.compareTo(prevNode.next[i].key) < 0)
                {
                    root[i].next[i] = newNode;
                    newNode.next[i] = prevNode;
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
        
        for(int i = maxLevel-1; i >= 0;i--)
        {
            if(root[i] != null)
            {
                SkipListNode<T> nodePtr = root[i].next[i];
                SkipListNode<T> prevNode = root[i];
                if(prevNode.key == key)
                    return prevNode;
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
        return null;
    }

    @Override
    public String toString() 
    {
        return "";
    }

    public boolean delete(T key) 
    {
        return false;
    }

    public void printSearchPath(T key) 
    {
    }
}
