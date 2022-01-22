/** 
 * @author Emre Sezer
 * Represents Office Cabinet which inherits Furniture.
*/

public class OfficeCabinet extends Furniture
{

	public OfficeCabinet(int model, int whichBranch)
	{
		super(model, whichBranch);
	}
	
	public String toString()
	{
		return String.format("Office Cabinet : Branch " + getWhichBranch() + " , Model " + getModel());
	}
	
} 