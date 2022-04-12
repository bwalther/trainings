package recursion;

public class ReverseList {

    public static void main(String[] args) {
        int n = 7;
        ListNode next = new ListNode(n - 1);
        ListNode head = null;
        for (int i = n - 2; i >= 0; i--) {
            head = new ListNode(i);
            head.next = next;
            next = head;
        }
        ListNode rev = new ReverseList().recReverseList(head);
        System.out.println(rev);
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            String str = next == null ? "null" : next.toString();
            return val + ", " + str;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    public ListNode recReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = recReverseList(head.next);
        // see https://stackoverflow.com/questions/67134728/how-can-this-linked-list-reversal-with-recursion-work
        //reverse connetions!
        head.next.next = head;
        head.next = null;
        return p;
    }
}
