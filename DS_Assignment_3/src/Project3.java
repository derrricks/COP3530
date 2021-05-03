import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 3: Linked Lists
 *
 * This class is responsible for manipulating the data of two linked list objects.
 * Stack x represents a double ended singly linked list, while Queue y represents a double 
 * ended doubly linked list.
 * 
 * The class begins by reading in a .csv file containing a list of countries.
 * The countries are then pushed onto Stack x if they belong to the Fair, Good, and VeryGood groups.
 * All other countries are ignored. After creating of Stack x, the countries are popped off the stack
 * one at a time and inserted into Queue y in front/back/front/back order. After creating Queue y,
 * the countries with a COVID-19 CFR between 2.5% and 3.5% are deleted from the queue. The countries 
 * are then removed from the Queue and pushed back to the stack in front/back/front/back order. The
 * stack is printed and the program ends.
 * 
 * 
 * Input: Countries3.csv file
 * Output: Stack / Queue / Stack / Queue
 * 
 * @author <Derrick_Palma>
 * @version <October 24, 2020>
 */
public class Project3 {

	public static void main(String[] args) {
		
		Country [] countries = new Country[153];
	
		Stack x = new Stack();
		Queue y = new Queue();
		
		String fileName = "Countries3.csv";

		String name;
		String capitol;
		long population;
		double GDP;
		int covidCases;
		int covidDeaths;
		int count = 0;
		
	try {
		
		Scanner myFile = new Scanner(new File("src//" + fileName));
		myFile.useDelimiter("[,\n]");			//skips commas and new lines
		
		for(int i = 0; i < 6; i++) {
			myFile.next();
		}
		
		while(myFile.hasNext()) {
			
			name = myFile.next();
			capitol = myFile.next();
			population = myFile.nextLong();
			GDP = myFile.nextDouble();
			covidCases = myFile.nextInt();
			covidDeaths = myFile.nextInt();
			
			countries[count] = new Country(name, capitol, population, GDP, covidCases, covidDeaths);
			
			if( countries[count].getCfr() < .01 ) {
				//excellent
				count++;
			}else
			
			if( countries[count].getCfr() >= .01 && countries[count].getCfr() < .02 ) {
				//veryGood
				x.push(countries[count]);
				count++;
			}else
			
			if( countries[count].getCfr() >= .02 && countries[count].getCfr() < .05 ) {
				//good
				x.push(countries[count]);
				count++;
			}else
			
			if( countries[count].getCfr() >= .05 && countries[count].getCfr() < .1 ) {
				//fair
				x.push(countries[count]);
				count++;
			}else
			
			if( countries[count].getCfr() >= .1 ) {
				//poor
				count++;
			}
		}
			
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
		
	}
	
	System.out.println("Stack Contents: ");
	addHeader();
	x.printStack();

	int counter1 = 0;
	while( !x.isEmpty() ) {
		
	if( counter1 % 2 == 0) {
		
		Country even = x.pop();
		y.insertFront(even);
	} else {
		
		Country odd = x.pop();
		y.insertEnd(odd);
	}
		counter1++;
	}

	
	System.out.println();
	System.out.println("Queue Contents: ");
	addHeader();
	y.printQueue();
	
	
	for(int i = 0; i < countries.length; i++) {
		
		y.intervalDelete(.025, .035);
	}
	
	System.out.println();
	System.out.println("Queue Contents: ");
	addHeader();
	y.printQueue();
	
	int counter2 = 0;
	while( !y.queueIsEmpty() ) {
		
		if( counter2 % 2 == 0) {
			
			Country even = y.removeFront();
			x.push(even);
		}else {
			
			Country odd = y.removeEnd();
			x.push(odd);
		}
		counter2++;
	}
	
	System.out.println();
	System.out.println("Stack Contents: ");
	addHeader();
	x.printStack();
	}
	
	
	public static void addHeader() {		//This method prints the header line when called
		
		String headerName = "Name";
		String headerCapitol = "Capital";
		String headerPopulation ="Population";
		String headerGDP = "GDP";
		String headerCovidCases ="Covid-19 Cases";
		String headerCovidDeaths = "Covid-19 Deaths";
		
		System.out.println("");
		System.out.printf("%-35s", headerName);
		System.out.printf("%-15s", headerCapitol);
		System.out.printf("%-15s", headerPopulation);
		System.out.printf("%-25s", headerGDP);
		System.out.printf("%-20s", headerCovidCases);
		System.out.printf("%-15s", headerCovidDeaths);
		System.out.println();
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------");
		System.out.println();	
	}
}