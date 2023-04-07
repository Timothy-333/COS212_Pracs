public class Main {
    public static void main(String[] args) {
        minDHeap<Integer> heap = new minDHeap<>(3);

        for (int i = 10; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);
        heap.changeD(2);
        System.out.println(heap);
        System.out.println(heap.pathToRoot(5));
        System.out.println(heap.max(1));
        for (int i = 0; i <= 5; i++) {
            heap.remove(i);
        }
        System.out.println(heap);
    }
}