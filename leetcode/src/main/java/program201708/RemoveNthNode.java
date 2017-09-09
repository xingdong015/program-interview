package program201708;

/**
 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 *
 * @author zhengcheng
 * @date 2017/8/25
 * @time 下午2:04
 **/

public class RemoveNthNode {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next=node2;node2.next=node3;node3.next=node4;node4.next=node5;
        printNode(node1);
        ListNode listNode = removeNthFromEnd(node1, 5);
        System.out.println();
        printNode(listNode);

    }

    static class ListNode {
        int val;
        ListNode(int val){
            this.val = val;
        }
        ListNode next;

        @Override
        public String toString() {
            return val+"";
        }
    }

    public static  ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start,fast = start;
        slow.next = head;
        for(int i = 1;i <= n+1;i++){
            fast = fast.next;
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    static void printNode(ListNode node){
        while(node != null){
            System.out.print(node+" ");
            node = node.next;
        }
    }



}
