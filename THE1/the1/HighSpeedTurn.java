public class HighSpeedTurn extends TrackFeature {

  public HighSpeedTurn(int turnNo, TurnDirection direction, double distance, double roughness) {
	  this.featureNo = turnNo;
	  this.turnDirection = direction;
	  this.distance = distance;
	  this.roughness = roughness;
	  this.type = "h";
  }
}
