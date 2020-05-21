import java.util.ArrayList;

public class Track {

  private String trackName;
  private ArrayList<TrackFeature> featureList;
  private boolean isClockwise;
  private int count;

  public Track() {
	// Default Constructor
  }

  public Track(String trackName, ArrayList<TrackFeature> featureList, boolean isClockwise) {
    this.trackName = trackName;
    this.featureList = new ArrayList<TrackFeature>(featureList.size());
    for (int i = 0; i < featureList.size(); i++) {
        this.featureList.add(featureList.get(i));
    }
    this.isClockwise = isClockwise;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }

  public ArrayList<TrackFeature> getFeatureList() {
    return featureList;
  }

  public void setFeatureList(ArrayList<TrackFeature> featureList) {
    this.featureList = featureList;
  }

  public boolean isClockwise() {
    return isClockwise;
  }

  public void setClockwise(boolean clockwise) {
    isClockwise = clockwise;
  }

  public int getTrackLength() {
    return featureList.size();
  }

  public TrackFeature getNextFeature() {
	  if (this.count == this.featureList.size()) {
		  this.count = 0;
	  }
	  return this.featureList.get(this.count++); 
  }

  public void addFeature(TrackFeature feature) {
    this.featureList.add(feature);
  }

  public boolean isValidTrack() {
	  int l = 0;
	  int r = 0;
	  
	  if (this.featureList.get(0).getTurnDirection() != TurnDirection.STRAIGHT) {
		  return false;
	  }
	  
	  if (this.featureList.get(featureList.size()-1).getTurnDirection() != TurnDirection.STRAIGHT) {
		  return false;
	  }
	  
	  for (int i = 0; i < featureList.size(); i++) {
		  if (this.featureList.get(i).getTurnDirection() == TurnDirection.RIGHT) {
			  r++;
		  }
		  if (this.featureList.get(i).getTurnDirection() == TurnDirection.LEFT) {
			  l++;
		  }
	  }
	  
	  if ((this.isClockwise && r-l != 4) || ((!this.isClockwise && l-r != 4))) {
		  return false;
	  }
	  return true;
  }
}
