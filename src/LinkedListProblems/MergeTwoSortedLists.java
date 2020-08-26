// LeetCode 21. Merge Two Sorted Lists
// Merge two sorted linked lists and return it as a new sorted list. The new list is created by splicing together nodes from the 2 given lists
//
// Intuition:
// We can approach this iteratively or recursively.
// Iterative: Iterate through each list with a pointer. Add the smaller of the two pointed values to the resulting list to return
// Recursive: At each recursive call, check which node is smaller. Recurse on the smaller list that excludes the smaller element.
//
// Growth Functions:
// O(l1 + l2) runtime: we must traverse through all elements of each list
// O(l1 + l2) space: the amount of space required (via the call stack or from list elements) is equivalent to the size of the lists

package LinkedListProblems;

public class MergeTwoSortedLists {

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // Build the list recursively, setting the ListNode's next as the smaller of the two values
        // When we hit null, we start popping recursive calls off the stack
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head; // "New" list to return

        // Set the smaller valued ListNode as the head to return
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        }
        else {
            head = l2;
            l2 = l2.next;
        }

        // Use another pointer to traverse our position in the "new" list
        ListNode curr = head;

        // Add the smaller of the values as the next node in the list
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }
            else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // Add the rest of the list that remains
        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;

        return head;
    }
}
