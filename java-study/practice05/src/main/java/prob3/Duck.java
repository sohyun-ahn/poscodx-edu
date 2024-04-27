package prob3;

public class Duck extends Bird{
	public Duck() {
		setSpecies("오리");
	}
	
	public void fly() {
		System.out.println(species + "(" + this.name + ")는 날지 않습니다.");
	};
	public void sing() {
		System.out.println(species + "(" + this.name + ")가 소리내어 웁니다.");
	}
}
