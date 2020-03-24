package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mypackage.TuplesList.Tuple;

public class TuplesList {
	
	class Tuple implements Comparable<Tuple>{
		
		int line; 
		String word;
		
		public Tuple(int line, String word) {
			this.line = line;
			this.word = word;
		}

		@Override
		public int compareTo(Tuple tuple) {
			return (this.word.compareTo(tuple.word));
		} 
	}
	
	
	ArrayList<Tuple> tupleList = new ArrayList<>();

	public TuplesList(ArrayList<Tuple> tupleList) {
		this.tupleList = tupleList;
	}
	
	public TuplesList() {
		
	}   
	
	public ArrayList<Tuple> getSorted() {         

	    Collections.sort(tupleList);         

	    return tupleList;     

	  } 

}
