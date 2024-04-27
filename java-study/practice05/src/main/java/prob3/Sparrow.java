package prob3;

public class Sparrow extends Bird{
	public Sparrow() {
		setSpecies("참새");
	}
	
	public void fly() {
		System.out.println(species + "(" + this.name + ")가 날아다닙니다.");
	};
	public void sing() {
		System.out.println(species + "(" + this.name + ")가 소리내어 웁니다.");
	};
}
