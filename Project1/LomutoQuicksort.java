import java.util.Arrays;
import java.util.Random;

public class LomutoQuicksort {

    static int keyComparisons, swaps;

    public static void main(String[] args) {
        // For Test Cases:
        int[] size100 = new int[100];
        int[] size1000 = new int[1000];
        int[] size10000 = new int[10000];

        System.out.println("\n        Lomuto's Quicksort Code:");

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
	System.out.println("All the same numbers test:\n");
        allTheSameNumbersArrays(size100);
        allTheSameNumbersArrays(size1000);
        allTheSameNumbersArrays(size10000);

	// RANDOM NUMBERS BETWEEN 1 AND 100000 ARRAY 
	//System.out.println("Random numbers between 1 and 100000 test:\n");
        //randomArrays(size100);
        //randomArrays(size1000);
        //randomArrays(size10000);

        int testArray[] = new int[]{5,4,2,7,1,6,3};

        lomutoHelper(size100, 0, size100.length - 1);
        System.out.printf("\nArray of 100 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);
        
        keyComparisons = 0;
        swaps = 0;

        lomutoHelper(size1000, 0, size1000.length - 1);
        System.out.printf("\n\nArray of 1000 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);

        keyComparisons = 0;
        swaps = 0;

        lomutoHelper(size10000, 0, size10000.length - 1);
        System.out.printf("\n\nArray of 10000 elements: \n# of Key Comparisons: %d\n# of Swaps: %d", keyComparisons, swaps);
    }

    public static int lomutoPartition(int[] A, int low, int high) {
        int last = A[high]; 
        int i = low - 1;
        
        for(int j=low; j<high; j++){
            if(A[j] <= last){
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                keyComparisons++;
            }
        }

        int temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;
        swaps++;

        return i+1;
    }

    public static void lomutoHelper(int[] A, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(A, low, high);
            lomutoHelper(A, low, pi - 1);
            lomutoHelper(A, pi + 1, high);
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
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((1000000) + 1);
        }
        return array;
    }
}  


