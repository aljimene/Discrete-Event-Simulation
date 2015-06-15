
public class DiscreteEventSimulation {

	public static void main(String[] args) {
		
		String filename = "trace_driven.txt";
		
		Simulation s = new Simulation(filename);
		
		s.setup(); // setup simulation;
		
		s.run(10000); // run of simulation
		
	}

}
