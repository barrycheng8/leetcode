package DP;

import java.util.PriorityQueue;

public class UglyNumberII {

    // A more efficient way is using DP and caching the results in an array
    // Initialize cache with the first ugly number
    // At each iteration, add the next smallest ugly number to the cache. Check if it is a duplicate of an ugly
    // number created by multiplying 2, 3, or 5. If it is, increment the index forward.
    // O(n) runtime: loop must iterate n times to achieve result
    // O(n) space: use an array to cache previous results up to n
    public static int nthUglyNumberV2(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2, index3, index5;
        index2 = index3 = index5 = 0;

        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(ugly[index2] * 2, Math.min(ugly[index3] * 3, ugly[index5] * 5));

            if (ugly[i] == ugly[index2] * 2)
                index2++;
            if (ugly[i] == ugly[index3] * 3)
                index3++;
            if (ugly[i] == ugly[index5] * 5)
                index5++;
        }
        return ugly[n - 1];
    }


    // Priority queue is quite slow
    public static int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);

        for (int i = 0; i < n - 1; i++) {
            long next = pq.poll();

            while (!pq.isEmpty() && pq.peek() == next) pq.poll();

            pq.offer(next * 2);
            pq.offer(next * 3);
            pq.offer(next * 5);
        }
        return pq.poll().intValue();
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1690));
        System.out.println(nthUglyNumberV2(1690));
    }
}
