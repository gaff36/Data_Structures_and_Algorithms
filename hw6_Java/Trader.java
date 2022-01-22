import java.io.*;
import java.lang.*;
import java.util.*;

public class Trader extends User
{
	private TreeSet<Order> orders;

/**
*   Constructor For Trader
*/
	public Trader(String name, Integer id, String password)
	{
		super(name, id, password);
		orders = new TreeSet<Order>();
	} 

/**
*   Prints the Orders of the Trader
*/
	public void checkOrders()
	{
		try
		{
			String line = "";  
			String splitBy = ";";  
			BufferedReader br = new BufferedReader(new FileReader("orders.txt"));  
			while ((line = br.readLine()) != null)   
			{ 
				String[] temp = line.split(splitBy); 	

				if(temp[2].equals(getName()) == true)
				{
					Order tempOrder = new Order(Integer.parseInt(temp[0]), temp[1], temp[2],Integer.parseInt(temp[3]));
					orders.add(tempOrder);
				}
			}

			br.close();
		 	
		 	System.out.println("ORDERS:");

			for(Order o : orders)
			{
				System.out.println(o);
			}
		}
			catch(IOException e){}
	}

/**
*  Cancels the Order with specific orderID(writes CANCELED to the end of the line)
*   @param num int
*/
	public void cancelOrder(int num) throws Exception
	{
		Order o = null;
		int counter = 0;
		for(Order x : orders)
		{
			if(x.getOrderID() == num)
			{
				o = x;
				break;
			}
		}

		if(o == null)
		{
			System.out.println("There is no Order with ID:" + num);
			throw new Exception();
		} 

		try
		{
			File inputFile = new File("orders.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = o.toString();
			String currentLine;

			while((currentLine = reader.readLine()) != null) 
			{
    			String trimmedLine = currentLine.trim();
    			if(trimmedLine.equals(lineToRemove)) 
    			{
    				continue;
    			}
    			writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			File x = new File("orders.txt");
			inputFile.delete();
			boolean successful = tempFile.renameTo(x);

			o.setStatus("CANCELED");
			FileWriter fileWritter = new FileWriter("orders.txt",true);
			fileWritter.write(o.toString());
			fileWritter.close();

		}
		catch(IOException e){}

	}

/**
*  Cancels the Order with specific orderID(writes MET to the end of the line)
*   @param num int
*/
	public void meetOrder(int num)	throws Exception
	{
		Order o = null;
		int counter = 0;
		for(Order x : orders)
		{
			if(x.getOrderID() == num)
			{
				o = x;
				break;
			}
		}

		if(o == null)
		{
			System.out.println("There is no Order with ID:" + num);
			throw new Exception();
		}

		try
		{
			File inputFile = new File("orders.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = o.toString();
			String currentLine;

			while((currentLine = reader.readLine()) != null) 
			{
    			String trimmedLine = currentLine.trim();
    			if(trimmedLine.equals(lineToRemove)) 
    			{
    				continue;
    			}
    			writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			File x = new File("orders.txt");
			inputFile.delete();
			boolean successful = tempFile.renameTo(x);

			o.setStatus("MET");
			FileWriter fileWritter = new FileWriter("orders.txt",true);
			fileWritter.write(o.toString());
			fileWritter.close();

		}
		catch(IOException e){}

	}

/**
*   Removes the specific Product From the "products.txt"
*   @param productID String
*/
	public void removeProduct(String productID)
	{
		try
		{
			boolean check = false;
			File inputFile = new File("products.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = productID;
			String currentLine;

			while((currentLine = reader.readLine()) != null) 
			{
    			String trimmedLine = currentLine.trim();
    			if(trimmedLine.contains(lineToRemove) && !check && trimmedLine.contains(getName())) 
    			{
    				check = true;
    				continue;
    			}
    			writer.write(currentLine + System.getProperty("line.separator"));
    		}

    		if(check == true) System.out.println(productID + " is removed");
  			else System.out.println("There is no " + productID);
    			   		
    		writer.close();
			reader.close();
			File x = new File("products.txt");
			inputFile.delete();
			boolean successful = tempFile.renameTo(x);
    	}
		catch(IOException e){}		
	}

/**
*   Adds the entered Product to the "products.txt"
*   @param p Product
*/
	public void addProduct(Product p)
	{
		try
		{
			boolean check = false;
			File inputFile = new File("products.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToFind = getName();
			String currentLine;

			while((currentLine = reader.readLine()) != null) 
			{
    			String trimmedLine = currentLine.trim();
    			if(!check && trimmedLine.contains(lineToFind)) 
    			{
    				check = true;
    				
    				writer.write(p.getID());
    				writer.write(";");
    				writer.write(p.getName());
    				writer.write(";");
    				writer.write(p.getCategory());
    				writer.write(";");
    				writer.write(String.valueOf(p.getPrice()));
    				writer.write(";");
    				writer.write(String.valueOf(p.getDiscountedPrice()));
    				writer.write(";");
    				writer.write(p.getDescription());
    				writer.write(";");
    				writer.write(p.getTrader());
    				writer.write("\n");
    			}
    			writer.write(currentLine + System.getProperty("line.separator"));
    		}

    		writer.close();
			reader.close();
			File x = new File("products.txt");
			inputFile.delete();
			boolean successful = tempFile.renameTo(x);
		}
		catch(IOException e){}
	}

/**
*   Modifies the specific Product at the "products.txt". Doesn't change the productID.
*   Changes other information with the input Product.
*   @param productID String
*/
	public void modifyProduct(String productID, Product p)
	{
		try
		{
			boolean check = false;
			File inputFile = new File("products.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToFind = productID;
			String currentLine;

			while((currentLine = reader.readLine()) != null) 
			{
    			String trimmedLine = currentLine.trim();
    			if(!check && trimmedLine.contains(productID) && trimmedLine.contains(getName())) 
    			{
    				check = true;  				
    				writer.write(p.getID());
    				writer.write(";");
    				writer.write(p.getName());
    				writer.write(";");
    				writer.write(p.getCategory());
    				writer.write(";");
    				writer.write(String.valueOf(p.getPrice()));
    				writer.write(";");
    				writer.write(String.valueOf(p.getDiscountedPrice()));
    				writer.write(";");
    				writer.write(p.getDescription());
    				writer.write(";");
    				writer.write(p.getTrader());
    				writer.write("\n");
    			}
    			writer.write(currentLine + System.getProperty("line.separator"));
    		}
    		if(check == true) System.out.println(productID + " is modified");
  			else System.out.println("There is no " + productID);
    		writer.close();
			reader.close();
			File x = new File("products.txt");
			inputFile.delete();
			boolean successful = tempFile.renameTo(x);
		}
		catch(IOException e){}
	}

}