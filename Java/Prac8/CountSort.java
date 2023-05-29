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
        

        //Do not change the statement below
        printArr(res, sumCount);
        //Ensure only return statement is below this
        
    }

    private int[] formCountArr(Comparable<T>[] arr){
        
    }

    private int[] sumCount(int[] countArr){
        
    }

    private int countArraySize(Comparable<T>[] arr){
        
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
