import java.util.*;

public class PerfectSquares {
    public static int numSquaresBFS(int n) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();

        int ans = 0;
        q.offer(0);

        while (!q.isEmpty()) {
            int size = q.size();
            ans++;
            while (size-- > 0) {
                int next = q.poll();
                for (int i = 1; i * i <= n; i++) {
                    int newVal = next + i * i;
                    if (newVal == n) {
                        return ans;
                    }
                    if (!seen.contains(newVal) && newVal <= n) {
                        seen.add(newVal);
                        q.offer(newVal);
                    }
                }
            }
        }
        return ans;
    }

    public static int numSquaresDP(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;

        for (int i = 1; i <= n; i++) {
            ans[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                ans[i] = (int) Math.min(ans[i], 1 + ans[i - (j * j)]);
            }
        }
        return ans[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquaresBFS(12));
        System.out.println(numSquaresDP(12));
    }
}