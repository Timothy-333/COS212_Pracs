public class Node<T extends Comparable<T>>{
    public Node<T> next;
    public Node<T> prev;
    public int accessCount;
    public T data;

    public Node(T data){
        accessCount = 0;
        this.data = data;
        next = null;
        prev = null;
    }

    @Override
    public String toString() {
        return "(" + data.toString() + "[" + accessCount + "]" + ")";
    }
}
