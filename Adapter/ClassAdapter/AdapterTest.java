import java.util.*;

interface Target
{
	void InterfaceClientNeeded();
}

class Adaptee{
	void InterfaceCanSupport(){
		System.out.println("This is interface can support.");
	}
}

class Adapter extends Adaptee implements Target {
	public void InterfaceClientNeeded(){
		System.out.println("This is the interface client needed.");
		InterfaceCanSupport();
	}
}

public class AdapterTest{
	public static void main(String[] args) {
		Adapter at = new Adapter();
		at.InterfaceClientNeeded();
	}
}
