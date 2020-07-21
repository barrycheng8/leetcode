// LeetCode 203. Remove Linked List Elements
// Remove all elements from a linked list of integers that have the value val.
// Ex. 1->2->6->3->4->5->6 val = 6
// Ans. 1->2->3->4->5
// O(n) runtime: we must traverse the entire list to remove elements with value equiv to val
// O(1) space: 2 pointers are used to keep track of the current node and trailing node

package LinkedListProblems;

public class RemoveLinkedListElements {

    // Recursive intuition:
    // Traverse the list recursively by calling the function with head.next
    // Within the recursive call, we check if the current node is equiv to value.
    // If it is, we return the current node's next pointer so the function call deeper in the stack can properly
    // reroute its current node to the removed node's next node.
    public ListNode removeElementsRecursive(ListNode head, int val) {
        if (head == null) return null; // This also covers the edge case 1->1, because we will keep recursing until we hit null

        // Recursively visit the next element
        ListNode next = removeElementsRecursive(head.next, val);

        // If we visit an element we need to remove, skip it return the recursively visited next element
        if (head.val == val) return next;

        // This line is to close any gaps when an element is removed
        head.next = next;

        return head;
    }


    // Iterative intuition:
    // Using 3 pointers, we traverse the array.
    // The initial while loop will ignore all nodes equivalent to value at the head of this list.
    // Upon exiting the first while loop, we know for sure all head nodes do not contain val. We safely set start = fast ptr.
    // Traverse the rest of the list by rerouting the prev (slow) node to fast's next node
    // Only increment slow if we do not encounter a node to remove. This covers the edge case 1->2->2->1
    // Increment fast at each iteration
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode slow = null; // keeps track of the previous pointer
        ListNode fast = head; // the current pointer with the value to check
        ListNode start = null; // stores the start of the list to return

        // Ignore all head nodes that equal to val
        while (fast != null && fast.val == val) {
            slow = fast;
            fast = fast.next;
        }

        start = fast; // 'fast' will now point to a node that is not equal to val

        while (fast != null) {
            // bypass the element to remove. keep slow at original position in case there are 2 removed elements in a row
            if (fast.val == val) slow.next = fast.next;
            else slow = fast;

            fast = fast.next;
        }
        return start;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}