package structural.decorator;

public abstract class Decorator extends Component {

	// 보조스트림을 쓰려면 주스트림을
	protected Component component;
	
	public Decorator(Component component) {
		this.component = component;
	}
	
}
