import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Timer;

public class Driver {

	public static void main(String [] args){

		// Check number of arguments

		if(args.length != 5){
			throw new InvalidParameterException("Expected Arguments: Input Path(String), Output Path(String)," +
				"Primary Failure Rate(Double), Secondary Failure Rate(Double), Time in milliseconds(Int)");
		}

		String file_i = args[0];
		String file_o = args[1];
		double pfr = Double.parseDouble(args[2]);
		double sfr = Double.parseDouble(args[3]);
		int time = Integer.parseInt(args[4]);

		File_Manager file_man = new File_Manager();

		String[] elements;
		try {
			elements = file_man.ReadIntArray_fromFile(file_i).split(" ");
		}catch(IOException e) {
			System.out.print("Error reading from file.");
			return;
		}
		int[] data = new int[elements.length];
		for(int i = 0; i < elements.length; ++i){
			data[i] = Integer.parseInt(elements[i]);
		}


		// Execute Variants
		PrimaryVariant varP = new PrimaryVariant(data, pfr, file_o);
		Timer t = new Timer();
		Watchdog w = new Watchdog(varP);
		t.schedule(w, time);
		varP.start();
		
		try {
			varP.join();
			t.cancel();
			if(varP.failed == false){
				System.out.println("Primary is successful!");
				return;
			}
			

		}catch(InterruptedException e) {}

		SecondaryVariant varS = new SecondaryVariant(data, sfr, file_o);
		t = new Timer();
		w = new Watchdog(varS);
		t.schedule(w, time);
		varS.start();

		try {
			varS.join();
			t.cancel();
			if(varS.failed == false){
				System.out.println("Secondary is successful!");
				return;
			}
		}catch(InterruptedException e) {}

		file_man.delete(file_o);
		System.out.println("Program exiting.");
		System.exit(1);
	}
}