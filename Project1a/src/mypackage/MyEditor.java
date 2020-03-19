package mypackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyEditor {
	
	final static int MAXLENGTH =80;
	
	public static void main(String[] args) {
		
		
		//This code needs to be changed for the final project in order to run from cmd
		//givenFile is args[0] 
		Scanner usersData = new Scanner(System.in);  
		System.out.println("Give File: ");
		
		String file = usersData.nextLine();
		System.out.println("File is: " +file);
		
		
		//Now back to the code for MyEditor
		try {
			FileReader fr = new FileReader(file);
			DoubleLinkedList dll = new DoubleLinkedList();
			DoubleLinkedList.Node cur = dll.new Node("",0);
			storeFile(fr,dll);
			boolean flag = true;
			while(flag) {
				String usersChoice = usersData.nextLine();
				switch(usersChoice) {
				case "^":
					System.out.println("Go to the first line");
					cur = dll.getHead();
					System.out.print(cur.data);
					break;
				case "$":
					System.out.println("Go to the last line");
					cur = dll.getTail()	;
					System.out.print(cur.data);
					break;
				case "-":
					System.out.println("Go up one line");
					cur = cur.previous;
					System.out.print(cur.data);
					break;
				case "+":
					System.out.println("Go down one line");
					cur = cur.next;
					System.out.print(cur.data);
					break;
				case "a":
					System.out.println("Add new line after current line");
					break;
				case "t":
					System.out.println("Add new line before current line");
					break;
				case "d":
					System.out.println("Delete current line");
					dll.deleteNode(cur);
					break;
				case "l":
					System.out.println("Print all lines");
					dll.printNodes();
					break;
				case "n":
					System.out.println("Toogle whether line numbers are displayed when printing all lines");
					dll.printNodesReverse();
					break;
				case "p":
					System.out.println("Print current line");
					System.out.print(cur.data);
					break;
				case "q":
					System.out.println("Quit without save");
					usersData.close();
					flag = false;
					break;
				case "w":
					System.out.println("Write file to disk");
					break;
				case "x":
					System.out.println("Exit and save");
					break;
				case "=":
					System.out.println("Print current line number");
					System.out.print(cur.count);
					break;
				case "#":
					System.out.println("Print number of lines and characters");
					System.out.print(dll.getTail().count);
					break;
				
				}
			}
			System.out.println("Koukou");
			fr.close();
		}
		catch (IOException ex) {
			System.out.println("Shit happens!");
		}

	}
	
	public static void storeFile (FileReader fr, DoubleLinkedList dll) throws IOException {
		char [] buff = new char[MAXLENGTH];
		String string = new String(buff);
		int endOfFile;
		int i = 0;
		while((endOfFile = fr.read())!= -1) {
			char c = (char)endOfFile;
			string = new String(buff);
			if(c == '\n') {
				dll.createList(string);
				buff = new char[MAXLENGTH];
				i = 0;
			}
			else {
				buff[i] = c;
				i++;
			}
		}
		char c = (char)endOfFile;
		i++;
		buff[i] = c;
		string = new String(buff);
		dll.createList(string);
	}


}


