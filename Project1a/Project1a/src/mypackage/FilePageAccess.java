package mypackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FilePageAccess {
	
	File fileName; 
	int pageSize; 
	int currentPage; 
	int numOfPages = 0;
			
	public FilePageAccess(File fileName, int pageSize) {
		super();
		this.fileName = fileName;
		this.pageSize = pageSize;
	}
	
	byte[] read(int pageNumber) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(fileName,"r");
		currentPage = pageNumber;
		raf.seek(pageNumber*pageSize);
		byte [] buf = new byte[pageSize]; 
		raf.read(buf);
		raf.close();
		return buf;
	}
	
	void write (int pageNumber, byte[] buf) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(fileName,"w");
		raf.seek(pageNumber*pageSize);
		raf.write(buf);
		numOfPages++;
		raf.close();
	}
	
	byte[] readNext() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(fileName,"r");
		byte [] buf = new byte[pageSize]; 
		if(hasNext()) {
			long pos = raf.getFilePointer();
			raf.seek(pos+pageSize);
			raf.read(buf);
			raf.close();
		}
		return buf;
	}
	
	boolean hasNext() {
		return currentPage<numOfPages;
	}
	

}
