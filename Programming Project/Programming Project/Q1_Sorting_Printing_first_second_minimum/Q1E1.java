import java.util.Scanner;
public class Q1E1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array1=new int[2]; //Initialize array to store pair1
		int[] array2=new int[2]; //Initialize array to store pair1

		Scanner scan=new Scanner(System.in); 
		System.out.println("Enter array Input size");
		int size=Integer.parseInt(scan.nextLine()); // Input from user for size
		int[] array=new int[size];
		System.out.println("Enter array elements"); // Input from user to get elements
		
		for(int i=0;i<size;i++)
		{
			array[i]=scan.nextInt(); // store input elements in array 
		}
		System.out.print("Input Array: [ ");
		for (int i : array) {
			System.out.print(i+" "); //Printing input array
		}
		//int[] array= {2,4,6,8,10};
		System.out.print("] ");
		System.out.println();
		 
		 
		int result[]=divide(array); //Sorting of array by Merge Sort
		System.out.print("Sorted Array: [ ");
		for (int i : result) {
			System.out.print(i+" "); //Printing sorted array
		}
		System.out.print("] ");
        System.out.println();
		
   
		int first=Integer.MAX_VALUE; //Initialize variable first to max int value
		int second=Integer.MAX_VALUE; //Initialize variable first to max int value
		for(int i=0;i<result.length-1;i++)
		{
			int diff=result[i+1]-result[i]; //Capture difference between two elements in array
			if(diff<first) //Compare difference with Max int value to get first difference between elements
			{

				first=diff;  // If difference is less than first element then capture that difference in first
				array1[0]=result[i]; // Storing pairs in array
				array1[1]=result[i+1];
			}


		}
		for(int i=0;i<result.length-1;i++)
		{
			int diff=result[i+1]-result[i]; //Capture difference between two elements in array
			if(diff<second & diff !=first) //Compare difference with Max int value to get first difference between elements and which is not equal to first minimum
			{
				second=diff;
				array2[0]=result[i];
				array2[1]=result[i+1];
			}

		}


		System.out.print("First Minimum: [ "); // Print first pair
		for (int i : array1) {
			System.out.print(i+" ");


		}
		System.out.print("] ");


		System.out.println(first); // print first minimum value
		if(second==Integer.MAX_VALUE)
		{
			second=first;
			System.out.println("No second minimum");// If all the elements of array have same difference
			
		}
		else
		{
			System.out.print("Second Minimum: [ ");// print second minimum pair

			for (int i : array2) {
				System.out.print(i+" ");			
			}
			
			System.out.print("] ");
			System.out.println(second); // print second minimum value

		}



	}
	public static int[] divide(int[] array)
	{
		if(array.length<=1)// Anchor condition
		{
			return array;
		}

		int n = array.length;
		int mid= n/2; // calculate mid
		int[] right;

		int[] left= new int[mid]; // Initialize right & left array
		if(n%2==0)
		{
			right= new int[mid]; // if even elements size = mid

		}
		else
		{
			right= new int[mid +1]; //if odd elements size = mid+1
		}

		int[] result= new int[n];

		for(int i=0; i<mid;i++)
		{
			left[i]=array[i]; // copy elements in left array

		}
		int x=0;
		for(int j=mid;j<n;j++)
		{
			if(x<right.length)
			{
				right[x]=array[j]; // copy elements in right array
				x++;
			}
		}

		left=divide(left);  // recursive function call for left partition
		right=divide(right);   // recursive function call for right partition

		result=merge(left, right); //merging of two array
		return result;




	}
	public static int[] merge(int[] left, int[] right)
	{
		int result_lenght= left.length+right.length; // declare result array with length left+right
		int[] result= new int[result_lenght];

		int i=0;
		int j=0;
		int k=0;
		while(i<left.length || j<right.length )
		{
			if(i<left.length && j<right.length)
			{
				if(left[i]<=right[j]) // compare left and right elements
				{
					result[k]=left[i]; //  if right > left, insert left elements first
					i++;
					k++;
				}

				else
				{
					result[k]=right[j]; //  if right < left, insert right elements first
					j++;
					k++;
				}
			}
			else if(i<left.length)
			{
				result[k]=left[i];  // copy remaining left elements in result array.
				i++;
				k++;
			}
			else if(j<right.length)
			{
				result[k]=right[j]; // copy remaining right elements in result array.
				j++;
				k++;
			}


		}
		return result;

	}





}
