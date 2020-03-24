package mypackage;

public class DoubleLinkedList {
	
	class Node{
		String data;
		int count;
		Node previous;
		Node next;
		
		public Node(String data, int count) {
			this.data = data;
			this.count = count;
		}
	}
	
	private Node head,tail = null;
	
	
	public Node getHead() {
			return head;
	}
		
	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public void createList(String data) {
		if(head == null) {
			Node newNode = new Node(data,1);
			head = tail = newNode; 
			head.previous = null;
			tail.next = null; 
		}
		else {
			int count = tail.count;
			count++;
			Node newNode = new Node(data,count);
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode; 
			tail.next = null;
		}
	
	}

	public void printNodes(boolean b) {
		if (head==null) {
			System.out.println("The list is Empty");
			return;
			}
		else {
			Node current = head; 
			while(current != null) {
				if(b == true) {
					System.out.print(current.count+ "  " +current.data);
				}
				else {
					System.out.print(current.data);
				}
				current = current.next;
			}
		}
	}
	
	public Node deleteNode (Node delNode) {
		if(head == null) {
			System.out.println("The list is Empty");
			return null;
		}
		else if (head.next == null) {
			head = tail = null;
			return head;
		}
		else if (delNode == head) {
			head = head.next;
			Node current = head; 
			while (current != tail.next) {
				current.count = current.count - 1;
				current = current.next;
			}
			return head; 
		}
		else if (delNode == tail) {
			tail = tail.previous;
			tail.next = null;
			return tail;
		}
		else {
			delNode.previous.next = delNode.previous;
			delNode.previous.next = delNode.next;
			Node current = delNode;
			while ( current != tail.next) {
				current.count = current.count - 1;
				current = current.next;
			}
			return delNode; 
		}
		
	}
	
	public Node addNodeAfter(String data, Node cur) {

		int count = cur.count;
		count++;
		if(head == null) {
			Node newNode = new Node(data,1);
			head = tail = newNode; 
			head.previous = null;
			tail.next = null; 
			return head;
		}
		else if (head.next == null) {
			Node addingNode = new Node(data,2);
			head.next = addingNode; 
			addingNode.previous = head;
			tail = addingNode; 
			tail.next = null;
			return tail;
		}
		else if (cur == tail){
			Node addingNode = new Node(data,count);
			addingNode.previous = tail;
			tail.next = addingNode;
			tail = addingNode;
			tail.next = null;
			return tail;
		}
		else {
			Node addingNode = new Node(data,count);
			addingNode.next = cur.next;
			addingNode.previous = cur;
			addingNode.next = cur.next;
			cur.next = addingNode;
			while (cur != tail) {
				cur = cur.next;
				cur.count = count;
				count++;
			}
			return addingNode;
		}
	}
	
	public Node addNodeBefore (String data, Node cur) {
		if(head == null) {
			Node newNode = new Node(data,1);
			head = tail = newNode; 
			head.previous = null;
			tail.next = null; 
			return head;
		}
		else if (head == cur) {
			Node addingNode = new Node(data,1);
			int count = cur.count;
			head.previous = addingNode; 
			addingNode.next = head;
			head.count = 2;
			head = addingNode;
			while (cur != tail.next) {
				count++;
				cur.count = count;
				cur = cur.next;
			}
			return head;
		}
		else {
			int count = cur.count;
			Node addingNode = new Node(data,count);
			addingNode.previous = cur.previous.next; 
			cur.previous.next = addingNode; 
			addingNode.next = cur; 
			cur.previous = addingNode;
			while (cur != tail.next) {
				count++;
				cur.count = count;
				cur = cur.next;
			}
			return addingNode;
		}
	}

	public void printNumOfChars () {
		Node cur = head; 
		int numOfChars = 0;
		while (cur != tail.next) {
			numOfChars = numOfChars + cur.data.length();	
			cur = cur.next; 	
		}
		System.out.print(numOfChars+" Characters \n");
	}
	
}


