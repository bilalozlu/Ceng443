import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class User implements Runnable{
	
	protected String name;
	protected ArrayList<String> reserve = new ArrayList<>();
	protected static Lock lock = new ReentrantLock();
	private int count;
	private boolean flag;
	
	public User(ArrayList<String> reserveList) {
		this.name = reserveList.get(0);
		for (int i = 1; i  < reserveList.size(); i++) {
			this.reserve.add(reserveList.get(i));
		}
		this.count = 0;
		this.flag = false;
	}
	@Override
	public void run() {
		while (flag == false) {
			String theSeats;
			count++;
			try {
			lock.lock();
			for (int i = 0; i < this.reserve.size(); i++) {
				int row = (int)(this.reserve.get(i).charAt(0) - 65);
				int col = (int)(this.reserve.get(i).charAt(1) - 48);
				if (Room.seatList.get(row).get(col).owner.length() > 0) {
						theSeats = "";
						for (int j = 0; j < this.reserve.size(); j++) {
							theSeats += this.reserve.get(j);
							if (j < this.reserve.size()-1) {
								theSeats += ", ";
							}
						}
						Logger.LogFailedReservation(this.name, "[" + theSeats + "]" , System.nanoTime() , "not empty");
						flag = true;
						break;
					}
				}
				
				if (flag == false) {
					double rand = Math.random();
					if (rand > 0.1) {
						for(int i = 0; i < this.reserve.size(); i++) {
							int row = (int)this.reserve.get(i).charAt(0) - 65;
							int col = (int)(this.reserve.get(i).charAt(1) - 48);
							Room.seatList.get(row).get(col).owner = this.name;
						}
						theSeats = "";
						for (int i = 0; i < this.reserve.size(); i++) {
							theSeats += this.reserve.get(i);
							if (i < this.reserve.size()-1) {
								theSeats += ", ";
							}
						}
						if (count == 1) {
							//lock.unlock();
							Thread.sleep(50);
							//lock.lock();
						}
						Logger.LogSuccessfulReservation(this.name, "[" + theSeats + "]", System.nanoTime()  , "count: " + count);
						flag = true;
					}
					else {
						theSeats = "";
						for (int i = 0; i < this.reserve.size(); i++) {
							theSeats += this.reserve.get(i);
							if (i < this.reserve.size()-1) {
								theSeats += ", ";
							}
						}
						Logger.LogDatabaseFailiure(this.name, "[" + theSeats + "]", System.nanoTime() ,"database failure");
						lock.unlock();
						Thread.sleep(100);
						lock.lock();
					}
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
		        lock.unlock();
		    }
		}
	}
}
