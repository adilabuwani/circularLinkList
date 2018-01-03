//Name:Adil Abuwani
//Email: adil.abuwani@outlook.com
//CSCI 313 PROJECT
//PROFESSOR: Svitak 

import java.io.*;
import java.util.Iterator;
public class Assignment1 {

	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		String strLine="";
		//read from file
		FileInputStream fstream= new FileInputStream("assignment1.txt");
		br = new BufferedReader(new InputStreamReader(fstream));
		
		CircularLinkedList<String>myList= new CircularLinkedList<String>();
		CircularListReader listReader=new CircularListReader(myList);
		
		//read each line till not empty and do command
		while((strLine=br.readLine())!=null){
			listReader.doCommand(strLine);
		
		}//end while
		
		
		
		

			
			
			
		
		
		
		
		
		
		

	}

}
