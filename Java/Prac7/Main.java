public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        String[] verts = { "a", "b", "c", "d", "e", "f", "g", "h" };
        String[][] edges = { { "a", "e", "1" },
                { "a", "f", "-1" },
                { "a", "g", "2" },
                { "b", "e", "-2" },
                { "b", "c", "3" },
                { "b", "h", "-3" },
                { "c", "g", "4" },
                { "d", "f", "-4" },
                { "d", "g", "5" },
                { "f", "g", "-5" },
                { "f", "h", "6" },
                { "g", "h", "-6" } };

        for (String s : verts) {
            g.addVertex(s);
        }

        for (String[] e : edges) {
            g.addEdge(e[0], e[1], Integer.valueOf(e[2]));
        }

        System.out.println("---\ntoString\n---\n" + g);
        System.out.println("---\nunionFind\n---");
        int[][] unionFindRes = g.unionFind();
        for (int r = 0; r < unionFindRes.length; r++) {
            for (int c = 0; c < unionFindRes[r].length; c++) {
                System.out.print(unionFindRes[r][c] + "\t");
            }
            System.out.println();
        }
        System.out.println("---\nminSpanningTree\n---");
        System.out.println(g.minSpanningTree());
        System.out.println("---\ncolouring\n---");
        Vertex[][] colourRes = g.brelazColouring();
        for (int r = 0; r < colourRes.length; r++) {
            for (int c = 0; c < colourRes[r].length; c++) {
                System.out.print(colourRes[r][c] + "\t");
            }
            System.out.println();
        }
    }
}