// LeetCode 797. All Paths From Source to Target
// Given a directed acyclic graph of N nodes, find all possible paths from 0 to N - 1. Paths can be returned in any order.
// Ex. [[1, 2], [3], [3], []]
// At node 0, there is a path that goes to Node 1 and 2. Aka. edges (0, 1), (0, 2)
// At node 1, there is a path that goes to Node 3. Edges: (1, 3)
// At node 2, there is a path that goes to Node 3: Edges: (2, 3)
// There is no path that goes out from Node 3.
// 0--->1
// |    |
// v    v
// 2--->3    Output: [[0,1,3],[0,2,3]] These are all the paths from 0 to 3
//
// Intuition: Starting from node 0, we will traverse the nodes using DFS and backtracking to find out which paths
// are valid in reaching our destination node. Upon successfully reaching the node, add the path to the result and backtrack
// to the point where we had other paths we didn't check.
//
// O(2^n) runtime: In the worst case, Node 1 directly linked to Node N, and all other combinations in between, such as
// (1, 2, end), (1, 2, 3, end), (1, 2, 3, 4, end), etc. This then becomes a permutation of whether to include that node
// in the final path or not. Therefore we have 2 options for each node. There are n nodes, so O(2^n) time complexity
// if such an edge list exists.
// O(2^n) space: Worst case is the same scenario mentioned above, where we need to store all such paths in the returned list.

package Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourceToTarget {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        int dest = graph.length - 1;

        // Visit nodes using dfs, starting at node 0
        dfs(graph, path, res, 0, dest);

        return res;
    }

    static void dfs(int[][] g, List<Integer> path, List<List<Integer>> res, int idx, int dest) {
        if (idx == dest) {
            path.add(idx); // Destination reached. Add it to the result before copying it as a valid path
            res.add(new ArrayList<>(path)); // need to make a copy, cannot modify the original

            return;
        }

        path.add(idx); // Otherwise, add the current index that we are at

        // See what other indices we can visit from this index
        for (int i = 0; i < g[idx].length; i++) {
            dfs(g, path, res, g[idx][i], dest);
            path.remove(path.size() - 1); // After visiting that index, remove it from the path
        }
    }



    /* This is a slightly cleaner implementation of my original answer */
    /*
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        int dest = graph.length - 1;
        path.add(0); // We start at node 0

        dfs(graph, path, res, 0, dest);

        return res;
    }

    static void dfs(int[][] g, List<Integer> path, List<List<Integer>> res, int idx, int dest) {
        if (idx == dest) {
            res.add(new ArrayList<>(path)); // need to make a copy, cannot modify the original
            return;
        }

        for (int i = 0; i < g[idx].length; i++) {
            path.add(g[idx][i]);
            dfs(g, path, res, g[idx][i], dest);
            path.remove(path.size() - 1);
        }
    }
    */

    public static void main(String[] args) {
        int[][] input = {{1, 2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(input));
    }
}
