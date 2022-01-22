/** 
 * @author Emre Sezer
 * Represents Office Desks inherits Furniture.
*/
public class OfficeDesk extends Furniture
{
	private int color;
	private static int maxColors;
	private static KWHybridList<String> colors;

/** OfficeDesk Constructor.
 * @param model int
 * @param whichBranch int
 * @param colar int
*/
	public OfficeDesk(int model, int whichBranch, int color)
	{
		super(model, whichBranch);
		super.setMaxModels(5);
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
	public int getMaxColors()
	{
		return maxColors;
	}

/** 
*	Getter method for color
 *  @return index'th element of colors
*/
	public String getColors(int index)
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