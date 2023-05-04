// public class Main {

//     public static void main(String[] args) {
//         // task1();
//         task2();
//     }

//     public static void task1() 
//     {
//         Treap<Integer> treap = new Treap<Integer>();
//         for (int i = 0; i < 5; i++) 
//         {
//             try 
//             {
//                 treap.insert(i);
//             } 
//             catch (DatabaseException e)
//             {
//                 System.out.println(e.toString());
//             }
//         }
//         System.out.println(treap);
//         for(int i = 0; i < 100; i++)
//         {
//             treap.access(3);
//         }
//         System.out.println(treap.access(4).toString());
//         System.out.println(treap);
//         for(int i = 0; i < 5; i++)
//         {
//             System.out.println(treap.remove(i));
//         }
//         System.out.println(validTreap(treap));
//         System.out.println(treap);
//         // System.out.println(validTreap(treap));
//     }

//     public static void task2() 
//     {
//         /*
//          * Note that we also want you to create your own main for this task.
//          * 
//          * It takes a while to set the DB up, so an example is given below, feel free to
//          * change it to test the rest of the functions
//          */
//         String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
//         Database db = new Database(columns, 100);
//         String[][] modules = {
//                 { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
//                 { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
//                 { "UP0102", "Academic orientation 102", "0", "1", "Year" },
//                 { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
//                 { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
//                 { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
//                 { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
//                 { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
//                 { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
//                 { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
//                 { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
//                 { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
//                 { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
//                 { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
//                 { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
//                 { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
//                 { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
//                 { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
//                 { "PHY263", "General physics 263", "24", "2", "Sem 2" },
//                 { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
//                 { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
//                 { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
//                 { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
//                 { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
//                 { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
//                 { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
//                 { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
//                 { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
//                 { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
//                 { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
//                 { "FSK700", "Physics 700", "135", "4", "Year" }
//         };

//         try {
//             for (String[] mod : modules) {
//                 db.insert(mod);
//             }

//             db.generateIndexAll();
//         } catch (DatabaseException e) {
//             System.out.println("Error: " + e);
//         }

//         printDB(db);
//         System.out.println();
//         try {
//             db.removeAllWhere("Year", "1");
//             db.removeFirstWhere("Module Code", "COS314");
//             String[] updated = db.updateFirstWhere("Module Code", "COS711", "YES420");
//             if(updated.length > 0)
//             {
//                 System.out.println(updated[0] + "\t" + updated[1] + "\t" + updated[2] + "\t" + updated[3] + "\t" + updated[4]);
//             }
//             else
//             {
//                 System.out.println("No rows updated");
//             }
//             db.updateAllWhere("Year", "2", "69");
//             String[] found1 = db.findFirstWhere("Module Code", "FSK700");
//             System.out.println(found1[0] + "\t" + found1[1] + "\t" + found1[2] + "\t" + found1[3] + "\t" + found1[4]);
//             String[][] found = db.findAllWhere("Session", "Sem 2");
//             for (String[] row : found) 
//             {
//                 System.out.println(row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4]);
//             }
            
//         } 
//         catch (DatabaseException e) 
//         {
//             System.out.println("Error: " + e);
//         }
//         printDB(db);
//         System.out.println(db.indexes[0]);
//         System.out.println(db.indexes[1]);
//     }
//     public static void printDB(Database db)
//     {
//         for (String[] row : db.database) 
//         {
//             if (row[0] != null) 
//             {
//                 int c = 0;
//                 for (String s : row) 
//                 {
//                     if (c++ == 1) 
//                     {
//                         System.out.print(String.format("%1$-75s", s));
//                     } 
//                     else 
//                     {
//                         System.out.print(s + "\t");
//                     }
//                 }
//                 System.out.println();
//             }
//         }
//     }
//     public static final String ANSI_RESET = "\u001B[0m";
//     public static final String ANSI_RED = "\u001B[31m";
//     public static final String ANSI_GREEN = "\u001B[32m";

//     public static <T extends Comparable<T>> String validTreap(Treap<T> t) 
//     {
//         return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
//     }

//     public static <T extends Comparable<T>> boolean validTreap(Node<T> n) 
//     {
//         if (n == null) {
//             return true;
//         }

//         if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
//             return false;
//         }

//         if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
//             return false;
//         }

//         if (!validTreap(n.left)) {
//             return false;
//         }

//         if (!validTreap(n.right)) {
//             return false;
//         }

//         return true;
//     }
// }
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // task1();
        task2();
    }

    public static void task1() {
        /*
         * You are not given a Main for this task, because we want you to figure out how
         * to do it for yourself.
         * 
         * You are provided with a validTreap() function which will print out valid or
         * invalid for a passed in Treap.
         * 
         * Use this function to make sure that your heaps follow the rules set by the
         * Assignment.
         * 
         * Tip : Create a Main that inserts / deletes a lot of elements and call
         * validTreap after every step
         */
        Treap<Integer> treap = new Treap<>();
        System.out.println(".................Testing Insert.................");
        Inserting(treap);
        System.out.println(".................Testing Remove.................");
        Removing(treap);
        System.out.println(".................Testing Access.................");
        Inserting(treap);
        Accessing(treap);
    }

    public static void Inserting(Treap<Integer> treap) {
        try {
            treap.insert(8);
            treap.insert(11);
            treap.insert(9);
            treap.insert(13);
            treap.insert(12);
            treap.insert(17);
            treap.insert(21);
            treap.insert(30);
            System.out.println(treap.toString());
        } catch (DatabaseException Error_Message) {
            System.out.println(Error_Message);
        }

    }

    public static void Removing(Treap<Integer> treap) {

        System.out.println("Removing Node: " + treap.remove(8));
        System.out.println("Removing Node: " + treap.remove(11));
        System.out.println("Removing Node: " + treap.remove(9));
        System.out.println("Removing Node: " + treap.remove(13));
        System.out.println(treap.toString());
        System.out.println("Removing Node that doesnt exist: " + treap.remove(100));
        System.out.println(treap.toString());
        System.out.println("Removing Node: " + treap.remove(12));
        System.out.println("Removing Node: " + treap.remove(17));
        System.out.println("Removing Node: " + treap.remove(21));
        System.out.println("Removing Node: " + treap.remove(30));
        System.out.println(treap.toString());
    }

    public static void Accessing(Treap<Integer> treap) {
        System.out.println("Accessing Node: " + treap.access(8));
        System.out.println("Accessing Node: " + treap.access(11));
        System.out.println("Accessing Node: " + treap.access(9));
        System.out.println("Accessing Node: " + treap.access(13));
        System.out.println("Accessing Node: " + treap.access(12));
        System.out.println("Accessing Node: " + treap.access(17));
        System.out.println("Accessing Node: " + treap.access(21));
        System.out.println("Accessing Node: " + treap.access(30));
        System.out.println(treap.toString());
        System.out.println("Accessing Node that doesnt exist: " + treap.access(100));
        System.out.println(treap.toString());
    }

    public static void task2() 
    {
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        Database db = new Database(columns, 100);

        System.out.println(".........Task 2........\n");
        System.out.println("Testing Database Insert");
        DBinsert(db, columns);
        PrintDB(db);

        // DBinsert(db, columns);
        // PrintDB(db);

        System.out.println("Creating Treaps");
        CreateTreaps(db);
        System.out.println();
        PrintTreaps(db, columns);
        System.out.println();

        System.out.println("Testing removeFirstWhere Function");
        // Removing Rows that dont exist, Treap
        // RemoveRows(db, "Module Code", "Test");
        // // Removing Rows that dont exist, Database
        // RemoveRows(db, "Session", "Test");
        // // Removing Rows that dont exist, Incorrect Col name
        // RemoveRows(db, "Test", "Test");
        // RemoveRows(db, "Module Code", "Test");
        // RemoveRows(db, "Credits", "6");
        // RemoveRows(db, "Module Code", "WTW124");
        // RemoveRows(db, "Session", "Year");
        // RemoveRows(db, "Module Code", "WTW114");
        // RemoveRows(db, "Module Code", "PHY114");
        // RemoveRows(db, "Module Code", "PHY124");
        // RemoveRows(db, "Module Code", "AIM102");
        // RemoveRows(db, "Module Code", "COS122");
        // RemoveRows(db, "Year", "2");
        // RemoveRows(db, "Module Code", "WTW123");
        // PrintDB(db);
        // PrintTreaps(db, columns);

        // System.out.println("Testing removeAllWhere Function");
        // // Removing Rows that dont exist, Treap
        // RemoveRowsAll(db, "Module Code", "Test");
        // // Removing Rows that dont exist, Database
        // RemoveRowsAll(db, "Session", "Test");
        // // Removing Rows that dont exist, Incorrect Col name
        // RemoveRowsAll(db, "Test", "Test");
        // RemoveRowsAll(db, "Credits", "16");
        // RemoveRowsAll(db, "Session", "Sem 1");
        // RemoveRowsAll(db, "Module Code", "UP0102");
        // RemoveRowsAll(db, "Description", "Numerical analysis 123");
        // RemoveRowsAll(db, "Year", "1");
        // PrintDB(db);
        // PrintTreaps(db, columns);

        System.out.println("Testing findFirstWhere Function");
        FindRow(db, "Module Code", "PHY114");
        FindRow(db, "Credits", "16");
        FindRow(db, "Session", "Year");
        FindRow(db, "Description", "Numerical analysis 123");
        FindRow(db, "Year", "2");
        System.out.println();
        // Doesnt Exist, Treap
        FindRow(db, "Module Code", "Test");
        // Doesnt Exist, Database
        FindRow(db, "Credits", "100");
        // Doesnt Exist, Incorrect Column Name
        FindRow(db, "Module", "100");
        PrintTreaps(db, columns);

        System.out.println("Testing findAllWhere Function");
        FindRowsAll(db, "Module Code", "PHY124");
        FindRowsAll(db, "Credits", "16");
        FindRowsAll(db, "Session", "Year");
        FindRowsAll(db, "Description", "Numerical analysis 123");
        FindRowsAll(db, "Year", "2");
        System.out.println();
        // Doesnt Exist, Treap
        FindRowsAll(db, "Module Code", "Test");
        // Doesnt Exist, Database
        FindRowsAll(db, "Credits", "100");
        PrintTreaps(db, columns);

        System.out.println("Testing updateFirstWhere");
        UpdateRows(db, "Module Code", "Test-1", "WTW124");
        UpdateRows(db, "Credits", "Credits", "0");
        UpdateRows(db, "Session", "Session", "Sem 2");
        UpdateRows(db, "Description", "Description", "Numerical analysis 123");
        UpdateRows(db, "Year", "Year", "2");
        // Testing Data that doesnt exist, Treap
        UpdateRows(db, "Module Code", "Year", "Test");
        // Testing Data that doesnt exist, Database
        UpdateRows(db, "Year", "Year", "10");
        // Testing Column Name that doesnt exist
        UpdateRows(db, "Test", "Year", "10");
        PrintDB(db);
        PrintTreaps(db, columns);

        System.out.println("Testing updateAllWhere");
        UpdateRowsAll(db, "Module Code", "Test-1", "WTW124");
        UpdateRowsAll(db, "Credits", "Credits", "0");
        UpdateRowsAll(db, "Session", "Session", "Sem 2");
        UpdateRowsAll(db, "Description", "Description", "Numerical analysis 123");
        UpdateRowsAll(db, "Year", "Year", "1");
        // Testing Data that doesnt exist, Treap
        UpdateRowsAll(db, "Module Code", "Year", "Test");
        // Testing Data that doesnt exist, Database
        UpdateRowsAll(db, "Year", "Year", "10");
        // Testing Column Name that doesnt exist
        UpdateRowsAll(db, "Test", "Year", "10");
        PrintDB(db);
        PrintTreaps(db, columns);

        System.out.println("Testing countOccurences");
        CountOccurences(db, "Module Code", "COS212");
        CountOccurences(db, "Credits", "16");
        CountOccurences(db, "Session", "Sem 1");
        CountOccurences(db, "Description", "Numerical analysis 123");
        CountOccurences(db, "Year", "1");
        // Testing Count that doesnt exist, Treap
        CountOccurences(db, "Module Code", "Test");
        // Testing Count that doesnt exist, Database
        CountOccurences(db, "Year", "Test");
        // Testing Count that doesnt exist, Invalid Column Name
        CountOccurences(db, "Module", "Test");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void DBinsert(Database db, String[] cols) {
        String[][] modules = {
                { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
                { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
                { "UP0102", "Academic orientation 102", "0", "1", "Year" },
                { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
                { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
                { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
                { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
                { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
                { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
                { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
        };
        try 
        {
            for (String[] mod : modules) 
            {
                db.insert(mod);
            }
            System.out.println(ANSI_GREEN + "Inserted Successfully " + ANSI_RESET);
        } 
        catch (DatabaseException e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET);
        }
    }

    public static void PrintDB(Database db)
    {
        System.out.println("Printing Database");
        for (String[] row : db.database) 
        {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void CreateTreaps(Database db)
    {
        try 
        {
            db.generateIndexAll();
            System.out.println(ANSI_GREEN + "Created Successfully " + ANSI_RESET);
        } 
        catch (DatabaseException e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET);
        }
    }

    public static void PrintTreaps(Database db, String[] cols)
    {
        for (int i = 0; i < 5; i++) 
        {
            System.out.println("Treap: " + cols[i]);
            System.out.println(db.indexes[i]);
        }
    }

    public static void RemoveRows(Database db, String colName, String Data)
    {
        try 
        {
            System.out.println("Removing: " + Data + "\nFrom: " + colName);
            String[] output = db.removeFirstWhere(colName, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                System.out.println(ANSI_GREEN + Arrays.toString(output) + " Deleted Successfully " + ANSI_RESET);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void RemoveRowsAll(Database db, String colName, String Data)
    {
        try 
        {
            System.out.println("Removing: " + Data + "\nFrom: " + colName);
            String[][] output = db.removeAllWhere(colName, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                for (int i = 0; i < output.length; i++) 
                {
                    System.out.println(ANSI_GREEN + Arrays.toString(output[i]) + " Deleted Successfully " + ANSI_RESET);
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void FindRow(Database db, String colName, String Data)
    {
        try 
        {
            System.out.println("Finding: " + Data + "\nFrom: " + colName);
            String[] output = db.findFirstWhere(colName, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                System.out.println(ANSI_GREEN + Arrays.toString(output) + " Found Successfully " + ANSI_RESET);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void FindRowsAll(Database db, String colName, String Data)
    {
        try 
        {
            System.out.println("Finding All Where: " + Data + "\nFrom: " + colName);
            String[][] output = db.findAllWhere(colName, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                for (int i = 0; i < output.length; i++) 
                {
                    System.out.println(ANSI_GREEN + Arrays.toString(output[i]) + " Found Successfully " + ANSI_RESET);
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void UpdateRows(Database db, String colName, String Data, String Update)
    {
        try 
        {
            System.out.println("Updating: " + Update + "\nFrom: " + colName + "\nTo: " + Data);
            String[] output = db.updateFirstWhere(colName, Update, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                System.out.println(ANSI_GREEN + Arrays.toString(output) + " Updated Successfully " + ANSI_RESET);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void UpdateRowsAll(Database db, String colName, String Data, String Update)
    {
        try 
        {
            System.out.println("Updating All Where: " + Update + "\nFrom: " + colName + "\nTo: " + Data);
            String[][] output = db.updateAllWhere(colName, Update, Data);

            if(output.length == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                for (int i = 0; i < output.length; i++) 
                {
                    System.out.println(ANSI_GREEN + Arrays.toString(output[i]) + " Updated Successfully " + ANSI_RESET);
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static void CountOccurences(Database db, String cols, String Data)
    {
        try 
        {
            System.out.println("Counting All Occurences\nData: " + Data + "\nFrom: " + cols);
            int output = db.countOccurences(cols, Data);

            if(output == 0)
            {
                System.out.println(ANSI_RED + "Error: Data not found in database" + ANSI_RESET + "\n");
            }
            else
            {
                System.out.println(ANSI_GREEN + "Occurences of [" + Data + "]: " + output + ANSI_RESET);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(ANSI_RED + "Error: " + e + ANSI_RESET + "\n");
        }
    }

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }
}
