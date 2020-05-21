import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class PartsStore {

	private List<List<String>> all;

	public PartsStore() throws IOException {
		this.all = Files.lines(Paths.get("pcparts.csv"))
			.map(one -> {
				List<String> temp = Arrays.asList(one.split(","));
				return temp;
			})
			.collect(Collectors.toList());
	}

	public void FindPartsWithBrand(String type, String brand) {
		this.all.stream()
		.filter(one -> (one.get(0).equals(type) || type == null) && (one.get(1).equals(brand) || brand == null))
		.map(one -> {
			return one.stream()
				.map(Object::toString)
				.collect(Collectors.joining(","));
		})
		.forEach(System.out::println);
	}

	public void TotalPrice(String type, String brand, String model) {
		double total = this.all.stream()
		.filter(one -> (one.get(0).equals(type) || type == null) && (one.get(1).equals(brand) || brand == null) && (one.get(2).equals(model) || model == null))
		.mapToDouble(one-> {
			String price = one.get(one.size() - 1);
			price = price.substring(0, price.length() - 4);
			return Double.parseDouble(price);
		})
		.sum();
		System.out.printf("%.2f", total);
		System.out.println(" USD");
	}

    public void UpdateStock(){
		List<List<String>> all2 = this.all.stream()
		.filter(one -> {
			String price = one.get(one.size() - 1);
			price = price.substring(0, price.length() - 4);
			return Double.parseDouble(price) > 0;
		})
		.collect(Collectors.toList());
		System.out.println(this.all.size() - all2.size() + " items removed.");
		this.all = all2;
    }

    public void FindCheapestMemory(int capacity){
		this.all.stream()
		.filter(one -> one.get(0).equals("Memory"))
		.filter(one -> {
			String cap = one.get(4);
			cap = cap.substring(0, cap.length() - 2);
			return Integer.parseInt(cap) >= capacity;
		})
		.min( (a, b)  -> {
			String price1 = a.get(6);
			price1 = price1.substring(0, price1.length() - 4);
			String price2 = b.get(6);
			price2 = price2.substring(0, price2.length() - 4);
			double x = Double.parseDouble(price1);
			double y = Double.parseDouble(price2);
			return Double.compare(x, y);
		})
		.ifPresent(a -> System.out.println(a.stream()
			.map(Object::toString)
	        .collect(Collectors.joining(","))));
    }

    public void FindFastestCPU(){
		this.all.stream()
		.filter(one -> one.get(0).equals("CPU"))
		.max( (a, b)  -> {
			String speed1 = a.get(4);
			speed1 = speed1.substring(0, speed1.length() - 3);
			String core1 = a.get(3);

			String speed2 = b.get(4);
			speed2 = speed2.substring(0, speed2.length() - 3);
			String core2 = b.get(3);

			double x = Double.parseDouble(speed1)*Integer.parseInt(core1);
			double y = Double.parseDouble(speed2)*Integer.parseInt(core2);
			return Double.compare(x, y);
		})
		.ifPresent(a -> System.out.println(a.stream()
			.map(Object::toString)
	        .collect(Collectors.joining(","))));
    }
}
