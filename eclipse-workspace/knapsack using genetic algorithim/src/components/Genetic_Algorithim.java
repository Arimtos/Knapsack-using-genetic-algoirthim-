package components;

public class Genetic_Algorithim {
    private Generation[] Generations;
    private int generation=0;
	private Gene[] items; // Produced items for the knapsack solution
	private int size; // number of produced items
	private Chromosome[] population; // population of produced solutions for knapsack
	private int populationSize;// number of solutions for knapsack solutions 
	private int maxWeight; // max weight all chromosomes should have
	private int solutionSize;// number of items in each solution
	private int iteration;// stores the number of iterations we are performing in our GA 
	

	public Genetic_Algorithim(int size, int populationSize, int maxWeight,int solutionSize,int iteration) {
		this.iteration=iteration;
		this.solutionSize=solutionSize;
		this.size = size; 
		this.items=new Gene[size];
		this.population=new Chromosome[populationSize];
		this.populationSize=populationSize;
		this.maxWeight=maxWeight;
		makeRandom();
		makePopulation();
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
		Generations[0]=new Generation(population,maxWeight,populationSize);
		
	}
}
