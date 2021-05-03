/**
 * This class is responsible for the creation of a hash table. The HashTable class
 * makes use of two other inner classes that assist in the hash table's creation.
 * The first class is the Node class, which is responsible for holding the Country object's
 * information. A SortedList class is then used when manipulating elements in the hash table.
 * Both the SortedList and HashTable class make use of an insert, delete, find, and display method.
 * The HashTable class is public and used within the main method. The SortedList class is only used
 * within the HashTable class to assist in proper creation and implementation.
 * 
 * @author <Derrick Palma>
 * @version <November 27, 2020>
 */
public class HashTable {
	
	/**
	 * The Node class meant to store the data from each country. This data will be used to build the tree.
	 */
	private class Node{
		String name;
		String capitol;
		double CFR;
		Node nextNode;
		
		public Node(String name, String capitol, double cfr) {		//Node contains only this info
			this.name = name;
			this.capitol = capitol;
			this.CFR = cfr;
		}
		
		public void printNode() {	//print Node data
			System.out.printf("%-40s %-20s %-30.6f\n", name, capitol, CFR);
		}
	}//END NODE CLASS

		
	/**
	 *  The SortedList class makes uses of a double-ended singly linked list which is used when
	 *  manipulating the values of the hash table. For example, proper insertion, searching for
	 *  a country by name and capital, and searching by a key for proper deletion.
	 */
	 public class SortedList{
		
		public Node first;
		private Node last;
		
		/**
		 * SortedList constructor to create a empty list
		 */
		public SortedList() {
			first = null;
			last = null;
		}
		
		/**
		 * The insert method inserts a country as the last element in the list
		 * @param name The given country's name
		 * @param capital The given country's capital
		 * @param cfr The given country's CFR
		 * @return none
		 */
		public void insert(String name, String capital, double cfr) {
		
		Node newCountry = new Node(name, capital, cfr);
			
		if(isEmpty()) 
			first = newCountry;
		else
			last.nextNode = newCountry;
		last = newCountry;
		
		}
		
		/**
		 * The find method finds a country based on a given name.
		 * @param name The given country's name
		 * @param capital The given country's capital
		 * @return null If no country is found
		 * @return current Country was located
		 */
		public Node find(String name, String capital) {
			
			Node current = first;
			if(current == null)
				return null;
			
			while(!current.name.equals(name) && !current.capitol.equals(capital)) {
				if(current.nextNode == null)
					return null;
				else
					current = current.nextNode;
			}
			return current;
		}
		
		
		/**
		 * The delete method deletes a country from the table given the country's name
		 * @param name The given country's name
		 * @param capital The given country's capital
		 * @return none
		 */
		public void delete(String name, String capital) {
		
			Node current = first;
			Node previous = first;
			while(!current.name.equals(name) && !current.capitol.equals(capital)) {
				if(current.nextNode == null)
					return;
				else {
					previous = current;
					current = current.nextNode;
				}
			}
			if(current == first) 
				first = first.nextNode;
			else
				previous.nextNode = current.nextNode;
			return;		
		}
			
		/**
		 * The displayList method displays the elements at a given position in the hash table
		 * @return none
		 */
		public void displayList() {
			
			Node current = first;
			
			if(current == null) 
				System.out.print("Empty");
				
			while(current != null) {
				current.printNode();
				current = current.nextNode;
			}
			System.out.println();
		}	

		
		/**
		 * A isEmpty method used to determine if the list is empty at a given location in the hash table
		 * @return true If list is empty at a given location
		 * @return false If list is not empty
		 */
		public boolean isEmpty() {
			if(first == null) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	//END SORTED LIST CLASS
		
	
	private SortedList [] hashArray;	//creates an empty hash table
	
	
	/**
	 * HashTable constructor is responsible for creating a hash table of size 307 and filling the table at a given index with null values
	 */
	public HashTable() {

		hashArray = new SortedList[307];
		for(int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new SortedList(); //crate an empty hashtable
		}
	}

	
	/**
	 * The hashFunction method is responsible for hashing (finding the proper location) each country into the table
	 * The hashFunction starts with the sum of the Unicode values (ASCII values) of all of the characters in the country name and capitol strings (including spaces), and
	 * then modulus the result with 307
	 * @param name The given country's name
	 * @param capital The given country's capital
	 * @return finalSum The index value to store a given country object
	 */
	public int hashFunction(String name, String capital){
	
		String countryName = name;
		String countryCapital = capital;
		int nameSum = 0;
		int capitalSum = 0;
		int finalSum = 0;
	
		for(int i = 0; i < countryName.length(); i++) {	
			nameSum = nameSum + (int)countryName.charAt(i);
		}
	
		for(int j = 0; j < countryCapital.length(); j++) {	
			capitalSum = capitalSum + (int)countryCapital.charAt(j);
		}
	
		finalSum = nameSum + capitalSum;
		finalSum = finalSum % 307;
		return finalSum;
	}
	
	
	/**
	 * The insert method inserts a country into the hash table given its hashValue
	 * @param name The given country's name
	 * @param capital The given country's capital
	 * @param cfr The given country's CFR
	 */
	public void insert(String country, String capital, double cfr) {
		
		int hashValue = hashFunction(country, capital);	
		hashArray[hashValue].insert(country, capital, cfr);
	}
	
	
	/**
	 * The find method locates a country given its hashValue
	 * @param name The given country's name
	 * @param capital The given country's capital
	 * @return -1 If no country is found
	 * @return newNode.cfr The given country's CFR
	 */
	public double find(String country, String capitol) {
		
		int hashValue = hashFunction(country, capitol);	
		Node newNode = hashArray[hashValue].find(country, capitol);
		
		if(newNode == null) {
			System.out.println(country+" was not found");
			return -1;
		}else {
			System.out.println(country+ " was found with a CFR of "+newNode.CFR);
			return newNode.CFR;		
		}
	}
	
	
	/**
	 * The delete method locates a country given its hashValue and then removes it from the table
	 * @param name The given country's name
	 * @param capital The given country's capital
	 */
	public void delete(String country, String capital) {
		
		int hashValue = hashFunction(country, capital);	
		hashArray[hashValue].delete(country, capital);
		System.out.println(country +" has been deleted from the hash table");
	}
	
	
	/**
	 * A display method used to display the hash table and its elements at a given index
	 */
	public void display() {
	
		System.out.println("Hash Table Contents:");
		for(int i = 0; i < hashArray.length; i++) {
			System.out.print(i + ".");
			hashArray[i].displayList();
		}
	}
	
	
	/**
	 * A method to traverse the hash table and count for empty cells or cells containing collisions
	 */
	public void printFreeAndCollisions() {
		
		int freeCellCounter = 0;
		int collisionCounter = 0;		
		
		for(int i = 0; i < hashArray.length; i++) {
			
			Node current = hashArray[i].first;
			if(current == null) {
				freeCellCounter += 1;
			}else if(current != null && current.nextNode != null) {
				collisionCounter += 1;
			}
		}
		System.out.println("There are " + freeCellCounter + " spaces avaiable and " + collisionCounter + " collisions in the hash table");
	}					
}