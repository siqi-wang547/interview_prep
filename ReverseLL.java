public class ReverseLL {
    class ListNode {
        int val;
        ListNode next;
        public ListNode(int v) { val = v; }
    }

    public ListNode reverseListRec(ListNode head) {

        return helper(head, null);
    }
    
    private ListNode helper(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return helper(next, head);
    }

    public ListNode reverseListItr(ListNode head) {
        if (head == null) return null;
        ListNode pre = head, node = head.next;
        head.next = null;
        while (node != null) {
            //System.out.println(node.val + " " + pre.val);
            ListNode next = node.next;
            node.next = pre;
            //System.out.println(node.val + " " + node.next.val);
            pre = node;
            node = next;
        }
        return pre;
    }
}
