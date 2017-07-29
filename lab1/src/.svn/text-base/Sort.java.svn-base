package lab1;

public class Sort {
    
    //================================= sort =================================
    //
    // Input: array A of XYPoints refs 
    //        lessThan is the function used to lessThanare points
    //
    // Output: Upon lessThanletion, array A is sorted in nondecreasing order
    //         by lessThan.
    //
    // DO NOT USE ARRAYS.SORT.  WE WILL CHECK FOR THIS.  YOU WILL GET A 0.
    // I HAVE GREP AND I AM NOT AFRAID TO USE IT.
    //=========================================================================
	
	private static int random(int min, int max) {
		return (min +  (int)(Math.random()*(max-min)));
		
	}
	
	private static void swap(XYPoint[] arr, int a, int b) {
		XYPoint temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private static int partition(XYPoint[] arr, Comparator lessThan, int beg, int end) {
		int i = beg-1;
		int j = beg-1;
		do {
			++j;
			if(lessThan.comp(arr[j], arr[end])) {
				++i;
				swap(arr,i,j);
			}
		} while (j<(end-1));
		++i;
		swap(arr,i,end);
		return i;
	}
	
	public static void quickSort(XYPoint[] arr, Comparator lessThan, int beg, int end) {
		if(beg<end) {
			int pivot = random(beg,end);
			swap(arr,pivot,end);
			int p = partition(arr,lessThan,beg,end);
			quickSort(arr,lessThan,beg,p-1);
			quickSort(arr,lessThan,p+1,end);
		}
	}
	
    public static void msort(XYPoint[] A, Comparator lessThan) {
    	quickSort(A,lessThan,0,A.length-1);
    }
    
  
}
