import java.util.Arrays;

public class Searching{
    public static void main(String args[]){
        Searching tester = new Searching();
        System.out.println(Arrays.toString(tester.findRange(new int[] {1, 1, 1, 1, 1, 5, 7, 9, 9 ,9, 9, 9, 12, 15, 18, 19, 20, 20, 20, 20}, 25)));
        System.out.println(tester.targetExists(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60,65}}, 60));
    }

    public int[] findRange(int[] numbers, int target){
        int start = -1;
        int end = -1;
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;
        boolean found = false;
        while (!found && leftPointer <= rightPointer){
            //Floor division will cause an edge case when adjusting left and right pointers
            int middle = ((rightPointer - leftPointer) / 2) + leftPointer;
            if (numbers[middle] == target){
                found = true;
                start = end = middle;
                while (start != 0 && numbers[start - 1] == target)
                    start--;
                while (end != numbers.length - 1 && numbers[end + 1] == target)
                    end++;
            }
            else if (leftPointer == rightPointer)
                found = true;
            else if (numbers[middle] > target)
                rightPointer = middle;
            else{
                //Very specific edge case. Because floor division is used to find the middle, there are times when floor division returns 0
                //When adjusting the left pointer, must increment by at least one, so directly increment leftPointer by one if
                //middle of leftPointer and rightPointer is equal to leftPointer
                if (leftPointer == middle)
                    leftPointer++;
                else
                    leftPointer = middle;
            }
        }

        return new int[] {start, end};
    }

    public boolean targetExists(int[][] grid, int target){
        boolean found = false;
        int leftPointer = 0;
        int rightPointer = grid.length - 1;
        while (leftPointer <= rightPointer){
            //Floor division will again cause and edge case later when adjusting left and right pointers
            int middle = ((rightPointer - leftPointer) / 2) + leftPointer;
            //Two cases handled here. If left and right pointers are equal, searching either the end or beginning of the grid
            //Else, if the current number array should hold the search target
            //Both cases with return whether the current middle array contains the target
            if (leftPointer == rightPointer || (grid[middle][0] <= target && grid[middle + 1][0] > target)){
                return this.findRange(grid[middle], target)[0] != -1;
            }
            else if (grid[middle][0] > target)
                rightPointer = middle;
            else{
                if (leftPointer == middle)
                    leftPointer++;
                else
                    leftPointer = middle;
            }
        }
        return found;
    }
}