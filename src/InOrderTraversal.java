import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Stack<TreeNode> s = new Stack<>();
        pushAllLeft(root, s);

        while (!s.isEmpty()) {
            TreeNode next = s.pop();
            ans.add(next.val);
            pushAllLeft(next.right, s);
        }
        return ans;
    }

    static void pushAllLeft(TreeNode curr, Stack<TreeNode> s) {
        while(curr != null) {
            s.push(curr);
            curr = curr.left;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));

        System.out.println(inorderTraversal(root));

    }
}

