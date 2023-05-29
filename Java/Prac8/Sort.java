abstract class Sort<T extends Comparable<T>> {
    public abstract Comparable<T>[] sort(Comparable<T>[] arr);

    public static <T extends Comparable<T>> void printArr(Comparable<T>[] arr){
        if(arr == null)
            System.out.println("NULL ARRAY");

        String res = "[";
        for(int i=0; i < arr.length; i++){
            res += arr[i] + ";";
        }
        if(arr.length > 0)
            res = res.substring(0, res.length()-1);
        res += "]";
        System.out.println(res);
    }
}
