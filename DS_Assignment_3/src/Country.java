/**
 * This class is responsible for putting together the country object.
 * Each country consist of a name, capitol, population, GDP, COVID-19 cases, 
 * and COVID-19 deaths. Each piece of needed information is then used in Getters/Setters
 * in order to allow us to manipulate the necessary information when needed. 
 * There is also a display method to display a country with all of the given information. 
 * 
 * @author <Derrick Palma>
 * @version <October 25, 2020>
 * 
 */
public class Country {			
	
	private String name;		//Variables pertaining to the Country object
	private String capitol;
	private long population;
	private double GDP;
	private int covidCases;
	private int covidDeaths;
	
	public Country next;
	public Country previous;
	
	private double gdpPerCapita;
	private double cfr;
	
	/**
	 * Country Constructor: This method constructs each country with all the information passed through. 
	 */
	public Country(String name, String capitol, long population, double GDP, int covidCases, int covidDeaths) {
		
		this.name = name;			
		this.capitol = capitol;
		this.population = population;
		this.GDP = GDP;
		this.covidCases = covidCases;
		this.covidDeaths = covidDeaths;
		
		this.gdpPerCapita = GDP / population;
		this.cfr = (double) covidDeaths / covidCases;
	}

	
	/**
	 * Gets the name of the current country and returns the value as a string		 
	 * @return name  
	 */	
	public String getName() {	
		return name;
	}

	/**
	 * A String name is passed through to set the name of a country at a given point
	 * @param name				 
	 */	
	public void setName(String name) {
		this.name = name;	
	}

	/**
	 * Gets the capital of the current country and returns the value as a string			 
	 * @return capitol  
	 */	
	public String getCapitol() {	
		return capitol;	
	}

	
	/**
	 * A String capitol is passed through to set the capitol of a country at a given point
	 * @param capitol				 
	 */	
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}


	/**
	 * Gets the population of the current country and returns the value as a long
	 * @return population  
	 */	
	public long getPopulation() {  
		return population;
	}
	

	/**
	 * A long population value is passed through to set the population of a country at a given point
	 * @param population				 
	 */	
	public void setPopulation(long population) {
		this.population = population;
	}


	/**
	 * Gets the GDP of the current country and returns the value as a double
	 * @return GDP  
	 */	
	public double getGDP() {	
		return GDP;	
	}
	

	/**
	 * A double GDP is passed through to set the GDP at current position
	 * @param GDP				 
	 */	
	public void setGDP(double GDP) {
		this.GDP = GDP;	
	}

	
	/**
	 * Gets the amount of COVID-19 cases of the current country and returns the value as an int
	 * @return covidCases  
	 */	
	public int getCovidCases() {	
		return covidCases;		
	}
	

	/**
	 * An int covidCases is passed through to set the number of COVID-19 cases at current position	 
	 * @param covidCases		
	 */	
	public void setCovidCases(int covidCases) {		
		this.covidCases = covidCases;
	}

	
	/**
	 * Gets the amount of COVID-19 deaths of the current country and returns the value as an int
	 * @return covidDeaths  
	 */	
	public int getCovidDeaths() {
		return covidDeaths;
	}

	
	/**
	 * An int covidDeaths is passed through to set the number of COVID-19 deaths at current position
	 * @param covidDeaths		
	 */	
	public void setCovidDeaths(int covidDeaths) {	
		this.covidDeaths = covidDeaths;
	}
	
	
	/**
	 * Gets the GDP per capita of the current country and returns the value as a double
	 * @return gdpPerCapita 
	 */	
	public double getGdpPerCapita() {
		return gdpPerCapita;	 //Returns the country's GDP per capita
	}
	
	
	/**
	 * A double gdpPerCapita is passed through to set the GDP per capita at a current position
	 * @param gdpPerCapita		
	 * 
	 */
	public void setGdpPerCapita(double gdpPerCapita) {
		this.gdpPerCapita = gdpPerCapita;
	}

	
	/**
	 * Gets the CFR (deaths/cases) of the current country and returns the value as a double
	 * @return cfr
	 */
	public double getCfr() {
		return cfr;
	}
	
	
	/**
	 * A double cfr is passed through to set the CFR at a current position
	 * @param cfr		
	 */
	public void setCfr(double cfr) {	
		this.cfr = cfr;
	}	
	
	
	/**
	 * This method compares two countries given their names.
	 * A Country x is passed through and compared to the country at the current spot
	 * @param x	given Country
	 * @return name The country that is lexicographically greater
	 */
	public String compareMethod(Country x) {  
		this.name.compareTo(x.name);	
		return name;				
	}
	

	/**
	 * A display method to display the information for a given country
	 * Formatters are used within printf to keep data aligned in columns
	 */
	public void displayCountry() {

		System.out.printf("%-35s", this.name);
		System.out.printf("%-15s", this.capitol);
		System.out.printf("%-15s", this.population);
		System.out.printf("%-25s", this.GDP);
		System.out.printf("%-20s", this.covidCases);
		System.out.printf("%-15s", this.covidDeaths);
		//System.out.printf("%-15s", this.cfr);
		System.out.println();
	}
}