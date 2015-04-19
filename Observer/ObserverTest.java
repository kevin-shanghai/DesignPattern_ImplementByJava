
import java.util.*;

interface ObserverBase{
	public void updateInformation(int a);
}

class Observer1 implements ObserverBase{
	public void updateInformation(int a){
		System.out.println("Observer1::updateInformation(int a):" + "a is:" + a);
	}
}

class Observer2 implements ObserverBase{
	public void updateInformation(int a){
		System.out.println("Observer1::updateInformation(int a):" + "a is:" + a);
	}
}

class Observer3 implements ObserverBase{
	public void updateInformation(int a){
		System.out.println("Observer1::updateInformation(int a):" + "a is:" + a);
	}
}


class Observered 
{
	List<ObserverBase> observerList_ = new ArrayList<ObserverBase>();

	public void addObserver(ObserverBase ob){
		observerList_.add(ob);
	}

	public void updateInformation(int a){
		Iterator it = observerList_.iterator();
		while(it.hasNext()){
			ObserverBase obj = (ObserverBase)it.next();
			if (obj instanceof ObserverBase){
				obj.updateInformation(a);
			}
		}
	}
}

public class ObserverTest{
	public static void main(String[] args) {

		Observered theObservered = new Observered();
		theObservered.addObserver(new Observer1());
		theObservered.addObserver(new Observer2());
		theObservered.addObserver(new Observer3());

		theObservered.updateInformation(1000);
		theObservered.updateInformation(8888);
	}
}

