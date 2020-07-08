// LeetCode 101. Symmetric Tree
// Given a binary tree, check if it is a mirror of itself (symmetric around its center)
// Ex.      1                                            1
//         / \                                          / \
//        2   2                                        2   2
//       / \ / \                                        \   \
//      3  4 4  3   True                                 3   3    False
// Intuition: Use 2 queues to record the different traversals (left then right vs. right then left)
// Each time we pop out a new node, we compare to see if they are null or equal in value
// If we encounter a difference, we return false. This can be performed iteratively or recursively.
// O(n) runtime: 2n to be exact, in one queue we traverse left right, and the other right left. We must touch all nodes
// O(2^h) space: Using a queue, we traverse with BFS. The max amount of elements at once is equivalent to the level with the most nodes, which is max 2^h

package TreeProblems;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right); // Recurse on the left and right nodes from the root
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        // Base case
        if (left == null && right == null) return true;
        // If we have a mismatch in null nodes, we return false
        if ((left != null && right == null) || (left == null && right != null)) return false;

        // Nodes were not null. Check for value equivalence.
        // Then recurse to check if (far left and far right are equiv), and (inner left and inner right are equiv)
        return (left.val == right.val) && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static boolean isSymmetricIter(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> qLeft = new LinkedList<>();
        Queue<TreeNode> qRight = new LinkedList<>();

        qLeft.offer(root);
        qRight.offer(root);

        while (!qLeft.isEmpty() || !qRight.isEmpty()) {
            TreeNode nextLeft = qLeft.poll();
            TreeNode nextRight = qRight.poll();

            if ((nextLeft != null && nextRight != null) && (nextLeft.val != nextRight.val))
                return false;

            if ((nextLeft == null && nextRight != null) || (nextLeft != null && nextRight == null))
                return false;

            if (nextLeft != null) {
                qLeft.offer(nextLeft.left);
                qLeft.offer(nextLeft.right);
            }

            if (nextRight != null) {
                qRight.offer(nextRight.right);
                qRight.offer(nextRight.left);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
        System.out.println(isSymmetric(root));
    }
}
