import java.util.ArrayList;

// This class generates all possible moves of pawn from input position
public class Pawn {
	static int row;
	static char column;
	ArrayList<String> position_list;
	//generates all possible move of pawn
	public ArrayList generate_positions(String position)
	{
		position_list=new ArrayList<String>(); // Arraylist to store possible moves values
		row=Integer.parseInt(position.substring(0,1)); 
		column=position.charAt(1);
		
		if(row==2) // for row =2 pawn can move two steps further
		{
			if(column=='a' || column=='h') // column validation
			{
				if(column=='a')
				{
					row++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					column++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					row++;
					column--;
					position_list.add(String.valueOf(row)+String.valueOf(column));
				}
				else
				{
					row++;
					column--;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					column++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					row++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					
				
				}
				
			}
			else
			{
				row++;
				--column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				++column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				++column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				++row;--column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				
			}
			
		}
		else if(row==8)// there will be null values if pawn is at 8th row
		{
			
		}
		else
		{
			if(column=='a' || column=='h')
			{
				if(column=='a')
				{
					row++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					column++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					
				}
				else
				{
					row++;
					column--;
					position_list.add(String.valueOf(row)+String.valueOf(column));
					column++;
					position_list.add(String.valueOf(row)+String.valueOf(column));
				
					
				
				}
				
			}
			else
			{
				row++;
				--column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				++column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				++column;
				position_list.add(String.valueOf(row)+String.valueOf(column));
				
			}
			
		}	
		
		return position_list;
	}

}
