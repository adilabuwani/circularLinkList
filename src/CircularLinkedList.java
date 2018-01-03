//Name:Adil Abuwani
//Email: adil.abuwani@outlook.com
//CSCI 313 PROJECT
//PROFESSOR: Svitak 


import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class CircularLinkedList<AnyType> implements List<AnyType>
{
  private static class Node<AnyType>
  {
    private AnyType data;
    private Node<AnyType> next;
    
    //constructor
    public Node(AnyType d, Node<AnyType> n){
      setData(d);
      setNext(n);
    }

    public AnyType getData() {
    	return data; 
    }

    public void setData(AnyType d) { 
    	data = d; 
    	
    }

    public Node<AnyType> getNext() {
    	
    	return next;
    }

    public void setNext(Node<AnyType> n) { 
    	next = n; 
    	}
  
  }  //end node class

  /////////////////////////////////////////////////////////////////////////////////
  
  private int theSize;
  private int modCount;
  private Node<AnyType> tail;
 

  public CircularLinkedList(){
	  this.clear();
	  this.modCount=0;
  }

  public void clear(){
	  this.tail=new Node<AnyType>(null, null);
      this.tail.setNext(tail);
	  this.theSize=0;  
  } //end clear

  public int size(){
	 return this.theSize;    //Return the number of data values currently in the list.
  }//end size

  public boolean isEmpty(){ //Return true if the list is empty
	  return (this.size()==0);
  }

  public AnyType get(int index){     //get data at specified index
	  Node<AnyType>temp=this.getNode(index);
	  return temp.getData();
  }

  public AnyType set(int index, AnyType newValue){
	  AnyType oldData=getNode(index).getData();
	  getNode(index).setData(newValue);
	  return oldData;
		  
  }

  public boolean add(AnyType newValue)
  {
    add(size(), newValue);
    return true;
  }

  public void add(int index, AnyType newValue){
	  
	  if (index < 0 || index > theSize){  //invalid index, throw exception
	      throw new IndexOutOfBoundsException("Invalid indexx");
	  }
	      
	 //if List empty,  or the index to insert at first element
	 if(isEmpty()||index==0){
		 insertAtFirst(index, newValue);  //insert at first
		 
	 }else if(index<theSize){      //insertion is inbetween the list
		 insertAtBetween(index, newValue);
		 
	 }else{                        //insertion is at the end of the list            
		 
		 insertAtLast(index, newValue);
	 }
	  this.theSize++;  
	  this.modCount++;
    
  }   //end add

  public AnyType remove(int index)
  {
     if(index<0||index>this.size()-1){
    	 throw new IndexOutOfBoundsException("Invalid Index");
     }
     
	  AnyType temp;
	  Node<AnyType>previous=tail;
	  Node<AnyType>current=tail.getNext();
	  
	  for(int i=0;i<index;i++){
		  previous=current;
		  current=current.getNext();  
	  }
	  //found data--currnt holds the data to remove
	  temp=current.getData();  //hold the data
	  previous.setNext(current.next);
	  if(this.size()==1){this.clear();}  //last elem? clear
	  
	  this.theSize--;
	  this.modCount++;  //change made
	  
	  return temp;    //return the data removed
  }

  public void rotate(){
	
   tail=tail.getNext();
   this.modCount++;  //change made
	  
  }

  public Iterator<AnyType> iterator()
  {
    return new LinkedListIterator();    
  }

  private Node<AnyType> getNode(int index){
    return (getNode(index, 0, size()-1));
  }

  private Node<AnyType> getNode(int index, int lower, int upper){
	  Node<AnyType> temp=tail.getNext();
	  
      if(index<lower|| index>upper){
    	  throw new IndexOutOfBoundsException("Invalid Index");
      }
      
      for(int i=0;i<index;i++){
    	temp=temp.getNext();
      }
      
      return temp;
  }    
  
  //insert at the start of the list-private
  
  private void insertAtFirst(int ind, AnyType data){
	  
	  Node<AnyType>newNode= new Node <AnyType>(data, null);  //create new node of data
		//if tail is the first element 
		if(isEmpty()){
			this.tail=newNode;      //tail is new Link
			newNode.setNext(tail);  //its self
		}else{
			//is not empty 
		   newNode.setNext(tail.getNext());
		   tail.setNext(newNode);    //set tail next to point to the newnode
       }	
  
  }//end ins first
  
  private void insertAtBetween(int ind, AnyType data){
	  
	    Node<AnyType> current=tail.getNext();
		Node<AnyType> previous=tail.getNext();
		
		Node<AnyType>newNode=new Node<AnyType>(data, null);  //create new Link of given data
		
		int i=0;  //start from first index
		
		while(i<ind){
			previous=current;
			current=current.getNext();  //ove current to next
		  i++;	 // inc index 
		}
		 newNode.setNext(current);  //set newNode next to be the current
		previous.setNext(newNode);  //set the next of previous to the new Link
	  
  }//end insert in between
  
  private void insertAtLast(int ind, AnyType data){
		//no need to acan if Last element
		Node <AnyType>newLink= new Node<AnyType>(data, null);
		Node<AnyType> previous=tail;
		Node<AnyType> current=tail.getNext();

		previous.setNext(newLink);
		newLink.setNext(current);
		tail=newLink;
	} //end insert at Last
  
  ////////////////////////////////////////////////////////////////////////////
  
  //make sure to remove this function it is JUST FOR YOUR TEST
  
  public void display(){
	  
	  if(tail==null){
		  System.out.println("The list is empty");
		  return;
	  }
	  
	  Node<AnyType> current = tail.next;  //first element
		
		do {
			System.out.print(current.getData() + ",");
			current = current.getNext();
			
		}while(current!=tail.next);
		
  }
	     
////////////////////////////////////////////////////////////////////////////////
  private class LinkedListIterator implements Iterator<AnyType>
  {
    private Node<AnyType> previous;
    private Node<AnyType> current;
    private int expectedModCount;
    private boolean okToRemove;

    LinkedListIterator(){
    	this.previous=tail;
    	this.current = tail.getNext();
    	this.expectedModCount=modCount;
    	this.okToRemove=false;

    }

    public boolean hasNext(){
     return (this.current!=this.previous);    
    }
    
    public AnyType next(){
        AnyType currentData;
        if(modCount!=this.expectedModCount){
        	throw new ConcurrentModificationException();
        }
        
        if (!hasNext()){   //if no next element
            throw new NoSuchElementException();
    }
        currentData=current.getData();
        previous=current;
        current=current.getNext();
        this.okToRemove=true;
        return currentData;          
    }

    public void remove(){
    	if (modCount != expectedModCount){
            throw new ConcurrentModificationException();   //modifications made in the circularList
    	}
            if (!okToRemove){
            throw new IllegalStateException();  //nothing to remove
        }
           
           if(this.hasNext()){      //if next element exist
        	   
        	   Node<AnyType> oldPrevious = tail;
				while (oldPrevious.getNext()!= this.previous) {
					oldPrevious = oldPrevious.getNext();
				}
				
				oldPrevious.setNext(this.current);
				this.previous.setNext(null);
				this.previous.setData(null);
				this.previous = oldPrevious;
				modCount++;                    //change made increment modCount and expectedmod
				this.expectedModCount++;
				this.okToRemove = false;
           }else if (tail.getData()!=null){
        	   current.setData(null);
				modCount++;
				expectedModCount++;
				okToRemove = false;
           }else{
        	   throw new IllegalStateException("Nothing to be removed");  //nothing to remove
           }
             
          
    }//end remove
    
    
  } //end LinkListIterator

}
