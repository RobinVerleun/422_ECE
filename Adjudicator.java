
public class Adjudicator {

	public Adjudicator(){	
	}
	
	public boolean checkResult(int[] result){
		int current, next;
		for(int i = 0; i < result.length-1; ++i){
			current = result[i];
			next = result[i+1];
			
			if(current > next){
				return false;
			}
		}
		return true;
	}
}

