/** 
 * @author Emre Sezer
 * Represents Bookcase which inherits Furniture.
*/

public class Bookcase extends Furniture
{

	public Bookcase(int model, int whichBranch)
	{
		super(model, whichBranch);
	}
	
	public String toString()
	{
		return String.format("Bookcase : Branch " + getWhichBranch() + " , Model " + getModel());
	}

} 