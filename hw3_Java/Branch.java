/** 
 * @author Emre Sezer
 * Branch class.Represents branches.
*/
public class Branch
{
	private int branchNum;
	private KWHybridList<Furniture> furnitureList;
	public static int totalBranches = 0;
	public static int totalEmployees = 0;

/** 
*	Getter method for furnitureList
*   @return furnitureList KWHybridList<Furniture>
*/
	public KWHybridList<Furniture> getFurnitureList()
	{
		return furnitureList;
	}

/** 
*	Getter method for furnitureList
*   @return branchNum int 
*/
	public int getBranchNum()
	{
		return branchNum;
	}

/** 
*	Getter method for totalBranches
*   @return totalBranches int 
*/
	public static int getTotalBranches()
	{
		return totalBranches;
	}
	
/** 
 * Branch Constructor
*/	
	public Branch()
	{
		furnitureList = new KWHybridList<Furniture>();				
		branchNum = totalBranches;	
		furnitureList.add(new OfficeChair(2,branchNum,2));															
		furnitureList.add(new OfficeChair(2,branchNum,2));															
		furnitureList.add(new OfficeDesk(1,branchNum,3));															
		furnitureList.add(new OfficeDesk(1,branchNum,3));															
		furnitureList.add(new OfficeDesk(1,branchNum,3));															
		furnitureList.add(new Bookcase(9,branchNum));															
		furnitureList.add(new Bookcase(9,branchNum));															
		furnitureList.add(new Bookcase(9,branchNum));															
		furnitureList.add(new Bookcase(9,branchNum));															
		furnitureList.add(new Bookcase(7,branchNum));															
		furnitureList.add(new OfficeCabinet(6,branchNum));															
		furnitureList.add(new OfficeCabinet(6,branchNum));															
		furnitureList.add(new OfficeCabinet(6,branchNum));															
		furnitureList.add(new MeetingTable(2,branchNum,2));															
		furnitureList.add(new MeetingTable(2,branchNum,2));															
		furnitureList.add(new MeetingTable(2,branchNum,2));															
		furnitureList.add(new MeetingTable(0,branchNum,2));															
		totalBranches++;									
	}
	
}