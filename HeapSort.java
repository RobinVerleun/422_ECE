
/*
 * Heapsort implementation taken from
 * https://github.com/farhankhwaja/HeapSort/blob/master/HeapSort.java
 * written by user farhankhwaja
 * 
 */
public class HeapSort 
{
    private int[] a;
    private int n;
    private int left;
    private int right;
    private int largest;
    
    public int memAccess = 0;

    // Build-Heap Function
    public void buildheap(int []a){
    	n=a.length-1;
    	for(int i=n/2;i>=0;i--){
    		maxheap(a,i);
    		memAccess += 5;
    	}
    	memAccess += 4;
    }

    // Max-Heap Function
    public  void maxheap(int[] a, int i){
    	left=2*i;
    	right=2*i+1;
  	
    	if(left <= n && a[left] > a[i]){
    		largest=left;
    		memAccess += 2;
    	}
    	else{
    		largest=i;
    		memAccess += 2;
    	}
    	
    	if(right <= n && a[right] > a[largest]){
    		largest=right;
    		memAccess += 2;
    	}
    	if(largest!=i){
    		exchange(i,largest);
    		maxheap(a, largest);
    		memAccess += 4;
    	}
    	memAccess += 18;
    }
    
    // Exchange Function
    public  void exchange(int i, int j){
    	int t=a[i];
    	a[i]=a[j];
    	a[j]=t; 
    	memAccess += 10;
    	}
    
    // Sort Function
    public int[] sort(int []a0){
    	a=a0;
    	buildheap(a);
    	memAccess += 3;
    	
    	for(int i=n;i>0;i--){
    		exchange(0, i);
    		n=n-1;
    		maxheap(a, 0);
    		memAccess += 7;
    	}
    	memAccess += 2;
    	return a;
    }
} 