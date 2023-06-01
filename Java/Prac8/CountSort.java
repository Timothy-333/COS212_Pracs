public class CountSort<T extends Comparable<T>> extends Sort<T> {
    
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Do not alter given code nor add code above
        int[] count = formCountArr(arr);
        printArr(arr, count);
        int[] sumCount = sumCount(count);
        printArr(arr, sumCount);
        Comparable<T>[] res = new Comparable[arr.length]; //This array needs to be populated with the final result
        //Add code below this
        for (int i = res.length-1; i>=0; i--) 
        {
            res[sumCount[arr[i].hashCode()]-1] = arr[i];
            sumCount[arr[i].hashCode()]--;
        }
        //Do not change the statement below
        printArr(res, sumCount);
        //Ensure only return statement is below this
        return res;
    }

    private int[] formCountArr(Comparable<T>[] arr)
    {
        int size = countArraySize(arr);
        int[] countArray = new int[size];
        for (int i = 0; i < countArray.length; i++) 
        {
            countArray[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) 
        {
            countArray[arr[i].hashCode()]++;
        }
        return countArray;
    }

    private int[] sumCount(int[] countArr)
    {
        int[] sumArr = new int[countArr.length];
        sumArr[0] = countArr[0];
        for (int i = 1; i < countArr.length; i++) 
        {
            sumArr[i] = countArr[i] + sumArr[i-1];
        }
        return sumArr;
    }

    private int countArraySize(Comparable<T>[] arr)
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) 
        {
            if(arr[i].hashCode() > max)
            {
                max = arr[i].hashCode();
            }
        }
        return max+1;
    }

    private void printArr(Comparable<T>[] arr, int[] count){
        if(arr == null || count == null)
            System.out.println("NULL ARRAYS");

        String res = "[";
        for(Comparable<T> t: arr){
            res += t + "{" + count[t.hashCode()] + "},";
        }
        if(res.length() > 0){
            res = res.substring(0, res.length()-1);
        }
        res += "]";
        System.out.println(res);
    }


}
