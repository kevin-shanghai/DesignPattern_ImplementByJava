import java.util.*;

class Singleton{
	private static Singleton instance = null;

	public static synchronized Singleton getInstance(){
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}

	private Singleton(){
		//not permit the user get the instance of this class by new operator
	}
}

public class SingletonTest{
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1);
		System.out.println(s2);
	}
}