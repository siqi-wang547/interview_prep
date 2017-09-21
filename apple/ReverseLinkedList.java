package apple;

/**
 * LC206, LC92 reverse linked list I, II
 */
public class ReverseLinkedList {

    /**
     * reverse singly linked list iterative solution
     * @param head
     * @return
     */
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = prev;
            prev = cur;
            cur = node;
        }
        return prev;
    }

    public ListNode reverseResusivelyI(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return helper(next, head);
    }

    public ListNode reverseRecursivelyII(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseRecursivelyII(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * LC24 swap nodes in pair
     * @param head
     * @return
     */
    public ListNode swapNodesInPair(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0), start = dummy;
        start.next = head; // important!
        while (start.next != null && start.next.next != null) {
            ListNode first = start.next, second = start.next.next;
            first.next = second.next;
            second.next = first;
            start.next = second;
            start = first;
        }
        return dummy.next;
    }
}
