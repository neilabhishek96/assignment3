/* WORD LADDER Main.java
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
import java.util.*;

//import assignment3.Tree.Node;

import java.io.*;

public class Main {
	
	// static variables and constants only here.
	static ArrayList<Node> dict_node;
	
	public static void main(String[] args) {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		System.out.println("hi");
		// If arguments are specified, read/write from/to files instead of Std IO.
//		if (args.length != 0) {
//			kb = new Scanner(new File(args[0]));
//			ps = new PrintStream(new File(args[1]));
//			System.setOut(ps);			// redirect output to ps
//		} else {
//			kb = new Scanner(System.in);// default from Stdin
//			ps = System.out;			// default to Stdout
//		}
		//test2();
		System.out.println("hi");
		//initialize();
		getWordLadderBFS("smart", "money");
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> arr = new ArrayList<String>();
		String word = keyboard.nextLine();
		if (word.equals("/quit")) {
			return arr;
		}
		else {
			arr.add(word);
			String word2 = keyboard.nextLine();
			arr.add(word2);
		}
		// TO DO
		return arr;
	}
	
	public static void test() {
		System.out.println("Hi");
		System.out.println("Git push worked!");
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();
		Iterator<String> ite = dict.iterator();
		//String[] dict_arr = (String[]) (dict.toArray());
		//String s = "smart";
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < dict.size(); i++) {
			s.add(ite.next());
		}
		dict_node = new ArrayList<Node>();
		for (int i = 0; i < dict.size(); i++) {
			dict_node.add(new Node(s.get(i)));
		}
		
		for(int i = 0; i < dict.size();i++) {
			Node node = dict_node.get(i);
			findEdge(node);
		}
		Node head = null;
		for (int i = 0; i < dict.size(); i++) {
			if (dict_node.get(i).actual.equals("STONE")) {
				head = dict_node.get(i);
				//break;
			}
		}
//		System.out.println(head.arrList);
		end = "MONEY";
		
		ArrayList<String> arr = new ArrayList<String>();
		head.parent = null;
		head.visited = true;
		Queue queue = new LinkedList();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node node = (Node)queue.remove();
//			System.out.println(node);
			if (node.actual.equals(end)) {
				while (node.parent != null) {
					arr.add(node.actual);
			//		System.out.println(node.parent.parent.parent.parent.parent.parent.parent.parent.parent.parent);
					node = node.parent;
				}
				arr.add(node.actual);
			}
//			findShort(node,queue,end,dict);
			for(Node n : node.arrList) {
				if (!n.visited) {
					n.parent = node;
					n.visited = true;
					queue.add(n);
				}
			}
		}
		Collections.reverse(arr);
		System.out.println(arr);
//		for (String t: s) {
//			if (t.equals("SMART")) {
//				System.out.println(t);
//			}
//		}
		// TODO more code
		//Node head = new Node(start);
//		makeTree(head, dict);
		//test3(dict);
		/* 
		 * while (next rung is not found) {
		 * 		make the tree
		 * 		use bfs to find the next rung
		 * 		add it to arraylist bob
		 * }
		 */
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	
	public static void findEdge(Node head) {
		for (int i = 0; i < dict_node.size();i++) {
			int cnt = 0;
			for (int j = 0; j < head.actual.length(); j++) {
				if (dict_node.get(i).actual.charAt(j) == head.actual.charAt(j)) {
					cnt++;
				}
			}
			if (cnt == head.actual.length()-1) {
				head.addEdge(dict_node.get(i));
			}
		}
	}
	
	public static void findShort(Node head, Queue q, String end, Set<String> dict) {
		
		
		for (int i = 0; i < head.actual.length(); i++) {
			ArrayList<Character> arr = new ArrayList<Character>();
			for (char c: head.actual.toCharArray()) {
				arr.add(c);
			}
			arr.set(i, end.charAt(i));
			String bob = "";
			for (int k = 0; k < arr.size(); k++) {
				bob += arr.get(k);
			}
			
			if (dict.contains(bob.toUpperCase())) {
				System.out.println(bob);
				for (int j = 0; j < dict.size(); j++) {
					if (dict_node.get(j).actual.equals(bob)) {
						Node node = dict_node.get(j);
						node.parent = head;
						node.visited = true;
						q.add(node);
						//break;
					}
				}
			}
			
		}
	}
	public static void makeTree(Node node, Set<String> dict) {
		Node newNode = null;
		
		if (node == null) {
			return;
		}
		else {
		for (int i = 0; i < node.actual.length();i++) {
			
			for (int j = 0; j < 2; j++) {
				//System.out.println(node.actual.length());
				String bob = "";;
				ArrayList<Character> arr = new ArrayList<Character>();
				for (char c: node.actual.toCharArray()) {
					arr.add(c);
				}
				if (j == 0) {
					char let = (char)(arr.get(i)-1);
					if (let >='a') {
						arr.set(i, (char)(arr.get(i)-1));
						for (int k = 0; k < arr.size(); k++) {
							bob += arr.get(k);
						}
						System.out.println(bob);
					}
				}
				else {
					char let = (char)(arr.get(i)+1);
					if (let <='z') {
						arr.set(i, let);
						for (int k = 0; k < arr.size(); k++) {
							bob += arr.get(k);
						}
						System.out.println(bob);
					}
				}
					if (dict.contains(bob)) {
						newNode = new Node(bob);
						System.out.println("hurraaaaay");
						newNode.parent = node;
//						node.add(newNode);
					}
					//System.out.println(node.actual.length());
				}
				makeTree(newNode, dict);
//				System.out.println(node.arrList.get(i));

			}
		}
		}
	public static void test3(Set<String> dict) {
		String input = "smart";
		
		for (int j=0;j<13;j++) {
		
			
			for (int i = 0; i<5;i++){
				ArrayList<Character> arr = new ArrayList<Character>();
				for (char c: input.toCharArray()) {
					arr.add(c);
				}
				String bob = "";
				boolean flag1 = false, flag2 = false;
				char let = (char) (input.charAt(i) + j);
				char let2 = (char) (input.charAt(i) + (-1*j));
				
				if (let <= 'z') {
					flag1 = true;
				}
				if (let2 >= 'a') {
					flag2 = true;
				}
				arr.set(i, let);
				for (int k = 0; k < arr.size(); k++) {
					bob += arr.get(k);
				}
				//System.out.println(bob);
				if(dict.contains(bob.toUpperCase())&& !bob.equals(input) && flag1) {
					System.out.println(bob);
				}
				
				bob = "";
				arr.set(i, let2);
				for (int k = 0; k < arr.size(); k++) {
					bob += arr.get(k);
				}
				//System.out.println(bob);
				if(dict.contains(bob.toUpperCase()) && !bob.equals(input) && flag2) {
					System.out.println(bob);
				}
			}
		}
	}
	public static void test2() {
		Tree t = new Tree();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(0);
		temp.add(1);
		temp.add(2);temp.add(3);temp.add(4);
		
		t.add("smart", "money", temp);
		t.printall();
//		char letter = 'B';
//		char newLet = (char) (letter - 1);
//		System.out.println(newLet);
//		
//		Set<String> dict = makeDictionary();
//		String[] chararr = "abcdefghijklmnopqrstuvwxyz".split("");
//		
//		String[] arr = {"s", "t", "a", "r", "s"};
//		String[] temp;
//		for (int a = 0; a < 5; a++) {
//			temp = arr.clone();
//			for (int b = 0; b < 26; b++) {
//				temp[a] = chararr[b];
//				String bob = String.join("", temp);
//				//String bob = Arrays.toString(temp);
//				//System.out.println(bob);
//				//System.out.println(dict.contains("SMART"));
//				//System.out.println(bob.equals(Arrays.toString(arr)));
//				if (dict.contains(bob.toUpperCase()) && !bob.equals(String.join("", arr))) {
//					System.out.println(bob);
//				}
//			}
//		}
//		
	}
	// TODO
	// Other private static methods here
}
