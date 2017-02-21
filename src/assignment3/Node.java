/* WORD LADDER Node.java
 * EE422C Project 3 submission by
 * Jon Ambrose
 * ja38435
 * 16215
 * Neil Abhishek Charles
 * nc23227
 * 16215
 * Slip days used: <0>
 * Git URL: https://github.com/neilabhishek96/assignment3/
 * Spring 2017
 */

package assignment3;

import java.util.ArrayList;

//import assignment3.Tree.Node;

public class Node {
		ArrayList<Node> arrList;
		String actual;
		boolean visited;
		
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
