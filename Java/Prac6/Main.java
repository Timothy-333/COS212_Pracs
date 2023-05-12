public class Main {
    public static void main(String[] args) {
        Graph g = new Graph("graph.txt");
        System.out.println(g);
        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("Y");
        g.insertVertex("U");
        g.insertVertex("B");
        g.insertVertex("X");
        g.insertEdge("A", "H", 1);
        g.insertEdge("H", "A", 1);
        g.insertEdge("H", "B", 1);
        g.removeEdge("A", "J");
        g.removeVertex("A");
        g.removeVertex("J");
        g.removeVertex("K");
        g.removeVertex("L");
        g.removeVertex("M");
        g.removeVertex("N");
        g.removeVertex("O");
        g.removeVertex("P");
        Double[][] m = g.shortestPaths();
        for (int i = 0; i < m.length; i++) 
        {
            for (int j = 0; j < m.length; j++)
                System.out.print(m[i][j] + " ");
        }
        System.out.println();
        System.out.println(g.shortestPath("A", "M"));
        System.out.println(g);
        System.out.println("DFS:" + g.depthFirstTraversal());
        System.out.println("BFS:" + g.breadthFirstTraversal());
        System.out.println("Strongly Connected:\n" + g.stronglyConnectedComponents());
    }
}
// public class Main {
//     public static void main(String[] args) {
//         Graph g = new Graph("graph.txt");
//         System.out.println(g);
//         /*g.insertVertex("B");
//         g.insertEdge("B", "A", 5);
//         print(g);
//         g.removeEdge("B", "A");
//         g.removeVertex("B");
//         print(g);*/
//         print("Testing with the text file given:____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         System.out.println("DFS:" + g.depthFirstTraversal());
//         System.out.println("BFS:" + g.breadthFirstTraversal());
//         System.out.println("Strongly Connected:\n" + g.stronglyConnectedComponents());
//         print(g.shortestPath("A", "P"));
//         print(g.shortestPath("A", "R"));

//         print("\nTesting for if your cycle detection works if you get true then false then true it works for this graph: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         print(g.cycleDetection());
//         g.removeEdge("N", "R");
//         print(g.cycleDetection());
//         g.insertEdge("N", "P", 8);
//         print(g.cycleDetection());

//         print("\nTesting for if your stuff doesn't fail for an empty graph: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         Graph test = new Graph("");
//         test.breadthFirstTraversal();
//         test.removeVertex("A");
//         test.removeEdge("A", "B");
//         test.toString();
//         test.depthFirstTraversal();
//         test.breadthFirstTraversal();
//         test.shortestPaths();
//         test.shortestPath("A", "B");
//         test.cycleDetection();
//         test.stronglyConnectedComponents();
//         print(test);



//         String[] input = new String[] {
//                 "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
//         };

//         String[] edges = new String[] {
//                 "a c 1", "c f 2", "f a 3", "d a 4", "d f 5", "d b 6", "b e 7", "b h 8", "e d 9", "h g 1", "h i 2",
//                 "h k 3", "g b 4", "g k 5", "i j 6"
//         };

//         test = make_graph(input, edges);

//         print("\nTesting if your strongly connected components funcion works for the graph in the text for strongly connected graphs: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         print(test);
//         print("Strongly Connected:\n" + test.stronglyConnectedComponents());

//         input = new String[] {
//                 "a", "b", "c", "d", "e", "f", "g", "h", "i"
//         };

//         edges = new String[] {
//                 "a e 1", "a f 2", "e i 3", "i a 4", "f e 5", "f i 6", "b g 7", "g a 8", "c h 9", "h d 1"
//         };

//         test = make_graph(input, edges);

//         print("\nTesting to so if your depth first and breadth first work for the example of both in the text book: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

//         print(test);
//         print("Depth First: " + test.depthFirstTraversal());
//         print("Breadth First: " + test.breadthFirstTraversal());
//         //print_double(test.shortestPaths());

//         input = new String[] {
//                 "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
//         };

//         edges = new String[] {
//                 "a e 1", "a h 10", "b c 2", "d a 4", "d h 1", "e f 3", "f b 1", "f c 3", "f g 7", "f i 1", "h e 5",
//                 "h i 9", "i j 2", "j g 1"
//         };

//         test = make_graph(input, edges);

//         print("\nTesting for if your shortest path works for the dijkstras algorithm example in the text book: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         print(test);
//         print_double(test.shortestPaths(), input);


//         print("\nTesting for if your code works for removing every vertex in the graph: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         for (String string : input) {
//             test.removeVertex(string);
//             print(test);
//         }

//         input = new String[] {
//                 "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
//         };

//         edges = new String[] {
//                 "a e -1", "a h -10", "b c -2", "d a -4", "d h -1", "e f -3", "f b -1", "f c -3", "f g -7", "f i -1",
//                 "h e -5", "h i -9", "i j -2", "j g -1"
//         };

//         test = make_graph(input, edges);

//         print("\nTesting to see if your code works for a graph of only negative waits: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         print(test);
//         print_double(test.shortestPaths(), input);
//         print(test.stronglyConnectedComponents());

//         print("\nTesting to see if your code works for a large tree: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         test = new Graph("");
//         edges = new String[19];
//         for(int index = 1; index <= 19; index++){
//             test.insertVertex(index+"");
//             edges[index-1] = index +"";
//         }

//         for(int index_outer = 1; index_outer <= 19; index_outer++){
//             for(int index_inner = 19; index_inner >=1; index_inner--){
//                 test.insertEdge(index_outer+"", index_inner+"", index_inner-index_outer);
//             }
//         }
//         print(test);
//         print_double(test.shortestPaths(), edges);

//         print("\nTesting to see if your code works for removing every edge in a graph: ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//         for(int index_outer = 1; index_outer <= 19; index_outer++){
//             for(int index_inner = 19; index_inner >=1; index_inner--){
//                 test.removeEdge(index_outer+"", index_inner+"");
//             }
//         }
//         print(test);
//     }

//     private static Graph make_graph(String[] input, String[] edges) {
//         Graph test = new Graph("");
//         for (String string : input) {
//             test.insertVertex(string);
//         }

//         for (String string : edges) {
//             String[] edge = string.split(" ");
//             test.insertEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
//         }
//         return test;
//     }

//     private static <T> void print(T input) {
//         System.out.println(input.toString());
//     }

//     private static void print_double(Double[][] in, String[] verts) {
//         System.out.print("\t");
//         for (String string : verts) {
//             System.out.print(string + "\t");
//         }
//         print("");
//         int count = 0;
//         for (Double[] array : in) {
//             System.out.print(verts[count] + "\t");
//             for (Double doubles : array) {
//                 if (doubles != Double.POSITIVE_INFINITY)
//                     System.out.print("[" + doubles + "]");
//                 else
//                     System.out.print("[-]");
//                 System.out.print("\t");
//             }
//             print("");
//             count++;
//         }
//     }

// }
