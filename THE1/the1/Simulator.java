public class Simulator {

  public static void main(String[] args) {
    SessionParser parser = new SessionParser();
    Session s = parser.parse("monza.xml");
    s.simulate();
  }
}
