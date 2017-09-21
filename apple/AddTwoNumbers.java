package apple;
import java.util.*;

/**
 * LC2 & LC445 Add two numbers
 *
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbersI(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0), node = head;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = ((l1 == null) ? 0: l1.val) + ((l2 == null) ? 0: l2.val) + carry;
            carry = sum / 10;
            node.next = new ListNode(sum % 10);
            node = node.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }

    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        pushToStack(st1, l1);
        pushToStack(st2, l2);
        ListNode dummy = new ListNode(0);
        int carry = 0;
        while (!st1.isEmpty() || !st2.isEmpty() || carry > 0) {
            int x = (st1.isEmpty())? 0: st1.pop();
            int y = (st2.isEmpty())? 0: st2.pop();
            ListNode node = new ListNode((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
            node.next = dummy.next;
            dummy.next = node;
        }
        return dummy.next;
    }

    private void pushToStack(Stack<Integer> st, ListNode head) {
        ListNode n = head;
        while (n != null) {
            st.push(n.val);
            n = n.next;
        }
    }
}
