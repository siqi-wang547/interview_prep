package ood;
import java.util.HashMap;
import java.util.Map;

/**
 * LC146. LRU Cache
 * Idea: use a Map<key, (value, hits)> to keep track all records and every time a new record is put
 * Use a LinkedList, a newly put node should be the first, and a recently used node should be moved to the first
 *
**/
public class LRUCache {
	class DLinkNode {
        int key;
        int value;
        DLinkNode prev;
        DLinkNode next;
    }

    public void addNode(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void deleteNode(DLinkNode node) {
        DLinkNode pre = node.prev;
        DLinkNode nex = node.next;
        pre.next = nex;
        nex.prev = pre;
    }

    public void moveToHead(DLinkNode node) {
        deleteNode(node);
        addNode(node);
    }

    public DLinkNode dropTail() {
        DLinkNode res = tail.prev;
        deleteNode(res);
        return res;
    }

    private int capacity, count;
    private DLinkNode head, tail;
    private Map<Integer, DLinkNode> cache = new HashMap<>();

    public LRUCache(int c) {
        capacity = c;
        count = 0;
        head = new DLinkNode();
        head.prev = null;
        tail = new DLinkNode();
        tail.next = null;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            DLinkNode newNode = new DLinkNode();
		    		newNode.key = key;
		    		newNode.value = value;
		    		addNode(newNode);
		    		cache.put(key, newNode);
		    		count++;
		    		if (count > capacity) {
		    		    DLinkNode d = dropTail();
		    		    cache.remove(d.key);
		    		    count--;
		    		}
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
