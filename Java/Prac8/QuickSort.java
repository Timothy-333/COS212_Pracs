public class QuickSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        // printArr(arr);
        //Add code below this line
        if(arr == null || arr.length == 0)
            return arr;
        return recSort(arr, new Comparable[arr.length]);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] recSort(Comparable<T>[] arr, Comparable<T>[] resultingArr){
        printArr(arr);
        //Add code below this line 
        if (arr.length <= 1) 
        {
            return arr;
        }
        int pivotPoint = getPivotPoint(arr);
        Comparable<T> pivot = arr[pivotPoint];
    
        Comparable<T>[] left = new Comparable[arr.length];
        Comparable<T>[] right = new Comparable[arr.length];
        int leftIndex = 0;
        int rightIndex = 0;
    
        for (int i = 0; i < arr.length; i++) {
            if (i == pivotPoint) {
                continue;
            }
            if (arr[i].compareTo((T) pivot) < 0) {
                left[leftIndex++] = arr[i];
            } else {
                right[rightIndex++] = arr[i];
            }
        }
    
        Comparable<T>[] leftArr = new Comparable[leftIndex];
        Comparable<T>[] rightArr = new Comparable[rightIndex];
    
        System.arraycopy(left, 0, leftArr, 0, leftIndex);
        System.arraycopy(right, 0, rightArr, 0, rightIndex);
    
        leftArr = recSort(leftArr, leftArr);
        rightArr = recSort(rightArr, rightArr);
    
        int index = 0;
        for (int i = 0; i < leftArr.length; i++) {
            resultingArr[index++] = leftArr[i];
        }
        resultingArr[index++] = pivot;
        for (int i = 0; i < rightArr.length; i++) {
            resultingArr[index++] = rightArr[i];
        }
        return resultingArr;
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
