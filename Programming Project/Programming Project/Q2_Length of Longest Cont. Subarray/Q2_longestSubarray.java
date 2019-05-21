import java.util.Scanner;

public class Q2_longestSubarray {

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


	public static double calculatemedian(int[]array, int low, int high)
	{
		int[] result= new int[(high-low)+1]; // Declare result array of input lengths 
		int x=0;
		double median=0;

		for(int i=low;i<=high;i++)
		{

			result[x]=array[i]; // copy elements in result array
			x++;
		}

		int result_length= result.length; //Capturing length of array
		int mid= result_length/2; // calculate mid
		if (result_length%2==0)
		{

			median = ((double)result[mid] + (double)result[mid - 1])/2;  // if result array is of even length,median = mean of two middle elements
		}
		else
		{
			median = (double)result[mid]; // if result array is of odd length, median = middle element 
		}

		return median;

	}

	/* Driver program to test above function */
	public static void main(String[] args)  
	{ 
		Scanner scan=new Scanner(System.in); 
		System.out.println("Enter array Input size:");
		int size=Integer.parseInt(scan.nextLine()); // Input from user for size
		int[] array=new int[size];
		System.out.println("Enter array elements:"); // Input from user to get elements

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

		System.out.println("Enter a number that needs to be greater than or equal to median of elements in sub array:");
		int number=scan.nextInt(); // store number

		int[] arr=divide(array); // sort the array using merge sort

		// To calculate median of array, array must be sorted in ascending form

		int n = arr.length; 

		int length_subarray=0;
		double median=calculatemedian(arr,0,(n-1)); // calculate median of sorted array
		if(number<=median)
		{
			length_subarray=arr.length; // if given number is less than median of original array, the longest subarray will of same length of original array.
		}
		else
		{
			//int mid= n/2;
			for(int i=1;i<n;i++)
			{
				double x=calculatemedian(arr,i,(n-1)); // if number is greater than median of original array, calculate the median of remaining array with one element less every time
				if(x>=number)
				{
					length_subarray=(((n-1)-i)+1); // If we find median greater than number, store the length of subarray
					break;

				}

			}
		}
		
		System.out.println("Length of longest subarray: " + length_subarray); // print the length of longest subarray
	
	}
}
