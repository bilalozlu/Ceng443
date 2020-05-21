public class Car {

  private int carNo;
  private String driverName;
  private double totalTime;
  private Tire tire;

  public Car() {
	// Default Constructor
  }

  public Car(String driverName, int carNo, Tire tire) {
	this.driverName = driverName;
	this.carNo = carNo;
    this.tire = tire;
  }

  public Tire getTire() {
    return tire;
  }

  public void setTire(Tire tire) {
    this.tire = tire;
  }

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public int getCarNo() {
    return carNo;
  }

  public void setCarNo(int carNo) {
    this.carNo = carNo;
  }

  public double getTotalTime() {
    return totalTime;
  }

  public void tick(TrackFeature feature) {
    if (this.getTire().getDegradation() > 70) {
    	this.totalTime += 25;
    	if (this.getTire().tip == "soft") {
    		this.setTire(new MediumTire());
    	}
    	else {
    		this.setTire(new SoftTire());
    	}
    }
    double rand = Math.random();
    this.totalTime += feature.getDistance()/this.getTire().getSpeed() + rand;
  }

}
