public class test {
    public static void main(String[] args) {
        int[] arr = {6,8,3,5,1};
        sortDESC(arr);
    }

    public static int[] sortDESC(int [] arr) {
        // code here
        for(int i =0; i<(arr.length-1); i++){
            int temp=arr[i];
            for(int j=(i+1); j <arr.length;j++){
                if(arr[i]<arr[j]){
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }
}
