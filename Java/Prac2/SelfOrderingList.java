abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;

    public void insert(T data){
        //TODO: Implement the function
    }

    public void remove(T data){
       //TODO: Implement the function
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();
}
