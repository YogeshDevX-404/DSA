/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return head;
        }
        int total = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            total++;
        }
        if(total < k){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp1 = dummy;
        while(total >= k){
            ListNode temp2 = temp1;
            int l = k;
            while(l > 0){
                temp2 = temp2.next;
                l--;
            }
            ListNode nxtgroup = temp2.next;
            ListNode start = temp1.next;
            temp1.next = reverse(start, nxtgroup);
            temp1 = start;
            total = total - k;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head, ListNode nxtnode){
        ListNode prev = nxtnode;
        ListNode current = head;
        while(current != nxtnode){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }
}
/*
1. Create dummy node and set temp1 = dummy
2. While remaining nodes ≥ k:
3.     Set temp2 = temp1
4.     Move temp2 k steps ahead
5.     Set start = temp1.next
6.     Set nextGroup = temp2.next
7.     Reverse nodes from start to nextGroup
8.     Connect temp1.next to new head of reversed group
9.     Move temp1 to start (tail of reversed group)
10.    Decrease remaining nodes by k
11. Repeat
*/