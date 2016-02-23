
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class File_Manager {
	
	public File_Manager(){
	}
	
	public void WriteIntArray_toFile(int[] data, String out_path) throws IOException {
		FileWriter write = new FileWriter(out_path);
		PrintWriter printLine = new PrintWriter(write);
		
		for(int x : data){
			printLine.printf("%d ", x);
		}
		printLine.printf("\n");
		
		printLine.close();
	}
	
	public String ReadIntArray_fromFile(String in_path) throws IOException {
		FileReader reader = new FileReader(in_path);
		BufferedReader buffReader = new BufferedReader(reader);
		
		String line = null;
		
		line = buffReader.readLine();
		
		buffReader.close();
		
		return line;
	}
	
	public boolean delete(String path){
		return new File(path).delete();
	}
}
