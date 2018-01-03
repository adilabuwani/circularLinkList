//Name:Adil Abuwani
//Email: adil.abuwani@outlook.com
//CSCI 313 PROJECT
//PROFESSOR: Svitak 

import java.util.Iterator;

public class CircularListReader {
	
private CircularLinkedList<String>myList;
private String firstLine="";
private String command="";
private String assign="";

	//constructor
	public CircularListReader(CircularLinkedList<String>list){
		this.myList=list;
		this.firstLine="";
		this.command="";
		this.assign="";
	}
	
	//mutator
	private void setFirstLine(String f){
		this.firstLine=f;
		firstLine=firstLine.trim();  //eliminate leading and trailing spaces
	}
	
	private void setCommand(String f){
		String []temp=firstLine.split(" ");  //split the line
		this.command=temp[0]; //first index holds the desired command
		this.command=command.toLowerCase(); //set command to lowercase
		
		if(temp.length!=1){  //command also has assignment
			for(int i=1;i<temp.length;i++){
				this.assign=assign+ temp[i];
				if (i<temp.length-1){this.assign = assign + " ";}//set assign rhs
			}
		}
	}
	
	//accessors
	private String getFirstLine(){
		return this.firstLine;
	}
	
	private String getCommand(){
		return this.command;
	}
	
	public String getAssign(){
		return this.assign;
	}
	
	private void clearAssign(){
		this.assign=new String();
	}
	
	private void clearCommand(){
		this.command=new String();
	}
	
	
	public void doCommand(String fLine){  //do command from the first line

		this.setFirstLine(fLine);  //set the first line
		this.setCommand(fLine);   //set command
		
		if(this.getCommand().contains("clear")){
			clear();
		}else if(this.command.contains("size")){
			size();
		}else if(this.command.contains("empty")){
			empty();
		} else if(this.command.equals("add")){
			add(assign);  //add the assign value
		}else if(this.command.contains("set")){
			set(assign);
		}else if(this.command.contains("get")){
			get(assign);
		}else if(this.command.contains("rotate")){
			rotate();
		}else if(this.command.contains("remove")){
			remove(assign);
		}else if(this.command.equals("add_last")){
			addLast(assign);
		}else if(this.command.contains("print")){
			print();	
		}else{
			return;    //do nothing
		}
		
		this.clearAssign();    //clear assign
		this.clearCommand();  //clear command
		
	}// end do command
	
private void add(String assignValue){
  assignValue=assignValue.trim();  //ignore whitespace front and leading
  String []temp=assign.split(" ");
  int ind=Integer.valueOf(temp[0]);
  String newVal="";
  for(int i=1;i<temp.length;i++){
	  newVal=newVal+temp[i];
	  if (i<temp.length-1){newVal = newVal + " ";}
  }//end for
  this.myList.add(ind, newVal);  //add at index new value
  System.out.println("The String \""+newVal+"\" at position "+ind+" in the circular Linked list.\n");
  
} //end add

private void set(String assignValue){
	  assignValue=assignValue.trim();  //ignore whitespace front and leading
	  String []temp=assign.split(" ");
	  int ind=Integer.valueOf(temp[0]);
	  String newVal="";
	  for(int i=1;i<temp.length;i++){
		  newVal=newVal+temp[i];
		  if (i<temp.length-1){newVal = newVal + " ";}
	  }
	  
	  System.out.println("The string \""+this.myList.set(ind, newVal)+"\" replaces the string \""+newVal+"\" at position "+ind+" in the circular Linked List.\n");
	  
}

private void get(String assignValue){
	assignValue=assignValue.trim();
	int ind=Integer.valueOf(assignValue);
	System.out.println("The String at position "+ind+" in the circular Linked List is \""+this.myList.get(ind)+"\"\n");
	this.clearAssign();  //clear assign  //clear assign
}

private void rotate(){
	this.myList.rotate();
	System.out.println("The value at the Head of the circular linked list was rotated to the tail of the circular Linked list."+'\n');
}

private void clear(){
	myList.clear();
	System.out.println("The Circular Linked List is now Empty."+'\n');
}

private void size(){
	System.out.println("The Circular List contains "+myList.size()+" Strings. \n");
}

private void empty(){
	if(myList.isEmpty()){
		System.out.println("The Circular List is empty. \n");
	}else{
		System.out.println("The Circular List is not empty. \n");
	}
}

private void remove(String assignValue){
	assignValue=assignValue.trim();
	int ind=Integer.valueOf(assignValue);
	System.out.println("Removed the string \""+this.myList.remove(ind)+"\" at position "+ind+" from the circular linked list. \n");
    this.clearAssign();  //clear assign
}

private void addLast(String assignValue){
	this.myList.add(assignValue);
	System.out.println("Added the string \""+assignValue+ "\" to the end of the circular Linked List. \n");
	this.clearAssign();

}

private void print(){
	  Iterator<String> it = this.myList.iterator();
	  if(this.myList.isEmpty()){
		System.out.println("There are no elements in the Circular Linked List.");
	}else{
		System.out.println("The circular Linked List contains:");
		for(int i=0;i<myList.size();i++){
			System.out.println(it.next());
			}
		
	}
	
}  //end print


}
