/** 
 * @author Emre Sezer
 * Represents Office Cabinet which inherits Furniture.
*/
public class OfficeCabinet extends Furniture
{

/** OfficeCabinet Constructor.
 * @param model int
 * @param whichBranch int
*/
	public OfficeCabinet(int model, int whichBranch)
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
		return String.format("Office Cabinet : Branch " + getWhichBranch() + " , Model " + getModel());
	}
	
} 