import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;


// Parent class Graph that handles entire operation of program
public class Graph {

	static Scanner scan;
	static int vertices;
	static LinkedList<Edge> [] adjacencylist;
	static LinkedList<Integer>[] adjList;
	static LinkedList<Integer> adj[];
	static Graph graph;
	static int[][] mat; 
	static int[][] weighted_matrix;

	// Constructor for class Graph that takes input of total number of vertices.
	public Graph(int vertices) {
		this.vertices = vertices;
		adjacencylist = new LinkedList[vertices];
		adjList = new LinkedList[vertices];
		adj=new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i <vertices ; i++) {
			adjacencylist[i] = new LinkedList<>();
			adjList[i] = new LinkedList<>();
			adj[i] = new LinkedList(); 

		}
	}

	// Egge class that takes source, destination and weight 
	static class Edge {
		int source;
		int destination;
		int weight;

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

	}

	//method that create adjacencyList for graph by adding individual edges
	public void addEgde(int source, int destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		adjacencylist[source].addFirst(edge); 
		adjList[source].addFirst(destination);
		adj[source].add(destination); 
		//adj[destination].add(source);
	}


	// Print graph result
	public void printGraph(){
		for (int i = 0; i <vertices ; i++) {
			LinkedList<Edge> list = adjacencylist[i];
			for (int j = 0; j <list.size() ; j++) {
				System.out.println("vertex- " + i + " ===> " + "vertex- " +
						list.get(j).destination + " weight " +  list.get(j).weight);
			}
		}
	}


	// DFS Recursive Function
	public void DFSRecursion(int startVertex){
		boolean [] visited = new boolean[vertices]; // Mark all the vertices as not visited
		DFS(startVertex, visited);
	}

	public void DFS(int start, boolean [] visited){
		visited[start] = true; // Mark Visited element as true
		System.out.print(start + " ");
		for (int i = 0; i <adjList[start].size() ; i++) {
			int destination = adjList[start].get(i);
			if(!visited[destination])
				DFS(destination,visited);
		}
	}


	// BFS  Function
	void BFS(int s) 
	{ 
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[vertices]; 

		// Create a queue for BFS 
		LinkedList<Integer> queue = new LinkedList<Integer>(); 

		// Mark the current node as visited and enqueue it 
		visited[s]=true; 
		queue.add(s); 

		while (queue.size() != 0) 
		{ 
			// Dequeue a vertex
			s = queue.poll(); 
			System.out.print(s+" "); 


			// Get all adjacent vertices of the dequeued vertex s 
			Iterator<Integer> i = adjList[s].listIterator(); 
			while (i.hasNext()) 
			{ 
				int n = i.next(); 
				if (!visited[n]) 
				{ 
					visited[n] = true; //If a adjacent has not been visited, then mark it visited and enqueue it 
					queue.add(n); 
				} 
			} 
		} 
	} 


	// Mehod that check if Cycle is present in graph
	public boolean isCycle() {
		HashSet<Integer> NotVisited = new HashSet<>();
		HashSet<Integer> Visited = new HashSet<>();
		HashSet<Integer> AlreadyVisited = new HashSet<>();

		//Initially put all vertices in NotVisited set
		for (int i = 0; i <adjList.length ; i++) {
			NotVisited.add(i);
		}
		//traverse only NotVisited vertices
		for (int i = 0; i <vertices ; i++) {
			if(NotVisited.contains(i) &&
					isCycleUtil(i,NotVisited,Visited,AlreadyVisited)){
				return true;
			}
		}
		return false;
	}

	// Function that check neighbours of unvisited vertex to find if cycle present in graph
	public boolean isCycleUtil(int vertex, HashSet<Integer> NotVisited, HashSet<Integer> Visited, HashSet<Integer> AlreadyVisited){
		NotVisited.remove(vertex);
		Visited.add(vertex); // make visited

		//visit neighbors
		for (int i = 0; i <adjList[vertex].size() ; i++) {
			int adjVertex = adjList[vertex].get(i);

			//check if this vertex is present in Visited set, means cycle is found
			if (Visited.contains(adjVertex))
				return true;

			//check if this vertex is present in AlreadyVisited set, means this vertex is already done
			if (AlreadyVisited.contains(adjVertex))
				continue;

			//do traversal from this vertex
			if (isCycleUtil(adjVertex, NotVisited, Visited, AlreadyVisited))
				return true;
		}
		//if here means cycle is not found from this vertex, make if AlreadyVisited from Visited
		Visited.remove(vertex);
		AlreadyVisited.add(vertex);
		return false;
	}


	// This method detect cycle in subgraph reachable from vertex v. 
	boolean isRepeatedUtil(int v, Boolean visited[], int parent) 
	{ 
		visited[v] = true;  // mark visited
		Integer i; 

		Iterator<Integer> it = adj[v].iterator(); 
		while (it.hasNext()) 
		{ 
			i = it.next(); 

			if (!visited[i]) // If not visited then recur for adjacent
			{ 
				if (isRepeatedUtil(i, visited, v)) 
					return true; 
			} 

			else if (i != parent) // Cycle is present if adjacent already visted and not parent
				return true; 
		} 
		return false; 
	} 

	// method to check if tree present in graph
	Boolean isTree() 
	{ 
		Boolean visited[] = new Boolean[vertices]; 
		for (int i = 0; i < vertices; i++) 
			visited[i] = false; // Mark all the vertices as not visited 

		if (isRepeatedUtil(0, visited, -1)) //This method detect cycle in subgraph reachable from vertex v and all reachable vertices from 0.
			return false; 

		for (int u = 0; u < vertices; u++) // If not reachable from 0 then no cycle present
			if (!visited[u]) 
				return false; 

		return true; 
	} 

	//Method to check if graph is Bipartite
	boolean isBipartite(int G[][],int src) 
	{ 
		int ver_array[] = new int[vertices]; 
		for (int i=0; i<vertices; ++i) 
			ver_array[i] = -1; // initial value -1

		// Assign first value as 1 to source 
		ver_array[src] = 1; 


		LinkedList<Integer>q = new LinkedList<Integer>();  // Create a queue 
		q.add(src); 

		// Run while there are vertices in queue
		while (q.size() != 0) 
		{ 
			int u = q.poll(); 
            if (G[u][u] == 1) 
		 		return false; // Return false if there is a self-loop    
			for (int v=0; v<vertices; ++v) 
			{ 			
				if (G[u][v]==1 && ver_array[v]==-1)  	// An edge from u to v exists and destination v with initial values as -1
				{ 	
					ver_array[v] = 1-ver_array[u];  // Assign different value to this adjacent v of u 
					q.add(v); 
				} 
				else if (G[u][v]==1 && ver_array[v]==ver_array[u]) // An edge from u to v exists and destination v have same value as u 
					return false; 
			} 
		} 
		return true; 
	}

	
	// Function that implements Dijkstra's single source shortest path algorithm for a graph represented using adjacency matrix representation 
	private static void dijkstra(int[][] adjacencyMatrix, 
			int startVertex) 
	{ 
		int mat_vertices = adjacencyMatrix[0].length; 
		int[] distance = new int[mat_vertices]; // distance[i] will hold the shortest distance from src  
		boolean[] done = new boolean[mat_vertices];  	// done[i] will true if vertex i is included in shortest path tree 

		for (int i = 0; i < mat_vertices;i++) 
		{ 
			distance[i] = Integer.MAX_VALUE; //Initial Max value to all paths
			done[i] = false; 
		} 

		distance[startVertex] = 0; 
		int[] parents = new int[mat_vertices]; 
		parents[startVertex] = -1 ; 

		for (int i = 1; i < mat_vertices; i++) 
		{ 
			int nearestVertex = -1; 
			int shortestDistance = Integer.MAX_VALUE; 
			for (int m = 0;m < mat_vertices;m++) 
			{ 
				if (!done[m] && distance[m] <shortestDistance)  // iterate on vertices not yet done
				{ 
					nearestVertex = m; 
					shortestDistance = distance[m]; 
				} 
			} 

			if(nearestVertex!=-1)
			{
			done[nearestVertex] = true;// picked vertices as done

			// Update dist value of the  adjacent vertices  
			for (int k = 0;k < mat_vertices;k++)  
			{ 
				int edge = adjacencyMatrix[nearestVertex][k]; 
				if (edge > 0 && ((shortestDistance + edge) < distance[k]))  
				{ 
					parents[k] = nearestVertex; 
					distance[k] = shortestDistance +edge; 
				} 
			} 
			}
		} 

		printdijkstra(startVertex, distance, parents); 
	} 

	// function to print result of dijkstra
	private static void printdijkstra(int startVertex, int[] distances, int[] parents) 
	{ 
		int mat_vertices = distances.length; 
		for (int i = 0;i < mat_vertices;i++)  
		{ 
			if (i != startVertex)  
			{ 
				if(distances[i]!=Integer.MAX_VALUE)
				{
				System.out.print("Shortes path from "+startVertex+" to " + i + " is of weight " + distances[i] + "  with Predecessors ");
				printPredecessors(i, parents); // function to print predecessors
				System.out.println();
				}
				else
				{
					System.out.print("No path exist from "+startVertex+" to " + i);
					System.out.println();
				}
			} 
		} 
	} 

	// Function to print all predecessors
	private static void printPredecessors(int currentVertex, int[] parents) 
	{ 
		if (currentVertex == -1) 
		{ 
			return; 
		} 
		printPredecessors(parents[currentVertex], parents); 
		System.out.print(currentVertex + " "); 
	} 
	
	
	
	// Takes input for graph creating from User
	public static  void EdgeInput()
	{
		int vertex_no=0;
		int edge_no=0;

		System.out.println("Enter the total number of Vetex in Undirected Graph");
		boolean number=false;
		while(!number)
			try
		{

				vertex_no=scan.nextInt();
				scan.nextLine();
				//choice=Integer.parseInt(scan.nextLine());
				number=true;
		}
		catch(Exception e)
		{
			System.out.println("Please enter Integer value");
			scan.nextLine();
		}


		System.out.println("Enter the total number of Edges in Undirected Graph");
		boolean number1=false;
		while(!number1)
			try
		{

				edge_no=scan.nextInt();
				scan.nextLine();
				//choice=Integer.parseInt(scan.nextLine());
				number1=true;
		}
		catch(Exception e)
		{
			System.out.println("Please enter Integer value");
			scan.nextLine();
		}
		//int sourceVertex = 0;



		mat=new int[vertex_no][vertex_no];
		weighted_matrix=new int[vertex_no][vertex_no];


		graph = new Graph(vertex_no);
		System.out.println("The vertex in graph are from 0 to "+(vertex_no-1));
		System.out.println("Enter the of Edges in Undirected Graph in the form of \"source destination weight\" ");
		for(int i=0;i<edge_no;i++)
		{

			int a=scan.nextInt(); // source
			int b=scan.nextInt(); // destination
			int c=scan.nextInt(); // weight

			graph.addEgde(a, b, c);
			scan.nextLine();
			mat[a][b]=1; // entering values in adjacency matrix
			weighted_matrix[a][b]=c; // entering values of weight in 2D matrix.

		}

		System.out.println("The adjacency matrix of entered graph is");
		for(int i=0;i<vertex_no;i++)
		{
			for(int j=0;j<vertex_no;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();	

		}

		graph.printGraph(); // Print graph result
	}


	// Main Function
	public static void main(String[] args) {
		int choice=0;
		while(choice!=8)
		{
			System.out.println();
			System.out.println();
			System.out.println("1. Create a Directed Graph using the inputs taken from console.\r\n" + 
					"2. Perform DFS Traversal on the Graph.\r\n" + 
					"3. Perform BFS Traversal on the Graph.\r\n" + 
					"4. Find Shortest Path from Source to all vertices using Dijkstra’s shortest path\r\n" + 
					"Algorithm.\r\n" + 
					"5. Detect if there is a Cycle in the graph.\r\n" + 
					"6. Check if the graph is Bipartite or not.\r\n" + 
					"7. Check if Graph is a tree or not\r\n" + 
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


			switch(choice)
			{
			case 1:

				try {
					EdgeInput();	
				}
				catch(ArrayIndexOutOfBoundsException exception)
				{
					System.out.println("Please enter correct values");
				}
				break;
			case 2:

				if(graph !=null)
				{
					int sourceDFS=0; 	
					System.out.println("Enter the source vetex in for DFS from 0 to "+ (vertices-1) );
					boolean number2=false;
					while(!number2)
						try
					{

							sourceDFS=scan.nextInt();
							scan.nextLine();
							//choice=Integer.parseInt(scan.nextLine());
							number2=true;
					}
					catch(Exception e)
					{
						System.out.println("Please enter Integer value");
						scan.nextLine();
					}

					System.out.println("DFS Traversal on the Graph ");

					try
					{
						graph.DFSRecursion(sourceDFS);

					}
					catch(ArrayIndexOutOfBoundsException exception)
					{
						System.out.println("Please enter source vertext from 0 to "+ (vertices-1));
					}


					System.out.println();
				}
				else
				{
					System.out.println("Please create a graph");
				}


				break;

			case 3:

				if(graph!=null)
				{
					System.out.println("Enter the source vetex in for BFS from 0 to "+ (vertices-1));

					int sourceBFS=0;
					boolean number3=false;
					while(!number3)
						try // to check integer
					{

							sourceBFS=scan.nextInt();
							scan.nextLine();
							//choice=Integer.parseInt(scan.nextLine());
							number3=true;
					}
					catch(Exception e)
					{
						System.out.println("Please enter Integer value");
						scan.nextLine();
					}


					System.out.println("BFS Traversal on the Graph ");

					try // to check array out of bound if wrong input is inserted
					{
						graph.BFS(sourceBFS);

					}
					catch(ArrayIndexOutOfBoundsException exception)
					{
						System.out.println("Please enter source vertext from 0 to "+ (vertices-1));
					}
					catch(InputMismatchException exception)
					{
						System.out.println("Please enter source vertext from 0 to "+ (vertices-1));
					}

					System.out.println();
				}

				else
				{
					System.out.println("Please create a graph");
				}

				break;

			case 4:

				if(graph!=null)
				{
					System.out.println("Enter the source vertex for Dijkstra’s shortest path algorithm from 0 to "+ (vertices-1));
					int sourceVertex=0;

					boolean number4=false;
					while(!number4)
						try
					{

							sourceVertex=scan.nextInt();
							scan.nextLine();
							//choice=Integer.parseInt(scan.nextLine());
							number4=true;
					}
					catch(Exception e)
					{
						System.out.println("Please enter Integer value");
						scan.nextLine();
					}
					
					
					try
					{
						dijkstra(weighted_matrix, sourceVertex);
						//graph.dijkstra(weighted_matrix, sourceVertex); 
						//dijkstra(weighted_matrix, sourceVertex);

					}
					catch(ArrayIndexOutOfBoundsException exception)
					{
						System.out.println("Please enter source vertext from 0 to "+ (vertices-1));
					}

				}
				else
				{
					System.out.println("Please create a graph");
				}

				break;

			case 5:

				if(graph!=null)
				{
					if (graph.isCycle()) 
						System.out.println("Cycle is present in graph"); 
					else
						System.out.println("No cycle in graph"); 
				}
				else
				{
					System.out.println("Please create a graph");
				}

				break;

			case 6:

				if(graph!=null)
				{
					if (graph.isBipartite(mat, 0)) 
						System.out.println("Graph is Bipartite"); 
					else
						System.out.println("Graph is not Bipartite"); 
				}
				else
				{
					System.out.println("Please create a graph");
				}



				break;

			case 7:

				if(graph!=null)
				{
					if (graph.isTree()) 
						System.out.println("Graph is Tree"); 
					else
						System.out.println("Graph is not Tree"); 
				}
				else
				{
					System.out.println("Please create a graph");
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
