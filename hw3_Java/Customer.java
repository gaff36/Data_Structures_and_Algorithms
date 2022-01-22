import java.util.Scanner;

/** 
 * @author Emre Sezer
 * Customer class.Represents Customer
*/

public class Customer	implements User
{
	private KWHybridList<Order> previousOrders;
	private int orderNum = 0;
	private int customerNum;
	private static int totalCustomers = 0;
	private String email;
	private String name;
	private String surname;
	private String password;

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
*	Getter method for orderNum
*  @return orderNum
*/
	protected int getOrderNum()
	{
		return orderNum;
	}

/** 
*	Getter method for customerNum
*  @return customerNum
*/
	protected int getCustomerNum()
	{
		return customerNum;
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

/** 
*	Setter method for customerNum
*   @param customerNum int
*/
	protected void setCustomerNum(int customerNum)
	{
		this.customerNum = customerNum;
	}

	public String toString()
	{
		return String.format("Customer: " + getEmail() + ", Customer No: " + getCustomerNum() + ", Name: " + getName() + ", Surname: " + getSurname());
	}

/** Prints the furnitures in the Branch
 * @param  furnitures KWHybridList<Furniture> that keeps all the furnitures in each branch.
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


/** Prints the furnitures every Branch
 * @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch.
*/
	public void showProductListEveryBranch(KWLinkedList <Branch> branchList)	throws Error
	{
		if(branchList.size() == 0)	throw new Error("There is no branches");

		for(int i = 0; i < branchList.size(); i++)
		{
			 showProductList(branchList.get(i).getFurnitureList());
		}
	}

/** 
 * Prints the previous orders of the customer
*/
	public void showPreviousOrders()	
	{
		if(previousOrders.size() == 0)	System.out.println("There is no Previous Orders");
		else
		{
			for(int i = 0; i < previousOrders.size(); i++)
			{
				System.out.println(previousOrders.get(i));
			}
		}
	}

/** Prints the furnitures every Branch
 * @param  totalBranch int that represents total branch numbers in the system.
*/		
	public Furniture selectFurniture(int totalBranch)	throws Error
	{
		int furniture;
		int model;
		int color;
		int branch;

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
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
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
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
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
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new MeetingTable(model, branch, color);
		}
		else if(furniture == 4)
		{
			Bookcase temp = new Bookcase(0,0);
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");			
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new Bookcase(model, branch);
		}
		else if(furniture == 5)
		{
			OfficeCabinet temp = new OfficeCabinet(0,0);
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > temp.getMaxModels())	throw new Error("You entered an invalid model");			
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new OfficeCabinet(model, branch);
		}
		else
		{
			throw new Error("Wrong Furniture Type is Entered");
		}
	}


/** For purchasing furnitures.User enters phone number and address.Then, selects an item.
 * @param  userList KWArrayList<User> that keeps all the users in the system.
 * @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch.
 * @param  f Furniture
 * @param  phone int
 * @param  address String
 * @param  howMany int
*/
	public void purchase(KWArrayList<User> userList, KWLinkedList<Branch> branchList, Furniture f, int phone, String address, int howMany)
	{
		try
		{
			for(int i = 0; i < userList.size(); i++)	
			{
				if(userList.get(i) instanceof BranchEmployee)
				{
					BranchEmployee x = (BranchEmployee) userList.get(i);
					if(x.getBranchNum() == f.getWhichBranch())
					{
						if(x.sale(userList, branchList.get(f.getWhichBranch()).getFurnitureList() ,f ,howMany))
						{
							addOrder(phone, address, f, howMany);		
							
						}return;
					}
				}
			}
			System.out.println("There is no such branch with index " + f.getWhichBranch());

		}
		catch(Error e){}
	}
		
	public boolean searchFurniture(KWLinkedList<Branch> branchList, Furniture f, int branchNum)
	{
		boolean flag = false;
		for(int i = 0; i < branchList.size(); i++)
		{
			if(branchList.get(i).getBranchNum() == branchNum)
			{
				flag = true;
				break;
			}
		}
		if(!flag)	return false;

		for(int j = 0; j < branchList.get(branchNum).getFurnitureList().size();j++)
		{
			if(f.equals(branchList.get(branchNum).getFurnitureList().get(j)))	return true;
		}
		return false;
	}		

/** Customer Constructor.
 * @param email String
 * @param name String
 * @param surname String 
 * @param password String
*/
	public Customer(String email, String name, String surname, int customerNum, String password)
	{	
		setEmail(email);
		setName(name);
		setSurname(surname);
		setCustomerNum(customerNum);
		setPassword(password);
		previousOrders = new KWHybridList<Order>();
	}

/** 
 *  Customer Constructor.
*/	
	public Customer()
	{
	}

/** 
 * Adds Customer to the userList
 * @param  userList KWArrayList<User> that keeps all the users in the system.
 * @param email String
 * @param name String
 * @param surname String 
 * @param password String
*/
	public void addCustomer(KWArrayList<User> userList, String email, String name, String surname, String password)	throws Error
	{		
		for(int i = 0; i < userList.size(); i++)
		{
			if(email.equals(userList.get(i).getEmail()))	throw new Error ("There is a user with the same e-mail address");	
		}
		Customer temp = new Customer(email, name, surname, totalCustomers, password);
		totalCustomers++;
		userList.add(temp);
		System.out.println(temp + " is added to the List");
	}

/** 
 * Adds Customer to the userList(Takes information from the user)
 * @param  userList KWArrayList<User> that keeps all the users in the system.
*/
	public void registerCustomer(KWArrayList<User> userList)
	{
		Scanner third = new Scanner(System.in);
		System.out.println("Enter email :");
		String email = third.nextLine();
		System.out.println("Enter Name :");
		String name = third.nextLine();
		System.out.println("Enter Surname :");
		String surname = third.nextLine();
		System.out.println("Enter Password :");
		String password = third.nextLine();

		try
		{
			addCustomer(userList, email, name, surname, password);
		}
		catch(Error e){}
	}

/** 
 * Adds Order to the Customer's Order List.
 * @param phoneNum int
 * @param address String
 * @param item Furniture 
 * @param num int
*/	
	public void addOrder(int phoneNum, String address, Furniture item, int num)
	{		
		
		Order temp = new Order(num, phoneNum, address, item);
		previousOrders.add(temp);
		orderNum++;
	}

/** Inner class for customer
* Represents customer orders	
*/
	public class Order
	{
		int ordNum;
		int num;
		String address;
		int phoneNum;
		Furniture item;

/** Customer Constructor.
 * @param num int
 * @param phoneNum int
 * @param address String 
 * @param item Furniture
*/
		public Order(int num, int phoneNum, String address, Furniture item)
		{
			setNum(num);
			setPhoneNum(phoneNum);
			setAddress(address);
			setItem(item);
			ordNum = orderNum;
		}

/** Overriden toString method	
*/
		public String toString()
		{		
			return String.format("Order no: " + ordNum + ", "+ getNum() + " , " + getItem() + ", Phone: " + getPhoneNum() + ", Address :" + getAddress());
		}

/** 
*	Getter method for num
 *  @return num
*/
		public int getNum()
		{
			return num;
		}
		public int getOrdNum()
		{
			return ordNum;
		}

/** 
*	Getter method for phoneNum
 *  @return phoneNum
*/		
		public int getPhoneNum()
		{
			return phoneNum;
		}

/** 
*	Getter method for address
 *  @return address
*/		
		public String getAddress()
		{
			return address;
		}

/** 
*	Getter method for item
 *  @return item
*/		
		public Furniture getItem()
		{
			return item;
		}

/** 
*	Setter method for num
*   @param num int
*/
		public void setNum(int num)
		{
			this.num = num;
		}

/** 
*	Setter method for phoneNum
*   @param phoneNum int
*/		
		public void setPhoneNum(int phoneNum)
		{
			this.phoneNum = phoneNum;
		}

/** 
*	Setter method for address
*   @param address String
*/		
		public void setAddress(String address)
		{
			this.address = address;
		}

/** 
*	Setter method for item
*   @param item Furniture
*/		
		public void setItem(Furniture item)
		{	
			this.item = item;
		}

	}
		
}