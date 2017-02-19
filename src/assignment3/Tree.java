package assignment3;

import java.util.ArrayList;

public class Tree {
	String start;
	String end;
	Node head;
	
	static class Node {
		String actualS;
		ArrayList<Character> actual;
		char letter;
		String start;
		int pos;
		
		Node parent;
		Node[] next;
		
		public Node() {
			
		}
		public Node(String start, int len) {
			actualS = start;
			next = new Node[len];
//			parent = null;
			actual = new ArrayList<Character>();
			for (char c: start.toCharArray()) {
				actual.add(c);
			}

//			
			
			//add money into the string and also the position
		}
		public Node(String s, char letter, int pos) {
			this.actualS = s;
			this.letter = letter;
			this.pos = pos;
	//		parent = null;
			actual = new ArrayList<Character>();
			for (char c: actualS.toCharArray()) {
				actual.add(c);
			}
			
			actual.set(pos, letter);
			String bob = ""; 
			for (int i = 0; i < actual.size(); i++) {
				bob += actual.get(i);
			}
			actualS = bob;
			next = new Node[2];
		}
		
		@Override
		public String toString() {
			return actualS;
		}
		
	}
	
	public void makeChild(Node node, String word) {
		for (int i = 0; i < word.length(); i++) {
			node.next[i] = new Node();
		}
	}
	
	public void abc(Node newnode) {
		Node node = newnode;
		char letter = node.actual.get(node.pos); //gets the char from the right position
		char newLet = (char) (letter - 1);
		while (newLet > 'a') {
			letter = node.actual.get(node.pos); //gets the char from the right position
			newLet = (char) (letter - 1);
			if (newLet >= 'a') {
				node.next[0] = new Node(node.actualS, newLet, node.pos);
//				System.out.println(node.next[0]);
				node.next[0].parent = node;
				node = node.next[0];
			}
			
//			System.out.println(node.next[0].parent);
//			System.out.println(node);
			if (newLet > 'a') {
				
			}
//			System.out.println(node);
		}
		
//		System.out.println(node);
//		System.out.println(newnode);
		Node node2 = newnode;
//		System.out.println(node2.actual.get(node2.pos));
		while (newLet < 'z') {
			letter = node2.actual.get(node2.pos); //gets the char from the right position
			newLet = (char) (letter + 1);
			if (newLet <= 'z') {
				node2.next[1] = new Node(node2.actualS, newLet, node2.pos);
				node2.parent = node2;
				node2 = node2.next[1];
				
			}
//			System.out.println(node2);
			
		}
	}
	
	public void add(String start, String end, ArrayList<Integer> arr) {
		this.start = start;
		this.end = end;
		
		head = new Node (start, arr.size());
		head.parent = null;
//		int i = 0;
//		head.next[i] = new Node(head.actualS, end.charAt(arr.get(i)), arr.get(i));
//		System.out.println(head.next[i]);
		make(head, arr);
	}
	
	public void make(Node node, ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			//System.out.println(node.next[i].parent);
			node.next[i] = new Node(node.actualS, end.charAt(arr.get(i)), arr.get(i));
//			System.out.println(node.next[i]);
			
			node.next[i].parent = node;
//			System.out.println(node.next[i].parent);
//			System.out.println(node);
			//System.out.println(node.next[i]);
			abc(node.next[i]);
			
		}
		
	}
	
	public void print(Node head) {
		if (head == null) {
			return;
		}
		else {
			System.out.println(head);
			print(head.next[0]);
			print(head.next[1]);
		}
	}
	public void printall () {
		System.out.println(head);
		print(head.next[0]);
		print(head.next[1]);
		print(head.next[2]);
		print(head.next[3]);
		print(head.next[4]);
	}
}
