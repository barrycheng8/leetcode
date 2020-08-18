// LeetCode 1103. Distribute Candies to People
// We need to distribute candies to people in the following way:
// Give 1 candy to the first person, 2 to the second, until we give n to the last person
// We then go back to the beginning, giving n+1 to the first person, n+2 to the second, and so on until we give 2n to the last person
// Repeat until all candies run out. The last person receives all left over candies if the required amount cannot be fulfilled.
//
// Intuition:
// Straightforward loop until all candies are exhaust. Use variables to keep track of current position in the array
// and the amount of candies to give at each iteration. Once we run out, break the loop and return the resulting array.
//
// Growth functions:
// O(sqrt(candies)) runtime: The total amount of candies distributed is c * (c + 1)/2, until it becomes bigger than the leftover
//                           candies. Because the solution is being solved in candies^2 speed, the problem shrinks in that size, which is sqrt(candies)
// O(n) space

package ArrayProblems;

public class DistributeCandies {

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int dist = 1;
        int pos = 0;

        while (candies > 0) {
            if (candies >= dist) {
                res[pos % num_people] += dist;
                candies -= dist;
                dist++;
            }
            else {
                res[pos % num_people] += candies;
                break;
            }
            pos++;
        }
        return res;
    }

    public int[] distributeCandiesShort(int candies, int num_people) {
        int[] res = new int[num_people];

        for (int i = 0; candies > 0; i++) {
            res[i % num_people] = Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return res;
    }
}
