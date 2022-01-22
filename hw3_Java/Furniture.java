/** 
 * @author Emre Sezer
 * Abstract Furniture class.Represents furnitures
*/

public abstract class Furniture
{
	private int model;
	private int whichBranch;
	private int maxModels;

/** Furniture Constructor.
 * @param model int
 * @param whichBranch int
*/
	public Furniture (int model, int whichBranch)
	{	
		setModel(model);
		setWhichBranch(whichBranch);
	}

/** 
*	Getter method for model
 *  @return model
*/	
	public int getModel()
	{
		return model;
	}

/** 
*	Getter method for whichBranch
 *  @return whichBranch
*/		
	public int getWhichBranch()
	{
		return whichBranch;
	}

	public int getMaxModels()
	{
		return maxModels;
	}

/** 
*	Setter method for model
*   @param model int
*/
	public void setModel(int model)	// SETTER
	{
		this.model = model;
	}

/** 
*	Setter method for whichBranch
*   @param whichBranch int
*/	
	public void setWhichBranch(int whichBranch)
	{
		this.whichBranch = whichBranch;
	}

	public void setMaxModels(int maxModels)
	{
		this.maxModels = maxModels;
	}

/** 
 * Overriden equals method for checking if 2 objects are equal.
 *   @param o Object
*/	
	public boolean equals(Object o)
	{
		boolean check = false;

		if(o == this)	return true;
		if(o instanceof OfficeChair && this instanceof OfficeChair)
		{
			OfficeChair temp1 = (OfficeChair) o;
			OfficeChair temp2 = (OfficeChair) this;
			if(temp1.getModel() == temp2.getModel() && temp1.getColor() == temp2.getColor())	check = true;
		}
		else if(o instanceof OfficeDesk && this instanceof OfficeDesk)
		{
			OfficeDesk temp1 = (OfficeDesk) o;
			OfficeDesk temp2 = (OfficeDesk) this;
			if(temp1.getModel() == temp2.getModel() && temp1.getColor() == temp2.getColor())	check = true;
		}
		else if(o instanceof MeetingTable && this instanceof MeetingTable)
		{
			MeetingTable temp1 = (MeetingTable) o;
			MeetingTable temp2 = (MeetingTable) this;
			if(temp1.getModel() == temp2.getModel() && temp1.getColor() == temp2.getColor())	check = true;
		}
		else if(o instanceof Bookcase && this instanceof Bookcase)
		{
			Bookcase temp1 = (Bookcase) o;
			Bookcase temp2 = (Bookcase) this;
			if(temp1.getModel() == temp2.getModel())	check = true;
		}
		else if(o instanceof OfficeCabinet && this instanceof OfficeCabinet)
		{
			OfficeCabinet temp1 = (OfficeCabinet) o;
			OfficeCabinet temp2 = (OfficeCabinet) this;
			if(temp1.getModel() == temp2.getModel())	check = true;
		}
		return check;
	}		
								
}