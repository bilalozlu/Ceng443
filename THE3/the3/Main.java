import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException  {
		PartsStore ps = new PartsStore();
		ps.FindPartsWithBrand("GPU", "Asus");
		ps.TotalPrice("GPU", "Asus", "GeForce RTX 2080");
		ps.UpdateStock();
		ps.FindCheapestMemory(16);
		ps.FindFastestCPU();
	}
}
