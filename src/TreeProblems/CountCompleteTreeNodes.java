package TreeProblems;

import java.util.LinkedList;
import java.util.Queue;

public class CountCompleteTreeNodes {

    public int countNodesBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int count = 0;

        while(!q.isEmpty()) {
            TreeNode next = q.poll();
            if (next.left != null)
                q.offer(next.left);
            if (next.right != null)
                q.offer(next.right);
            count++;
        }
        return count;
    }

    // Recursive solution
    public int countNodes(TreeNode root) {
        int leftDepth = countLeft(root);
        int rightDepth = countRight(root);

        // Complete tree is balanced. # Nodes is 2^depth - 1
        if (leftDepth == rightDepth)
            return (1 << leftDepth) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);

    }

    public int countLeft(TreeNode root) {
        int count = 0;
        while (root != null) {
            root = root.left;
            count++;
        }
        return count;
    }

    public int countRight(TreeNode root) {
        int count = 0;
        while (root != null) {
            root = root.right;
            count++;
        }
        return count;
    }
}



class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

     public String toString() {
         return Integer.toString(val);
     }
}
