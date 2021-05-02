import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 1 - Array Searches and Sorts
 * 
 * This class reads in a file and assigns the values into an array of objects.
 * The user is required to enter the file name they would like to use, but only Countries1.csv 
 * will run the program. Any other name will throw an error. The array is then sorted and searched
 * using different methods.
 * 
 * @author <Derrick Palma>
 * @version <September 11, 2020>
*/
public class Project1 {
	
	static Country [] countries = new Country[153];	//global array is used instead of passing array through each method
	static boolean isSortedByName = false;
	
	public static void main(String[] args) {
				
		Scanner userInput = new Scanner(System.in);			//take user input of file name
		System.out.println("Enter the file name:");
		String fileName = userInput.nextLine();
				
		String name;
		String capitol;
		long population;
		double GDP;
		int covidCases;
		int covidDeaths;
		int count = 0;
		
		try {
			
			Scanner myFile = new Scanner(new File("src//" + fileName));			//read in file data
			myFile.useDelimiter("[,\n]");			//skips commas and new lines when reading in data
		
			for(int i = 0; i < 6; i ++) {
				myFile.next();			//reads header line of csv file
			}
			
			while(myFile.hasNext()) {			//reads each variable in the file one-by-one 
			
				name = myFile.next();	
				capitol = myFile.next();
				population = myFile.nextInt();
				GDP = myFile.nextDouble();
				covidCases =  myFile.nextInt();
				covidDeaths = myFile.nextInt();
					
				countries[count] = new Country(name, capitol, population, GDP, covidCases, covidDeaths);			//starts array of country objects
				count++;
			}
				
		} catch (FileNotFoundException e) {			//catches error if user inputs invalid file name
			
			e.printStackTrace();
			
		}	
			checkUserInput();			//start selection menu
	}
		
	
	public static void checkUserInput(){			//selection method used to ask user for input they desire
														//This will run until "6" is selected by the user. All other cases will prompt re-entry.
		String userChoice;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Press 1 to print a countries report");
		System.out.println("Press 2 to sort aphabetically by name");
		System.out.println("Press 3 to sort by COVID-19 fatality rate");
		System.out.println("Press 4 to sort by GDP Per Capita");
		System.out.println("Press 5 to find a specific country");
		System.out.println("Press 6 to quit");
		System.out.print("Enter your choice:  ");
		userChoice = in.next();
		System.out.println();
		
		switch(userChoice) {
		case "1":
			getCountryReport(); //generate data report of each country and display it
			break;
			
		case "2":
			insertionSortName(); //sort by name (alphabetically using Insertion Sort)
			break;
			
		case "3":
			selectionSortCovidCFR(); //sort by COVID-19 CFR, case fatality rate(ascending using Selection Sort)
			break;
			
		case "4":
			bubbleSortGDP(); //sort by GDP per capita(descending using Bubble Sort)
			break;
			
		case "5":
			countrySearch(); //find and print a country for a given name (using Binary Search if the data is sorted by name;Sequential Search,if not)
			break;
			
		case "6":
			System.out.println("Have a nice day!");	//breaks out of program
			break;
		
		default:
			System.out.print("Invalid choice enter 1-6: ");	//ask user for re-entry of any entry out of bounds
			System.out.println();
			checkUserInput();
			
		}	
	}


	public static void getCountryReport() {			//print a country report listing every country
		
		String headerName = "Name:";
		String headerCapitol = "Capital:";
		String headerPopulation ="Population:";
		String headerGDP = "GDP:";
		String headerCovidCases ="Covid-19 Cases:";
		String headerCovidDeaths = "Covid-19 Deaths:";
		
		
		System.out.printf("%-35s", headerName);
		System.out.printf("%-15s", headerCapitol);
		System.out.printf("%-15s", headerPopulation);
		System.out.printf("%-25s", headerGDP);
		System.out.printf("%-20s", headerCovidCases);
		System.out.printf("%-15s", headerCovidDeaths);
		System.out.println();
		System.out.print("-----------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		for(int i= 0; i< countries.length; i++) {
			
			countries[i].displayCountry();	
			
		}
		
		System.out.println();		//skips line for appearance
		isSortedByName = false;
		checkUserInput();			//goes back to selection menu
	}
	
	public static void insertionSortName() {			//sort countries by name A-Z using Insertion Sort
		
		int in, out;
		
		for(out = 1; out < countries.length; out++) {
			
			Country temp = countries[out];
			in = out;
		
			while(in > 0 && countries[in-1].getName().compareTo(temp.getName())>0) {
				countries[in] = countries[in-1];
				--in;
		
			}
			countries[in] = temp;
		}
		
		System.out.println("Countries sorted by name");
		System.out.println();			//skips line for appearance
		isSortedByName = true;
		checkUserInput();			//goes back to selection menu
	}
	

	public static void selectionSortCovidCFR() {			//sort countries by COVID-19 fatality rate ascending using Selection Sort
	
		int out, in, min;
		
		for(out = 0; out < countries.length-1; out++) {
			
			min = out;
			for(in = out+1; in < countries.length; in++) 
				if(countries[in].getCfr() < countries[min].getCfr()) 
					min = in;
			swap(out, min);			//passing to swap method to execute algorithm properly
				
			
	}
					
	System.out.println("Countries sorted by COVID-19 death rate");
	System.out.println();			//skips line for better appearance
	isSortedByName = false;
	checkUserInput();			//go back to selection menu
	}
	
	
	public static void swap(int one, int two) {			//this method goes along with the selectionSortCovidCFR() and bubbleSortGDP()
		
		Country temp = countries[one];			// variables one and two are passed through
		countries[one] = countries[two];
		countries[two] = temp;
		
	}
	
	
	public static void bubbleSortGDP() {			//sort countries by GDP descending order using Bubble Sort
		
		int in, out;
		
		for(out = countries.length-1; out > 1; out--) {
			for(in = 0; in < out; in++)
				if(countries[in].getGdpPerCapita() < countries[in+1].getGdpPerCapita())
					swap(in, in+1);			//passing to swap method to execute algorithm properly
		}
		
		System.out.println("Countries are sorted by GPD Per Capita");
		System.out.println();
		isSortedByName = false;
		checkUserInput();
	}
	
	
	public static void countrySearch() {
						
		Scanner search = new Scanner(System.in);
		String searchKey;
		System.out.print("Enter a country name:  ");	//user input to search for country
		searchKey = search.nextLine();
		
		System.out.println();
		
		if(isSortedByName == true) { 		//Binary Search if sorted by name

			int lowerBound = 0; 				
			int upperBound = countries.length-1;
			int mid;	
		
			
			while(lowerBound <= upperBound) {
			
				mid = (lowerBound + upperBound) / 2;
				
				if(countries[mid].getName().compareTo(searchKey) == 0) {
					
					System.out.println("Name:   " + countries[mid].getName());	
					System.out.println("Capital:   " + countries[mid].getCapitol());
					System.out.println("Population:   " +countries[mid].getPopulation());
					System.out.println("GDP:  " + countries[mid].getGDP());
					System.out.println("COVID-19 Cases:  " +countries[mid].getCovidCases());
					System.out.println("COVID-19 Deaths:   " + countries[mid].getCovidDeaths());
					System.out.println();
					
					checkUserInput();
					return;
					
				} else if (countries[mid].getName().compareTo(searchKey) > 0) {
					upperBound = mid -1;
					}else {
					lowerBound = mid + 1;
					}
				}
			
				System.out.println("Error: Country not found");
				checkUserInput();
				return;
				
			}	
			else {			//Sequential Search if countries are not sorted by name
			
				int j;
				
				for(j = 0; j < countries.length; j++) 
					if(countries[j].getName().equals(searchKey)) {
						System.out.println("Name: " + countries[j].getName());
						System.out.println("Capital: " + countries[j].getCapitol());
						System.out.println("Population: " + countries[j].getPopulation());
						System.out.println("GDP: " + countries[j].getGDP());
						System.out.println("COVID-19 Cases: " + countries[j].getCovidCases());
						System.out.println("COVID-19 Deaths: " + countries[j].getCovidDeaths());
						System.out.println();
						checkUserInput();
						return;
					
					}
				
					if(j == countries.length) {
						System.out.println("Error: Country not found");
						checkUserInput();
						return;
					}
			}
	}		
}