package LinkedListProblems;

import java.util.HashSet;

public class RemoveDups {

    // O(n) time: Checks for equality with one pass by storing each seen value in to a hash table
    // O(n) space: A hash table stores all seen and unique nodes. Worst case is that all values in the list are unique.
    public static void removeDupsV2(Node n) {
        HashSet<Integer> set = new HashSet<>();
        Node prev = null;

        while (n != null) {
            if (set.contains(n.val))
                prev.next = n.next;
            else {
                set.add(n.val);
                prev = n;
            }
            n = n.next;
        }
    }

    // O(n^2) time: Upon visiting each node, all other nodes ahead of it are visited to check for equality
    // O(1) space: No extra data structures are used to compare previously seen node values
    public static void removeDups(Node n) {
        Node current = n;
        while (current != null) {
            Node prev = current;
            Node runner = prev.next;
            while (runner != null) {
                if (current.val == runner.val)
                    prev.next = runner.next;
                else
                    prev = runner;
                runner = runner.next;
            }
            current = current.next;
        }
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

        removeDupsV2(head);

        System.out.println();
        printNodes(head);

    }

}

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
