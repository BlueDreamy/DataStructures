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

	public void printNodes() {
		if (head==null) {
			System.out.println("The list is Empty");
			return;
			}
		else {
			Node current = head; 
			while(current != null) {
				System.out.print(current.data + "\n");
				current = current.next;
			}
		}
	}
	
	public void printNodesReverse() {
		if (head==null) {
			System.out.println("The list is Empty");
			return;
			}
		else {
			Node current = tail; 
			while(current != null) {
				System.out.print(current.data + "\n");
				current = current.previous;
			}
		}
	}
	
	public void deleteNode (Node delNode) {
		if(head == null) {
			System.out.println("The list is Empty");
			return;
		}
		else if (delNode == head) {
			head = head.next;
		}
		else if (delNode == tail) {
			tail = tail.previous;
			tail.next = null;
		}
		else {
			delNode.previous.next = delNode.previous;
			delNode.previous.next = delNode.next; 
			
		}
		
	}
	

}


