public abstract class Tire {

  protected double speed;
  protected double degradation;
  protected String tip;

  public double getSpeed() {
    return speed;
  }

  public double getDegradation() {
    return degradation;
  }

  abstract public void tick(TrackFeature f);
}
