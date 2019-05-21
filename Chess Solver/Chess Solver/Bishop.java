import java.util.ArrayList;
//This class generates all possible moves of bishop from input position
public class Bishop {

	static int row;
	static char column;
	ArrayList<String> position_list;
	//generates all possible move of bishop
	public ArrayList generate_positions(String position) {
		
		position_list=new ArrayList<String>();
		row=Integer.parseInt(position.substring(0,1));
		column=position.charAt(1);
		char a1=column;
		char a2=column;
		char b1=column;
		char b2=column;
		for(int i=row+1;i<=8;i++) // generates upper position from current position
		{
			a1++;
			if(a1<='h')
			{
			position_list.add(String.valueOf(i)+String.valueOf(a1));
			}
			b1--;
			if(b1>='a')
			{
			position_list.add(String.valueOf(i)+String.valueOf(b1));
			}

		}
		for(int i=row-1;i>=1;i--) // generates down position from current position
		{

			a2++;
			if(a2<='h')
			{
			position_list.add(String.valueOf(i)+String.valueOf(a2));
			}

			b2--;
			if(b2>='a')
			{
			position_list.add(String.valueOf(i)+String.valueOf(b2));
			}

		}
		
			
		return position_list;
	}
}
