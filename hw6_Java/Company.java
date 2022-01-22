import java.io.*;
import java.lang.*;
import java.util.*;

/**
*  @author Emre Sezer
*  Company Class. This is the Main Class For HW6.
*  This Class Includes the menu method.
*/
public class Company implements CompanyInterface
{
	HashMap<Integer, User> userData;

/**
*  	Constructor For Company
*/
	public Company()
	{
		readCSVFile();
		setUserData();
	}

/**
*  	Reads "users.txt" and stores User's at the Hash Table
*/
	public void setUserData()
	{
		userData = new HashMap<Integer, User>();

		try
		{
			BufferedReader br = new BufferedReader(new FileReader("users.txt")); 
			String line = "";  
			String splitBy = ";"; 
			while ((line = br.readLine()) != null)   
			{
				String[] temp = line.split(splitBy);

				if(temp[0].equals("Trader"))
				{
					Trader trader = new Trader(temp[1], Integer.parseInt(temp[2]), temp[3]);
					userData.put(Integer.parseInt(temp[2]), trader);
				}
				else if(temp[0].equals("Customer"))
				{
					Customer customer = new Customer(temp[1], Integer.parseInt(temp[2]), temp[3]);
					userData.put(Integer.parseInt(temp[2]), customer);
				}
			}
		}
		catch(IOException e){}

	}

/**
*  	Reads "e-commerce-samples.csv" and creates "products.txt" and "users.txt"
*   "users.txt" has User informations. "products.txt" has information of Product's.
*   "products.txt" is sorted by Trader name.
*/
	public void readCSVFile()
	{	 
		try
		{
			String line = "";  
			String splitBy = ";"; 
			BufferedReader br = new BufferedReader(new FileReader("e-commerce-samples.csv")); 
			FileWriter productsWriter = new FileWriter("products.txt");
			FileWriter usersWriter = new FileWriter("users.txt");
			ArrayList<String> tempUsers = new ArrayList<String>();		// This ArrayList is for preventing write duplicate Users to file
			ArrayList<String[]> tempProducts = new ArrayList<String[]>();
			int counter = 0;
			int userCounter = 0;

      	
			while ((line = br.readLine()) != null)   
			{  		
				String[] temp = line.split(splitBy); 

				if(tempUsers.contains(temp[6]) == false && counter != 0)
				{
					usersWriter.write("Trader"); 
					usersWriter.write(";");
					usersWriter.write(temp[6]); 
					usersWriter.write(";");
					usersWriter.write(String.valueOf(10000000 + userCounter)); 
					usersWriter.write(";"); 
					usersWriter.write("123456"); 
					usersWriter.write("\n");
					tempUsers.add(temp[6]);
					userCounter++;
				}
			
				if(counter != 0)
				{					
					tempProducts.add(temp);									
				}

				counter++;							
			}

			for(int nextPos = 1; nextPos < tempProducts.size(); nextPos++)
			{
				String [] nextVal = tempProducts.get(nextPos);

				while(nextPos > 0 && nextVal[6].compareTo(tempProducts.get(nextPos - 1)[6]) < 0)	
				{
					tempProducts.set(nextPos, tempProducts.get(nextPos - 1));
					nextPos--;
				}
				tempProducts.set(nextPos, nextVal);
			}

			for(int i = 0; i < tempProducts.size();i++)
			{
				for(int j = 0; j < 7;j++)
				{	
					productsWriter.write(tempProducts.get(i)[j]);
					if(j != 6) productsWriter.write(";");
				}
				productsWriter.write("\n");
			}

 			productsWriter.close();
 			usersWriter.close();
 		}
 		catch(IOException e){}

	}

/**
*  	Login method. If id and password matches return that User.
*   If login fails returns null.
*   @param id Integer
*   @param password String
*   @return User
*/
	public User login(Integer id, String password)
	{	
		if(userData.get(id) != null)
		{
			if(password.equals(userData.get(id).getPassword()))
			{
				return userData.get(id);
			}
			else return null;
		}
		return null;
	}

/**
*  	Registers new User (Customer) to the Hash Table and writes that Customer's information to "users.txt"
*   @param name String
*   @param id Integer
*   @param password String
*/
	public void registerCustomer(String name, Integer id, String password) throws Exception
	{
		String idLength = String.valueOf(id);

		if(userData.containsKey(id))	
		{
			System.out.println("There is already an User With Same ID\n");
			throw new Exception();
		}			
		else if(idLength.length() != 8)	
		{
			System.out.println("ID Should be a 8 Digit Number\n");
			throw new Exception();
		}
		else if(password.length() != 6)	
		{
			System.out.println("Password Should be 6 Characters\n");
			throw new Exception();
		}			
		else
		{
			Customer tempCustomer = new Customer(name, id, password);

			userData.put(id, tempCustomer);
			try
			{
				FileWriter fileWritter = new FileWriter("users.txt",true);
				fileWritter.write("Customer;");
				fileWritter.write(name);
				fileWritter.write(";");
				fileWritter.write(String.valueOf(id)); 			
				fileWritter.write(";");
				fileWritter.write(password);
				fileWritter.close();
				System.out.println("Customer Registered to the System");
			}
			catch(IOException e){}
		}
	}

/**
*   Menu method
*/
	public void menu()
	{
		Scanner myObj = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		Scanner myObj3 = new Scanner(System.in);
		Scanner myObj4 = new Scanner(System.in);
		User tempUser;
		Integer mainMenuChoice = 1;
		boolean flag = false;

		while(mainMenuChoice != 0)
		{
			flag = false;
			try
			{
				System.out.println("WELCOME TO MAIN MENU\n");
				System.out.println("0) Exit");		
				System.out.println("1) Login");
				System.out.println("2) Register New Customer");
				System.out.println("Enter a Number:");
				mainMenuChoice = myObj4.nextInt();
			}
			catch(InputMismatchException e){}
			catch (Exception e) {}

			if(mainMenuChoice == 0)
			{	
				System.exit(0);
			}	 
			else if(mainMenuChoice == 1)
			{
				while(true)
				{
					System.out.println("WELCOME TO LOGIN MENU\n");
					System.out.println("Please Enter Your ID:(8 Digits Unique NUmber)");
					Integer id = myObj.nextInt();
					System.out.println("Please Enter Your Password:(6 Digits String)");
					String password = myObj2.nextLine();
				
					tempUser = login(id, password);
					
		 			if(tempUser == null)	System.out.println("Login Failed. Please Try Again");
		 			else break;
				}

				while(!flag)
				{
					if(tempUser instanceof Trader)
					{
						Trader tempTrader = (Trader) tempUser; 
						System.out.println("WELCOME TO TRADER MENU");
						System.out.println("You are logged in as Trader [Name: " + tempTrader.getName() + " - ID: " + tempTrader.getID() + "]\n");
						System.out.println("0) Back");
						System.out.println("1) Check Orders");
						System.out.println("2) Meet Order");
						System.out.println("3) Cancel Order");
						System.out.println("4) Add Product");
						System.out.println("5) Remove Product");
						System.out.println("6) Modify Product");
						Integer traderMenu = myObj.nextInt();

						if(traderMenu == 0) flag = true;
						else if(traderMenu == 1) tempTrader.checkOrders();
						else if(traderMenu == 2) 
						{
							System.out.println("0) Get Back:");
							System.out.println("Please Enter the OrderID:(Type 0 to Get Back to Menu)");
							Integer orderNum = myObj.nextInt();
							if(orderNum != 0)
							{
								try
								{
									tempTrader.checkOrders();
									tempTrader.meetOrder(orderNum);
								}
								catch(Exception e){}
							}							
						}
						else if(traderMenu == 3) 
						{
							System.out.println("0) Get Back:");
							System.out.println("Please Enter the OrderID:(Type 0 to Get Back to Menu)");
							Integer orderNum = myObj.nextInt();
							if(orderNum != 0)
							{
								try
								{
									tempTrader.checkOrders();
									tempTrader.cancelOrder(orderNum);
								}
								catch(Exception e){}
							}							
						}
						else if(traderMenu == 4) 
						{
							System.out.println("0) Get Back:");
							System.out.println("Please Enter the Product ID:(Type 0 to Get Back to Menu)");
							String productNum = myObj2.nextLine();
							if(!productNum.equals("0"))
							{								
								System.out.println("Please Enter the Name of the Product:");
								String productName = myObj2.nextLine();
								System.out.println("Please Enter the Category of the Product:");
								String productCat = myObj2.nextLine();
								String productCatFinal = "\"[\"\"" + productCat + "\"\"]\"";
								System.out.println("Please Enter the Price of the Product:");
								double pr = myObj.nextDouble();
								System.out.println("Please Enter the Discounted Price of the Product:");
								double discPr = myObj.nextDouble();
								System.out.println("Please Enter the Desciption of the Product:");
								String productDesc = myObj2.nextLine();
								Product x = new Product(productNum, productName, productCatFinal, pr, discPr, productDesc, tempTrader.getName());
								tempTrader.addProduct(x);
							}
						}
						else if(traderMenu == 5) 
						{
							System.out.println("Please Enter the Product ID:");
							String productNum = myObj2.nextLine();
							tempTrader.removeProduct(productNum);
						}
						else if(traderMenu == 6) 
						{
							System.out.println("0) Get Back:");
							System.out.println("Please Enter the Product ID:(Type 0 to Get Back to Menu)");
							String productNum = myObj2.nextLine();
							if(!productNum.equals("0"))
							{								
								System.out.println("Please Enter the Name of the Product:");
								String productName = myObj2.nextLine();
								System.out.println("Please Enter the Category of the Product:");
								String productCat = myObj2.nextLine();
								String productCatFinal = "\"[\"\"" + productCat + "\"\"]\"";
								System.out.println("Please Enter the Price of the Product:");
								double pr = myObj.nextDouble();
								System.out.println("Please Enter the Discounted Price of the Product:");
								double discPr = myObj.nextDouble();
								System.out.println("Please Enter the Desciption of the Product:");
								String productDesc = myObj2.nextLine();
								Product x = new Product(productNum, productName, productCatFinal, pr, discPr, productDesc, tempTrader.getName());
								tempTrader.modifyProduct(productNum, x);
							}
						}
							
					}
					else if(tempUser instanceof Customer)
					{
						Customer tempCustomer = (Customer) tempUser; 
						System.out.println("\nWELCOME TO CUSTOMER MENU\n");
						System.out.println("You are logged in as Customer [Name: " + tempCustomer.getName() + " - ID: " + tempCustomer.getID() + "]\n");

						System.out.println("Please Enter the Product You Want to Search:(Type 0 if you want to get back to the menu)");		
						String productName = "";
						productName = myObj2.nextLine();
						if(productName.equals("0"))	flag = true;
						else tempCustomer.query(productName);
					}

				}

			}

			else if(mainMenuChoice == 2)
			{
				try
				{
					System.out.println("WELCOME TO CUSTOMER REGISTER MENU\n");
					System.out.println("Please Enter Your Name:");
					String name = myObj2.nextLine();
					System.out.println("Please Enter Your ID:(8 Digits Unique NUmber)");
					int id = myObj4.nextInt();
					System.out.println("Please Enter Your Password:(6 Digits String)");
					String password = myObj2.nextLine();
					registerCustomer(name, id, password);					
								
				}
				catch (Exception e) {}
			}	

		}
	}

	public void driver()
	{
		Scanner myObj = new Scanner(System.in);
		ArrayList<Product> list = new ArrayList<Product>();
		ArrayList<Product> initialList = new ArrayList<Product>();
		User tempUser;
		Customer tempCustomer;
		Trader tempTrader;
		int counter = 0;
		String [] str = new String[2];
		str[0] = "";
		str[1] = "";

		System.out.println("REGISTERING CUSTOMER WITH WRONG INPUTS");

		try
		{
			registerCustomer("Emre", 10000000, "123123");
		}
		catch(IOException e){}
		catch(Exception e){}

		try
		{
			registerCustomer("Emre", 123123123, "123123");
		}
		catch(IOException e){}
		catch(Exception e){}

		myObj.nextLine();

		System.out.println("\nREGISTERING CUSTOMER WITH CORRECT INPUTS");
		try
		{
			registerCustomer("Emre", 12312312, "123456");
		}
		catch(IOException e){}
		catch(Exception e){}

		myObj.nextLine();

		System.out.println("\nLOGINING CUSTOMER WITH WRONG INPUTS");

		tempUser = login(12312311, "123456");
		if(tempUser == null) System.out.println("Login Failed");

		myObj.nextLine();

		System.out.println("\nLOGINING CUSTOMER WITH CORRECT INPUTS");

		tempUser = login(12312312, "123456");
		if(tempUser == null) System.out.println("Login Failed");
		else System.out.println("Login Successfull");

		myObj.nextLine();

		tempCustomer = (Customer) tempUser;

		System.out.println("\nSEARCHING PRODUCT");

		tempCustomer.search("Camera", list);

		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		for(int i = 0; i < list.size(); i++)
		{
			initialList.add(list.get(i));
		}

		myObj.nextLine();

		System.out.println("\nSORTING PRODUCTS BY NAME (INCREASING ORDER)");

		Sorts.heapSort(list, new Product.sortByName());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nSORTING PRODUCTS BY NAME (DECREASING ORDER)");
		Sorts.heapSort(list, new Product.sortByNameReverse());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();
		
		System.out.println("\nSORTING PRODUCTS BY PRICE (INCREASING ORDER)");
		Sorts.quickSort(list, new Product.sortByPrice());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nSORTING PRODUCTS BY PRICE (DECREASING ORDER)");
		Sorts.quickSort(list, new Product.sortByPriceReverse());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nSORTING PRODUCTS BY DISCOUNT PERCENTAGE (INCREASING ORDER)");
		Sorts.insertionSort(list, new Product.sortByDiscountPercentage());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nSORTING PRODUCTS BY DISCOUNT PERCENTAGE (DECREASING ORDER)");
		Sorts.insertionSort(list, new Product.sortByDiscountPercentageReverse());
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nFILTERING PRODUCTS BY PRICE (LOWER THRESHOLD)");
		list = tempCustomer.filterByPriceLower(list, 400);
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nFILTERING PRODUCTS BY PRICE (UPPER THRESHOLD)");
		list = tempCustomer.filterByPriceUpper(list, 3300);
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nFILTERING PRODUCTS BY PRICE (BOTH THRESHOLDS)");
		list = tempCustomer.filterByPriceBoth(list, 800, 1500);
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nFILTERING PRODUCTS BY CATEGORY (YOU NEED TO MAKE SOME SELECTIONS AND TYPE -1 TO END FILTERING)");
		str = tempCustomer.filterByCategory(list, initialList, str);
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		myObj.nextLine();

		System.out.println("\nFILTERING  BY TRADER NAME");
		list = tempCustomer.filterByTrader("Alisha");
		counter = 0;
		for(Product x : list)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		System.out.println("\nMAKING ORDERS");

		tempCustomer.makeOrder("SRTEH2FF9KEDEFGF", "Alisha", 12312312);
		tempCustomer.makeOrder("SRTEH2FECMGNZJXJ", "Alisha", 12312312);
		tempCustomer.makeOrder("SRTEH2FVUHAAVH9X", "Alisha", 12312312);

		myObj.nextLine();

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\nLOGING IN AS TRADER NAMED \"ALISHA\"");
		tempUser = login(10000000, "123456");
		if(tempUser == null) System.out.println("Login Failed");
		tempTrader = (Trader) tempUser;

		System.out.println("\nCHECKING ORDERS");
		tempTrader.checkOrders();

		myObj.nextLine();

		System.out.println("\nMEETING ORDER");
		try
		{
			tempTrader.meetOrder(1);
		}
		catch(Exception e){}

		myObj.nextLine();

		System.out.println("\nCANCELING ORDER");

		try
		{
			tempTrader.meetOrder(2);
		}
		catch(Exception e){}

		System.out.println("\nMEETING ORDER (WITH WRONG INPUT)");
		try
		{		
			tempTrader.meetOrder(31);
		}
		catch(Exception e){}

		myObj.nextLine();

		System.out.println("\nCANCELING ORDER (WITH WRONG INPUT)");

		try
		{
			tempTrader.meetOrder(32);
		}
		catch(Exception e){}

		myObj.nextLine();

		System.out.println("\nADDING PRODUCT");
		Product x = new Product("EMRE1212", "Emre's Camera", "\"[\"\"Camera \"\"]\"", 30000, 24000, "Best Camera in the Universe", tempTrader.getName());
		tempTrader.addProduct(x);

		myObj.nextLine();

		System.out.println("\nREMOVING PRODUCT (WITH CORRECT INPUT)");
		tempTrader.removeProduct("SRTEH2FVVKRBAXHB");

		System.out.println("\nREMOVING PRODUCT (WITH WRONG INPUT)");
		tempTrader.removeProduct("12312321");

		System.out.println("\nMODIFYING PRODUCT");
		Product y = new Product("SRTEH2FECMGNZJXJ", "FALAN", "\"[\"\"FALAN \"\"]\"", 2399, 1222, "FALAN FALAN", tempTrader.getName());
		tempTrader.modifyProduct("SRTEH2FECMGNZJXJ",y);

	}


}