import java.util.*;

abstract class Animal{
	protected abstract void eat();
	protected abstract void sleep();
	public final void live(){
		eat();
		sleep();
	}
}

class Dog extends Animal{
	protected void eat(){
		System.out.println("dog eat....");
	}

	protected void sleep(){
		System.out.println("dog sleep....");
	}
}

class Cat extends Animal{
	protected void eat(){
		System.out.println("cat eat....");
	}

	protected void sleep(){
		System.out.println("cat sleep....");
	}
}

public class TemplateMethodTest{
	public static void main(String[] args) {
		Animal dog = new Dog();
		Animal cat = new Cat();
		dog.live();
		cat.live();
	}
}