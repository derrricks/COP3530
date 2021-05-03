/**
 * This class is responsible for the creation and manipulation of a stack.
 * The class creates the stack of a specified size that is passed through the constructor. Once created 
 * methods can be used to push (insert), pop (remove), print the stack (from the top-down),
 * and tell if the stack is empty or full.
 *  
 * @author <Derrick Palma>
 * @version <September 28, 2020> 
 */
public class Stack {

	private int maxSize;	//Size of stack array
	private Country [] countries;	//Array of Country objects
	private int top;	//Top of stack 
	
	
	public Stack(int x) {	//Stack constructor creates a stack based on value that is passed through
		maxSize = x;
		countries = new Country[maxSize];
		top = -1;
	}
	
	
	/**
	 * A method to push a Country object onto a stack on top of the last entry (First entry if empty)
	 * @param x Country being pushed 
	 */
	public void push(Country x) {	
		countries[++top] = x;	
	}
	
	
	/**
	 * A method to pop a Country from a stack, starting at the top
	 * @return Country at a given position
	 */
	public Country pop() {		
		return countries[top--];	
	}	
	
	
	/**
	 * A method to print a stack's contents from the top down
	 * @return none
	 */
	public void printStack() {
		
		for(int i = top; i >= 0; i--) {
			countries[i].displayCountry();
		}
	}

	
	/**
	 * A method to determine if the stack is empty. Returns true if the stack is empty
	 * @return true, false
	 */
	public boolean isEmpty() {	
		if(top == -1) {
			return true;	
		}else {
			return false;	
		}
	}
	
	
	/**
	 * A method to determine if the stack is full. Returns true if the stack is full.
	 * @return true, false
	 */
	public boolean isFull() {	
		if(top == maxSize - 1) {
			return true;
		}else {
			return false;	
		}
	}		
}		