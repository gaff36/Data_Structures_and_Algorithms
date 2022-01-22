/** @author Emre Sezer
 * Concrete BranchEmployee class represents Branch Employees 
*/

import java.util.Scanner;

public class BranchEmployee implements User
{
	private String email;
	public String name;
	private String surname;
	private String password;
	private int branchNum;

/** 
*	Getter method for email
 *  @return email
*/
	public String getEmail()
	{
		return email;
	}

/** 
*	Getter method for name
*  @return name
*/
	public String getName()
	{
		return name;
	}

/** 
*	Getter method for surname
*  @return surname
*/
	public String getSurname()
	{
		return surname;
	}

/** 
*	Getter method for password
*  @return password
*/
	public String getPassword()
	{
		return password;
	}

/** 
*	Getter method for branchNum
*  @return branchNum
*/	
	public int getBranchNum()
	{
		return branchNum;
	}

/** 
*	Setter method for name
*   @param name String
*/
	public void setName(String name)
	{	
		this.name = name;
	}

/** 
*	Setter method for furn
*   @param surname String 
*/
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

/** 
*	Setter method for email
*   @param email String
*/
	public void setEmail(String email)
	{
		this.email = email;
	}

/** 
*	Setter method for password
*   @param password String
*/
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String toString()
	{
		return String.format("BranchEmployee: " + getEmail() + ", Branch No: " + getBranchNum() + ", Name: " + getName() + ", Surname: " + getSurname());
	}

/** 
 *  Prints the furniture's properties in the list
 *   @param furnitures KWHybridList
*/
	public void showProductList(KWHybridList<Furniture> furnitures)
	{
		
		if(furnitures.size() == 0)	System.out.println("There is no furnitures in the list");

		else
		{	
			System.out.println("BRANCH " + furnitures.get(0).getWhichBranch() + " :");
			for(int i = 0; i < furnitures.size(); i++)
			{
				System.out.println(furnitures.get(i));
			}
		}
		System.out.println();
	}

/** BranchEmployee Constructor.
 * @param email String
 * @param name String
 * @param surname String 
 * @param password String
 * @param branchNum int
*/
	public BranchEmployee(String email, String name, String surname, String password, int branchNum)
	{	
		setEmail(email);
		setName(name);
		setSurname(surname);
		setPassword(password);
		this.branchNum = branchNum;
	}
	public BranchEmployee()
	{}

/** 
*	Method for seeing previous orders of a customer
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*   @param  customerNum int 	
*/	
	public void seePreviousOrders(KWArrayList<User> userList, int customerNum)
	{	
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof Customer)
			{	
				Customer temp = (Customer) userList.get(i);			
				if(temp.getCustomerNum() == customerNum)
				{	
					temp.showPreviousOrders();
					return;
				}
			}
		}	
		 
		 System.out.println("There is no such a Customer");		
	}

/** 
*	Method for seeing previous orders of a customer
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*   @param email String
* 	@param name String
* 	@param surname String 
* 	@param password String	
* 	@param branchNum int	
*/
	public void registerBranchEmployee(KWLinkedList<Branch> branchList, KWArrayList<User> userList, String email, String name, String surname, String password, int branchNum)	throws Error
	{	
		boolean flag = false;
		for(int i = 0; i < userList.size(); i++)
		{	
			if(email.equals(userList.get(i).getEmail()))	throw new Error ("There is a user with the same e-mail address");	
		}
		for(int i = 0; i < branchList.size(); i++)	
		{
			if(branchList.get(i).getBranchNum() == branchNum)
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			BranchEmployee temp = new BranchEmployee(email, name, surname, password, branchNum);
			userList.add(temp);
			System.out.println(temp + " is added to the System");
		}
		else 	System.out.println("There is no such Branch with index " + branchNum);
		
	}

/** 
 *	Method for registering customer in-Store
 *	@param  userList KWArrayList<User> that keeps all the users in the system.
*/		
	public void inStoreRegister(KWArrayList<User> userList)
	{	
		Customer temp = new Customer();
		temp.registerCustomer(userList);
	}						

/** 
 *	Method for setting order
  *	@param  c Customer
  *	@param  phoneNum int
  *	@param  address String
  *	@param  item Furniture
  *	@param  num int
*/	
	public void setOrder(Customer c, int phoneNum, String address, Furniture item, int num)
	{
		c.addOrder(phoneNum, address, item, num);	
	}

/** 
 *	This method sets a new wish to admin's wishlist
 *	@param num int
 *	@param f Furniture
*/	
	protected void setWish(int num, Furniture f)	// I couldn't understand how should i inform the manager.
	{
		System.out.println("Dear manager, there is lack of items in Branch : ");
		Administrator.wish x = new Administrator.wish(num, f); 
		Administrator.wishList.add(x);
	}
						
/** 
 *	This method is for saling requsted furniture.If there is no furniture equals to requested one.
 *  Adds as many as needed to array and removes them.
 *	@param furnitures KWHybridList<Furniture> that keeps all the furnitures in the branch. 
 *	@param item Furniture
 *	@param num int
*/				
	public boolean sale (KWArrayList<User> userList, KWHybridList<Furniture> furnitures, Furniture item, int num)	throws Error
	{
		//if(furnitures.size() == 0)	throw new Error("There is no furniture");

		if(checkBranchEmployeeNum(userList, item.getWhichBranch()) == -1)	
		{	
			System.out.println("There is no such branch with index " + item.getWhichBranch());
		}
		
			int size = countFurniture(furnitures, item);
			if(size >= num)
			{	
				try
				{
					removeFurniture(furnitures, item, num);
					System.out.println("You bought " + num + " " + item);
					return true;
				}
				catch(Error e)
				{}			
			}
			else
			{
				setWish(num, item);
				addFurniture(furnitures, item, num - size);
				System.out.println("You couldn't buy " + num + " " + item + ". There is not enough requested items in the branch");
				return false;
			}	
			return false;		
	
	}

	public int checkBranchEmployeeNum(KWArrayList<User> userList, int branchNo)
	{
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof BranchEmployee)
			{
				BranchEmployee temp = (BranchEmployee) userList.get(i);
				if(temp.getBranchNum() == branchNo)
				{	
					return i;
				}
			}
		}
		return -1;
	}
					
			
/** 
 *	This method is for adding the specific furniture to the specific branche's furniture list.  
 *  It adds as how many requested
 *	@param list KWHybridList<Furniture> that keeps all the furnitures in the branch. 
 *	@param item Furniture
 *	@param num int
*/
	public void addFurniture(KWHybridList<Furniture> list, Furniture item, int num)
	{
		for(int i = 0; i < num; i++)
		list.add(item);
		System.out.println("Branch Employee added " + num + " " + item);
	}

/** 
 *	This method is for removing the requested furnitures from the specific branche's list.
 *  If there are less selected furnitures in the array than requested amount, throws Exception.
 *	@param list KWHybridList<Furniture> that keeps all the furnitures in the branch. 
 *	@param item Furniture
 *	@param num int
*/
	public void removeFurniture(KWHybridList<Furniture> list, Furniture item, int num)	throws Error
	{
		if(countFurniture(list, item) < num)	
		{
			throw new Error("There is not enough items");
		}
		else
		{
			for(int i = 0; i < num; i++)
			{
				int x = list.indexOf(item);
				list.remove(x);
			}
			
		}
		System.out.println("Branch Employee removed " + num + " " + item);

	}

/** 
 *	Returns how many of the selected furniture is on the list.
 *	@param list KWHybridList<Furniture> that keeps all the furnitures in the branch. 
 *	@param item Furniture
*/
	public int countFurniture(KWHybridList<Furniture> list , Furniture item)
	{
		int total = 0;
		for(int i = 0; i < list.size(); i++)
		{
			if(item.equals(list.get(i)))	total++;
		}
		return total;
	}

/** User need to answer questions and select a furniture
 *  This method returns the selected furniture
  *	@param branchNumber int
  *	@param branch int
 *  @return the selected Furniture 
*/
	public Furniture selectFurniture(int totalBranch, int branch)	throws Error
	{
		int furniture;
		int model;
		int color;

		if(totalBranch == 0)	throw new Error("There is no branches");

		Scanner myObj = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		System.out.println("Select the furrniture from list");
		System.out.println("1) Office Chair");
		System.out.println("2) Office Desk");
		System.out.println("3) Meeting Table");
		System.out.println("4) Bookcase");
		System.out.println("5) Office Cabinet");
		furniture = myObj.nextInt();
		if(furniture == 1)
		{
			OfficeChair temp = new OfficeChair(0,0,0);
			System.out.println("Enter the Model Number (0-6)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");
			System.out.println("Colors:");
			for(int i = 0; i < temp.getMaxColors(); i++)		System.out.println(i + ") " + temp.getColors(i));
			System.out.println("Enter the Color Number (0-4)");
			color = myObj.nextInt();
			if(color < 0 || color > temp.getMaxColors())	throw new Error("You entered an invalid color");
			return new OfficeChair(model, branch, color);
		}
		else if(furniture == 2)
		{
			OfficeDesk temp = new OfficeDesk(0,0,0);
			System.out.println("Enter the Model Number (0-6)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");
			System.out.println("Colors:");
			for(int i = 0; i < temp.getMaxColors(); i++)		System.out.println(i + ") " + temp.getColors(i));
			System.out.println("Enter the Color Number (0-4)");
			color = myObj.nextInt();
			if(color < 0 || color > temp.getMaxColors())	throw new Error("You entered an invalid color");
			return new OfficeDesk(model, branch, color);
		}
		else if(furniture == 3)
		{
			MeetingTable temp = new MeetingTable(0,0,0);
			System.out.println("Enter the Model Number (0-6)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");
			System.out.println("Colors:");
			for(int i = 0; i < temp.getMaxColors(); i++)		System.out.println(i + ") " + temp.getColors(i));
			System.out.println("Enter the Color Number (0-4)");
			color = myObj.nextInt();
			if(color < 0 || color > temp.getMaxColors())	throw new Error("You entered an invalid color");
			return new MeetingTable(model, branch, color);
		}
		else if(furniture == 4)
		{
			Bookcase temp = new Bookcase(0,0);
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");			
			return new Bookcase(model, branch);
		}
		else if(furniture == 5)
		{
			OfficeCabinet temp = new OfficeCabinet(0,0);
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");			
			return new OfficeCabinet(model, branch);
		}
		else
		{
			throw new Error("Wrong Furniture Type is Entered");
		}
	}				

}