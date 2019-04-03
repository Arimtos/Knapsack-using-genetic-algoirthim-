package components;

public class Generation {
	private Chromosome[] Generation; // population of produced solutions for knapsack
	private int maxWeight;
	private int generationSize;
	public Generation(Chromosome[] Generation,int maxWeight, int generationSize) {
		this.Generation=Generation;
		this.maxWeight=maxWeight;
		this.generationSize=generationSize;
	}
	public void fitness() {// tests if the produced solutions satisfy the basic knapsack conditions, as well as tests how good they are
		for(int i=0;i<generationSize;i++) {
			if(Generation[i].getTotalWeight()>maxWeight) {
				Generation[i].setValid(false);
			}
			else
				Generation[i].setValid(true);
		}
		sort(Generation);
	}

	public void sort(Chromosome[] generation){ 
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

	public crossOver
}
