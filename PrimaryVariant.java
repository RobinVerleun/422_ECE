import java.io.IOException;

public class PrimaryVariant extends Thread {

	private double failure;
	private String file_o;
	private int[] data;
	public boolean success;

	public PrimaryVariant(int[] data_, double failure_, String file_o_){
		this.failure = failure_;
		this.file_o = file_o_;
		this.data = data_;
		success = false;
	}

	//@Override
	public void run(){

		try {
			
			// Calculate the array
			HeapSort hSort = new HeapSort();
			int[] result = hSort.sort(data);

			// Check if mem access fail
			double error = Math.random();
			if(error > 0.5 && error < 0.5 + hSort.memAccess * failure){
				System.out.println("Primary Variant memory access failed - moving to secondary variant.");
				return;
			}

			if( !(new Adjudicator().checkResult(result))){
				System.out.println("Primary Variant adjucator failed - moving to secondary variant.");
				return;
			}

			try{
				new File_Manager().WriteIntArray_toFile(result, file_o);
				success = true;
				return;
			} catch(IOException e){
				System.out.println("Error writing result to file.");
				return;
			}

		}catch(ThreadDeath td){
			System.out.println("Primary Variant has timed out.");
			throw new ThreadDeath();
		}
	}
}