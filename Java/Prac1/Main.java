public class Main {
    public static void main(String[] args) 
    {
        SkipList<Integer> myList = new SkipList<>(4);
        for (int i = 0; i < 20; i++) {
            myList.insert(i);
        }
        System.out.println(myList);
        System.out.print("Searching for 8\t");
        // System.out.print(myList.search(8).toString());
        myList.printSearchPath(8);
        System.out.print("Searching for 2\t");
        myList.printSearchPath(2);
        System.out.print("Searching for 7\t");
        myList.printSearchPath(7);
        System.out.print("Searching for 0\t");
        myList.printSearchPath(0);
        System.out.print("Searching for 19\t");
        myList.printSearchPath(19);
    }
}
