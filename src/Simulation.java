import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.ArrayList;


public class Simulation {	
	public EventHeap h;
	double now;
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	
	public List<Double> timeToNextFailure = new ArrayList<Double>();
	public List<Double> timeToRepair = new ArrayList<Double>();
	
	public Simulation(String filename) {
		h = new EventHeap(10000);
		now = 0;
		try{
			List<String> lines = Files.readAllLines(Paths.get(filename),
	                StandardCharsets.UTF_8);
			
			for (String line : lines) { 
				List<String> items = new ArrayList<String>(Arrays.asList(line.split(" ")));
				double file_timeToNextFailure = Double.parseDouble(items.get(0));
				double file_timeToRepair = Double.parseDouble(items.get(1));
				timeToNextFailure.add(file_timeToNextFailure);
				timeToRepair.add(file_timeToRepair);
			}
			
		}catch(Exception e){
				System.out.println("Error:" + e.getMessage());
		}
	}

	public void scheduleEvent(Event e) {
		h.insert(e);
	}
	
	public void setup() {
		Event machineEvent = new Event();
		machineEvent.setHandler(m);
		machineEvent.setType(working);
		machineEvent.setTime(0);
		scheduleEvent(machineEvent);
		
		Event userEvent = new Event();
		userEvent.setHandler(u);
		userEvent.setType(userCheck);
		userEvent.setTime(60);
		scheduleEvent(userEvent);
		return;
	}
	
	public void run(double maxTime) {
		while (!timeToNextFailure.isEmpty() && !timeToRepair.isEmpty() && !h.isEmpty() && h.peek().getTime()<=maxTime) {
			Event nextEvent = h.remove();
			now = nextEvent.getTime();
			nextEvent.getHandler().respondToEvent(nextEvent, this);
		}
	}
	// events
	public final int working = 1;
	public final int failure = 2;
	public final int startRepair = 3;
	public final int finishRepair = 4;
	public final int userCheck = 5;

}
