import java.util.ArrayList;

public class Session {

	private Track track;
	private ArrayList<Team> teamList;
	private int totalLaps;

	public Session() {
		// Default Constructor
	}

	public Session(Track track, ArrayList<Team> teamList, int totalLaps) {
		this.track = track;
		this.teamList = new ArrayList<Team>(teamList.size());
		for (int i = 0; i < teamList.size(); i++) {
			this.teamList.add(teamList.get(i));
		}
		this.totalLaps = totalLaps;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public ArrayList<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<Team> teamList) {
		this.teamList = teamList;
	}

	public int getTotalLaps() {
		return totalLaps;
	}

	public void setTotalLaps(int totalLaps) {
		this.totalLaps = totalLaps;
	}

	public void simulate() {
		if (this.track.isValidTrack()) {
			System.out.println("Track is valid.Strating simulation on " + this.track.getTrackName() + " for " + this.totalLaps + " laps.");

			for (int i = 0; i < this.totalLaps; i++) {
				for(TrackFeature f: this.track.getFeatureList()) {
					for (Team t: this.teamList) {
						for(Car c: t.getCarList()) {
							c.tick(f);
							c.getTire().tick(f);
						}
					}
				}
			}

			System.out.println(printWinnerTeam());
			System.out.println(printTimingTable());
		}
		else {
			System.out.println("Track is invalid.Simulation aborted!");
		}
	}

	public String printWinnerTeam() {
		int k = 0;
		double minTime = Integer.MAX_VALUE;

		for (int i = 0; i < this.teamList.size(); i++) {
			for (int j = 0; j < this.teamList.get(i).getCarList().size(); j++) {
				Car temp = this.teamList.get(i).getCarList().get(j);
				if (minTime > temp.getTotalTime()) {
					minTime = temp.getTotalTime();
					k = i;
				}
			}
		}

		int len = this.teamList.get(k).getTeamColors().length;

		String col = this.teamList.get(k).getTeamColors()[0];

		if (len > 1) {
			for (int i = 1; i < len - 1; i++) {
				col += ", " + this.teamList.get(k).getTeamColors()[i];
			}
			col += " and " + this.teamList.get(k).getTeamColors()[len - 1];
		}

		return ("Team " + this.teamList.get(k).getName() + " wins." + col + " flags are waving everywhere.");

	}

	private String printTimingTable() {
		String theStr = "";
		int numberofCars = 0;
		for (int i = 0; i < this.teamList.size(); i++) {
			for (int j = 0; j < this.teamList.get(i).getCarList().size(); j++) {
				numberofCars++;
			}
		}
		for (int a = 0; a < numberofCars; a++) {
			int k = 0;
			int l = 0;
			double minTime = Integer.MAX_VALUE;
			for (int i = 0; i < this.teamList.size(); i++) {
				for (int j = 0; j < this.teamList.get(i).getCarList().size(); j++) {
					Car temp = this.teamList.get(i).getCarList().get(j);
					if (minTime > temp.getTotalTime()) {
						minTime = temp.getTotalTime();
						k = i;
						l = j;
					}
				}
			}
			Car c = this.teamList.get(k).getCarList().get(l);
			this.teamList.get(k).getCarList().remove(l);
			int mili = (int)(c.getTotalTime()*1000);
			int hour = mili / 3600000;
			int min = (mili % 3600000) / 60000;
			int sec = (mili % 60000) / 1000;
			int mil = mili % 1000;
			String hours = Integer.toString(hour);
			String mins = Integer.toString(min);
			String secs = Integer.toString(sec);
			String mils = Integer.toString(mil);
			if (hour < 10)
				hours = "0" + hours;
			if (min < 10)
				mins = "0" + mins;
			if (sec < 10)
				secs = "0" + secs;
			theStr += c.getDriverName() + "(" + Integer.toString(c.getCarNo()) + "): " + hours + ":" + mins + ":" + secs + "." + mils;
			if (a < numberofCars - 1) {
				theStr += "\n";
			}
		}
		return theStr;
	}
}
