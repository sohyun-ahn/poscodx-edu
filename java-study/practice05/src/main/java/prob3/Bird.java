package prob3;

public abstract class Bird {
	protected String name;
	protected String species;
	
	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return species+ "의 이름은 " + name + " 입니다";
	};
	
	public abstract void fly() ;
	public abstract void sing() ;
}