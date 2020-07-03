// LeetCode 107. Binary Tree Level Order Traversal II
// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
// Ex.     3
//        / \
//       9   20
//           / \
//          15  7        =  [[15,7], [9, 20], [3]]
// Traverse tree using BFS. Add child nodes to queue. As you poll nodes, add it to the end of the linked list
// Keep track of the number of elements in each row using variable 'n' in the outer while loop

package TreeProblems;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderBottom {
    // BFS
    // O(n) runtime: traverse through all the nodes
    // O(n) space: 2-D list to store result, queue to do BFS traversal
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();

        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()) {
            int n = 0;
            int currSize = q.size();
            List<Integer> levelElements = new ArrayList<>();
            while (n < currSize) {
                TreeNode next = q.poll();

                if (next.left != null) q.offer(next.left);
                if (next.right != null) q.offer(next.right);

                levelElements.add(next.val);
                n++;
            }
            ans.add(0, levelElements);
        }
        return ans;
    }

    // DFS
    // O(n) runtime: must traverse each node on the tree
    // O(n) space: recursive call stack and 2-D list to store result
    public static List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFSHelper(ans, root, 0);
        return ans;
    }

    static void DFSHelper(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null) return;
        // Initialize a new array list to hold the lower level nodes
        if (level >= list.size())
            list.add(0, new ArrayList<>());

        DFSHelper(list, node.left, level + 1);
        DFSHelper(list, node.right, level + 1);

        // After traversing the tree with DFS, add the left then right nodes
        list.get(list.size() - level - 1).add(node.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode nullRoot = new TreeNode();

        System.out.println(levelOrderBottomDFS(root));
        System.out.println(levelOrderBottom(nullRoot));
    }
}
