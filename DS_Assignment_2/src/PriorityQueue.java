/**
 * This class is responsible for the creation and manipulation of a Priority Queue.
 * The class creates the queue of a specified size that is passed through the constructor. Once 
 * created the queue sorts the countries by COVID-19 CFR. The lower the CFR 
 * the higher the priority. There are methods to insert, delete, tell if empty or full, and 
 * a method to print each item of the given priority queue.
 *  
 * @author <Derrick Palma>
 * @version <September 28, 2020> 
 */
public class PriorityQueue {		//Start of Priority class
	
	private int maxSize;	//Used to set the max size of the queue
	private Country [] countries; //Array of Country objects
	private int nItems;		//Used to track amount of elements in the queue
	
	
	/**
	 * Priority constructor creates queue based on value that is passed through
	 * @param z maxSize of PriorityQueue
	 */
	public PriorityQueue(int z){		
		maxSize = z;
		countries= new Country[maxSize];
		nItems = 0;	
		
	}
	
	
	/**
	 * Method to insert a Country into a given priority queue. (O(N) insertion)
	 * Once passed through the Country is sorted based on COVID-19 CFR 
	 * @param z a given Country
	 */
	public void insert(Country z) {		
									
		int j;
		
		if(nItems == 0) {
			countries[nItems++] = z;
		}else {
			for(j = nItems - 1; j >= 0; j--) {
				if( z.getCfr() > countries[j].getCfr()) {
					countries[j+1] = countries[j];
				}else {
					break;
				}
			}
			countries[j+1]= z;
			nItems++;
		}
	}
	
	
	/**
	 * Method used to remove a Country object from a given priority queue. O(1) removal
	 * @return Country at a given index
	 */
	public Country remove() {
		return countries[--nItems];	
	}
	
	
	/**
	 * Method to print the priority queue in priority order
	 * @return none
	 */
	public void printQueue() {
		for(int i = nItems - 1; i >= 0; i--) {
			countries[i].displayCountry();
		}
	}
	
	
	/**
	 * Method to tell if the priority queue is empty. Returns true if empty.
	 * @return true,false
	 */
	public boolean PQisEmpty() {
		if(nItems == 0) {
			return true;	
		}else {
			return false;
		}
	}
	
	
	/**
	 * Method to tell if the priority queue is full. Returns true if full.
	 * @return true, false
	 */
	public boolean PQisFull() {	
		if(nItems == maxSize) {
			return true;		
		}else {
			return false;		
		}
	}	
}