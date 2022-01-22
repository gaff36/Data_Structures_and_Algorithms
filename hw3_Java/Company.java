import java.util.Scanner;

/** @author Emre Sezer
 * Company Main Class
*/

@SuppressWarnings("unchecked")
public class Company		// I desgined this class to test my code.With the menu method you can interact with my system.
{
	private KWLinkedList<Branch> branchList;
	private KWArrayList<User> userList;
	private boolean online = true;

	public Company(int num)
	{
		Administrator admin = new Administrator();
		branchList = new KWLinkedList<Branch>();	
		userList = new KWArrayList<User>();	
		try
		{
			admin.addAdministrator(userList, "admin","Emre", "Sezer", "1234");		/* Default administrator's email is "admin" and password is "1234"	*/
			for(int i = 0; i < num; i++)	admin.addBranch(branchList, userList);
		}
																						/* It is possible to add new Administrators to the system.			*/
		catch(Error E){}			
	}
	public Company()
	{
		branchList = new KWLinkedList<Branch>();	
		userList = new KWArrayList<User>();				
	}

/** 
 * This method is for adding new customer to Customer array and update the array.
*/	
	public Integer login (KWArrayList<User> userList)
	{	
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter email :");
		String email = obj.nextLine();
		System.out.println("Enter Password :");
		String password = obj.nextLine();

		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof Administrator)
			{	
				Administrator temp = (Administrator) userList.get(i);			
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a Administrator");
					return i;
				}
			}
			else if(userList.get(i) instanceof BranchEmployee)
			{
				BranchEmployee temp = (BranchEmployee) userList.get(i);
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a BranchEmployee");
					return i;
				}
			}
			else if(userList.get(i) instanceof Customer)
			{
				Customer temp = (Customer) userList.get(i);
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a Customer");
					return i;
				}
			}
		}
		return -1;
	}	

/** 
 * This method is for adding new customer to Customer array and update the array.
*/	
	public Integer loginAuto (KWArrayList<User> userList, String email, String password)
	{	
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i) instanceof Administrator)
			{	
				Administrator temp = (Administrator) userList.get(i);			
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a Administrator");
					return i;
				}
			}
			else if(userList.get(i) instanceof BranchEmployee)
			{
				BranchEmployee temp = (BranchEmployee) userList.get(i);
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a BranchEmployee");
					return i;
				}
			}
			else if(userList.get(i) instanceof Customer)
			{
				Customer temp = (Customer) userList.get(i);
				if(temp.getPassword().equals(password) && temp.getEmail().equals(email))
				{	
					System.out.println("You are logged in as a Customer");
					return i;
				}
			}
		}
		return -1;
	}

/** 
*	Menu Method
*/	
	public void menu ()
	{
		try
		{
		boolean logged = false;	 
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
					int x = login(userList);

					if(x != -1)		// If login is successful
					{
						Administrator admin = (Administrator) userList.get(x);
						while(online)
						{
							System.out.println("		ADMIN MENU");
							System.out.println("0) Logout");
							System.out.println("1) Add Branch");
							System.out.println("2) Remove Branch");
							System.out.println("3) Show Product List");
							System.out.println("4) Add Branch Employee");
							System.out.println("5) Remove Branch Employee");
							System.out.println("6) Show Users Registered to the System");
							System.out.println("7) See Wishes");
							System.out.println("Please enter a number :");
							select = obj.nextInt();

							int number;
							
							if(select == 0)	online = false;
							else if(select == 1)								// When you add a branch to the Branhch array, you also add a new BranchEmployee to system too.
							{													// That BranchEmployee can be accessed from that Branch.
								admin.addBranch(branchList, userList);
							}
							else if(select == 2)								// Removes the last element of the Branch array
							{
								admin.removeBranch(branchList, userList, branchList.size() -1);
							}
							else if (select == 3)								// Prints all of the furnitures in the store's inventory 
							{
								admin.showProductListEveryBranch(branchList);															
							}								
							else if(select == 4)		// Prints wishlist of the admin
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
								System.out.println("Enter Branch Number :");
								int branch = third.nextInt();
								admin.addBranchEmployee(branchList, userList, email,name,surname,password,branch);
							}
							else if(select == 5)		// Prints wishlist of the admin
							{	
								admin.removeBranchEmployee(branchList, userList);
							}		
							else if (select == 6)								
							{
								for(int i = 0; i < userList.size(); i++)	System.out.println(userList.get(i));																		
							}
							else if (select == 7)
							{
								admin.seeWishes();
							} 
						}
					}
					else System.out.println("Administrator Login Failed");
				}

			}
					
			else if(userSelect == 2)			
			{				
				System.out.println("		BRANCH EMPLOYEE LOGIN");
				System.out.println("0) Back");								// You need to write employeex as e-mail (x is index of Branch that employee is part of)
				System.out.println("1) Login");								// password is "1234"
				System.out.println("Please enter a number :");

				select = obj.nextInt();

				if(select == 0) 		// Gets back to main menu
				{	
					online = false;
				}
				else if(select == 1)									
				{																												
					int tempEmployeeNum = login(userList);

					if(tempEmployeeNum != -1)		// If login is successful
					{	
						BranchEmployee tempEmployee = (BranchEmployee) userList.get(tempEmployeeNum);

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
								Furniture f = tempEmployee.selectFurniture(branchList.size(), tempEmployee.getBranchNum());
								tempEmployee.addFurniture(branchList.get(tempEmployee.getBranchNum()).getFurnitureList(), f, x);
							}
							else if(select == 2)			// Removes the last element of the selected Branch's Furniture array.
							{
								System.out.println("Enter how many items you want :");
								int x = obj.nextInt();
								Furniture f = tempEmployee.selectFurniture(x, tempEmployee.getBranchNum());
								tempEmployee.removeFurniture(branchList.get(tempEmployee.getBranchNum()).getFurnitureList(), f, x);							
							}
							else if (select == 3)			// Prints all of the furnitures in the selected Branch.
							{
								System.out.println("PRODUCT LIST :");	
								tempEmployee.showProductList(branchList.get(tempEmployee.getBranchNum()).getFurnitureList());						
															
							}
							else if(select == 4)		// See previous orders of a selected customer.
							{	
								System.out.println("Enter the customer number :");
								int k = obj.nextInt();
								tempEmployee.seePreviousOrders(userList, k);
							}			
							else if(select == 5)		// Creates a new customer
							{
								tempEmployee.inStoreRegister(userList);
							}

					}

				}
				else System.out.println("Branch Employee Login Failed");

			}	
		}					
			else if(userSelect == 3)
			{
				Customer temp = new Customer();
				Integer userIndex = -1;

				while(online)
				{
				System.out.println("		CUSTOMER MENU");
				System.out.println("0) Back");
				System.out.println("1) Show Products and Buy");
				System.out.println("2) Show Product List");
				System.out.println("3) Register");
				System.out.println("4) Login");
				System.out.println("5) See Previous Orders");
				System.out.println("6) Search For Furniture in the Specific Branch");
				System.out.println("Please Type the Action You Want:");

				select = obj.nextInt();

				if(select == 0)	online = false;			// Gets back to main menu
				
				else if(select == 1)					// Sales furniture
				{	
					System.out.println("Dou want to buy in-store or online? (0 for online, 1 for in-Store)");
					System.out.println("Please enter a number :");
					int o;
					o = obj.nextInt();	
							
					if(o == 0 && !logged)
					{
						System.out.println("First, you need login or register to buy");
					}
							
					else if(o == 1 && !logged)			// Creates a new customer and buys furniture for him/her.
					{
						if(branchList.size() == 0)	throw new Error("There is no branches");
						else 
						{
							temp = new Customer();
							int check;	

							System.out.println("Enter how many items you want to buy :");
							int bb = obj.nextInt();	
							Furniture f = temp.selectFurniture(branchList.size());
							BranchEmployee y = new BranchEmployee("", "", "", "", -1);
					
							if(!logged)	
							{
								for(int i = 0; i < userList.size(); i++)
								{
									if(userList.get(i) instanceof BranchEmployee)
									{
										y = (BranchEmployee) userList.get(i);
										if(y.getBranchNum() == f.getWhichBranch())
										{	
											y.inStoreRegister(userList);
											check = i;
											break;
										}
									}
								}
								temp = (Customer) userList.get(userList.size() - 1);
							}
						
						System.out.println("Enter phone :");
						int phone = obj.nextInt();
						System.out.println("Enter address :");
						String add = obj2.nextLine();
						y.sale(userList, branchList.get(f.getWhichBranch()).getFurnitureList(), f, bb);
						temp.addOrder(phone, add, f, bb);
						logged = true;										
						}								
					}		
					else if(logged)		// Buys furniture
					{
						Furniture f = temp.selectFurniture(branchList.size());
						System.out.println("Enter How Many You Want :");
						int howMany = obj.nextInt();
						System.out.println("Enter phone :");
						int phone = obj.nextInt();
						System.out.println("Enter address :");
						String add = obj2.nextLine();
						temp.purchase(userList, branchList, f, phone, add, howMany);				
					}									
				}
							
				else if(select == 2)		// Prints all of the furnitures in the store's inventory 
				{
					if(branchList.size() == 0)
					{
						System.out.println("There is no branches");
					}
					else
					{
						temp.showProductListEveryBranch(branchList);
					}
				}
						
				else if(select == 3)		// Register new customer to system
				{
					if(!logged)
					{						
						temp.registerCustomer(userList);				
						temp = (Customer) userList.get(userList.size() - 1);
						logged = true;		
					}
					else 	System.out.println("You are already logged in");
				}
							
				else if(select == 4)		// Customer login to system
				{
					if(logged)	System.out.println("You are already logged in");
					else if(userList.size() == 0)	System.out.println("There is no users in the system");
					else if(userList.size() > 0)
					{					
						boolean loginCheck = false;			
						Scanner fourth = new Scanner(System.in);
						
						online = true;
						userIndex = login(userList);
						if(userIndex != -1)
						{
							logged = true;
							temp = (Customer) userList.get(userIndex);
						}	
						else System.out.println("Login failed");			
					}														
				}			
				else if(select == 5)		// See previous orders of specific customer
				{
					if(logged)
					{
						temp.showPreviousOrders();
					}
					
					else
					{
						System.out.println("First, you need login or register \n");
					}
					
				}
				else if (select == 6)
				{
					Furniture t = temp.selectFurniture(branchList.size());
					if(temp.searchFurniture(branchList, t, t.getWhichBranch()))	System.out.println("There is " + t + " in the Branch");
					else System.out.println("There is no " + t + " in the Branch");					
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
		
/** 
*	Driver Method
*/		
	public void driver()
	{
		Administrator admin2 = new Administrator();
		try
		{
			System.out.println("ADMINISTRATOR TESTS");	
			System.out.println("Testing adding Administrator to the System :");	
			admin2.addAdministrator(userList, "admin","Emre", "Sezer", "1234");	// By default number of branches is 4
			admin2 = (Administrator) userList.get(userList.size() - 1);

			System.out.println("\nTesting loging in to the System as an Administrator with wrong inputs :");
			if(loginAuto(userList, "asdas", "asdas") == -1)	System.out.println("Login Failed");				// Succesfull Login
			System.out.println("\nTesting loging in to the System as an Administrator with correct inputs :");	
			if(loginAuto(userList, "admin", "1234") == -1)	System.out.println("Login Failed");				// Unsuccesfull Login

			System.out.println("\nTesting Removing Branch.There is no Branches in the System.So, expecting to throw an exception :");
			admin2.removeBranch(branchList, userList, 4);
		}
		catch(Error e)
		{}
		catch(java.util.InputMismatchException e)
		{}

		try
		{
			System.out.println("\nTesting Seeing List of the Furnitures in Each Branch :"); 
			admin2.showProductListEveryBranch(branchList);	
		}
		catch(java.util.InputMismatchException e){}	catch(Error e){}

		try
		{						

		System.out.println("\nTesting adding Branch to the System :");	
		admin2.addBranch(branchList, userList);
		admin2.addBranch(branchList, userList);
		admin2.addBranch(branchList, userList);
		System.out.println("\nTesting adding BranchEmployee :");
		admin2.addBranchEmployee(branchList, userList, "kanye", "Kanye", "West", "1234", 0);				
		System.out.println(userList.get(userList.size() - 1));
		System.out.println("\nTesting Removing an existing BranchEmployee :");																		
		admin2.removeAutoBranchEmployee(branchList, userList, "kanye", 0);		// Succesfully removes
		System.out.println("\nTesting Removing not existing BranchEmployee. Expecting to throw an exception :");
		admin2.removeAutoBranchEmployee(branchList, userList, "hsbdhas", 4);	// Remove unsuccesfull
		admin2.removeBranch(branchList, userList, 2);																						
		}
		catch(java.util.InputMismatchException e)
		{}
		catch(Error e)
		{}

		try
		{
			System.out.println("\nTesting Seeing List of the Furnitures in Each Branch :"); 
			admin2.showProductListEveryBranch(branchList);										// Shows Furnitures in Every Branch	

			System.out.println("\nTesting Seeing Wishes For the Administrators (wish is for querying.If there is lack of items in branch a wish will be created):");
			admin2.seeWishes();	

			System.out.println("\nTesting Seeing List of the Users in the System :"); 
			admin2.showUsers(userList);
		}
		catch(Error e)
		{}
		catch(java.util.InputMismatchException e)
		{}
		System.out.println("\nBRANCHEMPLOYEE TESTS");
		System.out.println("You need to type a key to continue");

		Scanner obj = new Scanner(System.in);
		obj.nextLine();
		BranchEmployee be = new BranchEmployee();

		
		try
		{
			System.out.println("Testing adding BranchEmployee to the Branch with wrong inputs:");	
			be.registerBranchEmployee(branchList, userList, "burcu", "Burcu", "Demir", "1234", 4);
			System.out.println("Testing adding BranchEmployee to the Branch with correct inputs:");
			be.registerBranchEmployee(branchList, userList, "burcu", "Burcu", "Demir", "1234", 0);
			be = (BranchEmployee) userList.get(userList.size() - 1);

			System.out.println("\nTesting loging in to the System as an BranchEmployee with wrong inputs :");		// Succesfull Login						
			if(loginAuto(userList, "asdas", "fgasd") == -1)	System.out.println("Login Failed");		
			System.out.println("\nTesting loging in to the System as an BranchEmployee with correct inputs :");		// Unsuccesfull Login
			if(loginAuto(userList, "burcu", "1234") == -1)	System.out.println("Login Failed");
				
			System.out.println("\nTesting Adding Furniture to the Branch's List :");					
			be.addFurniture(branchList.get(be.getBranchNum()).getFurnitureList(), new Bookcase(3, be.getBranchNum()), 2);		// Adds Furniture to the Branch's list	
			System.out.println("\nTesting Removing existing Furnitures from the Branch's List :");
			be.removeFurniture(branchList.get(be.getBranchNum()).getFurnitureList(), new Bookcase(9,be.getBranchNum()), 2);		// Succesfull remove
			System.out.println("\nTesting Removing not existing Furnitures from the Branch's List :");
			be.removeFurniture(branchList.get(be.getBranchNum()).getFurnitureList(), new Bookcase(0,be.getBranchNum()), 2);		// Unsuccesfull remove
		}
		catch(Error e)
		{}
		catch(java.util.InputMismatchException e)
		{}
		try
		{
			System.out.println("\nTesting Counting Selected Furniture in the selected Branch's List :");
			Bookcase b = new Bookcase(9,be.getBranchNum());
			Bookcase c = new Bookcase(0,be.getBranchNum());
			System.out.println("There are " + be.countFurniture(branchList.get(be.getBranchNum()).getFurnitureList(), b) + " "+ b +" in the Branch " + be.getBranchNum());	// Furniture Search Tests
			System.out.println("There are " + be.countFurniture(branchList.get(be.getBranchNum()).getFurnitureList(), c) + " "+ c +" in the Branch " + be.getBranchNum());
			be.seePreviousOrders(userList, 0);		// Unsuccesful order check
			System.out.println("\nTesting Seeing List of  Furniture in the Branch :");
			be.showProductList(branchList.get(be.getBranchNum()).getFurnitureList());

		}	
		catch(java.util.InputMismatchException e)
		{}	

		System.out.println("\nCUSTOMER TESTS");
		System.out.println("You need to type a key to continue");
		obj.nextLine();

		Customer ce = new Customer();

		try
		{
			System.out.println("Testing adding Customer to the System :");
			ce.addCustomer(userList, "emre", "Emre", "Sezer", "1234");
			ce = (Customer) userList.get(userList.size() - 1);

			System.out.println("\nTesting loging in to the System as an Customer with correct inputs :");	
			if(loginAuto(userList, "asda", "asdas") == -1)	System.out.println("Login Failed");						// Unsuccesfull Login
			System.out.println("\nTesting loging in to the System as an Customer with wrong inputs :");
			if(loginAuto(userList, "emre", "1234") == -1)	System.out.println("Login Failed");						// Succesfull Login	
			
			OfficeChair a = new OfficeChair(2, 0, 3);		// There is no 'a' in the 0'th Branch's List
			Bookcase b = new Bookcase(9,0);					// There is 'b' in the 0'th Branch's List	 
			Bookcase c = new Bookcase(0,6);					// There is no Branch with index 6

			System.out.println("\nTesting Purchasing Furniture With Branch That Doesn't Exist: ");
			System.out.println("Some messages will be printed on screen.");
			ce.purchase(userList, branchList, c, 123123, "Kadikoy", 2);

			System.out.println("\nTesting Purchasing Furniture With Item That Doesn't Exist in the Specific Branch:");
			ce.purchase(userList, branchList, a, 123123, "Kadikoy", 2);

			System.out.println("\nTesting Purchasing Furniture With Item That Doesn't Exist in the Specific Branch:");
			ce.purchase(userList, branchList, b, 123123, "Kadikoy", 2);

			System.out.println("\nTesting Previous Orders :");
			ce.showPreviousOrders();

			OfficeChair d = new OfficeChair(3, 0, 3);		// There is no 'd' in the 0'th Branch's List

			System.out.println("\nTesting Search Furniture with Item That Doesn't Exist in the Specific Branch :");
			if(ce.searchFurniture(branchList, d, 0)) System.out.println("There is " + d + " in the Branch");
			else System.out.println("There is no " + d + " in the Branch 0");

			System.out.println("\nTesting Search Furniture with Item That Exists in the Specific Branch :");
			if(ce.searchFurniture(branchList, a, 0)) System.out.println("There is " + a + " in the Branch");
			else System.out.println("There is no " + a + " in the Branch 0");

			System.out.println("\nTesting Listing Items in Each branch :");
			ce.showProductListEveryBranch(branchList);

			System.out.println("\nOTHER TESTS");
			System.out.println("You need to type a key to continue");
			obj.nextLine();

			System.out.println("\nTesting Seeing Wishes of the Administrator:");
			admin2.seeWishes();

		}
		catch(Error e)
		{}
		catch(java.util.InputMismatchException e)
		{}

	}
								
}