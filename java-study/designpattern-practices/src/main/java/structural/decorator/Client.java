package structural.decorator;

public class Client {

	public static void main(String[] args) {
		System.out.println(new ConcreteComponent("Hello World").operation()); // Hello World
		
		System.out.println(new BracesDecorator(new ConcreteComponent("Hello World")).operation()); // {Hello World}
		
		System.out.println(new ParenthesesDecorator(new BracesDecorator(new ConcreteComponent("Hello World"))).operation()); // ({Hello World})
		
		System.out.println(new ParenthesesDecorator(new ConcreteComponent("Hello World")).operation()); // (Hello World)
		
		System.out.println(new ParenthesesDecorator((new ParenthesesDecorator(new ConcreteComponent("Hello World")))).operation()); // ((Hello World))
	}

}
