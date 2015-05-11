class Target{
	void Request(){
		System.out.println("This is client needed interface request() in Target.");
	}
}
class Adaptee{
	void SpecificRequest(){
		System.out.println("This is the interface SpecificRequest() in Adaptee");
	}
} 

class Adapter{
	private Adaptee adaptee;

	Adapter(Adaptee adaptee){
		this.adaptee = adaptee;
	}
	void Request(){
		adaptee.SpecificRequest();
	}
}

public class InstanceAdapterTest{
	public static void main(String[] args) {
		Adapter adapter = new Adapter(new Adaptee());
		adapter.Request();
	}
}