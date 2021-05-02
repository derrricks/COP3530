/**
 * This class is responsible for taking the array of objects and manipulating the variables. 
 * The country constructor assigns the private variables from the class and
 * makes them equal to those of the constructor. 
 * 
 * @author <Derrick Palma>
 * @version <September 11, 2020>
 */
public class Country {

	private String name;	//variables to use in this class only
	private String capitol;
	private long population;
	private double GDP;
	private int covidCases;
	private int covidDeaths;
	private double gdpPerCapita;
	private double cfr;
		
	
	public Country(String name, String capitol, long population, double GDP, int covidCases, int covidDeaths) {	 //Country constructor												
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
		return name;	//returns country's name when called
	}


	public void setName(String name) {	//gets name that is passed through and assigns it to value at current position
		this.name = name;	
	}


	public String getCapitol() {
		return capitol;		//returns current country's capital name when called 
	}


	public void setCapitol(String capitol) {	//gets capitol that is passed through and assigns it to value at current position
		this.capitol = capitol;
	}


	public long getPopulation() {
		return population;	//returns current country's population when called 
	}


	public void setPopulation(long population) {	//gets population that is passed through and assigns it to value at current position
		this.population = population;
	}


	public double getGDP() {
		return GDP;		//returns current country's GDP when called 
	}


	public void setGDP(double GDP) {	//gets GDP that is passed through and assigns it to value at current position
		this.GDP = GDP;	
	}


	public int getCovidCases() {
		return covidCases;	//returns current country's COVID-19 cases when called 
	}


	public void setCovidCases(int covidCases) {		//gets COVID cases that is passed through and assigns it to value at current position
		this.covidCases = covidCases;
	}


	public int getCovidDeaths() {
		return covidDeaths;		//returns current country's COVID-19 deaths when called
	}


	public void setCovidDeaths(int covidDeaths) {	//gets COVID-19 deaths that is passed through and assigns it to value at current position
		this.covidDeaths = covidDeaths;
	}
	
	public double getGdpPerCapita() {	//returns the current country's GDP per capita when called
		return gdpPerCapita;
	}


	public void setGdpPerCapita(double gdpPerCapita) {		//gets GDP per capita that is passed though and assigns it to value at current position 
		this.gdpPerCapita = gdpPerCapita;
	}


	public double getCfr() {	//returns the current country's COVID fatality rate when called
		return cfr;
	}


	public void setCfr(double cfr) {	//gets COVID fatality rate that is passed through and assigns it to value at current position
		this.cfr = cfr;
	}	
	
	public String compareMethod(Country x) {
		this.name.compareTo(x.name);
		return name;	//compares two counties and returns new one
		
	}
	
	public void displayCountry() {	//formatters are used within printf to keep data aligned 
		System.out.printf("%-35s", this.name);
		System.out.printf("%-15s", this.capitol);
		System.out.printf("%-15s", this.population);
		System.out.printf("%-25s", this.GDP);
		System.out.printf("%-20s", this.covidCases);
		System.out.printf("%-15s", this.covidDeaths);
		//System.out.printf("%-15s", this.cfrRate);
		System.out.println();	
	}	
}