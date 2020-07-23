// LeetCode 103. Binary Tree Zigzag Level Order Traversal
// Given a binary tree, return the zig zag level order traversal of its nodes (left to right, then right to left, and alternate)
// Ex.     3
//        / \
//       9   20
//           / \
//          15  7        =  [[3], [20, 9], [15, 7]]
//
// Intuition: We can approach this with either BFS or DFS.
// BFS: Using a deque, we will insert elements with the following rules:
//      - If level is even, insert the next level's (odd) elements to the back of the queue
//      - If level is odd, insert the next level's (even) elements in the front of the queue
//      - If level is even, process elements in the front of the queue
//      - If level is odd, process elements in the back of the queue.
//                                                   Level: 0      1        2
//      Using the example above, the queue will look like :[3 || 9, 20 || 15, 7]
// O(n) runtime: each node of the tree will be touched
// O(n) space: we will use a linked list to implement the deque. The result list will worst case have the same amount of values
// as the entire tree.

package TreeProblems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ZigZagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> deq = new LinkedList<>();
        deq.offer(root);
        int level = 0;

        while(!deq.isEmpty()) {
            int size = deq.size();
            List<Integer> nodes = new ArrayList<>();
            if (level % 2 == 0) {
                for (int i = 0; i < size; i++) {
                    TreeNode next = deq.pollFirst();
                    nodes.add(next.val);
                    if (next.left != null) deq.offerLast(next.left);
                    if (next.right != null) deq.offerLast(next.right);
                }
            }
            else {
                for (int i = 0; i < size; i++) {
                    TreeNode next = deq.pollLast();
                    nodes.add(next.val);
                    if (next.right != null) deq.offerFirst(next.right);
                    if (next.left != null) deq.offerFirst(next.left);
                }
            }
            level++;
            res.add(nodes);
        }
        return res;
    }
}
