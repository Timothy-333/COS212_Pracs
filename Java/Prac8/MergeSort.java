public class MergeSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Hint This function can be implemented as a one liner consisting of a return and a function call
        return mergeSort(arr);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] mergeSort(Comparable<T>[] arr){
        //Do not change the position of this function.
        printArr(arr);
        //Add any code below
        int first = 0;
        int last = arr.length-1;
        if(first < last)
        {
            int mid = getMidPoint(first, last);
            Comparable<T>[] left = new Comparable[mid+1];
            Comparable<T>[] right = new Comparable[last-mid];
            for (int i = 0; i < arr.length; i++) 
            {
                if (i <= mid) 
                {
                    left[i] = arr[i];
                }
                else if(i > mid)
                {
                    right[i-mid-1] = arr[i];
                }
            }
            left = mergeSort(left);
            right = mergeSort(right);
            return merge(left, right);
        }
        return arr;
    }
    @SuppressWarnings("unchecked")
    private Comparable<T>[] merge(Comparable<T>[] lh, Comparable<T>[] rh) 
    {
        int lcount = 0;
        int rcount = 0;
        int outCount = 0;
        Comparable<T>[] out = new Comparable[lh.length + rh.length];
        while (lcount < lh.length && rcount < rh.length) 
        {
            if (lh[lcount].compareTo((T) rh[rcount]) < 0) 
            {
                out[outCount++] = lh[lcount++];
            } 
            else 
            {
                out[outCount++] = rh[rcount++];
            }
        }
        while (lcount < lh.length) 
        {
            out[outCount++] = lh[lcount++];
        }
        while (rcount < rh.length) 
        {
            out[outCount++] = rh[rcount++];
        }
        return out;
    }

    private int getMidPoint(int first, int last)
    {
        return (int)Math.floor((first+last)/2);
    }
    
}
