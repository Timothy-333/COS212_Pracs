// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> {
    public T key;
    public SkipListNode<T>[] next;

    public SkipListNode(T key, int levels) {
    }

    @Override
    public String toString() {
        return "";
    }

    public String emptyString() {
        return "";
    }
}