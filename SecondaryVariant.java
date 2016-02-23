import java.io.IOException;

public class SecondaryVariant extends Thread {

	private double failure;
	private String file_o;
	private int[] data;
	public boolean failed;

	public SecondaryVariant(int[] data_, double failure_, String file_o_){
		this.failure = failure_;
		this.file_o = file_o_;
		this.data = data_;
		failed = false;
	}

//	@Override
	public void run(){

		try {

			InsertionSort iSort = new InsertionSort();
			System.loadLibrary("insertionsort");
			int[] return_array = iSort.insertionsort(data);
			
			int[] result = new int[return_array.length-1];
			for(int i = 0; i < return_array.length-1; ++i){
				result[i] = return_array[i];
			}

			double error = Math.random();
			if(error > 0.5 && error < 0.5 + return_array[return_array.length-1] * failure){
				System.out.println("Secondary Variant memory access failed.");
				failed = true;
				return;
			}

			if( !(new Adjudicator().checkResult(result))){
				System.out.println("Secondary Variant adjucator failed.");
				failed = true;
				return;
			}

			try{
				new File_Manager().WriteIntArray_toFile(result, file_o);
				return;
			} catch(IOException e){
				failed = true;
				System.out.println("Error writing result to file.");
				return;
			}

		}catch(ThreadDeath td){
			failed = true;
			System.out.println("Secondary Variant has timed out.");
			throw new ThreadDeath();
		}
	}
}