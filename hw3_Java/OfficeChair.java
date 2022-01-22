/** 
 * @author Emre Sezer
 * Represents Office Chair which inherits Furniture.
*/
public class OfficeChair extends Furniture
{
	private int color;
	private int maxColors;
	private KWHybridList<String> colors;

/** OfficeChair Constructor.
 * @param model int
 * @param whichBranch int
 * @param colar int
*/
	public OfficeChair(int model, int whichBranch,int color)
	{
		super(model, whichBranch);
		super.setMaxModels(7);
		maxColors = 5;
		colors = new KWHybridList<String>();
		colors.add("Red");
		colors.add("Blue");
		colors.add("Yellow");
		colors.add("Green");
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
 *  @return maxColors
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