package assignment3;

import java.util.ArrayList;

//import assignment3.Tree.Node;

public class Node {
		ArrayList<Node> arrList;
		String actual;
		boolean visited;
//		ArrayList<Character> actual;
//		char letter;
//		String start;
//		int pos;
		
		Node parent;
		
		public Node() {
			
		}
		public Node(String s) {
			arrList = new ArrayList<Node>();
			actual = s;
			visited = false;
		}
		
		public void addEdge(Node s) {
			arrList.add(s);
		}
		
		@Override
		public String toString() {
			return actual;
		}
}
