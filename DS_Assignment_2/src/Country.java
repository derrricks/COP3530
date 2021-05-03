/**
 * This class takes in a country object containing information about its
 * name, capital, population, GDP, COVID-19 cases, and COVID-19 deaths. 
 * Each piece of information is then used in Getters/Setters in order to allow
 * us to call the necessary information when needed. There is also a display method to
 * display a country with all of the given information and a compare method to compare
 * and two given countries based on their names. 
 * 
 * @author <Derrick Palma>
 * @version <September 28, 2020> 
 */
public class Country {			

	private String name;		//Variables pertaining to the Country object
	private String capitol;
	private long population;
	private double GDP;
	private int covidCases;
	private int covidDeaths;
	private double gdpPerCapita;
	private double cfr;
		
	
	Country(String name, String capitol, long population, double GDP, int covidCases, int covidDeaths) {
		
		this.name = name;			
		this.capitol = capitol;
		this.population = population;
		this.GDP = GDP;
		this.covidCases = covidCases;
		this.covidDeaths = covidDeaths;
		
		this.gdpPerCapita = GDP / population;
		this.cfr = (double) covidDeaths / covidCases;	
	}


	public String getName() {
		return name;		
	}

	public void setName(String name) {
		this.name = name;	
	}

	public String getCapitol() {	
		return capitol;		
	}

	public void setCapitol(String capitol) {	
		this.capitol = capitol;
	}

	public long getPopulation() {   
		return population;	
	}
	
	public void setPopulation(long population) {	
		this.population = population;
	}

	public double getGDP() {	
		return GDP;		
	}

	public void setGDP(double GDP) {	
		this.GDP = GDP;	
	}

	public int getCovidCases() {	
		return covidCases;		
	}

	public void setCovidCases(int covidCases) {		
		this.covidCases = covidCases;
	}

	public int getCovidDeaths() {	
		return covidDeaths;		
	}

	public void setCovidDeaths(int covidDeaths) {
		this.covidDeaths = covidDeaths;
	}
	
	public double getGdpPerCapita() {		
		return gdpPerCapita;	
	}

	public void setGdpPerCapita(double gdpPerCapita) {		
		this.gdpPerCapita = gdpPerCapita;
	}

	public double getCfr() {		
		return cfr;		
	}

	public void setCfr(double cfr) {
		this.cfr = cfr;
	}	
	
	public String compareMethod(Country x) {  
		this.name.compareTo(x.name);	
		return name;				
	}
	
	public void displayCountry() {	

		System.out.printf("%-35s", this.name);
		System.out.printf("%-15s", this.capitol);
		System.out.printf("%-15s", this.population);
		System.out.printf("%-25s", this.GDP);
		System.out.printf("%-20s", this.covidCases);
		System.out.printf("%-15s", this.covidDeaths);
		System.out.printf("%-15s", this.cfr);
		System.out.println();	
	}	
}	