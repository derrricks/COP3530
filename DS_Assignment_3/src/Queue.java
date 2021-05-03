/**
 * This class is responsible for the creation and manipulation of a double ended doubly linked list.
 * The class creates the queue of countries that are linked together. Once 
 * created methods are available to insert into the queue from front or back, 
 * remove from front or back, tell if the queue is empty or full, and print the queue
 * in order from first to last.
 *  
 * @author <Derrick Palma>
 * @version <October 25, 2020>
 */
public class Queue {		//Start of Priority class
	
	private Country first;
	private Country last;
	
	/**
	 * Queue constructor: This method creates a double ended doubly linked list
	 */	
	public Queue(){	
	
		first = null;
		last = null;
	}
	
	
	/**
	 * A method to insert a country in the front of the queue
	 * @param x given Country 
	 */
	public void insertFront(Country x) {

		Country newCountry = x;
		
		if ( queueIsEmpty() ) 
			last = newCountry;
		else
			first.previous = newCountry;
		newCountry.next = first;
		first = newCountry;	
	}
	
	
	/**
	 * A method to insert a country in the end of the queue
	 * @param z given Country
	 */
	public void insertEnd(Country z) {	

		Country newCountry = z;
		
		if ( queueIsEmpty() ) 
			first = newCountry;
		else
			last.next = newCountry;
			newCountry.previous = last;
		last = newCountry;
	}
	
	
	/**
	 * A method to remove a country from the end of the queue
	 * @return temp Country removed
	 */
	public Country removeEnd() {
		
		Country temp = last;
		
		if( first.next == null ) 
			first = null;
		else
		
			last.previous.next = null;
		last = last.previous;
		return temp;
	}
	
	
	/**
	 * A method to remove a country from the front of the queue
	 * @return temp Country removed
	 */
	public Country removeFront() {

		Country temp = first;
		
		if( first.next == null )
			last = null;
		else 
			first.next.previous = null;
		first = first.next;
		return temp;
	}
	
	
	/**
	 * A method to delete a country based on two key values (the cfr of 2.5% and 3.5%)
	 * @param key 1 cfr key level 1
	 * @param key 2 cfr key level 2
	 * @return true, false
	 */
	public boolean intervalDelete(double key1 , double key2 ) {

		Country current = first;
		
		while( current.getCfr() <= key1 || current.getCfr() >= key2) {
			current = current.next;
			
			if( current == null ) {
				return false;
			}
		}
		
		if(current == first) 
			first = current.next;
		else 
			current.previous.next = current.next;
		
		if(current == last) 
			last = current.previous;
		else 
			current.next.previous = current.previous;
		return true;
	}
			
	
	/** 
	 * A method to print a queue's contents from:
	 * "First --> Last:"
	 */
	public void printQueue() {		

		Country current = first;
		
		while ( current != null) {
			current.displayCountry();
			current = current.next;
		}
	}
	
	
	/**
	 * A method to determine if the queue is empty. Returns true if the queue is empty 
	 * @return true, false
	 */
	public boolean queueIsEmpty() {

		if(first == null) {
			return true;		
		}else {
			return false;		
		}
	}
	
	
	/**
	 * A method to determine if the queue is full. Returns false (Queue is never full)
	 * @return false
	 */
	public  boolean queueIsFull() {	

		return false;	
	}
}