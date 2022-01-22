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
		if(furnitures == null)	throw new Error("There is no furnitures");
		else
		{	
		for(int i = 0; i < furnitures.length; i++)
		{
			System.out.println(furnitures[i]);
		}
	}
	}
	
/** 
 *	Branch Empoyee constructor
*/	
	public BranchEmployee ()
	{
		setName("Employee");
		setSurname("Employee");
		setEmail("Employee");
		setPassword("1234");
	}


/** 
 *	Method for seeing previous orders of a customer
*/	
	protected void seePreviousOrders(Customer c)
	{
		if(c.getPreviousOrders() == null)	System.out.println("There is no previous order for customer numbered " + c.getCustomerNum());
		else
		{
			for(int i = 0; i < c.getPreviousOrders().length; i++)
			System.out.println(c.getPreviousOrders()[i]);
		}
	}
/** 
 *	Method for registering customer in-Store
*/	
	protected Customer [] inStoreRegister(Customer [] list, String email, String name, String surname, String password)	throws Error
	{	
		int size;
		if(list == null)	size = 0;
		else size = list.length;
		Customer [] temp = new Customer [size + 1];

		if(size == 0)
		{
			temp[0] = new Customer(email, name, surname, size + 1, password);
		}
		else
		{
			for(Customer x : list)
			{
				if(email.equals(x.getEmail()))	throw new Error ("There is a customer with the same e-mail address");
			}
			System.arraycopy(list, 0, temp, 0, size);
			temp[size] = new Customer(email, name, surname, size + 1, password);
		}
		
		return temp;		
	}

/** 
 *	Method for registering customer in-Store
*/	
	protected void setOrder(Customer c, int phoneNum, String address, Furniture item)
	{
		int lim = c.getOrderNum();
		Customer.Order temp = c.new Order(lim + 1, phoneNum, address, item);
		c.addOrder(temp);	
	}

/** 
 *	This method sets a new wish to admin's wishlist
*/	
	protected void setWish(Administrator admin, int num, Furniture f)	// I couldn't understand how should i inform the manager.
	{
		System.out.println("Dear manager, there is lack of items in Branch : ");
		Administrator.wish temp = admin.new wish(num, f);
		admin.addWish(temp);
	}

/** 
 *	This method is for saling requsted furniture.If there is no furniture equals to requested one.
 *  Adds as many as needed to array and removes them.
*/	
	protected Furniture [] sale (Administrator admin, Furniture [] furnitures, Furniture item, int num)	throws Error
	{
		if(furnitures == null)	throw new Error("There is no furniture");
		else
		{

		int size = countFurniture(furnitures, item);
		if(size >= num)
		{	
			try
			{
				furnitures = removeFurniture(furnitures, item, num);
			}
			catch(Error e)
			{

			}
		}
		else
		{
			setWish(admin, num, item);
			furnitures = addFurniture(furnitures, item, num - size);
			try
			{
				furnitures = removeFurniture(furnitures, item, num);
			}
			catch(Error e)
			{
			}
			
		}
		System.out.println("You bought " + num + " " + item);
		return furnitures;
	}
	}
/** 
 *	This method is for adding the specific furniture to the specific branche's furniture list.  
 *  It adds as how many requested
*/
	protected Furniture [] addFurniture(Furniture [] list, Furniture item, int num)
	{
		int newSize = list.length + num;
		Furniture [] temp = new Furniture [newSize];
		System.arraycopy(list, 0, temp, 0, list.length);
			
		for(int i = 0; i < num; i++)
		{
			temp[list.length + i] = item;
		}

		System.out.println("You added " + num + " " + item);
		return temp;
	}

/** 
 *	This method is for removing the requested furnitures from the specific branche's list.
 *  If there are less selected furnitures in the array than requested amount, throws Exception.
*/
	protected Furniture [] removeFurniture(Furniture [] list, Furniture item, int num)	throws Error
	{
		int counter = 0;
		int arrayCounter = 0;
		Furniture [] temp = new Furniture[list.length - num];
		int lim = list.length - num;

		if(countFurniture(list, item) < num)	
		{
			throw new Error("There is not enough items");
		}
		else
		{
			for(int i = 0; i < list.length; i++)
			{
				if(counter < num && list[i].equals(item))
				{
					counter++;
				}
				else if(counter == num && list[i].equals(item))
				{
					temp[arrayCounter] = list[i];
					arrayCounter++;
				}
				else
				{
					temp[arrayCounter] = list[i];
					arrayCounter++;
				}
			}
		System.out.println("You removed " + num + " " + item);
		return temp;

	}
}
/** 
 *	Returns how many of the selected furniture is on the array.
*/
	protected int countFurniture(Furniture [] list , Furniture item)
	{
		int total = 0;
		for(int i = 0; i < list.length; i++)
		{
			if(item.equals(list[i]))	total++;
		}
		return total;
	}

/** User need to answer questions and select a furniture
 *  This method returns the selected furniture
*/
	protected Furniture selectFurniture(int branchNumber)	throws Error
	{
		int furniture;
		int model;
		int color;

		Scanner myObj = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		System.out.println("Select the furniture from the list");
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
			return new OfficeChair(model, branchNumber, color);
		}
		else if(furniture == 2)
		{
			System.out.println("Enter the Model Number(0-4)");
			model = myObj.nextInt();
			if(model < 0 || model > 4)	throw new Error("You entered an invalid model");
			System.out.println("Enter the Color Number(0-3)");
			color = myObj.nextInt();
			if(color < 0 || color > 3)	throw new Error("You entered an invalid color");
			return new OfficeDesk(model, branchNumber, color);
		}
		else if(furniture == 3)
		{
			System.out.println("Enter the Model Number(0-9)");
			model = myObj.nextInt();
			if(model < 0 || model > 9)	throw new Error("You entered an invalid model");
			System.out.println("Enter the Color Number(0-3)");
			color = myObj.nextInt();
			if(color < 0 || color > 3)	throw new Error("You entered an invalid color");
			return new MeetingTable(model, branchNumber, color);
		}
		else if(furniture == 4)
		{
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > 12)	throw new Error("You entered an invalid model");			
			return new Bookcase(model, branchNumber);
		}
		else if(furniture == 5)
		{
			System.out.println("Enter the Model Number(0-12)");
			model = myObj.nextInt();
			if(model < 0 || model > 12)	throw new Error("You entered an invalid model");			
			return new OfficeCabinet(model, branchNumber);
		}
		else
		{
			throw new Error("Wrong Furniture Type is Entered");
		}	
	}

}