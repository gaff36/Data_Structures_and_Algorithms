import java.util.Scanner;

/** 
 * @author Emre Sezer
 * Customer class.Represents Customer
*/

public class Customer	implements User
{
	private Order [] previousOrders;
	private int orderNum = 0;
	private int customerNum;
	private String email;
	public String name;
	private String surname;
	private String password;

	// GETTERS
	public String getEmail()
	{
		return email;
	}
	public String getName()
	{
		return name;
	}
	public String getSurname()
	{
		return surname;
	}
	public String getPassword()
	{
		return password;
	}

	// SETTERS
	public void setName(String name)
	{	
		this.name = name;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

/** Takes a Furniture array.
 *  Prints the furniture's properties in the array
*/
	public void showProductList(Furniture [] furnitures)	throws Error
	{
		System.out.println("BRANCH " + furnitures[0].getWhichBranch() + " :");
		if(furnitures == null)	throw new Error("There is no furnitures");
		else
		{	
			for(int i = 0; i < furnitures.length; i++)
			{
				System.out.println(furnitures[i]);
			}
		}
		System.out.println();
	}

	// GETTERS
	protected int getOrderNum()
	{
		return orderNum;
	}
	protected Order [] getPreviousOrders()
	{
		return previousOrders;
	}

	protected int getCustomerNum()
	{
		return customerNum;
	}

	// SETTERS

	protected void setCustomerNum(int customerNum)
	{
		this.customerNum = customerNum;
	}
	
	// FUNCTIONS

/** User need to answer questions and select a furniture
 *  This method returns the selected furniture
*/
	protected Furniture selectFurniture(int totalBranch)	throws Error
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
			System.out.println("Enter the Model Number (0-6)");
			model = myObj.nextInt();
			if(model < 0 || model > 6)	throw new Error("You entered an invalid model");
			System.out.println("Enter the Color Number (0-4)");
			color = myObj.nextInt();
			if(color < 0 || color > 6)	throw new Error("You entered an invalid color");
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new OfficeChair(model, branch, color);
		}
		else if(furniture == 2)
		{
			System.out.println("Enter the Model Number(0-4)");
			model = myObj.nextInt();
			if(model < 0 || model > 4)	throw new Error("You entered an invalid model");
			System.out.println("Enter the Color Number(0-3)");
			color = myObj.nextInt();
			if(color < 0 || color > 3)	throw new Error("You entered an invalid color");
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new OfficeDesk(model, branch, color);
		}
		else if(furniture == 3)
		{
			System.out.println("Enter the Model Number(0-9)");
			model = myObj.nextInt();
			if(model < 0 || model > 9)	throw new Error("You entered an invalid model");
			System.out.println("Enter the Color Number(0-3)");
			color = myObj.nextInt();
			if(color < 0 || color > 3)	throw new Error("You entered an invalid color");
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new MeetingTable(model, branch, color);
		}
		else if(furniture == 4)
		{
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > 12)	throw new Error("You entered an invalid model");			
			System.out.println("Enter the Branch Number");
			branch = myObj.nextInt();
			if(branch > totalBranch || branch < 0)	throw new Error("You entered an invalid branch number");
			return new Bookcase(model, branch);
		}
		else if(furniture == 5)
		{
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > 12)	throw new Error("You entered an invalid model");			
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

/** Customer Constructor
*/

	public Customer(String email, String name, String surname, int customerNum, String password)
	{	
		setEmail(email);
		setName(name);
		setSurname(surname);
		setCustomerNum(customerNum);
		setPassword(password);
	}
	public Customer()
	{	
	}

/** Login function for customers
*	Takes poperties of a customer and a customer array.If there is no customer with that email address, login fails
*/	
	protected Customer loginCustomer (String email, String password, boolean logged, Customer [] customerList)
	{		
		Customer temp = new Customer("xxxx","xxx","xxxx",-1, "xxxx");
		for(int i = 0; i < customerList.length; i++)
		{
			if(customerList[i].getPassword().equals(password) && customerList[i].getEmail().equals(email))
			{	
				System.out.println("You are logged in.Your customer number is " + customerList[i].getCustomerNum());
				logged = true;
				temp = customerList[i];
				break;
			}
		}
		
		return temp;
	}

/** Adds order to customer's profile	
*/	
	protected void addOrder(Order o)
	{	
		if(orderNum > 0)
		{
			orderNum++;
			Order [] temp = new Order[orderNum];
			
			for(int i = 0; i < orderNum - 1; i++)
			{	
				temp[i] = previousOrders[i];
			}
			temp[orderNum - 1] = o;
			previousOrders = temp;
		}
		else
		{
			previousOrders = new Order[1];
			previousOrders[0] = o;
			orderNum++;
		}
	}

/** Outer class for customer
* Represents customer orders	
*/
	protected class Order
	{
		int num;
		String address;
		int phoneNum;
		Furniture item;

		// CONSTRUCTOR
		protected Order(int num, int phoneNum, String address, Furniture item)
		{
			setNum(num);
			setPhoneNum(phoneNum);
			setAddress(address);
			setItem(item);
		}

		// toString Method


/** Overriden toString method	
*/
		public String toString()
		{		
			return String.format("Order no: " + getNum() + " , " + getItem() + ", Phone: " + getPhoneNum() + ", Address :" + getAddress());
		}

		// GETTERS
		protected int getNum()
		{
			return num;
		}
		protected int getPhoneNum()
		{
			return phoneNum;
		}
		protected String getAddress()
		{
			return address;
		}
		protected Furniture getItem()
		{
			return item;
		}

		// SETTERS
		protected void setNum(int num)
		{
			this.num = num;
		}
		protected void setPhoneNum(int phoneNum)
		{
			this.phoneNum = phoneNum;
		}
		protected void setAddress(String address)
		{
			this.address = address;
		}
		protected void setItem(Furniture item)
		{	
			this.item = item;
		}

	}
		
}