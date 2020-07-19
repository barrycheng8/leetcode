// THIS SOLUTION IS INCOMPLETE
//
// LeetCode 662. Maximum Width of Binary Tree
// Find the maximum width of a binary tree, which includes null nodes that are encapsulated between non-null nodes
// Ex.            1
//              /   \
//             3     2
//            / \     \
//           5   3     9   max width = 4 (5, 3, null, 9)
// Intuition: Think of the binary tree in the form of an array. At index i, the child indices are 2*i and 2*i + 1.
// Use 2 arrays, start[] and end[] to record the start and stop indices at each level of the tree.
// We then do a max function for each respective start and end index, add 1, to find the maximum level.
// Utilize DFS.

package TreeProblems;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        int max = 0;
        if (root == null) return max;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = 0;
            int size = q.size();
            while (n < size) {
                TreeNode next = q.poll();
                if (next.left != null) q.offer(next.left);
                if (next.right != null) q.offer(next.right);
                n++;
            }
        }
        return max;
    }
}
