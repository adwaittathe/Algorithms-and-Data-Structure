import java.util.ArrayList;
//This class generates all possible moves of king from input position
public class King {
	
	static int row;
	static char column;
	ArrayList<String> position_list;
	//generates all possible move of king
	public ArrayList generate_positions(String position) {
		position_list=new ArrayList<String>();
		row=Integer.parseInt(position.substring(0,1));
		column=position.charAt(1);
		
		column--;
		// row and column validation to store only proper output
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		row++;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		row--;
		row--;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		column++;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		row++;
		row++;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		column++;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
		}
		row--;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
		row--;
		if((column<='h'&&column>='a') && (row>=1 && row<=8))
		{
			position_list.add(String.valueOf(row)+String.valueOf(column));
			
		}
			
		return position_list;
	}
	
	

}
