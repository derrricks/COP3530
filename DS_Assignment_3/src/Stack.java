/** 
 * This class is responsible for the creation and manipulation of a double ended singly linked list.
 * The class creates the stack that is linked together country by country. Once created 
 * methods can be used to push (insert), pop (remove), print the stack (from the top-down),
 * and tell if the stack is empty or full.
 *  
 * @author <Derrick Palma>
 * @version <October 25, 2020>
 */
public class Stack {

	class Link{
		
		public Country linker;
		public Link next;
		
		public Link(Country link) {
			linker = link;
		}
	}
	
	private Link first; //tracks front item in the stack
	private Link last;  //tracks last item in the stack
	
	/**
	 * Stack constructor: This method creates a double ended singly linked list		 
	 */
	public Stack() {		

		first = null; 
		last = null; 
	}

	
	/**
	 * A method to push a Country object onto a stack on top of the last entry (First entry if empty)	
	 * @param x given Country
	 */
	public void push(Country x) {	

		Link newCountry = new Link(x);
		
		if( isEmpty() ) 
			last = newCountry;
		newCountry.next = first;
		first = newCountry;
	}
	
	
	/**
	 * A method to pop a Country from a stack, starting at the top
	 * @return temp Country removed
	 */
	public Country pop() {	

		Country temp = first.linker;
		
		if( first.next == null ) 
			last = null;
		first = first.next;	
		return temp;
	}	
	
	
	/** 
	 * A method to print a stack's contents from:
	 * "Top --> Bottom:"
	 */
	public void printStack() {	

		Link current = first;
		while(current != null) {
			current.linker.displayCountry();
			current = current.next;
		}
	}
	
	
	/**
	 * A method to determine if the stack is empty. Returns true if the stack is empty 
	 * @return true, false
	 */
	public boolean isEmpty() {	

		if(first == null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * A method to determine if the stack is full. Returns false (Stack is never full)
	 * @return false
	 */
	public boolean isFull() {

		return false;
	}
}