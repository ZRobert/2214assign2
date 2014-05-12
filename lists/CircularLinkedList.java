/*********************************************************
* CircularLinkedList.java
* Author: Robert Payne
* Date: 7/26/2012
* Class: ITCS 2214-021

* Purpose: CircularLinkedList provides a generic framework 
* to implement a circular linked list with of any type. The 
* default linked list is an ordered double linked list. 
* There is a constructor that accepts a Boolean to set if 
* it is an ordered list(true) or inordered list(false).
////////////////////////////////////////////////////////*/
 
package lists;
import java.util.Random;

public class CircularLinkedList<T extends Comparable<T>> implements CustomList<T> {

/*
*  Fields
-----------------------------------------------*/
	private DoubleNode<T> head;
   private DoubleNode<T> tail;
   private int count;
	private boolean isSorted;

/*
*	Constructors
-----------------------------------------------*/

   public CircularLinkedList() {
		
		head = null;
		tail = null;
		count = 0;
		isSorted = true;      
    
	}
	 
	public CircularLinkedList(boolean sorted) {
			
		head = null;
		tail = null;
		count = 0;
		isSorted = sorted;
	 
	}

/*
*	Methods
*------------------------------------------------
*	addToFront(element)
*------------------------------------------------
* Adds the element to the front of the list, if
* it is ordered, it will call isDescendingSort
* and call ascendingSort or descendingSort
* as appropriate.
-----------------------------------------------*/

	@Override
	public void addToFront(T element) {
	 		
		DoubleNode<T> temp = new DoubleNode<T>();
		temp.setContent(element);	

		if(count == 0){
		
			head = temp;
			tail = temp;
			temp.setNext(temp);
			temp.setPrevious(temp);
			count++;
			
		}
			
		else{
				
			temp.setNext(head);
			head.setPrevious(temp);
			head = temp;
			head.setPrevious(tail);
			tail.setNext(head);
			count++;
			
		}

		if(isSorted && isDescendingSorted())
			descendingSort();
		
		else if(isSorted)	
			ascendingSort();		     
  	 
	}

/*
*------------------------------------------------
*	addToRear(element)
*------------------------------------------------
* Adds the element to the rear of the list, if
* it is ordered, it will check if it is in a
* descendingSort condition and will call either
* ascending or descending sort as appropriate.
------------------------------------------------*/

	 @Override
    public void addToRear(T element) {
		
		DoubleNode<T> temp = new DoubleNode<T>(element);
			
			
		if(count == 0){
		
			head = temp;
			tail = temp;
			temp.setNext(temp);
			temp.setPrevious(temp);
			count++;
		}
		
		else{
				
			temp.setNext(head);
			temp.setPrevious(tail);
			head.setPrevious(temp);
			tail.setNext(temp);
				tail = temp;
				count++;
		}
			
		if(isSorted && isDescendingSorted())
			descendingSort();
		else if(isSorted)
			ascendingSort();        
    
	}

/*
*------------------------------------------------
*	 addAfter(element, target)
*------------------------------------------------
* Searches for the target then adds the element
* after if it finds it. If the list is in order
* it will call addToRear. 
------------------------------------------------*/	
		 
	@Override
   public void addAfter(T element, T target) {
	 
		if(isEmpty()){
		
			addToFront(element);
			return;
		}
	 
		if(tail.getContent().compareTo(target) == 0 || isSorted){
	
	 		addToRear(element);
	 		return;
		}
	 
		DoubleNode<T> temp = new DoubleNode<T>(element);
		DoubleNode<T> current = head;
		DoubleNode<T> previous = tail;
		boolean found = false;
	 
		while(current != tail && !found){
	
			if(previous.getContent().compareTo(target) == 0){
		 	
				found = true;
				previous.setNext(temp);
				temp.setPrevious(previous);
				temp.setNext(current);
				current.setPrevious(temp);
				count++;
			}
		
			current = current.getNext();
			previous = previous.getNext();
		
		}
	
		if(!found)
			addToRear(element);
		
		if(isSorted && isDescendingSorted())
			descendingSort();
	
		else if(isSorted)
			ascendingSort();
	
	}

/*
*-----------------------------------------------
*	 addBefore(element, target)
*-----------------------------------------------
* Searches for the target then adds the element
* before if it finds it. If the list is in order
* it will call addToFront.
*/

	@Override
   public void addBefore(T element, T target) {

		if(isEmpty()){
			
			addToFront(element);
			return;
		}
	 
		if(head.getContent().compareTo(target) == 0 || isSorted){
		
			addToFront(element);
	 		return;
	 	}
	 
	 	DoubleNode<T> temp = new DoubleNode<T>(element);
	 	DoubleNode<T> current = head;
	 	DoubleNode<T> previous = tail;
	 	boolean found = false;
	 
	 	while(current != tail && !found){
		
	 		if(current.getContent().compareTo(target) == 0){
			
		 		found = true;
				previous.setNext(temp);
				temp.setPrevious(previous);
				temp.setNext(current);
				current.setPrevious(temp);
				count++;
			}
		
			current = current.getNext();
			previous = previous.getNext();
		}
		
		if(!found)
			addToFront(element);	
			
	}

/*
*-----------------------------------------------
*	 removeFirst()
*-----------------------------------------------
* Removes the element that the head is pointing
* to.
*/		

	@Override
	public T removeFirst() {
		
		DoubleNode<T> temp = null;
		
		if(isEmpty())
			return null;
			
		else{
	
			temp = head;
			tail.setNext(head.getNext());
			head.getNext().setPrevious(tail);
			head = head.getNext();
			count--;
			return temp.getContent();
		}
	}
	
/*
*-----------------------------------------------
*	 removeLast()
*-----------------------------------------------
* Removes the element that the tail is pointing
* to.
*/
	@Override
	public T removeLast() {

  		DoubleNode<T> temp = null;
		
		if(isEmpty())
			return null;
		
		else{
			
			temp = tail;
			tail.getPrevious().setNext(head);
			head.setPrevious(tail.getPrevious());
			tail = tail.getPrevious();
			count--;
			return temp.getContent();
		}
	}

/*
*-----------------------------------------------
*	 remove(target)
*-----------------------------------------------
* Searches for the target, then removes it from
* the list.
*/

	@Override
	public T remove(T target) {
   	
		if(isEmpty())
			return null;
					 	
		boolean found = false;
		DoubleNode<T> current = head;
		DoubleNode<T> previous = null;
		DoubleNode<T> temp = null;

			
		if(head.getContent().compareTo(target) == 0){
		
			found = true;
			current = head;
			removeFirst();
			return current.getContent();
		}
				
		else{
			
			for(int i = 1; i < count; i++){
					
					previous = current;
					current = current.getNext();
					if(current.getContent().compareTo(target) == 0){
						
						found = true;
						temp = current;
						current = current.getNext();
						previous.setNext(current);
						current.setPrevious(previous);
						count--;
						return temp.getContent();
					}
			}
		}
		
		return null;
	}
	
 /*
*-----------------------------------------------
*	 clear()
*-----------------------------------------------
* Sets the head and tail to null which deletes
* the list.
*/

	@Override
	public void clear() {
	 	
		head = null;
		tail = null;
	 }

/*
*-----------------------------------------------
*	 moveUp(target)
*-----------------------------------------------
* Searches for the target then swaps it's 
* position with the element above it on the list.
*/

	@Override	 	 
	public void moveUp(T target) {
	 
		if(isEmpty())
	 		return;
	
	 	boolean found = false;
	 	DoubleNode<T> current = head.getNext();
	 	DoubleNode<T> previous = head;
	 
	 	while(current != head && !found){
		
	 	 	if(current.getContent().compareTo(target) == 0){
			
		 		found = true;
				isSorted = false;
				current.setPrevious(previous.getPrevious());
				previous.getPrevious().setNext(current);
				previous.setNext(current.getNext());
				current.getNext().setPrevious(previous);
				current.setNext(previous);
				previous.setPrevious(current);
				return;
			}
		
			current = current.getNext();
			previous = previous.getNext();
		}
	}
	
/*
*-----------------------------------------------
*	 moveDown(target)
*-----------------------------------------------
* Searches for the target then swaps it's 
* position with the element below it on the list.
*/

	@Override
	public void moveDown(T target) {
	 
		if(isEmpty())
	 		return;
			
		boolean found = false;
		DoubleNode<T> current = head.getNext();
		DoubleNode<T> previous = head;
		T temp;
		
		if(head.getContent().compareTo(target) == 0){
		
			isSorted = false;
			temp = head.getContent();
			head.setContent(head.getNext().getContent());
			head.getNext().setContent(temp);
			return;
		}
			
		while(current != tail && !found){
		
			if(previous.getContent().compareTo(target) == 0){
		 		
				found = true;
				isSorted = false;
				current.setPrevious(previous.getPrevious());
				previous.getPrevious().setNext(current);
				current.getNext().setPrevious(previous);
				previous.setNext(current.getNext());
				current.getNext().setPrevious(previous);
				current.setNext(previous);
				previous.setPrevious(current);
				return;
			}
		
			current = current.getNext();
			previous = previous.getNext();
		}
	}

/*
*-----------------------------------------------
*	 contains(target)
*-----------------------------------------------
* Searches for the target then returns true 
* if found, else returns false.
*/

	@Override
	public boolean contains(T target) {
		
		DoubleNode<T> temp = head;
	
		for(int i = 0; i < count; i++){
	
			if(temp.getContent().compareTo(target) == 0)
				return true;
			
			temp = temp.getNext();
		}
		 
		return false;
	}
	
/*
*-----------------------------------------------
*	 ascendingSort()
*-----------------------------------------------
* Uses an selection sort algorithm to sort the 
* list from smallest to largest. Sets the 
* isSorted value to true.
*/
	@Override
   public void ascendingSort() {
	 
		if(isEmpty())
			return;
	 
		DoubleNode<T> current = head;
		DoubleNode<T> previous = head;
		DoubleNode<T> swap;
		T temp;
		
		
		for(int i = 0; i < count; i++){
		
			current = previous.getNext();
			swap = previous;
			previous = previous.getNext();
			
			for(int k = 1; k < count - i; k++){
				
				if(current.getContent().compareTo(swap.getContent()) < 0)
					swap = current;
				
				current = current.getNext();
			}
			
			if(swap.getContent().compareTo(previous.getPrevious().getContent()) < 0){
			
				temp =swap.getContent();
				swap.setContent(previous.getPrevious().getContent());
				previous.getPrevious().setContent(temp);
			}
		}	
		
		isSorted = true;
	}
		
/*
*-----------------------------------------------
*	 descendingSort()
*-----------------------------------------------
* Uses a selection sort algorithm to sort the 
* list from largest to smallest. Sets the 
* isSorted value to true.
*/

	@Override
	public void descendingSort() {
	 
		if(isEmpty())
			return;
		
		DoubleNode<T> current = head;
		DoubleNode<T> previous = head;
		DoubleNode<T> swap;
		T temp;
		
		for(int i = 0; i < count; i++){
		
			current = previous.getNext();
			swap = previous;
			previous = previous.getNext();
				
			for(int k = 1; k < count - i; k++){
			
				if(current.getContent().compareTo(swap.getContent()) > 0)
					swap = current;
				
				current = current.getNext();
			}
			
			if(swap.getContent().compareTo(previous.getPrevious().getContent()) > 0){
			
				temp =swap.getContent();
				swap.setContent(previous.getPrevious().getContent());
				previous.getPrevious().setContent(temp);
			}
		}
		
		isSorted = true;
	}
	
/*
*-----------------------------------------------
*	 slide()
*-----------------------------------------------
* Moves the head and the tail the proper amount
* to slide the elements. Ordered list does not
* slide. Changes the isSorted value to false.
*/        
 
	@Override	 
	public void slide(int amount) {
	 
		if(isEmpty())
	 		return;
	 
		int amtSlide = amount;
	 
		if(amount == 0)
			return;
	 
		//Positive Slide
	 	if(amount > 0)
	 		for(int slide = 0; slide < amtSlide; slide++){

				head = tail;
				tail = tail.getPrevious();
			}
		
		//Negative Slide
		if(amount < 0)
			for(int slide = 0; slide > amtSlide; slide--){

				tail = head;
				head = head.getNext();
			}	
		
		isSorted = false;
	}
 
 /*
*-----------------------------------------------
*	 shuffle()
*-----------------------------------------------
* Shuffle uses a combination removeFirst, 
* removeLast, addToFront, addToRear, slide,
* and random numbers to shuffle the elements 
* around. Changes the isSorted value to false.
*/

	@Override	 
	public void shuffle() {
	 
		if(isEmpty())
			return;
	 	
		DoubleNode<T> temp = null;
		Random randNum = new Random();
	 
		for(int i = 0; i < count; i++){
		
	 		slide(randNum.nextInt(count));	
			
			if(randNum.nextInt(2)%2 == 0){
				
				temp = head;
				removeFirst();
			}
			
			else{
				
				temp = tail;
				removeLast();
			}
				
			slide(randNum.nextInt(count));
			
			if(randNum.nextInt(2)%2 == 0)
				addToFront(temp.getContent());
			
			else
				addToRear(temp.getContent());
	 	}
		
		isSorted = false;
	}

/*
*-----------------------------------------------
*	 isEmpty()
*-----------------------------------------------
* Returns true if the head and tail are set to 
* null. False if they are not set to null.
*/ 

	@Override	 
	public boolean isEmpty() {
		
		if(head == null && tail == null)	
			return true;
		
		else
			return false;
	}

/*
*-----------------------------------------------
*	 size()
*-----------------------------------------------
* Returns a count or nodes as an integer.
*/
 	 
	@Override
	public int size() {
		return count;
	}

/*
*-----------------------------------------------
*	 toString()
*-----------------------------------------------
* Returns a string consisting of the contents
* of each element, starting with the head, with
* a "\n" between each element.
*/ 

	@Override
	public String toString() {
   		
		if(isEmpty())
			return null; 		
			
		String tempStr = "\n";
		DoubleNode<T> current = head;
		
		tempStr = current.getContent() + "\n";
			
			for(int i = 0; i < count - 1; i++){
			
				current = current.getNext();
				tempStr += current.getContent() + "\n";
			}
			 
			return tempStr;
	}
	
/*
*------------------------------------------------
*	 isDecendingSorted()
*------------------------------------------------
* This method checks to see if the list if either
* the tail or head of the list is sorted. This is
* done after every addition to the list, so if 
* there is an addition to the front, then the 
* we need to check the rear for sorting. If the
* addition is at the rear then we need to check 
* the front for sorting. If there is an add after
* or before a middle element, then both checks will
* be true and return false. If the elements are
* opposite logic of ascending or equal, then 
* decending sort will prevail. Otherwise, ascending
* sort prevails.
*/
 
private boolean isDescendingSorted()
	{
		if(isEmpty())
			return false;
	
		else if(head == tail)
			return false;
	
		else if(head.getContent().compareTo(head.getNext().getContent()) > 0|| 
		tail.getPrevious().getContent().compareTo(tail.getContent()) > 0)
			return false;
		
		else
			return true;
	}
}