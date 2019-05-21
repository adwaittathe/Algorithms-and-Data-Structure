import java.util.ArrayList;
//This class generates all possible moves of rook from input position
public class Rook {
	static int row;
	static char column;
	ArrayList<String> position_list;
	//generates all possible move of rook
	public ArrayList generate_positions(String position) {
		
		position_list=new ArrayList<String>();
		row=Integer.parseInt(position.substring(0,1));
		column=position.charAt(1);
		for(int i=row;i<=8;i++) // store value from current row to 8
		{
			position_list.add(String.valueOf(i)+String.valueOf(column));
		}
		for(int i=row-1;i>=1;i--) // store value from current row to 1
		{
			position_list.add(String.valueOf(i)+String.valueOf(column));

		}
		column++;
		for(char i=column;i<='h';i++) // store value from current column to h
		{
			position_list.add(String.valueOf(row)+String.valueOf(i));
		}
		column--;
		column--;
		for(char i=column;i>='a';i--) // store value from current column to a
		{
			position_list.add(String.valueOf(row)+String.valueOf(i));
		}		
		return position_list;
	}
}
