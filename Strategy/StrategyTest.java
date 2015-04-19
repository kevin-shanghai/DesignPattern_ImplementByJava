import java.util.*;

interface Customer{
	public void getPrice(double dPriceBeforeDiscount);
}

class PrimaryCustomer implements Customer{
	public void getPrice(double dPriceBeforeDiscount){
		System.out.println("Primary customer: no discount, price is:" + dPriceBeforeDiscount);
	}
}

class IntermediateCustomer implements Customer{
	public void getPrice(double dPriceBeforeDiscount){
		System.out.println("IntermediateCustomer: 20% discount, price is:" + dPriceBeforeDiscount*(1-0.2));
	}
}

class AdvancedCustomer implements Customer{
	public void getPrice(double dPriceBeforeDiscount){
		System.out.println("AdvancedCustomer: 50% discount, price is:" + dPriceBeforeDiscount * (1-0.5));
	}
}

public class StrategyTest{
	Customer customer;
	StrategyTest(Customer customer){
		this.customer = customer;
	}

	private void calculatePrice(double dPrice){
		customer.getPrice(dPrice);
	}

	public static void main(String[] args) {
		StrategyTest st1 = new StrategyTest(new IntermediateCustomer());
		st1.calculatePrice(1.8);

		StrategyTest st2 = new StrategyTest(new PrimaryCustomer());
		st2.calculatePrice(1.8);

		StrategyTest st3 = new StrategyTest(new AdvancedCustomer());
		st3.calculatePrice(1.8);


		}
}