package components;

public class Generation {
	private Chromosome[] Generation; // population of produced solutions for knapsack
	private int maxWeight; // max weight in each chromosome
	private int generationSize; // number of solutions in each generation
	private int fittestIndex; // the index of the fittest solution
	private Chromosome[] newGeneration; // children array
	private int solutionSize; // number of items in each solution
	private int numberOfItems; // number of items in the KP solution
	private Gene[] items;// Array of items in the KP system
	private int fittest;
	private Generation nextGeneration;//stores
	private int[] parents=new int[2];


	public Generation(Chromosome[] Generation,int maxWeight, int generationSize,int solutionSize,int numberOfItems,Gene[] items) {
		this.Generation=Generation;
		this.maxWeight=maxWeight;
		this.generationSize=generationSize;
		this.newGeneration=new Chromosome[generationSize];
		this.solutionSize=solutionSize;
		this.numberOfItems=numberOfItems;
		this.items=items;
		
	}

	public void fitness() {// tests if the produced solutions satisfy the basic knapsack conditions, as well as tests how good they are
		for(int i=0;i<generationSize;i++) {// for each item in the generation check KP conditions
			if(Generation[i].getTotalWeight()>maxWeight) {
				Generation[i].setValid(false);
			}
			else
				Generation[i].setValid(true);
		}
		sort(Generation);// selection sort based on on fitness index
		fittest=fittestIndex;
	}


	public void print() {// print out the value data in the generation
		for(int i=0;i<generationSize;i++) {

		}
	}
	public void printNew() {// print out the value data in the generation
		for(int i=0;i<generationSize;i++) {
			newGeneration[i].Print();
			int[] parent=newGeneration[i].getParents();

			System.out.println("First parent=="+parent[0]+"  Second parent=="+parent[1]);

		}
	}


	public Chromosome crossOver(int randomItem){// takes a random point between a certain number of items and takes part of a string after
		String Binary="";
      	Binary+=Generation[fittestIndex].getBinary().substring(0, randomItem);
      	parents[0]=fittestIndex;
		fittestIndex--;
		Binary+=Generation[fittestIndex].getBinary().substring(randomItem, items.length);
      	parents[1]=fittestIndex;

		fittestIndex--;
		return generateSolution(Binary);

	}
	public Generation makeNewGeneration() {
		int max = items.length; // 
		int min = 0; // 
		int range = max - min; //range of items indexes
		int i=0;
		while(i<generationSize) {
			int randomItem=(int)(Math.random() * range);// generates random index for the item
			if(Generation[fittestIndex].getValid()==true && fittestIndex-1>=0&&Generation[fittestIndex-1].getValid()==true)
			{
				
				Chromosome temp=crossOver(randomItem);
				temp.setParents(parents);
				newGeneration[i]=temp;
				i++;
			}
			else {
				
				fittestIndex=fittest;
				Chromosome temp=crossOver(randomItem);
				temp.setParents(parents);
				newGeneration[i]=temp;
				i++;
			}             
		}
		nextGeneration=new Generation(newGeneration, maxWeight,  generationSize, solutionSize, numberOfItems,items );
        printNew();
        return nextGeneration;
	}




	public Chromosome generateSolution(String binary) {// generates a new solution based on the binary string
		Chromosome newSolution=new Chromosome(solutionSize,maxWeight,numberOfItems);
		int j=0;
		for(int i=0;i<solutionSize;i++) {
			int test=Character.getNumericValue(binary.charAt(i));

			if(binary.charAt(i)=='1')// checks if the current binary is needed is taken or not
			{
				j++;
				newSolution.add(items[j], i);
			}
		}

		return newSolution;

	}



	public void sort(Chromosome[] generation){ 
		int n = generation.length; 

		// One by one move boundary of unsorted subarray 
		for (int i = 0; i < n; i++) 
		{ 
			// Find the minimum element in unsorted array 
			int min_idx = i; 
			for (int j = i+1; j < n; j++) 
				if (generation[j].getTotalValue() < generation[min_idx].getTotalValue()) 
					min_idx = j; 

			// Swap the found minimum element with the first 
			// element 
			Chromosome temp = generation[min_idx]; 
			generation[min_idx] = generation[i]; 
			generation[i] = temp; 
			fittestIndex=i;
		}
	}



}
