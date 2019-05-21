import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
//This is main class which handles all the bonary tree operations
public class BinarySearchTree {

	
	public static int lenght;
	static Scanner scan;
	public static Node root;
	public static HashMap<String,Integer> map;
	//inserting a new node to tree
	public void insert(String str_value)
	{
		Node new_node=new Node(str_value);
		if(root==null) //if root is null add new node as root
		{
			root= new_node;
		}
		else
		{
			Node temp=root;
			Node parent;
			while(true)
			{
				parent=temp;
				int compare=str_value.compareTo(temp.str_value);
				if(compare<0)
				{
					temp=temp.left;
					if(temp==null)
					{
						parent.left=new_node;
						return;
					}

				}
				else
				{
					temp=temp.right;
					if(temp==null)
					{
						parent.right=new_node;
						return;
					}
				}			
			}
		}	
	}
	
	
	//delete Element from BST
	public void delete(String element)
	{
		root=deleteElement(root,element);

	}
	
	
	
	//delete element is recursive function which delete given input
	public Node deleteElement(Node root,String element)
	{
		if(root==null)// anchor condition
		{
			System.out.println("element not found");
			return root;
		}
		if(element.compareTo(root.str_value)<0)   //string value comparison
		{
			root.left=deleteElement(root.left,element);
		}
		else if(element.compareTo(root.str_value)>0)  //string value comparison
		{
			root.right=deleteElement(root.right,element);
		}
		else
		{
		
			if(root.left==null)
			{
				System.out.println("element deleted successfully");
				return root.right;
				
			}
			else if(root.right==null)
			{
				System.out.println("element deleted successfully");
				return root.left;
				
			}

			root.str_value=getInorder(root.right); //Get Inorder tree transversal from right node

			root.right=deleteElement(root.right,root.str_value);
		}
		
		return root;
	}
	
	
	// get inorder tree traversal while deleting the element
		public String getInorder(Node node)
		{
			String min=node.str_value;
			while(node.left!=null)
			{
				min=root.left.str_value;
				node=node.left;
			}
			return min;

		}
		
		
	
	//Recursive function to calculate the height of BST
	public static int calculateHeight(Node root)
	{
		
		if(root==null)
		{
			return 0;
			
		}
		else
		{
			
				int left=calculateHeight(root.left); // calls same function with left child
				int right=calculateHeight(root.right); // calls same function with right child
				if(left>right)
				{
					return (left+1);
				}
				else
				{
					return (right+1);
					
				}
		}	
	}
	

	
	
	
	//print inorder tree traversal 
	public void inOrder(Node node)
	{
		if(node!=null)
		{
			inOrder(node.left);
			System.out.print(node+ " ");
			inOrder(node.right);

		}
	}

	//print preorder tree traversal
	public void preOrder(Node node)
	{
		if(node!=null)
		{   
			System.out.print(node+ " ");
			preOrder(node.left);
			preOrder(node.right);

		}
	}

	//print postorder tree traversal
	public void postOrder(Node node)
	{
		if(node!=null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node+" ");

		}
	}

	
	// Store nodes of given BST in sorted order 
	public static void storeBalance(Node node,ArrayList<Node> node_list)
	{
		if(node==null)
		{
			return;
		}
		storeBalance(node.left,node_list);
		node_list.add(node);
		storeBalance(node.right,node_list);

	}
	
	
	

	
	//construct left and right subtree it is balanced
	public static Node buildTreeUtil(ArrayList<Node> nodes, int start,int end)  
	{ 
		
		if (start > end) 
			return null; 

		// Get the middle element and make it root 
		int mid = (start + end) / 2; 
		Node node = nodes.get(mid); 

		//Using index in Inorder traversal, construct left and right subtress 
		node.left = buildTreeUtil(nodes, start, mid - 1); 
		node.right = buildTreeUtil(nodes, mid + 1, end); 
		return node; 
	} 
	
	
	

	// This functions converts an unbalanced BST to 
	// a balanced BST 
	public Node buildTree(Node root)  
	{ 
		// Store nodes of given BST in sorted order 
		ArrayList<Node> nodes = new ArrayList<Node>(); 
		storeBalance(root, nodes); 

		// Constucts BST from nodes[] 
		int n = nodes.size(); 
		return buildTreeUtil(nodes, 0, n - 1); 
	} 
	
	
	
	//check if binary tree isHeap
	public static boolean isHeap(Node node)
	{
		boolean flag=true;
		if(node==null)// anchor condition
			return true;
		if(root.left!=null) //check until left node is null
		{
			if(root.str_value.compareTo(root.left.str_value)<0)
			{
				flag=false;
				return flag;
			}
			else
			{
				isHeap(node.left);
			}
		}
		if(root.right!=null)  //check until right node is null
		{
			if(root.str_value.compareTo(root.left.str_value)>0)
			{
				flag=false;
				return flag;
			}
			else
			{
				isHeap(node.right);
			}
		}
		return flag;
	}
	
	// print all three transversal of tree
	public static void printTree(BinarySearchTree tree)
	{
	    System.out.println("Balanced Tree");
		System.out.print("InOrder ");
	    tree.inOrder(tree.root);
		System.out.println();
		System.out.print("PreOrder ");
		tree.preOrder(tree.root);
		System.out.println();
		System.out.print("PostOrder ");
		tree.postOrder(tree.root);
		System.out.println();
		
	}

	// put inOrder transversal in arrayList
	public static ArrayList get_inOrder(Node node,ArrayList list)
	{
		
		if(node!=null)
		{
			get_inOrder(node.left,list);
			
			list.add(node.str_value);
			map.put(node.str_value,0);
			get_inOrder(node.right,list);

		}
		return list;
	}
	
	
	// Quick sort algorithm to sort char array
	// finds pivot element to partition the char array
	public static char[] quick_sort(char[] array, int low, int high)

		{
			if(low<high)
			{
				int p=partition(array,low,high);
				quick_sort(array,low,p-1);
				quick_sort(array,p+1,high);
				
			}
			return array;

		}
	
	// partition char array according to pivot condition
	public static int partition(char[] array, int low, int high)
		{
			Random rand= new Random();
			int random=rand.nextInt((high-low)+1)+low;
			char temp=array[high];
			array[high]=array[random];
			array[random]=temp; // swap pivot element with randomly generated array
			char pivot=array[high];
			int i=low-1;
			for(int j=low;j<=high-1;j++)
			{
				if(array[j]<=pivot)
				{
					i++;
					char tempq=array[i];
					array[i]=array[j];
					array[j]=tempq;
					
				}
			}	
			char tempqb=array[high];
			array[high]=array[i+1];
			array[i+1]=tempqb;
			return i+1;
		}
	
	
	// function that checks the anagram of BST
	public static void checkAnagram(String a, String b)
	{
		// If two string are not equal in length no need to check for anagram condition	
		if(a.length()!=b.length())
		{
			
		}
		else
		{
			char[] char_a=a.toCharArray(); // convert string to char array
			char[] char_b=b.toCharArray();
			char_a=quick_sort(char_a,0,char_a.length-1); // sort the char array
			char_b=quick_sort(char_b,0,char_b.length-1);
			String charToString_a=String.copyValueOf(char_a); // covert again to string
			String charToString_b=String.copyValueOf(char_b);
			if(charToString_a.equals(charToString_b)) // After sorted if their length is equal then they are anagrams
			{
				if(map.containsKey(a)) 
				{
					map.put(a, map.get(a) + 1); //Put value in hashmao
				}

				if(map.containsKey(b))
				{
				map.put(b, map.get(b) + 1);
				}		
			}				
		}	
	}
	
	//Check anagram - get in order traversal in array and send two strings to check if anagram
	public static void isAnagram()
	{
		ArrayList<String> list=new ArrayList<>();
		list=get_inOrder(root,list);
	
		for(int i=0;i<list.size()-1;i++)
		{
			for(int j=i+1;j<list.size();j++)
			{
			checkAnagram(list.get(i),list.get(j));
			}
		}
	
		
		
	}

	public static boolean isValidInt()
	{
		boolean number=false;
		while(!number)
			try
			{
		
		
		    lenght=scan.nextInt();
		    
			scan.nextLine();
			//choice=Integer.parseInt(scan.nextLine());
			number=true;
			}
			catch(Exception e)
			{
				System.out.println("Please enter Integer value");
				scan.nextLine();
			}
			
		return number;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree tree=new BinarySearchTree();

		map=new HashMap<String,Integer>();
		int choice=0;

		while(choice!=8)
		{
			System.out.println();
			System.out.println();
			System.out.println("1. Create a Balanced Binary Search Tree using the input Strings entered from\r\n" + 
					"console.\r\n" + 
					"2. Find the length of the Balanced Binary Search Tree.\r\n" + 
					"3. Add an element to BST\r\n" + 
					"4. Delete an element from BST.\r\n" + 
					"5. Print Elements of the BST.\r\n" + 
					"6. Check if BST is Max Binary Heap or not.\r\n" + 
					"7. Find the number of Anagrams for each input string in the BST.\r\n" + 
					"8. Exit");

			scan=new Scanner(System.in);
			System.out.println("Select choice");
			boolean number=false;
			while(!number)
			try
			{
		
			choice=scan.nextInt();
			scan.nextLine();
			//choice=Integer.parseInt(scan.nextLine());
			number=true;
			}
			catch(Exception e)
			{
				System.out.println("Please enter Integer value");
				scan.nextLine();
			}
			

			// Switch case to take user choice
			switch(choice)
			{
			case 1:
				System.out.println("Enter the total number of string elements in binary search tree");
				if(isValidInt())
				{
				
								
				System.out.println("Enter the elements in binary search tree");
				
				
			    //String word = scan.next();
			    
				for(int m=0;m<lenght;m++)
				{
					while (!scan.hasNext("[A-Za-z]+")) {
				        System.out.println("Please enter a string element");
				        scan.next();
				    }
					String word = scan.next();
					tree.insert(word);
					//tree.insert(scan.nextLine().toString());// inserts node in BST

				}
				tree.root=tree.buildTree(root); // Balance the binary tree
				System.out.println("Elements successfully added to binary search tree");
				break;
				}
			break;
			case 2:

				int i=calculateHeight(root);//calculate height of binary tree
				if(i==0)
				{
					System.out.println("No nodes are added yet to calculate height");
				}
				else
				{
				System.out.println("Height of BST is "+(i-1) );
				}
				break;

			case 3:
				System.out.println("Enter the string element to add in binary search tree");
				while (!scan.hasNext("[A-Za-z]+")) {
			        System.out.println("Please enter a string element");
			        scan.next();
			    }
				String word = scan.next();
				tree.insert(word);
				//tree.insert(scan.nextLine().toString()); 
				tree.root=tree.buildTree(root);  // Balance the binary tree
				System.out.println("Element successfully added to binary search tree");

				break;
				
			case 4:
				System.out.println("Enter the string element to delete from binary search tree");
				tree.delete(scan.nextLine().toString()); //delete selected node from BST
				tree.root=tree.buildTree(root);
					
				break;
				
			case 5:

				printTree(tree); //print BST
				break;
				
			case 6:

				boolean heap=isHeap(root); // check if BST is heap
				if(heap == true)
				{
					System.out.println("Binary search tree is max heap");
				}
				else
				{
					System.out.println("Binary search tree is not max heap");	
				}
				break;
				
			case 7:

				isAnagram(); //check if BST contains anagram
				System.out.println("Anagrams");
				for(Map.Entry<String, Integer> entry:map.entrySet())
				{
					
					System.out.println(entry.getKey() + " - " + entry.getValue());
				}
				
				break;
				
			case 8:
				System.out.println("Exited from program");
				break;
				
			default:
				System.out.println("Please enter valid choice");
				break;
			}
		}

	}

}

//Node class that stores left child , right child and string value
class Node
{

	String str_value;

	Node left;
	Node right;

	public Node(String str_value)
	{
		this.str_value=str_value;

	}

	public String toString()
	{
		return this.str_value;
	}



}