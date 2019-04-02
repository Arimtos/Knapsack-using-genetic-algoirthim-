package components;

public class Genetic_Algorithim {

	private Gene[] items; // Produced items for the knapsack solution
	private int size; // number of produced items
	private Chromosome[] population; // population of produced solutions for knapsack
	private int populationSize;// number of solutions for knapsack solutions 
	private int maxWeight; // max weight all chromosomes should have
	private int solutionSize;// number of items in each solution

	public Genetic_Algorithim(int size, int populationSize, int maxWeight,int solutionSize) {
		this.solutionSize=solutionSize;
		this.size = size; 
		this.items=new Gene[size];
		this.population=new Chromosome[populationSize];
		this.populationSize=populationSize;
		this.maxWeight=maxWeight;
		makeRandom();
		makePopulation();
		fitness();
		System.out.print(population[0].getTotalValue());
	}
	//generates items with random weights and values
	public void makeRandom() {
		int max = 50; // maximum range of weight and value
		int min = 0; // minmum range of weight and value
		int range = max - min + 1; 
		items=new Gene[size];  // intilazies the array 
		for(int i=0;i<size;i++) {
			Gene temp=new Gene((int)(Math.random() * range) + min,(int)(Math.random() * range) + min,i);
			// makes a new item with random weight, value, and stores the index of that item
			items[i]=temp;
			// stores the item in the array
		}
	}
	public void makePopulation(){ // Generates intital random population of solutions 
		int max = size; // 
		int min = 0; // 
		int range = max - min; //range of items indexes

		for(int i=0;i<populationSize;i++) { // iterates through the needed number of solutions
			Chromosome temp=new Chromosome(solutionSize,maxWeight,size);// creates a new solution without items
			for(int j=0;j<solutionSize;j++) { // adds items to the solution
				boolean flag=true;
				int randomItem=(int)(Math.random() * range);// generates random index for the item
				Gene TempItem=items[randomItem];
				while(flag==true) {
					if(temp.exists(TempItem)==true){
						randomItem=(int)(Math.random() * range);// generates random index for the item
						TempItem=items[randomItem];
					}
					else
						flag=false;

				}
				temp.add(TempItem,randomItem);// adds the item with the random index and stores that index
			}
			population[i]=temp;
			System.out.println("Soltuion"+i);
			temp.Print();

		}
	}
	public void fitness() {// tests if the produced solutions satisfy the basic knapsack conditions, as well as tests how good they are
		for(int i=0;i<populationSize;i++) {
			if(population[i].getTotalWeight()>maxWeight) {
				population[i].setValid(false);
			}
			else
				population[i].setValid(true);
		}
		sort(population);
	}
	void sort(Chromosome[] generation) 
	{ 
		 int n = generation.length; 
		  
	        // One by one move boundary of unsorted subarray 
	        for (int i = 0; i < n-1; i++) 
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
	        }
	} 


}
