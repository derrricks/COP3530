/**
 * COP 3530: Project 4 - Binary Search Trees
 *
 * This class is responsible for the implementation of a binary search tree. Nodes are inserted into the tree based on
 * their names. This class makes use of different traversal methods and other various methods.
 * For example, deleting a node based on a given name, searching the tree for an existing country based on a given name,
 * and deleting a node from the tree. There are also two methods used to get the top twenty/bottom twenty nodes of the tree
 * regarding their CFR.
 * 
 * @author Derrick Palma
 * @version 11-15-20
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4 {
	
	public static void main(String [] args) {
		
		String fileName = "src//Countries4.csv";	
		
		String countryName = null;	
		String capital;
		long population;
		double GDP;
		int covidCases;
		int covidDeaths;
		double cfr = 0;
		
		BinarySearchTree BST = new BinarySearchTree();		//create new empty tree
	
		try {
			
			Scanner readFile = new Scanner(new File(fileName));	//read in file
			readFile.useDelimiter("[,\n]");	
			
			for(int i = 0; i < 6; i ++) {
				readFile.next();	//read header
			}
			
			while(readFile.hasNext()) {
			
				countryName = readFile.next();
				capital = readFile.next();
				population = readFile.nextLong();
				GDP = readFile.nextDouble();
				covidCases = readFile.nextInt();
				covidDeaths = readFile.nextInt();
				
				cfr = (double) covidDeaths / covidCases;	
				BST.insert(countryName, cfr);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}		
		
		
		// "\n" skips line (removes redundant system.out.println statements)
		System.out.println("Inorder Traversal: \n"); 
		addHeader();
		BST.printInorder(BST.root);  //start by printing full tree
		System.out.println();
		
		BST.delete("Greece");
		BST.delete("Mongolia");	//delete these values
		BST.delete("Norway");
		System.out.println();
		
		System.out.println("Preorder Traversal: \n" );
		addHeader();
		BST.printPreorder(BST.root);	//print the tree again without the values previously removed
		System.out.println();
		
		BST.find("Mongolia");
		BST.find("Cyprus");		//search for these countries and amount of nodes visited
		BST.find("United States");
		BST.find("Norway");
		
		BST.delete("Qatar");
		BST.delete("Somalia");	//delete these values
		BST.delete("Canada");
		BST.delete("Yemen");
		BST.delete("New Zealand");
		System.out.println();
		
		System.out.println("Postorder Traversal: \n");	//print new tree contents
		addHeader();
		BST.printPostorder(BST.root);
		System.out.println();
		
		System.out.println("Bottom Twenty Countries Regarding CFR: \n");
		addHeader();
		BST.displayBottomTwentyNodes();
		System.out.println();
		
		System.out.println("Top Twenty Countries Regarding CFR: \n");
		addHeader();
		BST.displayTopTwentyNodes();
	}
	
	
	public static void addHeader() {
		String name = "Country Name";
		String cfr = "Country CFR";
		
		//System.out.println();
		System.out.printf("%-35s", name);
		System.out.printf("%15s", cfr);
		System.out.println();
		System.out.println("-----------------------------------------------------");
	}	
}