import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
    }

    public int chooseLevel() {
        return 0;
    }

    public void insert(T key) {
    }

    public boolean isEmpty() {
        return false;
    }

    public SkipListNode<T> search(T key) {
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
