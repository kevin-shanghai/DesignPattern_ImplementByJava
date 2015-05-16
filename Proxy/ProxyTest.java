import java.util.*;

abstract class AbstractObject{
	protected abstract void doSomeOperation();
}

class ConcreteObject extends AbstractObject{
	protected void doSomeOperation(){
		System.out.println("ConcreteObject doSomeOperation()....");
	}
}

class Proxy{
	AbstractObject absObject_;
	Proxy(AbstractObject absObject){
		absObject_ = absObject;
	}
	void doSomeOperation(){
		absObject_.doSomeOperation();
	}
}

public class ProxyTest{
	public static void main(String[] args) {
		Proxy proxy = new Proxy(new ConcreteObject());
		proxy.doSomeOperation();
	}
}