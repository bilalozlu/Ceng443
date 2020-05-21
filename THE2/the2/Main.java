import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {
		int n, m, numberOfUsers;
		ArrayList<ArrayList<String>> userList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		numberOfUsers = scan.nextInt();

		Scanner scan2 = new Scanner(System.in);
		for(int i = 0; i < numberOfUsers; i++) {
			ArrayList<String> oneList = new ArrayList<>();
	        String temp = scan2.nextLine();
	        String[] tempList = temp.split(" ");
	        for(int j = 0; j < tempList.length; j++ ) {
	        	oneList.add(tempList[j]);
	        }
	        userList.add(oneList);
		}
		scan.close();
		scan2.close();
		
		Room.fill(n, m);
		Logger.InitLogger();
		ExecutorService executor = Executors.newCachedThreadPool();
	    for (int i = 0; i < numberOfUsers; i++) {
	        executor.execute(new User(userList.get(i)));
	    }
	    executor.shutdown();
	    while (!executor.isTerminated()) {
	    }
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m ; j++) {
		    	if(Room.seatList.get(i).get(j).owner.length() > 0) {
		    		System.out.printf( "T:%s ", Room.seatList.get(i).get(j).owner ) ;
		    	}
		    	else {
		    		System.out.printf("E:%s ","");
		    	}
			}
	    	System.out.println();
		}
	}
}
