
/**
 * Maman 14.
 * 
 * Answer to question 1:
 * The true sentences are: 3,5,6.
 * 
 * @author Tovi Berkovich
 */
public class Ex14
{
    /**
     * Finds if the array (that returns true in method What) contains a certain value.
     * @param m The array that is searched in.
     * @param val The value that is searched for.
     * @return True if the value has been found.
     * Time complexity: O(n).
     * Storage complexity: O(1).
     */
    public static boolean findValWhat(int[][] m, int val){
        int row = 0;
        int col = m.length - 1;
        while(row < m.length && col >= 0){
            if(m[row][col] == val)
                return true;

            //Every row in the array is sorted, therefore if the value is less than the current cell, then the value can only be to the left of the current cell.
            //If the value is greater than the current cell, the value can be in a lower row.
            if(m[row][col] > val)
                col --;
            else if(m[row][col] < val){
                row ++;
                col = m.length-1;
            }
        }
        return false;
    }

    /**
     * Finds if the array (that returns true in method Test) contains a certain value.
     * 
     * Time complexity: O(n).
     * Storage complexity: O(1).
     *
     * @param m The array to be searched in.
     * @param val The value that is searched for.
     * @return True if the value has been found.
     */
    public static boolean findValTest(int[][] m, int val){
        int col = 0;
        int row = 0;
        while(row < m.length){
            //Search in the row.
            if(m[row][col] == val)
                return true;
            else
                col++;

            //If the value hasn't been found, search in the row below. 
            if(col == m.length){  
                if(m[row][col - 1] > val)
                    return false;
                col = 0;
                row ++;
            }
        }
        return false;
    }

    /**
     * Returns the number of sub-arrays that are arranged in an increasing order.
     * 
     * Time complexity: O(n).
     * Storage complexity: O(1).
     * 
     * @param a The array to be checked.
     * @return The number of sub-arrays that are arranged in an increasing order.
     */
    public static int strictlyIncreasing(int[] a){
        int counter = 0;
        int lower = 0;
        int upper = 1;
        if(a.length <= 0) //If the array is empty, there is no sub-arrays.
            return 0;

        while(lower < upper){
            //If the lower and the upper are in the correct order, and the upper is grater than its previous.
            if(upper < a.length && a[lower] < a[upper] && a[upper - 1] < a[upper]){
                counter ++;
                upper++;
            }else{
                //Start a new check from the next cell.
                lower ++; 
                upper = lower + 1;
            }
        }
        return counter;
    }

    /**
     * Returns the length of the longest flat sequence.
     * Flat sequence is a sequence which is consists of only two consecutive numbers.
     * @param arr The array that is checked.
     * @return The length of the longest flat sequence in the array.
     */
    public static int longestFlatSequence(int[] arr){
        if(arr.length <= 0)
            return 0;
        return longestFlatSequence(arr, 0);
    }

    private static int longestFlatSequence(int[] arr, int cur){
        if(cur == arr.length - 1) //If the current index is the last cell, the length is 1.
            return 1;

        //Return the maximum between the current length and the longest length that is found when starting the sequence one cell to the right.
        return Math.max(lengthFlat(arr,cur,cur), longestFlatSequence(arr, cur+1)); 
    }

    private static int lengthFlat(int[] arr, int i, int start){
        if(i == arr.length - 1) 
            return 1;

        if(i == start) //If the index is in the starting point, don't check for the previous cell. 
            if(nextTo(arr, i, i+1))
                return(lengthFlat(arr, i+1, start) + 1); //Reuturn 1 plus the length of the sequence from the following cell.

        //Check if the current and the following numbers are consecutive and if the previous and the following numbers are consecutive.
        if(nextTo(arr, i, i+1) && nextTo(arr, i+1, i-1)) 
            return(lengthFlat(arr, i+1, start) + 1);
        return 1;
    }

    private static boolean nextTo(int[] arr, int i, int j){
        return (arr[i] == arr[j] || arr[i] == arr[j] + 1 || arr[i] == arr[j] - 1); //Check if two numbers are consecutive.
    }

    /**
     * Returns the highest sum of values of a route in the array.
     * The array has only 0,1,-1 values, and the route can go only through 0 and 1.
     * In an even index of row, we can go only right or down.
     * In an odd index of row, we can go only left or down.
     * @param mat The array that is checked.
     * @return The highest sum of values of a route in the array.
     */
    public static int findMaximum(int[][] mat){
        if(mat.length <= 0 || mat[0].length <= 0)
            return 0;
        if(mat[0][0] == -1)
            return -1;
        return findMaximum(mat,0,0);
    }

    private static int findMaximum(int[][] mat, int row, int col){
        int curMax = mat[row][col];

        if(row%2 == 0){ //Checks if the index of the row is even.
            //Check if we can move to the right.
            if(col+1 < mat[0].length && mat[row][col+1] >= 0)
            //Set the current maximum to the greater between the maximum found starting from the following cell plus the number in the current cell, and the current maximum.
                curMax = Math.max(mat[row][col] + findMaximum(mat,row,col+1), curMax);             
            if(row+1 < mat.length && mat[row+1][col] >= 0)
                curMax = Math.max(mat[row][col] + findMaximum(mat,row + 1,col), curMax);
        }else{
            if(col-1 >= 0 && mat[row][col-1] >= 0)
                curMax = Math.max(mat[row][col] + findMaximum(mat,row,col-1), curMax);
            if(row+1  < mat.length && mat[row+1][col] >= 0)
                curMax = Math.max(mat[row][col] + findMaximum(mat,row + 1,col), curMax);
        }
        return curMax;
    }

    public static boolean what(int [][] m)
    {
        int n=m.length;
        for(int x=0; x<n; x++)
            for (int y=0; y<(n-1); y++)
                if(m[x][y] > m[x][y+1]) 
                    return false;
        for(int x=0; x<n; x++)
            for (int y=0; y<(n-1); y++)
                if(m[y][x] > m[y+1][x]) 
                    return false;
        return true;
    }

    public static boolean test(int [][] m)
    {
        int n=m.length;
        for(int r=0; r<(n-1); r++)
            for (int c=0; c<n; c++)
                for (int i=0; i<n; i++)
                    if(m[r][c] > m[r+1][i]) 
                        return false;
        return true;
    }
}
