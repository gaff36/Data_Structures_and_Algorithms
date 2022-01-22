/** 
 * @author Emre Sezer
 * Branch class.Represents branches.
*/

public class Branch
{
	private int branchNum;
	private Furniture [] furnitures;
	private BranchEmployee be;				// There is only one Branch Employee in each Branch.So, when you add a new branch to the system you also add a BranchEmployee too.

	// GETTERS
	protected int getBranchNum()
	{
		return branchNum;
	}
	protected BranchEmployee getBranchEmployee()
	{
		return be;
	}
	protected Furniture [] getFurnitures()
	{
		return furnitures;
	}
	
	// SETTERS

	protected void setFurnitures(Furniture [] f)
	{
		furnitures = f;
	}

	protected void setBranchNum(int branchNum)
	{
		this.branchNum = branchNum;
	}

/** 
 * This method is for seting a default furniture array.It is for making easier tests.
 * While testing you can add any furniture to the Furniture array of any branch
*/
	protected void defaultFurnitures()
	{
		furnitures = new Furniture[8];
		furnitures[0] = new OfficeChair(1, getBranchNum(), 0);
		furnitures[1] = new OfficeChair(1, getBranchNum(), 0);
		furnitures[2] = new OfficeDesk(1, getBranchNum(), 1);
		furnitures[3] = new OfficeDesk(1, getBranchNum(), 1);
		furnitures[4] = new MeetingTable(4, getBranchNum(), 2);
		furnitures[5] = new MeetingTable(3, getBranchNum(), 1);
		furnitures[6] = new Bookcase(5, getBranchNum());
		furnitures[7] = new OfficeCabinet(6, getBranchNum());
		
	}

	// CONSTRUCTOR
	public Branch(Furniture [] f, BranchEmployee b)
	{
		furnitures = f;
		be = b;
	}
	public Branch(int num)
	{
		branchNum = num;
		be = new BranchEmployee();
		defaultFurnitures();
	}

	
}