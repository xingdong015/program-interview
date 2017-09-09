package program201708;

/**
 * @author zhengcheng
 * @date 2017/8/28
 * @time 上午9:33
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list,
 * only nodes itself can be changed.
 **/

public class SwapPairs2Test {
    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;n2.next =n3;n3.next=n4;

        printListNode(n1);

        ListNode listNode = swapPairs(n1);
        System.out.println();

        printListNode(listNode);


    }

    private static void printListNode(ListNode head) {
        while(head != null){
            System.out.print(head.value+",");
            head = head.next;
        }
    }

    public static  ListNode swapPairs(ListNode head) {
       if(head == null || head.next == null){
           return head;
       }
       ListNode dummpy = new ListNode(0);
       ListNode pre = dummpy,slow = head,fast = slow.next;
       while(fast != null){
           slow.next = fast.next;
           fast.next = slow;
           pre.next =fast;
           pre = slow;
           slow = slow.next;
           if(slow != null){
               fast = slow.next;
           }else{
               break;
           }
       }
       return dummpy.next;

    }

    static class ListNode{
        int value;
        ListNode next;
        ListNode(int x) { value = x; }


    }
}
