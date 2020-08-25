// LeetCode 404. Sum of Left Leaves
// Find the sum of all left leaves in a binary tree.
// Ex.     3
//        / \
//       9   20
//           / \
//          15  7        = 9 + 15 = 24
//
// Intuition:
// This can be done via BFS or DFS.
// If the node satisfies the conditions of a left leaf, we add it to the running sum. What are the conditions of a left leaf?
// 1. if the root's left node has no left or right child
// 2. if it is the left most node in its level and it has no children
//
// O(n) runtime: all nodes will be visited
// O(n) space: worst case the recursion stack is filled with an execution context of all nodes


package TreeProblems;

public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int sum = 0;

        if (root.left != null) {    // check the left node
            if (root.left.left == null && root.left.right == null)  // if the left node is a leaf, add it to sum
                sum += root.left.val;
        }

        sum += sumOfLeftLeaves(root.left);  // recurse on the left node. If it was a leaf, this will return 0
        sum += sumOfLeftLeaves(root.right); // recurse on the right node

        return sum;
    }
}
