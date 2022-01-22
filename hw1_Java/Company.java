import java.util.Scanner;

/** @author Emre Sezer
 * Company Class for testing
 * It has Branch array, Customer array, Administrator, Customer (activeCustomer) and setters/getters
 * It has a menu function that represents the system
*/
public class Company		// I desgined this class to test my code.With the menu method you can interact with my system.
{
	private Branch [] branches;
	private Customer [] customerList;
	private Customer activeCustomer;
	private boolean online;
	private boolean logged = false;
	private Administrator admin;

	public Company()
	{
		admin = new Administrator();		
		branches = admin.addBranch(branches, new Branch(1));
		branches = admin.addBranch(branches, new Branch(2));
		branches = admin.addBranch(branches, new Branch(3));
		branches = admin.addBranch(branches, new Branch(4));
	}

	// GETTERS

	public Branch [] getBranches()
	{
		return branches;
	}
	public Customer [] getCustomerList()
	{
		return customerList;
	}
	public Customer getActiveCustomer()
	{
		return activeCustomer;
	}
	public Administrator getAdmin()
	{
		return admin;
	}

	// FUNCTIONS

/** 
 * This method is for adding new customer to Customer array and update the array.
*/
	public Customer [] addCustomer(String email, String name, String surname, String password)	throws Error
	{
		Customer [] temp;

		if(customerList == null)
		{
			temp = new Customer [1];
			temp[0] = new Customer(email, name, surname, 1, password);	
		}
		else
		{
			for(Customer x : customerList)
			{
				if(email.equals(x.getEmail()))	throw new Error ("There is a customer with the same e-mail address");
			}

			temp = new Customer [customerList.length + 1];
			System.arraycopy(customerList, 0, temp, 0, customerList.length);
			temp[customerList.length] = new Customer(email, name, surname, customerList.length + 1, password);
		}
		return temp;
	}
	
/** 
 * This method is for testing the homework
*/				
	public void menu ()
	{
		try
		{
		online = true;
		int mainMenu;
		int select;
		String mail;
		String pass;
		int n;
		boolean online = true;

		while(true)
		{
			online = true;
			System.out.println("		WELCOME TO MAIN MENU");
			System.out.println("Please Select the User Type:");		// You can login to system as Administrator, BranchEmployee and Customer.
			System.out.println("0) Exit:");							// You need to enter the number.
			System.out.println("1) Admin:");
			System.out.println("2) Branch Employee:");
			System.out.println("3) Customer:");

			Scanner obj = new Scanner(System.in);
			Scanner obj2 = new Scanner(System.in);
			Scanner obj3 = new Scanner(System.in);
			int userSelect = obj.nextInt();

			if(userSelect == 0)		// Closes the program
			{
				System.exit(0);	
			}
			else if(userSelect == 1)
			{
				System.out.println("		ADMIN LOGIN");			// Administrator Login				
				System.out.println("0) Back");
				System.out.println("1) Login");
				System.out.println("Please enter a number :");
				select = obj.nextInt();

				if(select == 0)				// Gets back to main menu
				{
					online = false;
				} 
				else if(select == 1)		// Login for Administrator
				{
					System.out.println("Enter email :(You need to enter admin)");	// email is "admin"
					mail = obj2.nextLine();
					System.out.println("Enter password :(You need to enter 1234)");	// password is "1234"
					pass = obj2.nextLine();

					if(getAdmin().adminLogin(mail, pass) == true)		// If login is successful
					{
						while(online)
						{
							System.out.println("		ADMIN MENU");
							System.out.println("0) Logout");
							System.out.println("1) Add Branch");
							System.out.println("2) Remove Branch");
							System.out.println("3) Show Product List");
							System.out.println("4) Show Wishes");
							System.out.println("Please enter a number :");
							select = obj.nextInt();

							int number;
							
							if(select == 0)	online = false;
							else if(select == 1)								// When you add a branch to the Branhch array, you also add a new BranchEmployee to system too.
							{													// That BranchEmployee can be accessed from that Branch.
								Branch temp;
								if(getBranches() == null)	number = 0;			// Adds new branch to Branch array
								else number = getBranches().length;
								branches = getAdmin().addBranch(branches, new Branch(number + 1));
							}
							else if(select == 2)								// Removes the last element of the Branch array
							{
								branches = getAdmin().removeBranch(branches);
							}
							else if (select == 3)								// Prints all of the furnitures in the store's inventory 
							{
								if(branches == null)
								{
									System.out.println("There is no items");
								}
								else
								{
									System.out.println("PRODUCT LIST :");
									for(Branch temp : branches)
									{
										getAdmin().showProductList(temp.getFurnitures());
									}
								}
							}	
							else if(select == 4)		// Prints wishlist of the admin
							{	
								if(getAdmin().getWishNum() == 0)	System.out.println("There is no wishes for admin");
								for(int i = 0; i < admin.getWishNum(); i++)
								System.out.println(admin.getWishList()[i]);
							}
						}
					}
				}

			}
			else if(userSelect == 2)			// There is only one BranchEmployee in each branch.
			{
				System.out.println("		BRANCH EMPLOYEE LOGIN");
				System.out.println("0) Back");
				System.out.println("1) Login");
				System.out.println("Please enter a number :");

				select = obj.nextInt();

				if(select == 0) 		// Gets back to main menu
				{	
					online = false;
				}
				else if(select == 1)									
				{														// Branch numbers are started from 1.Let's say there are 3 branches in the system.
																		// branches[0]'s Branch Number is 1.branches[1]' one is 2 and branches[2]' one is 3.
																		// If we add a new branch branches[3]'s Branch Number will be 4.
					System.out.println("Enter Branch Number :");		// You need to enter a number.Number is need to be between 1 and total Branches in the branches array.
					n = obj.nextInt();									// Whenever a new branch is added to array, That branch's number is increased by one.
					System.out.println("Enter password :(You need to enter 1234)");		// By default there are 4 branches.When you add a new branch new branch's number will be 5.If you remove it it will be 3.
					pass = obj2.nextLine();

					if(getBranches().length >= n && pass.equals(getBranches()[0].getBranchEmployee().getPassword()))		// If login is successful
					{		
						while(online)
						{
							System.out.println("		BRANCH EMPLOYEE MENU");
							System.out.println("0) Logout");
							System.out.println("1) Add Furniture");
							System.out.println("2) Remove Furniture");
							System.out.println("3) Show Product List");
							System.out.println("4) See Previous Orders of Customer");
							System.out.println("5) Create New Customer");
							System.out.println("Please enter a number :");
							select = obj.nextInt();

							if(select == 0)	online = false;
							else if(select == 1)
							{
								System.out.println("Enter how many items you want :");			// Adds new furniture to Furniture array of the selected Branch's .
								int x = obj.nextInt();
								Furniture f = getBranches()[n - 1].getBranchEmployee().selectFurniture(n);
								getBranches()[n - 1].setFurnitures(getBranches()[n - 1].getBranchEmployee().addFurniture(getBranches()[n - 1].getFurnitures(), f, x));
							}
							else if(select == 2)			// Removes the last element of the selected Branch's Furniture array.
							{
								System.out.println("Enter how many items you want :");
								int x = obj.nextInt();
								Furniture f = getBranches()[n - 1].getBranchEmployee().selectFurniture(n);
								getBranches()[n - 1].setFurnitures(getBranches()[n - 1].getBranchEmployee().removeFurniture(getBranches()[n - 1].getFurnitures(), f, x));
							}
							else if (select == 3)			// Prints all of the furnitures in the selected Branch.
							{
								System.out.println("PRODUCT LIST :");								
								getBranches()[n - 1].getBranchEmployee().showProductList(getBranches()[n - 1].getFurnitures());								
							}
							else if(select == 4)		// See previous orders of a selected customer.
							{	
								System.out.println("Enter the customer number :");
								int k = obj.nextInt();
								if(customerList == null || customerList.length < k)	System.out.println("There is no such a customer");
								else branches[n - 1].getBranchEmployee().seePreviousOrders(customerList[k - 1]);
							}
							else if(select == 5)		// Creates a new customer
							{
								if(branches == null)	throw new Error("There is no branches");
								else 
								{							
									System.out.println("Enter email :");
									String email = obj2.nextLine();
									System.out.println("Enter Name :");
									String name = obj2.nextLine();
									System.out.println("Enter Surname :");
									String surname = obj2.nextLine();
									System.out.println("Enter Password :");
									String password = obj2.nextLine();
									System.out.println("Enter phone :");
									int phone = obj2.nextInt();
									System.out.println("Enter address :");
									String add = obj3.nextLine();
									Furniture f = branches[n - 1].getBranchEmployee().selectFurniture(n);
									System.out.println("Enter how many of the selected furniture you want to buy :");
									int number = obj3.nextInt();
									customerList = branches[n - 1].getBranchEmployee().inStoreRegister(customerList, email, name, surname, password);
									getBranches()[f.getWhichBranch() - 1].setFurnitures(branches[f.getWhichBranch() - 1].getBranchEmployee().sale(admin, branches[f.getWhichBranch() - 1].getFurnitures(), f, number));	
									branches[n - 1].getBranchEmployee().setOrder(customerList[customerList.length - 1], phone, add, f);							
							}

						}

					}

				}
				else System.out.println("Branch Employee Login Failed");

			}
		}
			else if(userSelect == 3)
			{
				while(online)
				{
				System.out.println("		CUSTOMER MENU");
				System.out.println("0) Back");
				System.out.println("1) Show Products and Buy");
				System.out.println("2) Show Product List");
				System.out.println("3) Register");
				System.out.println("4) Login");
				System.out.println("5) See Previous Orders");
				System.out.println("Please Type the Action You Want:");

				select = obj.nextInt();

				if(select == 0)	online = false;			// Gets back to main menu
				else if(select == 1)					// Sales furniture
				{	
					System.out.println("Dou want to buy in-store or online? (0 for online, 1 for in-Store)");
					System.out.println("Please enter a number :");
					int o;
					o = obj.nextInt();

					Customer temp = new Customer();			
							
					if(o == 0 && !logged)
					{
						System.out.println("First, you need login or register to buy");
					}
					else if(o == 1 && !logged)			// Creates a new customer and buys furniture for him/her.
					{
						if(branches == null)	throw new Error("There is no branches");
						else 
						{
						Furniture f;								
						System.out.println("Enter email :");
						String email = obj2.nextLine();
						System.out.println("Enter Name :");
						String name = obj2.nextLine();
						System.out.println("Enter Surname :");
						String surname = obj2.nextLine();
						System.out.println("Enter Password :");
						String password = obj2.nextLine();
						System.out.println("Enter phone :");
						int phone = obj2.nextInt();
						System.out.println("Enter address :");
						String add = obj3.nextLine();
						System.out.println("Enter the branch Number:");
						int bb = obj2.nextInt();				
						customerList = branches[bb - 1].getBranchEmployee().inStoreRegister(customerList, email, name, surname, password);
						Furniture ff = branches[bb - 1].getBranchEmployee().selectFurniture(bb);
				 		System.out.println("Enter how many of the selected furniture you want to buy :");
						int number = obj3.nextInt();
						getBranches()[ff.getWhichBranch() - 1].setFurnitures(branches[ff.getWhichBranch() - 1].getBranchEmployee().sale(admin, branches[ff.getWhichBranch() - 1].getFurnitures(), ff, number));	
						branches[bb - 1].getBranchEmployee().setOrder(customerList[customerList.length - 1], phone, add, ff);	
						}								
					}
					else if(logged)		// Buys furniture
					{
						int totalBranch;
						int howMany;
						if(branches == null)	totalBranch = 0;
						else totalBranch = branches.length;
						Furniture f = temp.selectFurniture(totalBranch);
						if(branches == null)	throw new Error("There is no branches");
						System.out.println("Enter how many of the selected furniture you want to buy :");
						howMany = obj.nextInt();
						
						System.out.println("Enter phone :");
						int phone = obj.nextInt();
						System.out.println("Enter address :");
						String add = obj2.nextLine();
						getBranches()[f.getWhichBranch() - 1].setFurnitures(branches[f.getWhichBranch() - 1].getBranchEmployee().sale(admin, branches[f.getWhichBranch() - 1].getFurnitures(), f, howMany));
						branches[f.getWhichBranch() - 1].getBranchEmployee().setOrder(getActiveCustomer(), phone, add, f);
					}									
				}
				
				else if(select == 2)		// Prints all of the furnitures in the store's inventory 
				{
					if(branches == null)
					{
						System.out.println("There is no items");
					}
					else
					{
						Customer t = new Customer();
						System.out.println("PRODUCT LIST :");
						System.out.println(branches.length);
						for(Branch temp : branches)
						{
							t.showProductList(temp.getFurnitures());
						}
					}
				}

				else if(select == 3)		// Register new customer to system
				{
					if(!logged)
					{
						Customer temp = new Customer();
						Scanner third = new Scanner(System.in);
						System.out.println("Enter email :");
						String email = third.nextLine();
						System.out.println("Enter Name :");
						String name = third.nextLine();
						System.out.println("Enter Surname :");
						String surname = third.nextLine();
						System.out.println("Enter Password :");
						String password = third.nextLine();
						customerList = addCustomer(email, name, surname, password);
						activeCustomer = temp.loginCustomer(email, password, logged, customerList);
						logged = true;
					}
					else 	System.out.println("You are already logged in");
				}

				else if(select == 4)		// Customer login to system
				{
					if(logged)	System.out.println("You are already logged in");
					else if(getCustomerList() == null)	System.out.println("There is no customers in the system");
					else if(getCustomerList().length > 0)
					{
						Customer temp = new Customer();
						boolean loginCheck = false;			
						Scanner fourth = new Scanner(System.in);
						System.out.println("Enter email :");
						String email2 = fourth.nextLine();
						System.out.println("Enter Password :");
						String password2 = fourth.nextLine();
						online = true;
						activeCustomer = temp.loginCustomer(email2, password2, logged, customerList);
						if(activeCustomer.getCustomerNum() != -1)	loginCheck = true;
						else System.out.println("Login failed");								
					}
		
				}
					
				else if(select == 5)		// See previous orders of specific customer
				{
					if(getActiveCustomer().getOrderNum() == 0)	System.out.println("There is no previous orders");
					else if(logged)
					{
						for(int i = 0; i < getActiveCustomer().getOrderNum(); i++)
						System.out.println(getActiveCustomer().getPreviousOrders()[i]);
					}
					
					else
					{
						System.out.println("First, you need login or register \n");
					}
					
				}
			}
			}
			logged = false;
		}
		}
	
		catch(Error e)
		{}
		catch(java.util.InputMismatchException e)
		{}
	}
			
	public static void main (String [] argv)
	{
		Company comp = new Company();
		comp.menu();
	}									
}