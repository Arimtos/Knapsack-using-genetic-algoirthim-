package components;

public class Generation {
	private Chromosome[] Generation; // population of produced solutions for knapsack
	private int maxWeight; // max weight in each chromosome
	private int generationSize; // number of solutions in each generation
	private int fittestIndex; // the index of the fittest solution
	// children array
	private int solutionSize; // number of items in each solution
	private int numberOfItems; // number of items in the KP solution
	private Gene[] items;// Array of items in the KP system
	private int fittest;
	private Generation nextGeneration;//stores



	public Generation(Chromosome[] Generation,int maxWeight, int generationSize,int solutionSize,int numberOfItems,Gene[] items) {
		this.Generation=Generation;
		this.maxWeight=maxWeight;
		this.generationSize=generationSize;
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
            Generation[i].Print();
		}
	}
	

	public Chromosome crossOver(int randomItem){// takes a random point between a certain number of items and takes part of a string after
		String Binary="";
		Binary+=Generation[fittestIndex].getBinary().substring(0, randomItem);
		fittestIndex--;
		Binary+=Generation[fittestIndex].getBinary().substring(randomItem, items.length);
		fittestIndex--;
		return generateSolution(Binary);

	}

	public Generation MakeNewGeneration() {
		Chromosome[] newGeneration= new Chromosome[generationSize];
		Chromosome temp;
		int max = solutionSize; // 
		int min = 0; // 
		int range = max - min; //range
		
		int i=0;
		while (i<generationSize) {
			if(fittestIndex>0&&Generation[fittestIndex].getValid()==true&&Generation[fittestIndex-1].getValid()==true) {
				int randomItem=(int)(Math.random() * range);
				temp=crossOver(randomItem);
				newGeneration[i]=temp;
				
				i++;
			}
			else {
				fittestIndex=fittest;
			}
		}
	    return new Generation(newGeneration, maxWeight, generationSize, solutionSize, numberOfItems, items);
	}






















	public String GenerationReport(){
		StringBuilder builder=new StringBuilder("");
		for(int i=0;i<generationSize;i++){
			builder.append("Solution"+(i+1)+"\r\n");
			builder.append("Binary String: "+Generation[i].getBinary().toString()+"\r\n");
			builder.append("Total value: "+Generation[i].getTotalValue()+"\r\n");
			builder.append("Total Weight: "+Generation[i].getTotalWeight()+"\r\n");
			builder.append("======================================"+"\r\n");
		}    	
		return builder.toString();

	}



	public Chromosome generateSolution(String binary) {// generates a new solution based on the binary string
		Chromosome newSolution=new Chromosome(solutionSize,maxWeight,numberOfItems);
		int j=0;
		for(int i=0;i<solutionSize;i++) {
			int test=Character.getNumericValue(binary.charAt(i));

			if(binary.charAt(i)=='1')// checks if the current binary is needed is taken or not
			{
				newSolution.add(items[i], i);

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
