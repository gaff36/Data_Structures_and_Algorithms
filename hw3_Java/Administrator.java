import java.util.Scanner;
import java.util.*;

/** 
 * @author Emre Sezer
 * Administrator class.Represents admins.
*/

public class Administrator implements User
{
	public static KWHybridList<wish> wishList = new KWHybridList<wish>();
	private String email;
	public String name;
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

	// SETTERS

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
		return String.format("Administrator: " + getEmail() + ", Name: " + getName() + ", Surname: " + getSurname());
	}
	
	public void showUsers(KWArrayList<User> userList)
	{
		for(int i = 0; i < userList.size(); i++)
		{
			System.out.println(userList.get(i));
		}
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
 *	Prints all wishes from wishList.
*/

	public void seeWishes()
	{
		if(wishList.size() == 0)	System.out.println("There is no wishes");
		for(int i = 0; i < wishList.size(); i++)	System.out.println(wishList.get(i));
	}

/** 
*	Administrator constructor.As default, i set email "admin" and password "1234"
*/	
	public Administrator()
	{
		setName("Emre");
		setSurname("Sezer");
		setEmail("admin");
		setPassword("1234");
	}

/** Administrator Constructor.
 * @param email String
 * @param name String
 * @param surname String 
 * @param password String
*/
	public Administrator(String email, String name, String surname, String password)
	{	
		setEmail(email);
		setName(name);
		setSurname(surname);
		setPassword(password);
	}

/** Adds a new Administrator to userList.
 * @param  userList KWArrayList<User> that keeps all the users in the system.
 * @param email String
 * @param name String
 * @param surname String 
 * @param password String
*/	
	
	public void addAdministrator(KWArrayList<User> userList, String email, String name, String surname, String password)	throws Error
	{		
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof Administrator)
			{
				if(email.equals(userList.get(i).getEmail()))	throw new Error ("There is a user with the same e-mail address");
			}
		}
		Administrator temp = new Administrator(email, name, surname, password);
		userList.add(temp);
		System.out.println(temp + " is added to the System");
	}

/** Registers a new Administrator to the system.
 * @param  userList KWArrayList<User> that keeps all the users in the system.
*/
	public void registerAdministrator(KWArrayList<User> userList)
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
			addAdministrator(userList, email, name, surname, password);
		}
		catch(Error e){}
	}

/**
*	Adds a new Branch to the system.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*/
	
	public void addBranch(KWLinkedList<Branch> branchList, KWArrayList<User> userList)
	{
		branchList.add(branchList.size(), new Branch());
		int last = branchList.size() - 1;
		addDefaultBranchEmployee(branchList, userList, Branch.totalBranches);		
		System.out.println("Administrator added a new Branch");	
	}			

/**
*	Removes a specific Branch from the system.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*	@param  index int
*/
	public void removeBranch(KWLinkedList<Branch> branchList, KWArrayList<User> userList, int index)	throws Error
	{
		if(index >= branchList.size() || index < 0)	throw new Error("There is no Branch with that index");
		else if (branchList.size() == 0)	throw new Error("There is no Branch in the list");
		else 
		{
			branchList.remove(index);
			ListIterator it = userList.iterator();

			while(it.hasNext())
			{
				if(it.next() instanceof BranchEmployee)
				{
					it.previous();
					BranchEmployee x = (BranchEmployee) it.next();
					if(x.getBranchNum() == index)
					{
						System.out.println("Removed the Branch with index " + index);
						it.remove();	
						return;	
					}
							
				}
			}
		}
		System.out.println("There is no such a Branch with that index");
	}

/**
*	Adds a default Branch Employee to the system.It is called whenever addBranch() method is called.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*/
	public void addDefaultBranchEmployee(KWLinkedList<Branch> branchList, KWArrayList<User> userList, int branch)
	{		
		String mail = "employee" + String.valueOf(Branch.totalEmployees);	// i set default BranchEmployee's email to "employeex" x is index of Branch employee is part of.
		String name = "Ahmet";												// By default i create a BranchEmployee for each Branch.I call them default BranchEmployee
		String sur = "Sonuc";												
		String pass = "1234";			// i set password every default BranchEmployee's password to "1234"

		Branch.totalEmployees++;
		BranchEmployee temp = new BranchEmployee(mail, name, sur, pass, branch - 1);
		userList.add(temp);
	}


/**
*	Adds a Branch Employee to the system.Branch Employee information should be entered.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*/

	public void addBranchEmployee(KWLinkedList<Branch> branchList, KWArrayList<User> userList, String mail, String name, String surname, String password, int branch)
	{
		boolean flag = true;

		for(int i = 0; i < userList.size(); i++)
		{
			if(mail.equals(userList.get(i).getEmail()))
			{
				flag = false;
				System.out.println("There is a user with the same e-mail address");
				return;
			}
		}
		if(flag == true)
		{
			
			BranchEmployee temp = new BranchEmployee(mail, name, surname, password, branch);
		
			try
			{
				temp.registerBranchEmployee(branchList, userList, mail, name, surname, password, branch - 1);
			}
			catch(Error e)
			{
				System.out.println("There is no such a branch");
			}
			
		}

	}

/**
*	Removes a Branch Employee from the system.Branch Employee information should be entered.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*/		

	public void removeAutoBranchEmployee(KWLinkedList<Branch> branchList, KWArrayList<User> userList, String email, int branchNum)
	{
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof BranchEmployee)
			{
				BranchEmployee x = (BranchEmployee) userList.get(i);
		
				if(email.equals(x.getEmail()) && branchNum == x.getBranchNum())
				{
					System.out.println("You removed " + x.getName() + " " + x.getSurname() + ", " + x.getEmail() + " from the system");
					userList.remove(i);
					return;
				}
			}
		}

		System.out.println("There is no such a user");
	}


/**
*	Removes a Branch Employee from the system.Branch Employee information should be entered.
*   @param  branchList KWLinkedList <Branch> that keeps all the branches in each branch. 
*	@param  userList KWArrayList<User> that keeps all the users in the system.
*/	
	public void removeBranchEmployee(KWLinkedList<Branch> branchList, KWArrayList<User> userList)
	{
		Scanner myObj = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		int branch;
		String mail;

		System.out.println("Enter Branch Number:");
		branch = myObj.nextInt();

		if(branch < 0 || branch >= branchList.size())
		{
			System.out.println("There is no such a branch");
			return;
		}	

		System.out.println("Enter the e-mail address :");
		mail = myObj2.nextLine();

		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof BranchEmployee)
			{
				BranchEmployee x = (BranchEmployee) userList.get(i);
		
				if(mail.equals(x.getEmail()) && branch == x.getBranchNum())
				{
					System.out.println("You removed " + x.getName() + " " + x.getSurname() + " from the system");
					userList.remove(i);
					return;
				}
			}
		}

		System.out.println("There is no such a user");
	}
	
/** 
*	Outer class for Administrator.Represents wishes.Wish is needed when there is a lack of requested furnitures in the branch.
*   Branch Employee sends a wish to Administrator.So, Administrator can see the situation.
*/
	public static class wish
	{
		private int num;
		private Furniture furn;

/** 
*	Constructor for wish
 * @param num int 
 * @param f Furniture
*/
		public wish(int num, Furniture f)
		{
			setNum(num);
			setFurn(f);
		}

/** 
*	Getter method for num
*/
		public int getNum()
		{
			return num;
		}

/** 
*	Getter method for furn
*/
		public Furniture getFurn()
		{
			return furn;
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
*	Setter method for furn
*   @param furn Furniture 
*/
		public void setFurn(Furniture furn)
		{
			this.furn = furn;
		}

/** 
*	Overriden toString Method
*/
		public String toString()
		{		
			return String.format("Item number: " + getNum() + " Furniture : " + getFurn());
		}

	}

}
