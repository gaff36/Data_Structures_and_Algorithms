import java.io.*;
import java.lang.*;
import java.util.*;

/**
*  @author Emre Sezer
*  Product Class Represents Customers
*/
public class Customer extends User
{

/**
*  	Constructor For Customer
*/
	public Customer(String name, Integer id, String password)
	{
		super(name, id, password);
	}

/**
*  	Method For Making Order. It writes Order's Information to end of the file named "orders.txt".
*   By Default Each Order's Status is : PENDING
*   @param productID String
*   @param tradername String
*   @param customerID Integer
*/
	public void makeOrder(String productID, String traderName, Integer customerID)
	{
		try
		{
			File file = new File("orders.txt");
			int lines = 1;	

			if(file.exists() == true)
			{					
				BufferedReader reader = new BufferedReader(new FileReader("orders.txt"));			
				while (reader.readLine() != null) lines++;
				reader.close();
			}
			
			FileWriter fileWritter = new FileWriter("orders.txt",true);
			fileWritter.write(String.valueOf(lines));
			fileWritter.write(";");
			fileWritter.write(productID);
			fileWritter.write(";");
			fileWritter.write(traderName);
			fileWritter.write(";");
			fileWritter.write(String.valueOf(customerID));
			fileWritter.write(";");
			fileWritter.write("PENDING");
			fileWritter.write("\n");

			System.out.println("Succesfully Ordered. Product ID: \n" + productID);
			fileWritter.close();
		}
		catch(IOException e){} 	
		catch (Exception e) {}
	}

/**
*   Searches the products. Adds the products that have the entered String at its name or desciption to the product list.
*   @param productList ArrayList<Product> list of products 
*   @param searchName String that wants to be searched.
*/
	public void search(String searchName, ArrayList<Product> productList)
	{
		try
		{
			String line = "";  
			String splitBy = ";";  
			BufferedReader br = new BufferedReader(new FileReader("products.txt"));  
			while ((line = br.readLine()) != null)   
			{ 
				String[] temp = line.split(splitBy); 	

				if(temp[1].contains(searchName) == true || temp[5].contains(searchName) == true)
				{
					
					double price = Double.parseDouble(temp[3]);
					double discountedPrice = Double.parseDouble(temp[4]);
					productList.add(new Product(temp[0], temp[1], temp[2], price, discountedPrice, temp[5], temp[6]));
				}

			}
		}
			catch(IOException e){} 		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

/**
*   Finds all of the products of the entered trader's name.
*   @param productList ArrayList<Product> list of products 
*   @param filter String trader's name 
*/
	public ArrayList<Product> filterByTrader(String filter)
	{
		Queue<Product> tempQueue = new ArrayDeque<Product>();
		ArrayList<Product> x = new ArrayList<Product>();
		try
		{
			boolean check = false;
			String line = "";  
			String splitBy = ";";  
			BufferedReader br = new BufferedReader(new FileReader("products.txt"));  
			while ((line = br.readLine()) != null)   
			{ 
				String[] temp = line.split(splitBy); 	

				if(temp[6].contains(filter) == true)
				{
					double price = Double.parseDouble(temp[3]);
					double discountedPrice = Double.parseDouble(temp[4]);
					tempQueue.offer(new Product(temp[0], temp[1], temp[2], price, discountedPrice, temp[5], temp[6]));
					check = true;					
				}
				else if(check == true)
				{
					break;
				}

			}
			
			int length = tempQueue.size();

			for(int i = 0; i < length; i++)
			{
				x.add(tempQueue.poll());
			}

			br.close();
		}
		catch(IOException e){} 
		return x;	
	}

/**
*   Filters the product list by entered lower threshold (price)
*   @param productList ArrayList<Product> list of products 
*   @param limit int lower treshold
*   @return filtered product list
*/
	public ArrayList<Product> filterByPriceLower(ArrayList<Product> productList, int limit)
	{
		ArrayList<Product> temp = new ArrayList<Product>();
		for(Product x : productList)
		{
			if(x.getDiscountedPrice() >= limit)
			{
				temp.add(x);
			}
		}
		return temp;
	}

/**
*   Filters the product list by entered upper threshold (price)
*   @param productList ArrayList<Product> list of products 
*   @param limit int upper treshold
*	@return filtered product list
*/
	public ArrayList<Product> filterByPriceUpper(ArrayList<Product> productList, int limit)
	{
		ArrayList<Product> temp = new ArrayList<Product>();
		for(Product x : productList)
		{
			if(x.getDiscountedPrice() <= limit)
			{
				temp.add(x);
			}
		}
		return temp;
	}

/**
*   Filters the product list by entered lower and upper thresholds (price)
*   @param productList ArrayList<Product> list of products 
*   @param lowerLimit int lower treshold
*   @param upperLimit int upper treshold
*   @return filtered product list
*/
	public ArrayList<Product> filterByPriceBoth(ArrayList<Product> productList, int lowerLimit, int upperLimit)
	{
		ArrayList<Product> temp = new ArrayList<Product>();
		for(Product x : productList)
		{
			if(x.getDiscountedPrice() >= lowerLimit && x.getDiscountedPrice() <= upperLimit)
			{
				temp.add(x);
			}
		}
		return temp;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

/**
*   Filters the product list by Category
*   @param currentList ArrayList<Product> list of products 
*   @param initialList ArrayList<Product> list of initial products
*   @param filter String [] that includes before and current Categories
*   @return before and current selected Categories
*/
	public String [] filterByCategory(ArrayList<Product> currentList, ArrayList<Product> initialList, String [] filter)
	{
		Scanner myObj = new Scanner(System.in); 
		ArrayList<String> subCategories = new ArrayList<String>();
		String before = filter[0];
		String current = filter[1];
		String next = "";
		boolean done = false;
		boolean close = false;
		String [] result = new String[2];
		int index = 0;
		
		System.out.println("FILTER: " + filter[0] + ", " + filter[1]);	

		while(!close)
		{
			subCategories = new ArrayList<String>();

			if(!current.equals(""))
			{
				int length = currentList.size();
				for(int i = 0; i < length; i++)
				{
					currentList.remove(0);
				}

				if(before.equals("") == true)
				{
					for(int i = 0; i < initialList.size(); i++)
					{
						if(initialList.get(i).getCategoryTree().contains(current))
						{
							currentList.add(initialList.get(i));
						}
					}
				}
				else
				{
					for(int i = 0; i < initialList.size(); i++)
					{
						if(initialList.get(i).getCategoryTree().contains(current) && initialList.get(i).getCategoryTree().contains(before))
						{
							currentList.add(initialList.get(i));
						}
					}
				}
							
				index = currentList.get(0).getCategoryTree().indexOf(current);
				if(index > 0)	before = currentList.get(0).getFromCategoryTree(index - 1);

				for(int i = 0; i < currentList.size(); i++)
				{				
					index = currentList.get(i).getCategoryTree().indexOf(current); 
					String cmp = "";

					if(index >= 0 && currentList.get(i).getCategoryTree().size() - 1 > index)	cmp = currentList.get(i).getFromCategoryTree(index + 1);

					if(!subCategories.contains(cmp))
					{
						subCategories.add(cmp);
					}
				}
			}
			else
			{
				for(int i = 0; i < initialList.size(); i++)
				{
					if(!currentList.contains(initialList.get(i))) currentList.add(initialList.get(i));
				} 

				for(int i = 0; i < currentList.size(); i++)
				{				
					if(!subCategories.contains(currentList.get(i).getFromCategoryTree(0)))
					{
						subCategories.add(currentList.get(i).getFromCategoryTree(0));
					}
				}index++;
			}

			if(done)	
			{
				close = true;
				break;
			}
				
				while(true)
				{

					if(before.equals("")) System.out.println("0) Any Category");
					else System.out.println("0) " + before);
					for(int i = 0; i < subCategories.size(); i++)
					{
						System.out.println(i + 1 + ") " + subCategories.get(i));
					}	

					System.out.println("Please Enter A Number: (-1 To Finish Filtering)");
					int selection = myObj.nextInt();

					if(selection == -1)
					{
						result[0] = before;
						result[1] = current;
						done = true;
						break;
					}
					else if(selection == 0)
					{
						current = before;
						if(index == 1) before = "";
						break;
					}
					else if (selection <= subCategories.size())
					{
						int count  = 0;
						for(Product p : currentList)
						{
							if(p.getFromCategoryTree(index + 1) == null)
							{								
								count++;
							}	
						}
							
						System.out.println("COUNT: " + count + " SIZE: " + currentList.size() + " INDEX: " + index);

						if(count == currentList.size())	
						{
							System.out.println("THERE IS NO SUB CATEGORY");
						}
						else
						{
							before = current;
							current = subCategories.get(selection - 1);
						}						
						break;
					}
					else System.out.println("INDEX IS OUT OF BOUNDS");					
				}
		}	
		return result;

	}

/**
*   Query method 
*   @param productName String
*/
	public void query(String productName)
	{
		int counter = 0;
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Product> productListCategorized = new ArrayList<Product>();

		Scanner myObj = new Scanner(System.in); 
		Scanner myObj2 = new Scanner(System.in); 
		boolean flag = false;

		String [] filterCat = new String[2];
		filterCat[0] = "";
		filterCat[1] = "";
					
		search(productName, productList);

		if(productList.size() == 0)	System.out.println("There is No items Found With the Key: " + productName + "\n");

		System.out.println("SEARCH RESULTS:");	
	
		for(int i = 0; i < productList.size(); i++)
		{
			productListCategorized.add(productList.get(i));
		}

		Sorts.heapSort(productListCategorized, new Product.sortByNameReverse());		// First Search Results are sorted with decreasing order

		for(Product x : productListCategorized)
		{
			System.out.println(counter + ")" + x);
			counter++;
		}

		while(!flag)
		{

		System.out.println("\n0) Get Back To Search:");
		System.out.println("1) Sort Search by Name in Increasing Order:");
		System.out.println("2) Sort Search by Name in Decreasing Order:");
		System.out.println("3) Sort Search by Price in Increasing Order:");
		System.out.println("4) Sort Search by Price in Decreasing Order:");
		System.out.println("5) Sort Search by Discount Percentage in Increasing Order:");
		System.out.println("6) Sort Search by Discount Percentage in Decreasing Order:");
		System.out.println("7) Filter Search by Categories:");
		System.out.println("8) Filter Search by Price:");
		System.out.println("9) Filter Search by Trader:");
		System.out.println("10) Select Product to Order:");
		System.out.println("Enter a number to Select One of the Options Above:");
		Integer filterSearch = myObj.nextInt();	

		if(filterSearch == 0)	flag = true;
		else if(filterSearch == 1)
		{
			Sorts.heapSort(productListCategorized, new Product.sortByName());
		}
		else if(filterSearch == 2)
		{
			Sorts.heapSort(productListCategorized, new Product.sortByNameReverse());
		}
		else if(filterSearch == 3)
		{
			Sorts.quickSort(productListCategorized, new Product.sortByPrice());
		}
		else if(filterSearch == 4)
		{
			Sorts.quickSort(productListCategorized, new Product.sortByPriceReverse());
		}
		else if(filterSearch == 5)
		{
			Sorts.insertionSort(productListCategorized, new Product.sortByDiscountPercentage());	
		}
		else if(filterSearch == 6)
		{
			Sorts.insertionSort(productListCategorized, new Product.sortByDiscountPercentageReverse());
		}
		else if(filterSearch == 7)
		{		
			filterCat = filterByCategory(productListCategorized, productList, filterCat);	
		}
		else if(filterSearch == 8)
		{
			System.out.println("0) Get Back:");
			System.out.println("1) Lower Treshold:");
			System.out.println("2) Upper Treshold:");
			System.out.println("3) Both Lower/Upper Treshold:");
			System.out.println("Please Select an Option:");
			Integer filterByPrice = myObj.nextInt();

			if(filterByPrice == 1)	
			{
				System.out.println("Please Enter the Lower Threshold:");
				Integer limit = myObj.nextInt();
				productListCategorized = filterByPriceLower(productListCategorized, limit);
			}
			else if(filterByPrice == 2)	
			{
				System.out.println("Please Enter the Upper Threshold:");
				Integer limit = myObj.nextInt();
				productListCategorized = filterByPriceUpper(productListCategorized, limit);
			}
			else if(filterByPrice == 3)	
			{
				System.out.println("Please Enter the Lower Threshold:");
				Integer lowerLimit = myObj.nextInt();
				System.out.println("Please Enter the Upper Threshold:");
				Integer upperLimit = myObj.nextInt();
				productListCategorized = filterByPriceBoth(productListCategorized, lowerLimit, upperLimit);
			}			
		}
		else if(filterSearch == 9)
		{
			System.out.println("Enter the Trader's Name:");
			String traderName = myObj2.nextLine();
			productListCategorized = filterByTrader(traderName);		
		}
		else if(filterSearch == 10)
		{
			if(productList.size() == 0)	System.out.println("There is No Products in the List");
			else	
			{	
				System.out.println("Enter the number of the Product You Want to Order:");
				Integer prod = myObj.nextInt();

				if(prod < productListCategorized.size())
				{
					makeOrder(productListCategorized.get(prod).getID(), productListCategorized.get(prod).getTrader(), getID());
				}
				else
				{
					System.out.println("INDEX OUT OF BOUNDS");
				}
			}
		}

		if(filterSearch != 0 && filterSearch != 10)
		{
			if(filterSearch == 7 || filterSearch == 8 || filterSearch == 9)
			{
				counter = 0;
				for(Product x : productListCategorized)
				{
					System.out.println(counter + ") " + x.toString());
					counter++;
				}
			}
			else
			{
				counter = 0;
				for(Product x : productListCategorized)
				{
					System.out.println(counter + ") " + x.toString());
					counter++;
				}
			}
		}

		if(filterSearch != 7 && filterSearch != 8 && filterSearch != 9 && filterSearch != 10 
		   && productList.size() == 0)	System.out.println("There is No items in the List\n");
		}
	}

}