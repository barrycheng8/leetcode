// LeetCode 938. Range Sum of BST
// Given the root of a BST, return the sum of all nodes with value between L and R inclusive.
// Intuition: Traverse through the BST using BFS and add each value to the running sum if it is between L and R inclusive
// O(n) runtime: must touch each node in the tree
// O(2^(h-1)) space: worst case space is the level with the most nodes. That is when the q is at max capacity.
// Space complexity can be influenced by how imbalanced the tree is.

package TreeProblems;

import java.util.LinkedList;
import java.util.Queue;

public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;

        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode next = q.poll();
            if (next.val >= L && next.val <= R)
                ans += next.val;
            if (next.left != null) q.offer(next.left);
            if (next.right != null) q.offer(next.right);
        }
        return ans;
    }
}
