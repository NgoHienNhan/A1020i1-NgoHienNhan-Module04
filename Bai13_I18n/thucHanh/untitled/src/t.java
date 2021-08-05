public class t {
    public static void main(String[] args) {
        getArray();
        int[] arr = new int[5];
        arr[0] = 6;
        arr[1] = 8;
        arr[2] = 3;
        arr[3] = 5;
        arr[4] = 1;
        sortDESC(arr);
    }

    public static int[] getArray() {
        int[] arr = new int[255];
        for(int i = 0; i < 255 ; i++){
            arr[i]=i+1;
        }
        return arr;
    }

    public static int[] sortDESC(int [] arr) {
        int temp = arr[0];
        // code here
        for(int i = 0 ; i< arr.length -1; i++){
            temp = arr[i];
            for(int j = i+1; j < arr.length ; j++){
                if(arr[i] < arr[j]){
                 arr[i] = arr[j];
                 arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
