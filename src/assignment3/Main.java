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
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
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
	
	// TODO
	// Other private static methods here
}
