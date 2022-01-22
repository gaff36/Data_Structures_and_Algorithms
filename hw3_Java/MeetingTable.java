/** 
 * @author Emre Sezer
 * Represents Meeting tables which inherits Furniture.
*/

public class MeetingTable extends Furniture
{
	private int color;
	private static int maxColors;
	private static KWHybridList<String> colors;


/**  MeetingTable Constructor.
 * @param model int
 * @param whichBranch int
 * @param colar int
*/
	public MeetingTable(int model, int whichBranch, int color)
	{
		super(model, whichBranch);
		setMaxModels(10);
		maxColors = 4;
		colors = new KWHybridList<String>();
		colors.add("Red");
		colors.add("Blue");
		colors.add("Yellow");
		colors.add("Purple");
		if((model >= super.getMaxModels()) && (color < 0 || color >= maxColors))	throw new IndexOutOfBoundsException();
		setColor(color);
	}

/** 
*	Getter method for color
 *  @return color
*/		
	public int getColor()
	{
		return color;
	}

/** 
*	Getter method for color
 *  @return maxColors
*/
	public static int getMaxColors()
	{
		return maxColors;
	}

/** 
*	Getter method for color
 *  @return index'th element of colors
*/
	public static String getColors(int index)
	{
		return colors.get(index);
	}

/** 
*	Setter method for color
*   @param color int
*/	
	public void setColor(int color)
	{
		this.color = color;
	}

/** 
*	Overriden toString Method
*/
	public String toString()
	{
		return String.format("Office Chair : Branch " + getWhichBranch() + ", Model " + getModel() + ", Color " + getColor());
	}
	
} 