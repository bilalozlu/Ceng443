import java.util.ArrayList;

public class Room {
	public static ArrayList<ArrayList<Seat>> seatList = new ArrayList<>();
	
	public static void fill (int n, int m) {
		for(int i = 0; i < n; i++) {
			ArrayList<Seat> oneList = new ArrayList<>();
			for(int j = 0; j < m ; j++) {
				oneList.add(new Seat ((char)(i+65) + Integer.toString(j), ""));
			}
			Room.seatList.add(oneList);
		}
	}
}