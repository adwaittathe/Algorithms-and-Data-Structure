import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// Class chess is a main class that will handel entire piece movements 
public class Chess {

	static int row;
	static char column;
	static HashMap<Integer,String> possiblePositions; 
	static HashMap<String,Integer> occupiedPositions;
	static Scanner scan=new Scanner(System.in);
	static ArrayList valid_list;
	static int user_choice;
	
	//fill arraylist with all possible inputs in chess
	public static void fill_valid_list()
	{
		
		for(int i=1;i<=8;i++)
		{
			for(char x='a'; x<='h';x++)
			{
				valid_list.add(String.valueOf(i)+String.valueOf(x));
				
			}
		}
		
	}
	public static void main(String[] args) {

		possiblePositions=new HashMap<Integer,String>();
		occupiedPositions=new HashMap<String,Integer>();

		valid_list=new ArrayList();
		fill_valid_list();
		System.out.println("You can select one chess piece from the following: "
				+ "1.Rook "
				+ "2.Knight "
				+ "3.Bishop "
				+ "4.Queen "
				+ "5.King "
				+ "6.Pawn ");
		
		
		piece_choices();

	}
	
	//piece_choice() takes input from user to get piece name
	public static void piece_choices()
	{
		System.out.println("Enter the piece name:");

		scan=new Scanner(System.in);
		String piece= scan.nextLine().toLowerCase();
		switch(piece)
		{
		case "rook":
			rookMoves();
			break;
		case "knight":
			knightMoves();
			break;
		case "bishop":
			bishopMoves();
			break;
		case "queen":
			queenMoves();
			break;
		case "king":
			kingMoves();
			break;
		case "pawn":
			pawnMoves();

			break;
		default:
			System.out.println("Please enter a correct piece name");
			piece_choices();
			break;

		}
	}
	
	


	
	//rookMoves() generates the possible number of moves for rook from its initial position
	public static void rookMoves()
	{
		System.out.println("Enter the position of rook");
		String position= scan.nextLine().toLowerCase();
		if(valid_list.contains(position))
		{


			if(occupiedPositions.containsKey(position))// check if the input initial position is already filled 
			{
				System.out.println("This position is already filled. Please select different position");
				rookMoves();
				return;
			}

			Rook rook=new Rook();
			ArrayList<String>position_list=rook.generate_positions(position); // returns all the possible moves of rook
			int i=0;
			
			row=Integer.parseInt(position.substring(0,1));
			column=position.charAt(1);
			possiblePositions.clear();
			char col_right='k';
			char col_left='a';
			int row_up=10;
			int row_down=0;
			position_list.remove(String.valueOf(row)+String.valueOf(column)); // remove current position from possible moves list
			
			//This loop restricts moves of rook beyond already filled row and position. 
			for(Map.Entry<String, Integer>entry:occupiedPositions.entrySet())
			{
				if(row==Integer.parseInt(entry.getKey().substring(0,1).trim())) // if row is same as current position then we need to check for columns
				{
					if(entry.getKey().charAt(1)>column)
					{
						if(entry.getKey().charAt(1)<=col_right)
						{
							col_right=entry.getKey().charAt(1); //store right column beyond which rook cannot move
						}
					}
					else
					{
						if(entry.getKey().charAt(1)>=col_left)
						{
							col_left=entry.getKey().charAt(1); //store right left beyond which rook cannot move
						}

					}

				}
				if(column==entry.getKey().charAt(1)) // if column is same as current position then we need to check for rows 
				{
					if(Integer.parseInt(entry.getKey().substring(0,1))>row)
					{ 
						if(Integer.parseInt(entry.getKey().substring(0,1))<row_up)
						{
							row_up=Integer.parseInt(entry.getKey().substring(0,1)); //store up row beyond which rook cannot move
						}
					}
					else
					{
						if(Integer.parseInt(entry.getKey().substring(0,1))>row_down)
						{
							row_down=Integer.parseInt(entry.getKey().substring(0,1)); //store down row beyond which rook cannot move
						}

					}

				}



			}


			for(String occupied:position_list)
			{
				int rook_row=Integer.parseInt(occupied.substring(0,1));
				char rook_column=occupied.charAt(1);		
				if(!occupiedPositions.containsKey(occupied)) //removes already occupied position while storing possible positions
				{
					if(rook_row==row)
					{
						if(rook_column<col_right && rook_column>=col_left)
						{
							possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column)); //store possible position with column restriction

						}
					}
					else if(rook_column==column)
					{
						if(rook_row<row_up && rook_row>row_down)
						{
							possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column)); //store possible position with row restriction
						}
					}
					else
					{
						possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column));
					}
				}
			}

			printSelected("rook");


			
		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			rookMoves();
		}
		

	}
	
	//rookMoves() generates the possible number of moves for queen from its initial position
	public static void queenMoves()
	{
		System.out.println("Enter the position of queen");
		String position= scan.nextLine().toLowerCase();

		if(valid_list.contains(position))
		{

		if(occupiedPositions.containsKey(position))// check if the input initial position is already filled
		{
			System.out.println("This position is already filled. Please select different position");
			rookMoves();
			return;
		}

		Queen queen=new Queen();
		ArrayList<String>position_list=queen.generate_positions(position); // returns all the possible moves
		int i=0;
		
        HashMap<String,Integer> bishop_possible=new HashMap();
		
        
        // Bishop logic to remove possible positions beyond already filled positions.
		for(String occupied:position_list)
		{
			
			int b_row=Integer.parseInt(occupied.substring(0,1));
			char b_column=occupied.charAt(1);	
			bishop_possible.put(String.valueOf(b_row)+String.valueOf(b_column),0);	
			
			
		}
		for(Map.Entry<String, Integer>entry:occupiedPositions.entrySet())
		{
		
			char bishop_col=entry.getKey().charAt(1);
			int bishop_row=Integer.parseInt(entry.getKey().substring(0,1));
			if(bishop_possible.containsKey(entry.getKey()))
			{
				if(bishop_row>row)
				{
					if(bishop_col>column)
					{
						for(int m=(bishop_row+1);m<=8 && bishop_col <='h';m++)
						{	bishop_col++;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
					}
					else
					{
						for(int m=(bishop_row+1);m<=8 && bishop_col >='a';m++)
						{	bishop_col--;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
						
					}
				}
				else
				{
					if(bishop_col>column)
					{
						for(int m=(bishop_row-1);m>=0 && bishop_col <='h';m--)
						{	bishop_col++;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
					}
					else
					{
						for(int m=(bishop_row-1);m>=0 && bishop_col >='a';m--)
						{	bishop_col--;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
						
					}
				}
			}
			
		}
		
		
		row=Integer.parseInt(position.substring(0,1));
		column=position.charAt(1);
		possiblePositions.clear();
		
		
		// Rook logic to remove possible positions beyond already filled positions.
		char col_right='k';
		char col_left='a';
		int row_up=10;
		int row_down=0;
		position_list.remove(String.valueOf(row)+String.valueOf(column));
		//This loop restricts moves of queen beyond already filled row and position. 
		for(Map.Entry<String, Integer>entry:occupiedPositions.entrySet())
		{
			int m=Integer.parseInt(entry.getKey().substring(0,1).trim());
			if(row==Integer.parseInt(entry.getKey().substring(0,1).trim()))
			{
				if(entry.getKey().charAt(1)>column)
				{
					if(entry.getKey().charAt(1)<=col_right)
					{
						col_right=entry.getKey().charAt(1);
					}
				}
				else
				{
					if(entry.getKey().charAt(1)>=col_left)
					{
						col_left=entry.getKey().charAt(1);
					}
				}
			}
			if(column==entry.getKey().charAt(1))
			{
				if(Integer.parseInt(entry.getKey().substring(0,1))>row)
				{
					if(Integer.parseInt(entry.getKey().substring(0,1))<row_up)
					{
						row_up=Integer.parseInt(entry.getKey().substring(0,1));
					}
				}
				else
				{
					if(Integer.parseInt(entry.getKey().substring(0,1))>row_down)
					{
						row_down=Integer.parseInt(entry.getKey().substring(0,1));
					}
				}
			}
		}


		for(String occupied:position_list)
		{
			int rook_row=Integer.parseInt(occupied.substring(0,1));
			char rook_column=occupied.charAt(1);		
			if(!occupiedPositions.containsKey(occupied)) //removes already occupied position while storing possible positions
			{
				if(rook_row==row)
				{
					if(rook_column<col_right && rook_column>=col_left)
					{
						possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column)); //store possible position with column restriction


					}
				}
				else if(rook_column==column)
				{
					if(rook_row<row_up && rook_row>row_down)
					{
						possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column)); //store possible position with row restriction

					}
				}
				else
				{
					possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column));
				}

			}
		}

		printSelected("queen");
		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			queenMoves();
			
		}

	}


	//bishopMoves() generates the possible number of moves for bishop from its initial position
	public static void bishopMoves()
	{
		System.out.println("Enter the position of Bishop");
		String position= scan.nextLine().toLowerCase();
		
		if(valid_list.contains(position))
		{	
			
		if(occupiedPositions.containsKey(position))// check if the input initial position is already filled 
		{
			System.out.println("This position is already filled. Please select different position");
			rookMoves();
			return;
		}

		Bishop bishop=new Bishop();
		ArrayList<String>position_list=bishop.generate_positions(position);// returns all the possible moves 
        int i=0;
		row=Integer.parseInt(position.substring(0,1));
		column=position.charAt(1);
		possiblePositions.clear();
		

		HashMap<String,Integer> bishop_possible=new HashMap();
		
		for(String occupied:position_list)
		{
			
			int b_row=Integer.parseInt(occupied.substring(0,1));
			char b_column=occupied.charAt(1);	
			bishop_possible.put(String.valueOf(b_row)+String.valueOf(b_column),0);	
			
			
		}
		for(Map.Entry<String, Integer>entry:occupiedPositions.entrySet())
		{
		
			char bishop_col=entry.getKey().charAt(1);
			int bishop_row=Integer.parseInt(entry.getKey().substring(0,1));
			if(bishop_possible.containsKey(entry.getKey()))// if one of the possible position is already occupied
			{
				if(bishop_row>row)
				{
					if(bishop_col>column)
					{
						for(int m=(bishop_row+1);m<=8 && bishop_col <='h';m++) // East-north direction 
						{	bishop_col++;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
					}
					else
					{
						for(int m=(bishop_row+1);m<=8 && bishop_col >='a';m++) // North-west direction
						{	bishop_col--;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
						
					}
				}
				else
				{
					if(bishop_col>column)
					{
						for(int m=(bishop_row-1);m>=0 && bishop_col <='h';m--) // South-east direction
						{	bishop_col++;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
					}
					else
					{
						for(int m=(bishop_row-1);m>=0 && bishop_col >='a';m--) // South-west direction
						{	bishop_col--;										
						    position_list.remove(String.valueOf(m)+String.valueOf(bishop_col));
						}
						
					}
				}
			}
			
		}
	
		for(String occupied:position_list)
		{
			int rook_row=Integer.parseInt(occupied.substring(0,1));
			char rook_column=occupied.charAt(1);		
			if(!occupiedPositions.containsKey(occupied)) //removes already occupied position while storing possible positions
			{
				
					possiblePositions.put(i++,String.valueOf(rook_row)+String.valueOf(rook_column));		
			}
		}
		printSelected("bishop");

		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			bishopMoves();
		}


	}
	
	//validateChoice() validates Int input for possible positions 
	public static boolean validateChoice(String a,Integer ch)
	{
		boolean flag=true;
		try
		{
			int choice=Integer.parseInt(a);
			if(choice>=0 && choice<=ch )
			{
				
			}
			else
			{
				flag=false;
			}
			
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	
	}
	
	
	// check if input is integer and in between possible position values
	public static boolean isValidInt(Integer ch)
	{
		boolean number=false;
		while(!number)
			try
			{
		
		
		    user_choice=scan.nextInt();
		    
			scan.nextLine();
			if(user_choice>=0 && user_choice<=ch )
			{
				number=true;
			}
			else
			{
				System.out.println("Please enter valid value as shown in possible positions");
			}
			//choice=Integer.parseInt(scan.nextLine());
			
			}
			catch(Exception e)
			{
				System.out.println("Please enter valid value as shown in possible positions");
				scan.nextLine();
			}
			
		return number;
	}

	
	//printSelected() prints the possible positions for given piece and shows the final selected position
	public static void printSelected(String piece)
	{
		if(!possiblePositions.isEmpty())
		{
			for(Map.Entry<Integer, String>entry:possiblePositions.entrySet())//print possible positions
			{
				System.out.println("Possible Position: " +entry.getKey());
				System.out.println(entry.getValue());

			}
			System.out.println("Select the position (0 - "+(possiblePositions.size()-1)+")you want "+piece+" to move to");
			
			if(isValidInt(possiblePositions.size()-1))//
			{
				//System.out.println("Select the position (0 - "+(possiblePositions.size()-1)+")you want "+piece+" to move to");			
				String choice_position="";
				if(possiblePositions.containsKey(user_choice))
				{
					choice_position=possiblePositions.get(user_choice);
				}
				occupiedPositions.put(choice_position, 1);
				System.out.println("Finally selected row details row: "+choice_position.substring(0,1) //prints final selected position
				+ " column "+ choice_position.substring(1));
			}
			
		}
		else
		{
			System.out.println("All possible positions are filled.No position available for "+ piece);
		}
		System.out.println("Do you want to continue [Yes/No]");
		String chess_continue=scan.nextLine().toLowerCase();
		if(chess_continue.equalsIgnoreCase("yes"))
		{
			piece_choices();
		}
		else
		{
			System.out.println("Hope you enjoyed playing game");
			System.out.println("Thank You");
		}

	}


	//kingMoves() generates the possible number of moves for king from its initial position
	public static void kingMoves()
	{
		System.out.println("Enter the position of king");
		String position= scan.nextLine().toLowerCase();
		if(valid_list.contains(position))
		{
		if(occupiedPositions.containsKey(position))// check if the input initial position is already filled 
		{
			System.out.println("This position is already filled. Please select different position");
			pawnMoves();
			return;
		}
		King king=new King();
		ArrayList<String>position_list=king.generate_positions(position); // returns all the possible moves 

		int i=0;
		possiblePositions.clear();
		for(String occupied:position_list) //removes already occupied position while storing possible positions
		{

			row=Integer.parseInt(occupied.substring(0,1));
			column=occupied.charAt(1);
			if(!occupiedPositions.containsKey(occupied))
			{
				possiblePositions.put(i++,String.valueOf(row)+String.valueOf(column));
				//position_list.remove(occupied);

			}
		}
		printSelected("king");
		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			kingMoves();
		}
	}
	
	//knightMoves() generates the possible number of moves for knight from its initial position
	public static void knightMoves()
	{
		System.out.println("Enter the position of knight");
		String position= scan.nextLine().toLowerCase();
		if(valid_list.contains(position))
		{
		if(occupiedPositions.containsKey(position))// check if the input initial position is already filled 
		{
			System.out.println("This position is already filled. Please select different position");
			pawnMoves();
			return;
		}
		Knight knight=new Knight();
		ArrayList<String>position_list=knight.generate_positions(position); // returns all the possible moves 

		int i=0;
		possiblePositions.clear();
		for(String occupied:position_list) //removes already occupied position while storing possible positions
		{

			row=Integer.parseInt(occupied.substring(0,1));
			column=occupied.charAt(1);
			if(!occupiedPositions.containsKey(occupied))
			{
				possiblePositions.put(i++,String.valueOf(row)+String.valueOf(column));
				//position_list.remove(occupied);

			}
		}
		printSelected("knight");
		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			kingMoves();
		}
	}


	//pawnMoves() generates the possible number of moves for pawn from its initial position
	public static void pawnMoves()
	{
		System.out.println("Enter the position of pawn");
		String position= scan.nextLine().toLowerCase();
		if(valid_list.contains(position))
		{
		if(occupiedPositions.containsKey(position))// check if the input initial position is already filled 
		{
			System.out.println("This position is already filled. Please select different position"); 
			pawnMoves();
			return;
		}
		Pawn pawn=new Pawn();
		ArrayList<String>position_list=pawn.generate_positions(position); // returns all the possible moves 

		int i=0;
		possiblePositions.clear();
		for(String occupied:position_list) //removes already occupied position while storing possible positions
		{

			row=Integer.parseInt(occupied.substring(0,1));
			column=occupied.charAt(1);
			if(!occupiedPositions.containsKey(occupied))
			{
				possiblePositions.put(i++,String.valueOf(row)+String.valueOf(column));
				//position_list.remove(occupied);

			}
		}
		printSelected("pawn");
		}
		else
		{
			System.out.println("Enter a valid position between [1a - 8h]");
			pawnMoves();
		}

	}
}
