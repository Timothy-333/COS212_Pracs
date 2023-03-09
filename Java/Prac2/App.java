public class App {
    public static void main(String[] args) throws Exception {
        //Note: the print function tests both next and previous links
        //comment part of the code you will not be testing
        System.out.println("Testing CountList fucntions.................................");

        // CountList <Integer> countList = new CountList<>();
        // MoveToFrontList <Integer> countList = new MoveToFrontList<>();
        // NaturalOrderList <Integer> countList = new NaturalOrderList<>();
        // TransposeList <Integer> countList = new TransposeList<>();
        Count();
    }
    public static void Count()
    {
        CountList <Integer> countList = new CountList<>();
        insertRandom(countList);
        System.out.println("Testing both next and prev links");
        print(countList);

        System.out.println("Testing remove");
        removeFromHead(countList);
        insertRandom(countList);
        System.out.println("Testing access");
        accessFromfront(countList);
        accessRandom(countList);

        IterativeTraverse<Integer> list = new IterativeTraverse<>(countList);
        list.setList(countList);
        System.out.println("Testing Iterative Class");
        System.out.println(list);
        System.out.println("Reversing List");
        SelfOrderingList<Integer> newList = list.reverseList();
        list.setList(newList);
        System.out.println(list + "\n");
        System.out.println("Testing Contains");
        System.out.println("Value = 0");
        System.out.println(list.contains(0));
        System.out.println("Value = 5");
        System.out.println(list.contains(5));
        System.out.println("Value = 9");
        System.out.println(list.contains(9));
        System.out.println("Value = 69");
        System.out.println(list.contains(69) + "\n");

        System.out.println("Testing the Get Method");
        System.out.println("Value = 0");
        System.out.println(list.get(0));
        System.out.println("Value = 5");
        System.out.println(list.get(5));
        System.out.println("Value = 9");
        System.out.println(list.get(9));
        System.out.println("Value = 69");
        System.out.println(list.get(69) + "\n");

        System.out.println("Testing Size Method");
        System.out.println("Size = " + list.size() + "\n");

        System.out.println("Testing Clone");
        newList = list.clone(newList);
        list.setList(newList);
        System.out.println(list);

        // RecursiveTraverse<Integer> list = new RecursiveTraverse<>(countList);
        // list.setList(countList);
        // System.out.println("Testing Recursive Class");
        // System.out.println(list);
        // System.out.println("Reversing List");
        // SelfOrderingList<Integer> newList = list.reverseList();
        // list.setList(newList);
        // System.out.println(list + "\n");
        // System.out.println("Testing Contains");
        // System.out.println("Value = 0");
        // System.out.println(list.contains(0));
        // System.out.println("Value = 5");
        // System.out.println(list.contains(5));
        // System.out.println("Value = 9");
        // System.out.println(list.contains(9));
        // System.out.println("Value = 69");
        // System.out.println(list.contains(69) + "\n");

        // System.out.println("Testing the Get Method");
        // System.out.println("Value = 0");
        // System.out.println(list.get(0));
        // System.out.println("Value = 5");
        // System.out.println(list.get(5));
        // System.out.println("Value = 9");
        // System.out.println(list.get(9));
        // System.out.println("Value = 69");
        // System.out.println(list.get(69) + "\n");

        // System.out.println("Testing Size Method");
        // System.out.println("Size = " + list.size() + "\n");

        // System.out.println("Testing Clone");
        // newList = list.clone(newList);
        // list.setList(newList);
        // System.out.println(list);
    }
    public static void print(SelfOrderingList<Integer> list)
    {
        if (list.head == null)
        {
            // System.out.println("List is empty");
            return;
        }
        Node<Integer> node = list.head;
        while(node.next != null)
        {
            System.out.print(node.toString());
            node = node.next;
        }
        System.out.println(node.toString());

        // while(node != null)
        // {
        //     System.out.print(node.toString());
        //     node = node.prev;
        // }
        // System.out.println();
    }
    public static void insertlist(SelfOrderingList<Integer> list)
    {
        for(int i = 0; i < 10; i++)
        {
            list.insert(i);
        }
    }

    public static void insertRandom(SelfOrderingList<Integer> list)
    {
        list.insert(3);
        list.insert(8);
        list.insert(4);
        list.insert(2);
        list.insert(9);
        list.insert(0);
        list.insert(1);
        list.insert(5);
        list.insert(7);
        list.insert(6);
    }

    public static void removeFromHead(SelfOrderingList<Integer> list)
    {
        System.out.println("Removing From the head of the list");
        for(int i = 0; i < 10; i++)
        {
            System.out.println("Removing " + i);
            list.remove(i);
            print(list);
        }
        // System.out.println();
    }
    public static void removeFromTail(SelfOrderingList<Integer> list)
    {
        System.out.println("Removing from the tail of the list");
        for(int i = 9; i >= 0; i--)
        {
            list.remove(i);
            print(list);
        }
        System.out.println();
    }
    public static void removeRandom(SelfOrderingList<Integer> list)
    {
        System.out.println("Removing Randomly");
        list.remove(3);
        print(list);
        list.remove(8);
        print(list);
        list.remove(4);
        print(list);
        list.remove(2);
        print(list);
        list.remove(9);
        print(list);
        list.remove(0);
        print(list);
        list.remove(1);
        print(list);
        list.remove(5);
        print(list);
        list.remove(7);
        print(list);
        list.remove(6);
        print(list);
        System.out.println();
    }
    public static void accessFromfront(SelfOrderingList<Integer> list)
    {
        System.out.println("Accessing from the head of the list");

        for (int i = 0; i < 10; i++) 
        {
            System.out.println("Accessing " + i);
            list.access(i);
            print(list);    
        }
        System.out.println();
    }
    public static void accessFromBack(SelfOrderingList<Integer> list)
    {
        System.out.println("Accessing from the tail of the list");

        for (int i = 9; i >= 0; i--) 
        {
            System.out.println("Accessing " + i);
            list.access(i);
            print(list);    
        }
        System.out.println();
    }
    public static void accessRandom(SelfOrderingList<Integer> list)
    {
        System.out.println("Accessing Randomly");

        System.out.println("Accessing 3");
        list.access(3);
        print(list);
        System.out.println("Accessing 3");
        list.access(3);
        print(list);
        System.out.println("Accessing 8");
        list.access(8);
        print(list);
        System.out.println("Accessing 4");
        list.access(4);
        print(list);
        System.out.println("Accessing 2");
        list.access(2);
        print(list);
        System.out.println("Accessing 9");
        list.access(9);
        print(list);
        System.out.println("Accessing 0");
        list.access(0);
        print(list);
        System.out.println("Accessing 1");
        list.access(1);
        print(list);
        System.out.println("Accessing 5");
        list.access(5);
        print(list);
        System.out.println("Accessing 7");
        list.access(7);
        print(list);
        System.out.println("Accessing 6");
        list.access(6);
        print(list);
        System.out.println("Accessing 100, shouldnt move anything");
        list.access(100);
        print(list);
        System.out.println();
        System.out.println();
    }
}
