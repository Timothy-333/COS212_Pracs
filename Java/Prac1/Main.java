public class Main {
    public static void main(String[] args) 
    {
        SkipList<Integer> myList = new SkipList<>(3);
        for (int i = 0; i < 10; i++) {
            myList.insert(i);
        }
        SkipList<String> myList1 = new SkipList<>(3);
        myList1.insert("A");
        myList1.insert("Z");
        myList1.insert("C");
        myList1.insert("A");
        myList1.insert("A");
        myList1.insert("A");
        myList1.insert("Hello");
        myList1.insert("Hellothere");
        myList1.insert("Y");
        myList1.printSearchPath("Z");
        myList1.insert("Z");
        myList1.insert("Z");
        System.out.println(myList1);
        myList1.delete("Z");
        System.out.println(myList1);
        // System.out.print("Searching for 8\t");
        // myList.printSearchPath(8);
        // System.out.print("Searching for 2\t");
        // myList.printSearchPath(2);
        // System.out.print("Searching for 7\t");
        // myList.printSearchPath(7);
        // System.out.print("Searching for 0\t");
        // myList.printSearchPath(0);
        // System.out.print("Searching for 19\t");
        // myList.printSearchPath(19);
        // System.out.println();
        // System.out.println(myList.delete(1));
        // myList.delete(5);
        // myList.delete(1);
        // myList.delete(3);
        // myList.delete(0);
        // myList.delete(5);
        // myList.delete(9);
        // myList.insert(15);
        // myList.insert(30);
        // myList.insert(25);
        // myList.insert(25);
        // myList.insert(25);
        // myList.insert(25);
        // myList.insert(25);
        // myList.insert(30);
        // myList.insert(30);
        // myList.insert(30);
        // myList.insert(30);
        // myList.insert(100);
        // myList.delete(25);
        // myList.delete(25);
        // myList.delete(25);
        // myList.delete(25);
        // myList.delete(25);
        // System.out.println(myList.search(25).next[0]);
        // myList.printSearchPath(100);
        // System.out.println(myList);
        // System.out.println(myList1);
        // myList.printSearchPath(30);

    }
}
