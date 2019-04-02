package components;

public class Gene {
	private int value;
	private int weight;
	private int index;
	
	public Gene(int value, int weight, int index) {
		this.value = value;
		this.weight = weight;
		this.index=index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
