/** 
 * @author Emre Sezer
 * Represents Office Chair which inherits Furniture.
*/

public class OfficeChair extends Furniture
{
	private int color;

	public OfficeChair(int model, int whichBranch,int color)
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
		return String.format("Office Chair : Branch " + getWhichBranch() + ", Model " + getModel() + ", Color " + getColor());
	}
	
} 