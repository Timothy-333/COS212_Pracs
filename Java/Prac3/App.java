public class App {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        BinaryTree<Integer> t = new StandardBinaryTree<>();
    
        t.insert(2);
        t.insert(1);
        t.insert(3);
        t.insert(5);
        t.insert(6);
        t.insert(0);
        t.insert(2);
        t.insert(8);
        t.insert(12);
    
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);

        t = t.convertTree();
    
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
    }
}

/*
Tree Height
1

Tree Number of Leaves
3

Depth First Traversal
L[null]<-[1]->R[null]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]

Find parent of [3]
L[1]<-[2]->R[3]

Find [1]
L[1]<-[2]->R[3]
L[null]<-[1]->R[null]

Contains [5]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]
 */