/** 
 * COP 3530: Project 2 - Stacks and Priority Queues
 * 
 * This class beings by creating five different priority queues, each holds
 * certain countries based on the CFR rate calculated. Once the file is read
 * the countries are then pushed into an array of countries and then pushed onto Stack x.
 * After the file is read completely the countries are then taken from the stack and 
 * placed into the appropriate priority queue, based on COVID-19 CFR. After each country is entered
 * into the priority queue each queue is printed from Poor to Excellent. After each queue is printed we then 
 * remove the items from each queue 1-by-1 and push them back onto a final stack. We end the program by 
 * printing the final stack (from top to bottom). 
 * 
 * The input required is the appropriate .csv file, which outputs the different countries in different
 * appropriate priority queues and one stack containing all countries.
 * 
 * An addHeader() method is used in order to avoid redundancy when creating a header for each section
 * 
 * @author <Derrick Palma>
 * @version <September 28, 2020> 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project2 {

	public static void main(String[] args) {
		
		Country [] countries = new Country[153];
		
		Stack x = new Stack(153);
		
		PriorityQueue excellent = new PriorityQueue(153);
		PriorityQueue veryGood = new PriorityQueue(153);
		PriorityQueue good = new PriorityQueue(153);
		PriorityQueue fair = new PriorityQueue(153);
		PriorityQueue poor = new PriorityQueue(153);
		
	//	Scanner userInput = new Scanner(System.in);			//take user input of file name
	//	System.out.println("Enter the file name:");
	//	String fileName = userInput.nextLine();

		String fileName = "Countries2.csv";

		String name;
		String capitol;
		long population;
		double GDP;
		int covidCases;
		int covidDeaths;
		int count = 0;
		
		try {
			
			Scanner myFile = new Scanner(new File("src//" + fileName));			//read file
			myFile.useDelimiter("[,\n]");			//skips commas and new lines
		
			for(int i = 0; i < 6; i ++) {
				myFile.next();			//reads header line of csv file
			}
			
			while(myFile.hasNext()) {			//reads each variable in the file one-by-one 
			
				name = myFile.next();	
				capitol = myFile.next();
				population = myFile.nextLong();
				GDP = myFile.nextDouble();
				covidCases =  myFile.nextInt();
				covidDeaths = myFile.nextInt();
					
				countries[count] = new Country(name, capitol, population, GDP, covidCases, covidDeaths);			//starts array of country objects
				x.push(countries[count]);
				count++;
				
			}
				
		} catch (FileNotFoundException e) {			//catches error if user inputs invalid file name
			
			e.printStackTrace();
		}	
		
		for(int i = countries.length - 1; i >= 0; i--) {
		
			if(countries[i].getCfr() < 0.01) {
				Country z = x.pop();
				excellent.insert(z);
				
			}else if(countries[i].getCfr() >= 0.01 && countries[i].getCfr() < .02) {
				Country z = x.pop();
				veryGood.insert(z);
				
			}else if(countries[i].getCfr() >= 0.02 && countries[i].getCfr() < .05 ) {
				Country z = x.pop();
				good.insert(z);
				
			}else if(countries[i].getCfr() >= 0.05 && countries[i].getCfr() < 0.1) {
				Country z = x.pop();
				fair.insert(z);
			
			} if(countries[i].getCfr() >= 0.1) {
				Country z = x.pop();
				poor.insert(z);
				
			}
		}
	
		System.out.print("POOR Priority Queue Contents:");
		addHeader();
		poor.printQueue();
		System.out.println();
	
		System.out.print("FAIR Priority Queue Contents:");
		addHeader();
		fair.printQueue();
		System.out.println();
		
		System.out.print("GOOD Priority Queue Contents:");
		addHeader();
		good.printQueue();
		System.out.println();
		
		System.out.print("VERYGOOD Priority Queue Contents:");
		addHeader();
		veryGood.printQueue();
		System.out.println();
		
		System.out.print("EXCELLENT Priority Queue Contents:");
		addHeader();
		excellent.printQueue();
		System.out.println();

		while( !poor.PQisEmpty()) {
			Country z = poor.remove();
			x.push(z);
		}
				
		while( !fair.PQisEmpty()) {
			Country z = fair.remove();
			x.push(z);
		}
				
		while( !good.PQisEmpty()) {
			Country z = good.remove();
			x.push(z);
		}
			
		while( !veryGood.PQisEmpty()) {
			Country z = veryGood.remove();
			x.push(z);
		}		
		
		while( !excellent.PQisEmpty()) {
			Country z = excellent.remove();
			x.push(z);
		}
		
		System.out.print("Stack Contents:");
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