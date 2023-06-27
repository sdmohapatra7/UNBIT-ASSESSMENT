import java.util.Arrays;
import java.util.Scanner;

public class App {

    // function for finding the integers pair whose sum equal to target and store in
    // 2D array
    public static int[][] findPairs(int[] arr, int target) {
        int[][] pairs = new int[arr.length][2];
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    pairs[count][0] = arr[i];
                    pairs[count][1] = arr[j];
                    count++;
                }
            }
        }
        return Arrays.copyOfRange(pairs, 0, count);
    }

    // function for merge the 2D array in to single array and sort in ascending
    // order
    public static int[] mergeAndSort(int[][] arr) {
        int totalElements = 0;
        // for-each loop or an enhanced for loop in Java .The for-each loop is a
        // convenient way to iterate over elements in an array or a collection. It
        // simplifies the process of accessing elements without the need for an explicit
        // index variable.
        for (int[] subArray : arr) {
            totalElements += subArray.length;
        }

        int[] mergedArray = new int[totalElements];
        int index = 0;

        for (int[] subArray : arr) {
            for (int num : subArray) {
                mergedArray[index] = num;
                index++;
            }
        }

        Arrays.sort(mergedArray);

        return mergedArray;
    }

    public static int[][] findCombinations(int[] arr, int target) {
        int[][] combinations = new int[0][];
        int[] currentCombination = new int[arr.length];
        int index = 0;

        combinations = backtrack(arr, target, combinations, currentCombination, index);

        return combinations;
    }

    public static int[][] backtrack(int[] arr, int target, int[][] combinations, int[] currentCombination, int index) {
        if (target == 0) {
            combinations = addCombination(combinations, currentCombination, index);
            return combinations;
        }

        for (int i = index; i < arr.length; i++) {
            if (i > index && arr[i] == arr[i - 1]) {
                // Skip duplicates to avoid duplicate combinations
                continue;
            }

            if (target - arr[i] >= 0) {
                currentCombination[index] = arr[i];
                combinations = backtrack(arr, target - arr[i], combinations, currentCombination, index + 1);
                currentCombination[index] = 0;
            }
        }
        return combinations;
    }

    public static int[][] addCombination(int[][] combinations, int[] currentCombination, int index) {
        int[][] newCombinations = Arrays.copyOf(combinations, combinations.length + 1);
        newCombinations[combinations.length] = Arrays.copyOfRange(currentCombination, 0, index);
        return newCombinations;
    }

    public static void main(String[] args) throws Exception {
        try (// int[] arr = { 1, 3, 2, 2, -4, -6, -2, 8 };
             // int target = 8;
                Scanner s = new Scanner(System.in)) {
            System.out.println("Enter the length of the array:");
            int n = s.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Enter element at " + i + "th index");
                arr[i] = s.nextInt();
            }
            System.out.println("Enter the target element:");
            int target = s.nextInt();
            int[][] pairs = findPairs(arr, target);

            System.out.println("The pairs having sum of " + target + " :");
            for (int i = 0; i < pairs.length; i++) {
                int[] pair = pairs[i];
                System.out.println(Arrays.toString(pair));
            }

            int[] mergedArray = mergeAndSort(pairs);

            System.out.println("Merged and sorted array:");
            for (int num : mergedArray) {
                System.out.print(num + " ");
            }
            System.out.println();

            int doubleTarget = 2 * target;
            int[][] combinations = findCombinations(mergedArray, doubleTarget);

            System.out.println("Combinations that equal to " + doubleTarget + ":");
            for (int[] combination : combinations) {
                System.out.println(Arrays.toString(combination));
            }
        }
    }

}
