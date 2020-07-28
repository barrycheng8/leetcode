// LeetCode 106. Construct Binary Tree from Inorder and Postorder Traversal
// Given the inorder and postorder traversals of a tree, construct the binary tree
// Example:
// Inorder = [9, 3, 15, 20, 7]      Postorder = [9, 15, 7, 20, 3]
//         3
//        / \
//       9   20
//           / \
//          15  7    This would be the resulting tree
//
// Intuition: Inorder and postorder traversals tell us different things about the tree.
// Inorder: tells you which nodes will be left of the node, and which nodes will be right of it
// Postorder: the final element in the array will give you the root node of the tree
// Using this information, we start with a global index that points to the last element in the postorder array.
// Then we perform the following steps:
// 1. Using the 'build' function, we set the last element to the root
// 2. Search the inorder array to find the index of the node that we created
//      For example, we created 3 as the root. We then search inorder and return 1, because 3 is at index 1.
//      We then know that 9 is left of 3, and [15, 20, 7] are to the right of 3.
// 3. Next, we recurse on the right nodes.
//      This will increment start to end. Once we reach that case, we return all the nodes up to the next level up node that contains a left node
// 4. After completing the right nodes, recurse on the left. This follows the postorder listing which is governed by the global variable
//      Once we finish processing all the left nodes, we return it and continue with the left side
// 5. Return the tree after recursing
//
// O(n^2) runtime: We touch each node, and then scan the inorder array to determine where that node lies in respect to the rest of tree.
// worst case the tree is completely skewed to the right, and each node only has right nodes. Then we must traverse through the entire inorder array to
// locate the index of the next node to build the tree
// O(n) space: The tree size will be equivalent to the size of the passed in inorder + postorder array

package TreeProblems;

public class BuildTreeInorderPost {

    // Global index that will keep track of the next node we are working on
    // Because postorder is left, right, root, we initialize 'index' to the end of the postorder array
    // and recurse right then left. We declare this in the class scope so that it does not change in the context of each function.
    private static int index;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) return null;
        index = postorder.length - 1;

        // Recursive call to build tree on the entire array
        TreeNode root = build(inorder, postorder, 0, index);
        return root;
    }

    public static TreeNode build(int[] inorder, int[] postorder, int start, int end) {
        if (start > end) return null; // Base case if we already finish processing the level
        TreeNode node = new TreeNode(postorder[index--]); // Create the node
        if (start == end) return node; // This case will hit once we finish processing the right side of each node

        // Locate where the currently created node is in respect to the other nodes of the tree
        int createdNodeIndex = findNextIndex(inorder, node.val, end);

        // IMPORTANT to recurse on the right nodes first. Right nodes are biased to the end of the array in postorder
        // Index will not work properly if we recurse on the left nodes first
        // Recurse on the left and right and begin building the tree
        node.right = build(inorder, postorder, createdNodeIndex + 1, end);
        node.left = build(inorder, postorder, start, createdNodeIndex - 1);

        // This case is reached when the left side of each node is done building, or when the tree is complete
        return node;
    }

    // Helper function that locates where the current node resides in the inorder array
    public static int findNextIndex(int[] inorder, int val, int end) {
        for (int i = end; i >= 0; i--) {
            if (inorder[i] == val) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        int[] postorder = {1, 3, 2, 5, 7, 6, 4};

        System.out.println(buildTree(inorder, postorder));
    }
}
