/** 
 * @author Emre Sezer
 * Represents Office Desks inherits Furniture.
*/

public class OfficeDesk extends Furniture
{
	private int color;

	public OfficeDesk(int model, int whichBranch,int color)
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
		return String.format("Office Desk : Branch " + getWhichBranch() + ", Model " + getModel() + ", Color " + getColor());
	}
	
} 