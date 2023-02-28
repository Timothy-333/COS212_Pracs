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
        
    }
    public boolean isEmpty() 
    {
        return root[0] == null;
    }

    public SkipListNode<T> search(T key) 
    {
        int i = maxLevel;
        while(i >= 0)
        {
            SkipListNode<T> nodePtr = root[i];
            while((int)key > (int)nodePtr.next[i].key && nodePtr.next[i] != null)
            {
                nodePtr = nodePtr.next[i];
            }
            if(nodePtr.key == key)
            {
                return nodePtr;
            }
            i--;
        }
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean delete(T key) {
        return false;
    }

    public void printSearchPath(T key) {
    }
}
