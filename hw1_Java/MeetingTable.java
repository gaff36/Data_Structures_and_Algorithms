/** 
 * @author Emre Sezer
 * Represents Meeting tables which inherits Furniture.
*/

public class MeetingTable extends Furniture
{
	private int color;

	public MeetingTable(int model, int whichBranch,int color)
	{
		super(model, whichBranch);
		setColor(color);
	}
	public int getColor()	// GETTER
	{
		return color;
	}
	public void setColor(int color)	// SETTER
	{
		this.color = color;
	}

	public String toString()
	{
		return String.format("Meeting Table : Branch " + getWhichBranch() + ", Model " + getModel() + ", Color " + getColor());
	}
	
} 