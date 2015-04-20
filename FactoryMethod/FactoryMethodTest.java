import java.util.*;

abstract class Product{
	public Product(){
		System.out.println("Product");
	}
}

class ProductA extends Product{
	ProductA(){
		System.out.println("ProductA");
	}
}

class ProductB extends Product{
	ProductB(){
		System.out.println("ProductB");
	}
}

class Factory{
	public static Product CreateInstance(String productName){
		if(productName.equals("ProductA")) return new ProductA();
		if(productName.equals("ProductB")) return new ProductB();
		else
		throw new RuntimeException(
      "Bad product creation: " + productName);
	}
}

public class FactoryMethodTest{
	public static void main(String[] args) {
		Factory.CreateInstance("ProductA");
		Factory.CreateInstance("ProductB");
		//Factory.CreateInstance("bad product");
	}
}