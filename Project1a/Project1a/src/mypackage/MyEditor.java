package mypackage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyEditor {

	final static int MAXLENGTH = 80;
	final static int MINWORDSIZE = 5;
	final static int MAXWORDSIZE = 20;

	public static void main(String[] args) {

		// This code needs to be changed for the final project in order to run from cmd
		// givenFile is args[0]
		boolean showLines = true;
		Scanner usersData = new Scanner(System.in);
		System.out.println("Give File: ");
		String file = usersData.nextLine();
		System.out.println("File is: " + file);

		// Now back to the code for MyEditor
		DoubleLinkedList dll = new DoubleLinkedList();
		DoubleLinkedList.Node cur = dll.new Node ("",0);
		try {
			FileReader fr = new FileReader(file);
			storeStrings(fr, dll);
			fr.close();
		}
		catch (IOException ex) {
			System.out.println("This file doesn't exist");
		}
		boolean flag = true;
		printMenu();
		while (flag) {
			try {
				System.out.println("CMD> ");
				String usersChoice = usersData.nextLine();
	
				switch (usersChoice) {
				case "^":
					cur = dll.getHead();
					System.out.print(cur.data);
					break;
				case "$":
					cur = dll.getTail();
					System.out.print(cur.data);
					break;
				case "-":
					cur = cur.previous;
					System.out.print(cur.data);
					break;
				case "+":
					cur = cur.next;
					System.out.print(cur.data);
					break;
				case "a":
					System.out.println("Give String: ");
					String userString = usersData.nextLine();
					cur = dll.addNodeAfter(userString+"\n", cur);
					break;
				case "t":
					System.out.println("Give String: ");
					userString = usersData.nextLine();
					cur = dll.addNodeBefore(userString+"\n", cur);
					break;
				case "d":
					cur = dll.deleteNode(cur);
					break;
				case "l":
					dll.printNodes(showLines);
					break;
				case "n":
					showLines = !showLines;
					break;
				case "p":
					System.out.print(cur.data);
					break;
				case "q":
					usersData.close();
					flag = false;
					break;
				case "w":
					storeFile(dll,file);
					break;
				case "x":
					storeFile(dll,file);
					usersData.close();
					flag = false;
					break;
				case "=":
					System.out.print(cur.count+"\n");
					break;
				case "#":
					System.out.print(dll.getTail().count+" Lines \n");
					dll.printNumOfChars();
					break;
				case "c":
					indexTable(dll);
				default: 
					System.out.println("Bad command");
				}
			}
			catch (NullPointerException ex) {
				System.out.println("The list is Empty");
				cur = dll.new Node ("", 0);
			}
		}
	} 

	
	public static void printMenu() {
		System.out.println("Welcome to the Text Editor Console");
		System.out.println("^ : Go to the first line");
		System.out.println("$ : Go to the last line");
		System.out.println("- : Go up one line");
		System.out.println("+ : Go down one line");
		System.out.println("a : Add new line after current line");
		System.out.println("t : Add new line before current line");
		System.out.println("d : Delete current line");
		System.out.println("l : Print all lines");
		System.out.println("n : Toggle whether line numbers are displayed when printing all lines");
		System.out.println("p : Print current line");
		System.out.println("q : Quit without save");
		System.out.println("w : Write file to disk");
		System.out.println("x : Exit with save");
		System.out.println("= : Print current line number");
		System.out.println("# : Print number of lines and characters");
	}
	
	public static void storeStrings(FileReader fr, DoubleLinkedList dll) throws IOException {
		String string = new String();
		int endOfFile;
		while ((endOfFile = fr.read()) != -1) {
			char c = (char) endOfFile;
			if (c == '\n') {
				string += c;
				if (string.length() <= MAXLENGTH+1) {
					dll.createList(string);
				}
				string = new String();
			} else {
				string += c;
			}
		}
	}

	public static void storeFile (DoubleLinkedList dll, String file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			DoubleLinkedList.Node cur = dll.getHead();
			while (cur != dll.getTail().next){
				fw.write(cur.data);
				cur = cur.next;
			}
			fw.close();
		} catch (IOException ex) {
			System.out.println("Can't store changes\n");
		}
	}

	public static void indexTable(DoubleLinkedList dll) {
		DoubleLinkedList.Node cur = dll.new Node("", 0);
		TuplesList tl = new TuplesList();
		cur = dll.getHead();
		String[] words;
		while (cur != dll.getTail()) {
			words = cur.data.split("\\P{Alpha}+");
			for (int i = 0; i < words.length; i++) {
				if(words[i].length() < MINWORDSIZE) {}	
				else if (words[i].length() > MAXWORDSIZE) {
					TuplesList.Tuple tuple = tl.new Tuple(cur.count, words[i].substring(MAXWORDSIZE));
					tl.tupleList.add(tuple);
				}
				else  {
					TuplesList.Tuple tuple = tl.new Tuple(cur.count, words[i]);
					tl.tupleList.add(tuple);
				}

			}
			cur = cur.next;
		}
		tl.getSorted();

		
		for(int i = 0 ; i < tl.tupleList.size(); i++) {
		System.out.println(tl.tupleList.get(i).line+")"+tl.tupleList.get(i).word); 
		}
		System.out.println(tl.tupleList.size());
		
	}
	
}
