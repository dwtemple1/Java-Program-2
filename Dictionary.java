
public class Dictionary implements DictionaryInterface {

	private class Node {
		String key;
		String value;
		Node next;

		Node(String x, String y) {
			key = x;
			value = y;
			next = null;
		}
	}

	private Node head;
	private int numItems;

	public Dictionary() {
		head = null;
		numItems = 0;
	}

	public boolean isEmpty() {
		return(numItems == 0);
	}

	public int size() {
		return numItems;
	}

	public String lookup(String key) {
		Node n = head;
		while(n != null) {
			if(n.key == key)
				return n.value;
			n = n.next;
		}
		return null;
	}

	public void insert(String key, String value) throws DuplicateKeyException {
		Node n = head;
		for(int i = 0; i < size(); i++) {
			n = n.next;
		}
		if(lookup(key) == null) {
			n = new Node(key, value);
			numItems++;
		}
		else
			throw new DuplicateKeyException("cannot insert duplicate keys");
	}

	public void delete(String key) throws KeyNotFoundException {
		Node n = head;
		boolean deleted = true;
		if(n.key == key) {
			if(n.next != null)
				head = head.next;
			else
				head = null;
		}
		else
			deleted = false;
		while(n != null) {
			if(n.next != null && n.next.key == key) {
				n.next = n.next.next;
				deleted = true;
			}
			n = n.next;	
		}
		if(deleted)
			numItems = numItems-1;
		else
			throw new KeyNotFoundException("cannot delete non-existent key\"");
	}

	public void makeEmpty() {
		head = null;
		numItems = 0;
	}
	
	public String toString() {
		String s = "";
		Node n = head;
		while(n != null)
			s+=(n.key + " " + n.value + "\n");	
		return s;
	}

}
