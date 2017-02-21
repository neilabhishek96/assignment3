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
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
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
		printLadder(getWordLadderDFS("balls", "malls"));
		kb.close();
		ps.close();
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		Set<String> dict = makeDictionary();
		Iterator<String> ite = dict.iterator();
		ArrayList<String> s = new ArrayList<String>();
		
		for (int i = 0; i < dict.size(); i++) {
			s.add(ite.next().toLowerCase());
		}
		dict_node = new ArrayList<Node>();
		for (int i = 0; i < dict.size(); i++) {
			dict_node.add(new Node(s.get(i)));
		}
		
		for(int i = 0; i < dict.size();i++) {
			Node node = dict_node.get(i);
			findEdge(node);
		}
		
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
		return arr;
	}
	
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		Node head = null;
		for (int i = 0; i < dict_node.size(); i++) {
			if (dict_node.get(i).actual.equals(start.toLowerCase())) {
				head = dict_node.get(i);
				break;
			}
		}
		
		Node finalnode[] = new Node[1];
		ArrayList<String> res = new ArrayList<String>();
		head.visited = true;
		DFSInner(head, end, finalnode);
		
		while(finalnode[0].parent != null){
			res.add(finalnode[0].actual);
			finalnode[0] = finalnode[0].parent;
		}
		if(res.isEmpty()){
			res.add(end);
			res.add(start);
		}
		res.add(start);	
		Collections.reverse(res);
		
		return res;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		
		Node head = null;
		for (int i = 0; i < dict_node.size(); i++) {
			if (dict_node.get(i).actual.equals(start.toLowerCase())) {
				head = dict_node.get(i);
				//break;
			}
		}
		
		ArrayList<String> arr = new ArrayList<String>();
		head.parent = null;
		head.visited = true;
		Queue queue = new LinkedList();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			if (node.actual.equals(end.toLowerCase())) {
				while (node.parent != null) {
					arr.add(node.actual);
					node = node.parent;
				}
				arr.add(node.actual);
			}
			
			for(Node n : node.arrList) {
				if (!n.visited) {
					n.parent = node;
					n.visited = true;
					queue.add(n);
				}
			}
		}
		if(arr.isEmpty()){
			arr.add(end);
			arr.add(start);
		}
		Collections.reverse(arr);
		//System.out.println(arr);

		return arr; // replace this line later with real return
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
		if(ladder.size() == 2){			
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		}
		else {
		System.out.println("a " + ladder.size() + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
		for(int i = 0; i < ladder.size(); i++){
				System.out.println(ladder.get(i));
			}
		}
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
	
	private static boolean DFSInner(Node start, String finish, Node[] res) {
		
		if (start == null){
			return false;
		}
		start.visited = true;
		if(start.actual.equals(finish)) {
			res[0] = start;
			return true;
		}
		else {
			for(int i = 0; i < start.arrList.size(); i++){
				if(!start.arrList.get(i).visited){ 
					boolean found = DFSInner(start.arrList.get(i), finish, res);
					if(found){	
						start.arrList.get(i).parent = start;
						return true;
					}
				}
			}
			return false;
		}
		
	}
	
}
