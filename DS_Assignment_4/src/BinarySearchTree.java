/**
 * This class is responsible for the creation of a binary search tree. It makes use of various methods.
 * For example, inserting a node based on its name, finding a node in the tree, and removing a node from 
 * the tree. It also holds functions like find the top/bottom twenty elements of the tree based on CFR.
 * 
 * @author Derrick Palma
 * @version 11-15-20
 */


public class BinarySearchTree {
	
	/**
	 * A node class meant to store the data from each country. This data will be used to build the tree.
	 */
	private class Node{
		String name; //Country's name
		double cfr;  //Country's cfr
		Node leftChild;
		Node rightChild;
		
		public Node(String name, double cfr){
			this.name = name;
			this.cfr = cfr;
		}
		
		public void printNode() {	//Print node data
			System.out.printf("%-40s%, -30.6f\n", name, cfr);
		}	
	}

	public Node root;	//Root of the tree
	public Node [] bottomTwenty = new Node[20];
	public Node [] topTwenty = new Node [20];	
	public int twentyCounterBottom = 0;
	public int twentyCounterTop = 0;

	/**
	 * BinarySearchTree constructor to build an empty tree
	 */
	public BinarySearchTree() {
		root = null;	//Empty tree, no nodes yet
	}
	
	
	/**
	 * An insert method to insert a Country node into the BST. 
	 * The country is inserted alphabetically based on its name.
	 * @param name The country's name being inserted
	 * @param cfr The country's cfr being inserted
	 */
	public void insert(String name, double cfr) {
		
		Node newCountry = new Node(name, cfr);
		
		if(root == null) {		//if tree is empty insert as root
			root = newCountry;
		}else {
			Node current = root;		//start at root
			Node parent;
			
			while(true) {
				parent = current;
				if(newCountry.name.compareTo(current.name) < 0) {		//insert to the left?
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newCountry;
						return;
					}
				}else {		//insert to the right? 
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newCountry;
						return;
					}
				}
			}
		}
	}
	
	
	/**
	 * A find method used to locate a country in the BST given its name
	 * @param name The country's name to search for
	 * @return cfr The country's cfr
	 * @return -1 if no country is found
	 */
	public double find(String name) {
		int counter = 0;
		Node current = root;
		
		while(!current.name.equals(name)) {
			if(name.compareTo(current.name) < 0) {
				current = current.leftChild;
				counter += 1;
			}else {
				current = current.rightChild;
				counter += 1;
			}
			
			if(current == null) {	
				System.out.println(name + " not found: ");
				System.out.println(counter + " nodes were visited \n"); //additional code needed to find how many visited nodes
				return -1;
			}
		}
		System.out.println(current.name + " has been located: CFR = " + current.cfr);
		System.out.println(counter + " nodes were visited \n"); //additional code needed to find how many visited nodes
		return current.cfr;//found it
	}

	
	/**
	 * A delete method to remove a given node from the tree based on its name
	 * This method holds 3 cases: node to be deleted contains no children, 1 child, or 2 children
	 * @param name The county's name being deleted
	 */
	public void delete(String name) {		
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(!current.name.equals(name)) {		//search for node
			parent = current;
			if(name.compareTo(current.name) < 0) {		//go left?
				isLeftChild = true;
				current = current.leftChild;
			}else {	//or go right?
				isLeftChild= false;
				current = current.rightChild;
			}
			
			if(current == null) {	//did not find it 
				return;
			}
		}
		
			//if no children, simply remove
		if(current.leftChild == null && current.rightChild == null) {
			if(current == root) {
				root = null;
			}else if (isLeftChild) {
				parent.leftChild = null; 	//disconnect from parent
			}else {
				parent.rightChild = null;
			}
		}
		
			//if no right child, replace with left subtree
		else if(current.rightChild == null) { 	
			if(current == root) {
				root = current.leftChild;
			}else if(isLeftChild) {
				parent.leftChild = current.leftChild;
			}else {
				parent.rightChild = current.leftChild;
			}
		}
		
			//if no left child, replace with left subtree
		else if(current.leftChild == null) {
			if(current == root) {
				root = current.rightChild;
			}else if(isLeftChild){
				parent.leftChild = current.rightChild;
			}else {
				parent.rightChild = current.rightChild;
			}	
		}
		
		//if two children, find successor and replace with node to be deleted
		else {
			Node successor = getSuccessor(current);  
			
			//connect parent of current to successor instead
			if(current == root) {
				root = successor;
			}else if (isLeftChild){
				parent.leftChild = successor;
			}else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;	//connect successor to currents left child
		}
		System.out.println(name + " was deleted from the tree");
	}
	
	/**
	 * This method ties along with deletion Case 3, which requires finding the successor node
	 * @param delNode Node to be deleted and replaced with successor node
	 * @return successor Node used to replace delNode
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;	//one move to the right, and then all the way left
		
		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to the left 
		}
		
		if(successor != delNode.rightChild) {  //if successor is not the right child, connect nodes
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
	
	/**
	 * This method prints used inorder traversal to print the tree (Left Node / Root Node / Right Node)
	 * @param localRoot Using recursion, localRoot calls upon itself
	 */
	public void printInorder(Node localRoot) {		
		if(localRoot != null) {
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
		}
	}
	

	/**
	 * This method prints used preorder traversal to print the tree (Root Node / Left Node / Right Node)
	 * @param localRoot Using recursion, localRoot calls upon itself	 
	 */
	public void printPreorder(Node localRoot) {
		if(localRoot != null) {
		localRoot.printNode();
		printPreorder(localRoot.leftChild);
		printPreorder(localRoot.rightChild);
		}
	}
	
	
	/**
	 * This method prints used postorder traversal to print the tree (Left Node / Right Node / Root Node)
	 * @param localRoot Using recursion, localRoot calls upon itself
	 */
	public void printPostorder(Node localRoot) {
		if(localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}
	
	
	/** 
	 * This method finds the bottom twenty ranked elements in the tree (ranked by CFR)
	 * Nodes are being sorted while inserted into an arry 
	 * @param localRoot Using recursion to traverse the tree
	 */
	public void getBottomTwenty(Node localRoot) { 	

		if(localRoot != null) {
						
			if(twentyCounterBottom < 20) {
			bottomTwenty[twentyCounterBottom] = localRoot;
			twentyCounterBottom++;
			}else if(twentyCounterBottom == 20){
				
				int in, out;
		
				for(out = 1; out < bottomTwenty.length; out++) {
					
					Node temp = bottomTwenty[out];
					in = out;
				
					while(in > 0 && bottomTwenty[in-1].cfr > temp.cfr) {
						bottomTwenty[in] = bottomTwenty[in-1];
						--in;
				
					}
					bottomTwenty[in] = temp;
				} 

				if(localRoot.cfr < bottomTwenty[twentyCounterBottom-1].cfr) {
				bottomTwenty[twentyCounterBottom-1] = localRoot;
				}
			}
			getBottomTwenty(localRoot.leftChild);
			getBottomTwenty(localRoot.rightChild);
		}
	}	
	
	
	/**
	 * This method ties to getBottomTwenty and is responsible for actually printing out the values
	 */
	public void displayBottomTwentyNodes(){
	
		getBottomTwenty(root);
		for(int j = 0; j < bottomTwenty.length; j++) {
			bottomTwenty[j].printNode();
		}
	}

	
	/** 
	 * This method finds the top twenty ranked elements in the tree (ranked by CFR)
	 * Nodes are being sorted while inserted into an arry 
	 * @param localRoot Using recursion to traverse the tree
	 */
	public void getTopTwenty(Node localRoot) {
	
		if(localRoot != null) {
			
			if(twentyCounterTop < 20) {
			topTwenty[twentyCounterTop] = localRoot;
			twentyCounterTop++;
			
			}else if(twentyCounterTop ==  20){
				
				int in, out;
		
				for(out = 1; out < topTwenty.length; out++) {
					
					Node temp = topTwenty[out];
					in = out;
				
					while(in > 0 && topTwenty[in-1].cfr < temp.cfr) {
						topTwenty[in] = topTwenty[in-1];
						--in;
				
					}
					topTwenty[in] = temp;
				} 

				if(localRoot.cfr > topTwenty[twentyCounterTop-1].cfr) {
					topTwenty[twentyCounterTop-1] = localRoot;
				}
			}
			getTopTwenty(localRoot.leftChild);
			getTopTwenty(localRoot.rightChild);
		}
	}
	
	
	/**
	 * This method ties to getBottomTwenty and is responsible for actually printing out the values
	 */
	public void displayTopTwentyNodes(){
		
		getTopTwenty(root);
		for(int j = 0; j < topTwenty.length; j++) {
			topTwenty[j].printNode();
		}
	}
}