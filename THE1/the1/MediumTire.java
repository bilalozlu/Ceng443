public class MediumTire extends Tire {

  public MediumTire() {
    this.speed = 310;
    this.degradation = 0;
    this.tip = "medium";
  }

@Override
public void tick(TrackFeature f) {
	if (this.speed >= 100) {
		this.speed -= Math.min(this.degradation, 75) * 0.25;
	}
	if (f.type == "h") {
		this.degradation += 1.55 * f.getRoughness() * 1.1;
	}
	if (f.type == "l") {
		this.degradation += 1.30 * f.getRoughness() * 1.1;
	}
	if (f.type == "s") {
		this.degradation += 1.0 * f.getRoughness() * 1.1;
	}
}

}
