@SuppressWarnings("unchecked")
public class minDHeap<T extends Comparable<T>> {
    private int d;
    private T[] nodes;

    @Override
    public String toString() {
        if (nodes.length == 0) {
            return "";
        }

        return "[" + nodes[0] + "]\n" + toStringRec(0, "");
    }

    public String toStringRec(int i, String pre) {
        if (i >= nodes.length) {
            return "";
        }
        String res = "";
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                if (k == d - 1 || c + 1 >= nodes.length) {
                    res += pre + "└── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "    ");
                } else {
                    res += pre + "├── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "│   ");
                }
            }
        }
        return res;
    }

    public T[] getNodes() {
        return nodes;
    }

    /*
     * Don't change anything above this line
     */

    public minDHeap(int d) 
    {
        this.d = d;
        nodes = (T[]) new Comparable[0];
    }

    public void insert(T val) 
    {
        T[] temp = (T[]) new Comparable[nodes.length + 1];
        for (int i = 0; i < nodes.length; i++) 
        {
            temp[i] = nodes[i];
        }
        temp[nodes.length] = val;
        nodes = temp;
        int i = nodes.length - 1;
        while (i > 0 && nodes[i].compareTo(nodes[(i - 1) / d]) < 0) 
        {
            T temp1 = nodes[i];
            nodes[i] = nodes[(i - 1) / d];
            nodes[(i - 1) / d] = temp1;
            i = (i - 1) / d;
        }
    }

    public void remove(T val) 
    {
        int i = 0;
        while (i < nodes.length && !nodes[i].equals(val)) 
        {
            i++;
        }
        if (i == nodes.length) 
        {
            return;
        }
        nodes[i] = nodes[nodes.length - 1];
        T[] temp = (T[]) new Comparable[nodes.length - 1];
        for (int j = 0; j < temp.length; j++) 
        {
            temp[j] = nodes[j];
        }
        nodes = temp;
        while (i < nodes.length) 
        {
            int min = i;
            for (int j = 1; j <= d; j++) 
            {
                if (d * i + j < nodes.length && nodes[d * i + j].compareTo(nodes[min]) < 0) 
                {
                    min = d * i + j;
                }
            }
            if (min != i) 
            {
                T temp1 = nodes[i];
                nodes[i] = nodes[min];
                nodes[min] = temp1;
                i = min;
            } 
            else 
            {
                break;
            }
        }
    }

    public void changeD(int newD) 
    {
        d = newD;
        T[] temp = (T[]) new Comparable[nodes.length];
        for (int i = 0; i < nodes.length; i++) 
        {
            temp[i] = nodes[i];
        }
        nodes = temp;
        for (int i = nodes.length - 1; i >= 0; i--) 
        {
            while (i < nodes.length) 
            {
                int min = i;
                for (int j = 1; j <= d; j++) 
                {
                    if (d * i + j < nodes.length && nodes[d * i + j].compareTo(nodes[min]) < 0) 
                    {
                        min = d * i + j;
                    }
                }
                if (min != i) 
                {
                    T temp1 = nodes[i];
                    nodes[i] = nodes[min];
                    nodes[min] = temp1;
                    i = min;
                } 
                else 
                {
                    break;
                }
            }
        }
    }

    public T min(int i) 
    {
        if (i >= nodes.length || i < 0 || nodes.length == 0) 
        {
            return null;
        }
        int min = i;
        for (int j = 1; j <= d; j++) 
        {
            if (d * i + j < nodes.length && nodes[d * i + j].compareTo(nodes[min]) < 0) 
            {
                min = d * i + j;
            }
        }
        return nodes[min];
    }

    public T max(int i) 
    {
        if (i >= nodes.length || i < 0 || nodes.length == 0) {
            return null;
        }
        MaxObject<T> max = new MaxObject<>(nodes[i]);
        maxHelper(i, max);
        return max.getValue();
    }
    
    private void maxHelper(int i, MaxObject<T> max) 
    {
        for (int j = 1; j <= d; j++) 
        {
            int childIndex = d * i + j;
            if (childIndex < nodes.length) 
            {
                T childValue = nodes[childIndex];
                if (childValue.compareTo(max.getValue()) > 0) 
                {
                    max.setValue(childValue);
                }
                maxHelper(childIndex, max);
            }
        }
    }
    
    private static class MaxObject<T> 
    {
        private T value;
    
        public MaxObject(T value) 
        {
            this.value = value;
        }
    
        public T getValue() 
        {
            return value;
        }
    
        public void setValue(T value) 
        {
            this.value = value;
        }
    }

    public String pathToRoot(T val) 
    {
        int i = 0;
        while (i < nodes.length && !nodes[i].equals(val)) 
        {
            i++;
        }
        if (i == nodes.length) 
        {
            return "";
        }
        String res = "[" + nodes[i] + "]";
        while (i > 0) 
        {
            res += "[" + nodes[(i - 1) / d] + "]";
            i = (i - 1) / d;
        }
        return res;
    }

}
