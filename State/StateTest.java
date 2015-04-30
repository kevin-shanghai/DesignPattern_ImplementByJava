import java.util.*;
import java.util.concurrent.*;
import java.lang.InterruptedException;

interface RoomStateBase{
	public static final int CMD_BOOK_ROOM = 0;
	public static final int CMD_CANCEL_BOOK_ROOM = 1;
	public static final int CMD_CHECKOUT_ROOM = 2;
	public static final int CMD_CHECKIN_ROOM = 3;
	public RoomManager roomManager = RoomManager.getInstance();
	void HandleCommand(int iCommandID);
}

class FreeState implements RoomStateBase{
	@Override 
	public void HandleCommand(int iCommandID){
		switch (iCommandID){
			case CMD_BOOK_ROOM:
				System.out.println("Confirm, the room was booked success!!!");
				roomManager.changeState(RoomManager.RoomStateIDEnum.E_BOOK_STATE);
				break;
			case CMD_CANCEL_BOOK_ROOM:
				System.out.println("The room is not booked, you have no need to cancel book the room");
				break;
			case CMD_CHECKOUT_ROOM:
				System.out.println("The room is free state, can not handle the check out command.");
				break;
			case CMD_CHECKIN_ROOM:
				System.out.println("The room is free state, can not handle the check in command.");
				break;

		}
	}
}

class BookedState implements RoomStateBase{
	@Override 
	public void HandleCommand(int iCommandID){
		switch (iCommandID){
			case CMD_BOOK_ROOM:
					System.out.println("The room is in booked already, you can not booked any more");
					break;
			case CMD_CANCEL_BOOK_ROOM:
					System.out.println("Confirm, cancel book the room success!!!");
					roomManager.changeState(RoomManager.RoomStateIDEnum.E_FREE_STATE);
					break;
			case CMD_CHECKOUT_ROOM:
					System.out.println("The room is only booked, you have not checked in, so, there is no need to check out!");
					break;
			case CMD_CHECKIN_ROOM:
					System.out.println("Confirm, check in this room!!!");
					roomManager.changeState(RoomManager.RoomStateIDEnum.E_CHECK_IN_STATE);
					break;
		}
	}
}

class CheckInState implements RoomStateBase{
	@Override 
	public void HandleCommand(int iCommandID){
		switch (iCommandID){
			case CMD_BOOK_ROOM:
					System.out.println("The room is Check in already, you can not booked any more");
					break;
			case CMD_CANCEL_BOOK_ROOM:
					System.out.println("The room is check in already, you can not do the cancel book operation.");
					break;
			case CMD_CHECKOUT_ROOM:
					System.out.println("Confirm, Check out the room Success!!!");
					roomManager.changeState(RoomManager.RoomStateIDEnum.E_FREE_STATE);
					break;
			case CMD_CHECKIN_ROOM:
					System.out.println("The room is Checed in already, can not handle the check in command.");
					break;
		}	
	}
}

class RoomManager{
	private static final RoomManager instance = new RoomManager();
	RoomStateBase currentRoomState = new FreeState();
	Map<Integer, RoomStateBase> mapAllRoomState = new HashMap<Integer, RoomStateBase>(); 
	enum RoomStateIDEnum{
		E_FREE_STATE,
		E_BOOK_STATE,
		E_CHECK_IN_STATE;
	}
	

	private RoomManager(){
		mapAllRoomState.put(RoomStateIDEnum.E_BOOK_STATE.ordinal(), new BookedState());
		mapAllRoomState.put(RoomStateIDEnum.E_CHECK_IN_STATE.ordinal(), new CheckInState());
		mapAllRoomState.put(RoomStateIDEnum.E_FREE_STATE.ordinal(), new FreeState());
	}

	public static synchronized RoomManager getInstance(){
		return instance;
	}

	void changeState(RoomStateIDEnum newState){
		currentRoomState = mapAllRoomState.get(newState.ordinal());
	}

	void handleCommand(int iCmd){
		currentRoomState.HandleCommand(iCmd);
	}
}

class RoomStateMachineActor implements Runnable{
	BlockingQueue<Integer> cmdQueue = new ArrayBlockingQueue<Integer>(10);
	void sendCommand(int icmd){
		try{
			cmdQueue.put(icmd);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	void handleCmd(){
		try{
			RoomManager.getInstance().handleCommand(cmdQueue.take().intValue());
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void run(){
		while(true){
			handleCmd();
			try{
				Thread.currentThread().sleep(1000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

public class StateTest{
	public static void main(String[] args) {
		RoomStateMachineActor st = new RoomStateMachineActor();
		Thread thread = new Thread(st);
		thread.start();
		st.sendCommand(RoomStateBase.CMD_CHECKIN_ROOM);
		st.sendCommand(RoomStateBase.CMD_CHECKOUT_ROOM);
		st.sendCommand(RoomStateBase.CMD_BOOK_ROOM);
		st.sendCommand(RoomStateBase.CMD_CANCEL_BOOK_ROOM);
		st.sendCommand(RoomStateBase.CMD_CHECKOUT_ROOM);
		st.sendCommand(RoomStateBase.CMD_BOOK_ROOM);
		st.sendCommand(RoomStateBase.CMD_CHECKIN_ROOM);
		st.sendCommand(RoomStateBase.CMD_CHECKOUT_ROOM);
		st.sendCommand(RoomStateBase.CMD_BOOK_ROOM);

		try{
			thread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}



