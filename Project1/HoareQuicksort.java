import java.util.Arrays;
import java.util.Random;

public class HoareQuicksort {
    
    static int keyComparisons, swaps;

    public static void main(String[] args) {
        // 3 Test Cases:
        int[] size100 = new int[100];
        int[] size1000 = new int[1000];
        int[] size10000 = new int[10000];

	System.out.println("\n        Hoare's Quicksort Code:");

	// ASCENDING ARRAY 
	//System.out.println("Distinct numbers in ascending order test:\n");
        //ascendingArrays(size100);
        //ascendingArrays(size1000);
       	//ascendingArrays(size10000);
  
	// DESCENDING ARRAY 
	//System.out.println("Distinct numbers in descending order test:\n");
        //descendingArrays(size100);
        //descendingArrays(size1000);
        //descendingArrays(size10000);

	//ALL THE SAME NUMBERS ARRAY
	//System.out.println("All the same numbers test:\n");
        //allTheSameNumbersArrays(size100);
        //allTheSameNumbersArrays(size1000);
        //allTheSameNumbersArrays(size10000);

	// RANDOM NUMBERS BETWEEN 1 AND 100000 ARRAY 
	//System.out.println("Random numbers between 1 and 100000 test:\n");
        //randomArrays(size100);
        //randomArrays(size1000);
        //randomArrays(size10000);

        
	
	hoareHelper(size100, 0, size100.length - 1);
        System.out.printf("\nArray of 100 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);
        
	keyComparisons = 0;
        swaps = 0;

        hoareHelper(size1000, 0, size1000.length - 1);
        System.out.printf("\n\nArray of 1000 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);

        keyComparisons = 0;
        swaps = 0;

        hoareHelper(size10000, 0, size10000.length - 1);
        System.out.printf("\n\nArray of 10000 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);
    }

    public static void hoareHelper(int[] A, int low, int high) {
        
	if (low < high) {
	    Random random = new Random();
            int randomIndex = random.nextInt(high - low + 1) + low;
            
	    int pi = hoarePartition(A, low, high);
            hoareHelper(A, low, pi);
            hoareHelper(A, pi + 1, high);
        }
    }

    public static int hoarePartition(int[] A, int low, int high) {
        int data = A[low];
        int i = low - 1, j = high + 1;
        while (true) {
            do {
                i = i + 1;
                keyComparisons++;
            } while (A[i] < data);

            do {
                j--;
            } while (A[j] > data);

            if (i >= j)
                return j;

            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            swaps++;
        }
    }

    public static int[] ascendingArrays(int array[]){
        int test = 0;
        for(int i = 0; i < array.length; i++){
            array[i] = test++;
        }
        return array;
    }

    public static int[] descendingArrays(int array[]){
        int test2 = array.length;
        for(int i = 0; i < array.length; i++){
            array[i] = test2--;
        }
        return array;
    }

    public static int[] allTheSameNumbersArrays(int array[]){
        int test3 = 7;
        for(int i = 0; i < array.length; i++){
            array[i] = test3;
        }
        return array;
    }

    public static int[] randomArrays(int array[]) {
        Random test4 = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = test4.nextInt((1000000) + 1);
        }
        return array;
    }
}
