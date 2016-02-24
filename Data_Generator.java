
import java.io.IOException;
import java.util.Random;

public class Data_Generator {
	
	public static void main(String [] args){
		String filePath;
		File_Manager fileMan;
		int numInts;
		int[] generatedInts;
		
		Data_Generator dGen;
		
		/*
		 * Check arguments, assign and convert
		 */
		if(args.length != 2){
			System.out.print("Please enter: Number of Ints, Output Filepath");
			return;
		}
		try{
			numInts = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e){
			System.out.print("Invalid integer value.");
			return;
		}
		filePath = args[1];
		
		/*
		 * Generate data
		 */
		dGen = new Data_Generator();
		generatedInts = dGen.GenerateNumbers(numInts);
		
		/*
		 * Write to file
		 */
		fileMan = new File_Manager();
		try{
			fileMan.WriteIntArray_toFile(generatedInts, filePath);
		}
		catch(IOException e){
			System.out.print("Unable to write to file.");
			return;
		}
	}
	
	
	public int[] GenerateNumbers(int num){
		Random rand = new Random();
	
		int[] data = new int[num];
		for(int i = 0; i < num; ++i){
			data[i] = rand.nextInt(100000);
		}
		return data;
	}
}
