import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 - Hash Tables
 * 
 * This class makes use of a hash table using country objects.
 * A file is first read in containing information of a given country,
 * then each country is inserted into the hash table based on their names.
 * The hash table is displayed and then functions are used to search and delete 
 * countries from the hash table.
 * 
 * Input: Countries5.csv file
 * Output: A hash table is displayed containing the file elements 
 * 
 * @author <Derrick Palma>
 * @version <November 27, 2020>
 */
public class Project5 {

	public static void main(String [] args){
		
		String fileName = "Countries5.csv";

		String name;
		String capital;
		long population;
		double GDP;
		int covidCases;
		int covidDeaths;
	    double cfr = 0;
		
		HashTable hashTable = new HashTable();
		
	try {
		
		Scanner myFile = new Scanner(new File("src//" + fileName));
		myFile.useDelimiter("[,\n]");			//skips commas and new lines
		
		for(int i = 0; i < 6; i++) {
			myFile.next();
		}
		
		while(myFile.hasNext()) {
			
			name = myFile.next();
			capital = myFile.next();
			population = myFile.nextLong();
			GDP = myFile.nextDouble();
			covidCases = myFile.nextInt();
			covidDeaths = myFile.nextInt();
				
			cfr = (double) covidDeaths / covidCases;	
			hashTable.insert(name, capital, cfr);
			
		}
			
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	hashTable.display();
	System.out.println();
	
	hashTable.delete("Australia", "Canberra");
	hashTable.delete("Tunisia", "Tunis");
	hashTable.delete("Norway", "Oslo");
	System.out.println();

	hashTable.find("Sri Lanka", "Colombo");
	hashTable.find("Cyprus", "Nicosia");
	hashTable.find("Tunisia", "Tunis");
	System.out.println();
	
	hashTable.delete("Malawi", "Lilongwe");
	hashTable.delete("Germany", "Berlin");
	hashTable.delete("Ireland", "Dublin");
	hashTable.delete("Yemen", "Sanaa");
	hashTable.delete("India", "New Delhi");
	System.out.println();

	hashTable.display();
	System.out.println();
	
	hashTable.printFreeAndCollisions();
	}
}
