import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class averageservice {

	public static void main(String[] args) throws IOException {
		String fileName = "data.txt";
		List<String> lines = Files.readAllLines(Paths.get(fileName),
                StandardCharsets.UTF_8);
		Float downtine = (float) 0;
		Integer n_repair = 0;
		Float avg_service = (float) 0;
		Float start_repair = (float) 0;
		
		for (String line : lines) { 
			List<String> items = new ArrayList<String>(Arrays.asList(line.split(" ")));
			Float value = Float.parseFloat(items.get(0));
			String action = items.get(1);
			String action2 = items.get(2);
			System.out.println(action);
			System.out.println(action2);
			if(action.equals("starting") && action2.equals("repair")){
				start_repair=value; 
			}
			if(action.equals("finishing") && action2.equals("repair")){
				downtine= downtine + (value-start_repair);
				n_repair = n_repair+1;
			}
		}
		avg_service = (downtine/n_repair);
		System.out.println(downtine);
		System.out.println(n_repair);
		System.out.println(avg_service);

	}
	

}
