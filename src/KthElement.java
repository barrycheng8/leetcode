public class KthElement {

    // O(n) time: Runner pointer traverses through the entire list to hit null
    // O(1) space: uses 2 pointers and no underlying structures
    public static Node kthElement(Node head, int k) {
        Node trail = head;
        Node runner = head;

        for (int i = 0; i < k; i++) {
            if (runner == null) return null; // the runner went out of bounds
            runner = runner.next;
        }

        while (runner != null) {
            runner = runner.next;
            trail = trail.next;
        }

        return trail;
    }

    public static void printNodes(Node n) {
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node (1);

        printNodes(head);

        System.out.println();
        System.out.println(kthElement(head, 2).val);

    }
}
