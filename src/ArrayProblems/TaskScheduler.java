// LeetCode 621. Task Scheduler
// Given a char[] containing CPU tasks represented by capital letters from A - Z, where each letter represents a different task.
// The task can be done in any order, and each task takes one unit of time.
// The CPU can be either idle or performing the given task.
// The parameter n (non-negative int) is given, representing the cooldown period between two of the same tasks.
// For example if n is 2, and the task list is [A, A], the CPU must wait 2 cycles before performing A again. A -> idle -> idle -> A
// Return the least number of units that the CPU will take to finish the given task
// Ex. Tasks [A, A, A, B, B, B], n = 2      Output = 8
// A -> B -> idle -> A -> B -> idle -> A -> B
//
// Intuition: We know the least number of units will be dictated by teh task that appears the most frequently.
// First we find this task by iterating through the char[] and storing frequency in charFreqs. We then sort the array to figure out
// which task appears the most frequently. Knowing this, we can determine how many idle spots there will be if we only execute the most frequent task
// For example, if we have [A, A, A], n = 3, we know the spaces will be A _ _ _ A _ _ _ A. Therefore the minimum time is 9. We are finished at the last execution
// of A, so we obtain the number of idle spots using (n * (freq - 1)).
// Next, we iterate through the frequencies of the other characters and fit them into the idle spots.
// At the end, if we used up all idle spots, we can just return the length of the task, because all tasks fit within the idle spots, and we know there are no leftover idle spots
// Otherwise, we count how many idle spots are left and how many tasks we executed.
//
// O(n log n) runtime: sorting the frequency array will take nlogn time
// O(1) space: an array of size 26 is used to store the frequency of each task
// This problem can also be solved in O(n) time and O(1) space

package ArrayProblems;

import java.util.Arrays;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] charFreqs = new int[26];

        // Iterate over the tasks and increment the occurrence of each task
        // freq[0] = freq of A, freq[1] = freq of B, etc.
        for (char c : tasks)
            charFreqs[c - 'A']++;

        Arrays.sort(charFreqs);

        // After sorting, the most frequent task will appear at the end.
        // The number of chunks is equivalent to the most frequent task - 1
        int chunkCount = charFreqs[25] - 1;
        int idleSpots = chunkCount * n;

        // Iterate over the other tasks and see if they can fit into the idle spots
        // Logic:
        // If the task count is equal to the chunkCount, then we fit as much as we can in each chunk (decrementing) and add one last execution at the end
        // If task count is less than the chunkCount, then we fit each execution into the available chunks and decrement how many idle spots we have left.
        for (int i = 24; i >= 0; i--) {
            idleSpots -= Math.min(charFreqs[i], chunkCount);
        }

        return (idleSpots < 0) ? tasks.length : idleSpots + tasks.length;
    }

    // O(n) solution: 1 pass through the array
    // O(1) space: frequency array of size 26
    public static int leastIntervalV2(char[] tasks, int n) {
        int[] charFreqs = new int[26];
        int max = 0;
        int maxCount = 0;

        // Loop over all tasks. Determine which tasks appears most frequent and how many times that task occurs.
        for (char c : tasks) {
            charFreqs[c - 'A']++; // Increment the task occurrence
            if (max < charFreqs[c - 'A']) { // Reset the max count and the value of the task that is most frequent
                max = charFreqs[c - 'A'];
                maxCount = 1;
            }
            else if (max == charFreqs[c - 'A']) // Otherwise we found another task that is equal to the max. Increment count
                maxCount++;
        }

        // Determine how many chunks of idles need to be in between the most frequent task
        int chunkCount = max - 1;

        // Determine how many spaces are leftover after weaving in any other tasks is maxCount > 1
        // E.g. [A, A, A, B, B, B];   A _ _ A _ _ A -> A B _ A B _ A B
        // 1 idle spot remaining after weaving in B, which also was a max occurrence
        int idleSpotsAfterMaxFreqs = n - (maxCount - 1);

        // Determine the total of these idle spots. That is, multiply how many spots are leftover with the amount of chunks we have
        int availableIdleSpots = chunkCount * idleSpotsAfterMaxFreqs;

        // Determine how many tasks were eliminated doing the prior 3 calculations
        int remainingTasks = tasks.length - (maxCount * max);

        // If any tasks are leftover at this point, they are guaranteed to not be part of the maxCount.
        // We safely fill in the remaining tasks in the available idle spots.
        // 1. If more tasks remain than idle spots, then we simply need to return the length of the task list.
        //    This is because all tasks can be performed without any idles in between
        // 2. If we still have idle spots after filling in the remaining tasks, that means the idle spaces were bigger
        //    than the amount of available tasks given. We need to factor in those idle spaces when we determine the final CPU execution time.
        int finalRemainingIdles = Math.max(0, availableIdleSpots - remainingTasks);

        return tasks.length + finalRemainingIdles;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'B', 'B', 'A', 'B'};
        System.out.println(leastIntervalV2(tasks, 2));
    }
}
