// LeetCode 100. Same Tree
// Given two binary trees, create a function that checks whether the are the same or not.
// Two trees are considered the same if they are structurally identical and the nodes have the same value.
// Intuition: Starting from the root of each tree, we want to check if they are null or not.
// This is our base case. If we encounter a null node, we check if q also has a null node.
// Otherwise, we know we will not encounter a NullPointerException. We dereference the node value and check for equality.
// If equal, recurse on the left and right nodes.
// If the de-referenced values are not equal, we stop and return false.
// O(n) runtime: Each node is visited once
// O(n) space: worst case, the tree is a fully unbalanced tree (completely left or right) and all nodes end up on the recursion stack.

package TreeProblems;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
