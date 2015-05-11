import java.util.*;
import java.lang.*;

interface CreateComputerCmd{
	public void excuteCmd();
}

class CreatePcCmd implements CreateComputerCmd{
	public void excuteCmd(){
		System.out.println("Create Pc...");
	}
}

class CreateLaptopCmd implements CreateComputerCmd{
	public void excuteCmd(){
		System.out.println("Create laptop...");
	}
}

class TaskLooper implements Runnable{
	List<CreateComputerCmd> cmdList = new ArrayList<CreateComputerCmd>();
	Boolean addCmd(CreateComputerCmd cmd){
		return cmdList.add(cmd);
	}

	Boolean removeCmd(CreateComputerCmd cmd){
		return cmdList.remove(cmd);
	}

	public void run(){
		for (CreateComputerCmd cmd: cmdList)
		{
			cmd.excuteCmd();
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}

public class CommandTest{
	public static void main(String[] args) {
		TaskLooper task = new TaskLooper();
		task.addCmd(new CreatePcCmd());
		task.addCmd(new CreateLaptopCmd());
		Thread t = new Thread(task);
		task.removeCmd(new CreatePcCmd());
		task.removeCmd(new CreateLaptopCmd());
		t.start();
		try{
			t.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}

