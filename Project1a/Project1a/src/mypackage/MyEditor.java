package mypackage;

import mypackage.DoubleLinkedList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** 
 * @author Maria Nikoloudi (AM: 2015030075)
 * @version 1.0 
 * @since 24/03/20
 */

public class MyEditor {
	
	/** 
	 * This a Java program that implements a Text Editor. 
	 * The program is called from the CMD line using the command "java mypackage.MyEditor filename.txt".
	 * If the file that is given in the command doesn't exist, the user can still use the Text Editor 
	 * and in the end decide whether or not to create a new file with the input data. 
	 * ....Write more about the second part....
	 */

	final static int MAXLENGTH = 80;                            //Maximum line size
	final static int MINWORDSIZE = 5;
	final static int MAXWORDSIZE = 20;

	public static void main(String[] args) {

		String file = args[0];
		boolean showLines = true; 										//used to Toggle whether or not to show line numbering 
		Scanner usersData = new Scanner(System.in);						//used to get input data from the user
		System.out.println("File is: " + file);

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
		boolean flag = true;											//used to terminate the program when the user presses "x" or "q"
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
					if (cur == dll.getHead()) {
						System.out.print("Top of the list: " +cur.data);
					}
					else {
						cur = cur.previous;
						System.out.print(cur.data);
					}
					break;
				case "+":
					if (cur == dll.getTail()) {
						System.out.print("End of the list: " +cur.data);
					}
					else {
						cur = cur.next;
						System.out.print(cur.data);
					}
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
					//indexTable(dll);
				default: 
					System.out.println("Bad command");
				}
			}
			catch (NullPointerException ex) {
				System.out.println("The list is Empty");
				cur = dll.new Node ("", 0);
			}
		}
		System.out.println("Exiting Text Editor");
		System.exit(0);
	} 

	
	/**
	 * This method is used in order to print a menu of choices to the user for the Text Editor.
	 */
	
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
	
	/**
	 * This method is used in order to store the lines of the text file to a DoubleLinkedList. 
	 * Each line is read char b char, stored into a string and then if the string's size is less
	 * or equal to the maximum line size, the string is stored in the DoubleLinkedList.  
	 * @param fr is the first parameter used in order to read the input file
	 * @param dll is the second parameter used in order to fill the DoubleLinkedList 
	 * @throws IOException
	 * @see IOException 
	 */
	
	public static void storeStrings(FileReader fr, DoubleLinkedList dll) throws IOException {
		String string = new String();
		int endOfFile;
		while ((endOfFile = fr.read()) != -1) {
			char c = (char) endOfFile;
			if (c == '\n') {							//Checking if there is a change of line
				string += c;
				if (string.length() <= MAXLENGTH+1) {	//Checking if the string containing a line is of acceptable size
					dll.createList(string);
				}
				string = new String();
			} else {
				string += c;
			}
		}
	}

	/**
	 * This method is used in order to store the data of the DoubleLinkedList to a file. 
	 * Traversing the DoubleLinkedList from the beginning, each Node's data are store in a
	 * line in the file.
	 * @param dll is the first parameter used to get the DoubleLinkedList with its' data.
	 * @param file is the second parameter used to specify the name of the file that the data 
	 * will be written to. 
	 */
	
	public static void storeFile (DoubleLinkedList dll, String file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			DoubleLinkedList.Node cur = dll.getHead();
			while (cur != dll.getTail().next){				//Traversing through the DoubleLinkedList from the beginning
				fw.write(cur.data);
				cur = cur.next;
			}
			fw.close();
		} catch (IOException ex) {
			System.out.println("Can't store changes\n");
		}
	}

	/*
	 * public static void indexTable(DoubleLinkedList dll) { DoubleLinkedList.Node
	 * cur = dll.new Node("", 0); TuplesList tl = new TuplesList(); cur =
	 * dll.getHead(); String[] words; while (cur != dll.getTail()) { words =
	 * cur.data.split("\\P{Alpha}+"); for (int i = 0; i < words.length; i++) {
	 * if(words[i].length() < MINWORDSIZE) {} else if (words[i].length() >
	 * MAXWORDSIZE) { TuplesList.Tuple tuple = tl.new Tuple(cur.count,
	 * words[i].substring(MAXWORDSIZE)); tl.tupleList.add(tuple); } else {
	 * TuplesList.Tuple tuple = tl.new Tuple(cur.count, words[i]);
	 * tl.tupleList.add(tuple); }
	 * 
	 * } cur = cur.next; } tl.getSorted();
	 * 
	 * 
	 * for(int i = 0 ; i < tl.tupleList.size(); i++) {
	 * System.out.println(tl.tupleList.get(i).line+")"+tl.tupleList.get(i).word); }
	 * System.out.println(tl.tupleList.size());
	 * 
	 * }
	 */
	
}
