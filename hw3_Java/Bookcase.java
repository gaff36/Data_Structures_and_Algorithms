/** 
 * @author Emre Sezer
 * Represents Bookcase which inherits Furniture.
*/
public class Bookcase extends Furniture
{

/** Bookcase Constructor.
 * @param model int
 * @param whichBranch int
*/
	public Bookcase(int model, int whichBranch)
	{	
		super(model, whichBranch);
		setMaxModels(12);
		if(model >= super.getMaxModels())	throw new IndexOutOfBoundsException();
	}

/** 
*	Overriden toString Method
*/
	public String toString()
	{
		return String.format("Bookcase : Branch " + getWhichBranch() + " , Model " + getModel());
	}

} 