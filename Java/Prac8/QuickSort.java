public class QuickSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        printArr(arr);
        //Add code below this line
        
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] recSort(Comparable<T>[] arr, Comparable<T>[] resultingArr){
        printArr(arr);
        //Add code below this line
        
    }

    private int getPivotPoint(Comparable<T>[] arr){
        if(arr == null || arr.length == 0)
            return 0;

        if(arr.length % 2 == 0)
            return (int)Math.floor(arr.length/2)-1;
        else 
            return (int)Math.floor(arr.length/2);
    }
}
