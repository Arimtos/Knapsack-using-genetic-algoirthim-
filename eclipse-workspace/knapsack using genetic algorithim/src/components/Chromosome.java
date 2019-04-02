package components;

public class Chromosome {
	int current; // stores the current index of the item
	private int size; // number of allowed items
	private Gene[] items; // list of items in the solution
	private StringBuilder binary; // a binary value to represent the items array
	private int maxWeight; // max weight allowed for the solution
	private int totalWeight; // current total weight in the solution
	private int totalValue;  // current total value in the solution
	private int totalIems;// number of items in the GA system;
	private boolean valid;// Tests if the solution is valid or not

	public Chromosome(int size,int maxWeight, int totalNumber) {
		this.totalIems=totalNumber;// 
		current=0; // currently no items in the solution
		this.size=size; // number of items in the solution
		this.totalValue=0;
		this.totalWeight=0;
		this.maxWeight=maxWeight; // max weight of the solution 
		items=new Gene[size]; //making an empty list of solutions 
		binary=new StringBuilder("");
		for(int i=0;i<totalIems;i++) {       /*saying that there are no items included so far 
		 */ 			binary.append('0');
		}
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void Print() {
		for(int i=0;i<size;i++) {
			System.out.println("Value:"+items[i].getValue()+"   Weight:"+items[i].getWeight()+"    Index:"+items[i].getIndex());
		}
		System.out.println(binary.toString());
		System.out.println("Total weight:"+" "+ totalWeight);
		System.out.println("Total value:"+" "+totalValue);
		System.out.println("==========================================");
	}
	public void add(Gene item, int index) { //adds an item to the solution
		items[current]=item; //add the item to the list
		binary.setCharAt(index,'1'); // modify the item in the binary array
		totalWeight+=item.getWeight(); //add weight to total weight
		totalValue+=item.getValue();// add value to total value
		current++;		// update index for next addition
	}
	public boolean exists(Gene item) {
		if(isEmpty()==true)
			return false;
		boolean exists=false;
		for(int i=0;i<current;i++) {
			if(items[i].getIndex()==item.getIndex())
				exists=true;
		}
		return exists;
	}
	public boolean isEmpty() {
		if(current==0)
			return true;
		else
			return false;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Gene[] getItems() {
		return items;
	}

	public void setItems(Gene[] items) {
		this.items = items;
	}

	public StringBuilder getBinary() {
		return binary;
	}

	public void setBinary(StringBuilder binary) {
		this.binary = binary;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public int getTotalIems() {
		return totalIems;
	}

	public void setTotalIems(int totalIems) {
		this.totalIems = totalIems;
	}
}
