import java.io.*;
import java.lang.*;
import java.util.*;

/**
*  @author Emre Sezer
*  Product Class Represents Products
*/
public class Product implements ProductInterface
{
	private String id;
	private String name;
	private String category;
	private LinkedList<String> categoryTree;		// I keep categories of the prodcut with LinkedList<String>
	private double price;
	private double discountedPrice;
	private String description;
	private String trader;

	// CONSTRUCTOR

/**
*  Constructor with parameters
*  @param id String
*  @param name String
*  @param category String
*  @param price double
*  @param discountedPrice double
*  @param description String
*  @param trader String
*/
	public Product(String id, String name, String category, double price, double discountedPrice, String description, String trader)
	{
		setID(id);
		setName(name);
		setCategory(category);
		setPrice(price);
		setDiscountedPrice(discountedPrice);
		setDescription(description);
		setTrader(trader);
		setCategoryTree();
	}

/**
*  	Splits category String recently read to LinkedList Nodes. Each subcategory is put to a LinkedList node.
*/
	public void setCategoryTree()
	{
		categoryTree = new LinkedList<String>();
		String line = category;  
		String splitBy = "\"\"";  // First splits category from "" characters.
		String[] temp1 = line.split(splitBy);

		line = temp1[1];
		splitBy = ">> ";				// Splits category by "<< "characters.With that step i pick categories that i wanted from String.
		String[] temp2 = line.split(splitBy);

		for(String x : temp2)	categoryTree.add(x);
	}

/**
*  	Getter method for categoryTree
*   @return LinkedList<String>
*/
	public LinkedList<String> getCategoryTree()
	{
		return categoryTree;
	}

/**
*  	Gets the index'th element of categoryTree
*   @return String (index'th element of categoryTree)
*/
	public String getFromCategoryTree(int i)
	{
		if(i >= categoryTree.size())	return null;
		return categoryTree.get(i);
	}

	// TOSTRING

/**
*  	toString method
*   @return String
*/
	public String toString()
	{
		return "PRODUCT NAME: " + getName() + "TRADER: "+ getTrader() + "PRICE: " + getPrice() + " DISCOUNTED PRICE: " + getDiscountedPrice(); 
	}

	// COMPERATOR

/**
*  	Comparator class (Sorts Name By Increasing Order)
*/
	public static class sortByName implements Comparator<Product>		// Sort Name By Increasing Order
	{
   		public int compare(Product a, Product b)		// While comparing 2 strings, it converts Strings to Upper cases and compares them
    	{
    		String temp1 = a.name.toUpperCase();    		 
    		String temp2 = b.name.toUpperCase();    		 
        	return temp1.compareTo(temp2);
    	}
	}

/**
*  	Comparator class (Sorts Name By Decreasing Order)
*/
	public static class sortByNameReverse implements Comparator<Product>	// Sort Name By Decreasing Order
	{
   		public int compare(Product a, Product b)						// While comparing 2 strings, it converts Strings to Upper cases and compares them
    	{
    		String temp1 = a.name.toUpperCase();    		 
    		String temp2 = b.name.toUpperCase();    		 
        	return temp1.compareTo(temp2) * (-1);       	 
    	}
	}

/**
*  	Comparator class (Sorts discountedPrice By Increasing Order)
*/
	public static class sortByPrice implements Comparator<Product>		// Sort discountedPrice By Increasing Order
	{
   		public int compare(Product a, Product b)
    	{
        	if(a.discountedPrice < b.discountedPrice)	return -1;
        	else if(a.discountedPrice == b.discountedPrice)	return 0;
        	else return 1;
    	}
	}

/**
*  	Comparator class (Sorts discountedPrice By Decreasing Order)
*/
	public static class sortByPriceReverse implements Comparator<Product>		// Sort discountedPrice By Decreasing Order
	{
   		public int compare(Product a, Product b)
    	{
        	if(a.discountedPrice < b.discountedPrice)	return 1;
        	else if(a.discountedPrice == b.discountedPrice)	return 0;
        	else return -1;
    	}
	}

/**
*  	Comparator class (Sorts Discount Percentage By Increasing Order)
*/
	public static class sortByDiscountPercentage implements Comparator<Product>		// Sort Discount Percentage By Increasing Order
	{
   		public int compare(Product a, Product b)
    	{
        	if((a.price - a.discountedPrice) / a.price < (b.price - b.discountedPrice) / b.price)	return -1;
        	else if((a.price - a.discountedPrice) / a.price == (b.price - b.discountedPrice) / b.price)	return 0;
        	else return 1;
    	}
	}

/**
*  	Comparator class (Sorts Discount Percentage By Decreasing Order)
*/	
	public static class sortByDiscountPercentageReverse implements Comparator<Product>		// Sort Discount Percentage By Increasing Order
	{
   		public int compare(Product a, Product b)
    	{
        	if((a.price - a.discountedPrice) / a.price < (b.price - b.discountedPrice) / b.price)	return 1;
        	else if((a.price - a.discountedPrice) / a.price == (b.price - b.discountedPrice) / b.price)	return 0;
        	else return -1;
    	}
	}

	// GETTERS

/**
*  	Getter method for id
*   @return id 
*/
	public String getID()
	{
		return id;
	}

/**
*  	Getter method for name
*   @return name 
*/	
	public String getName()
	{
		return name;
	}

/**
*  	Getter method for category
*   @return category 
*/		
	public String getCategory()
	{
		return category;
	}

/**
*  	Getter method for price
*   @return price 
*/		
	public double getPrice()
	{
		return price;
	}

/**
*  	Getter method for discountedPrice
*   @return discountedPrice 
*/	
	public double getDiscountedPrice()
	{
		return discountedPrice;
	}

/**
*  	Getter method for description
*   @return description 
*/	
	public String getDescription()
	{
		return description;
	}

/**
*  	Getter method for trader
*   @return trader 
*/		
	public String getTrader()
	{
		return trader;
	}

	// SETTERS

/**
*  	Setter method for id
*   @param id String 
*/	
	public void setID(String id)
	{
		this.id = id;
	}

/**
*  	Setter method for name
*   @param name String 
*/		
	public void setName(String name)
	{
		this.name = name;
	}

/**
*  	Setter method for category
*   @param category String 
*/		
	public void setCategory(String category)
	{
		this.category = category;
	}

/**
*  	Setter method for price
*   @param price double 
*/	
	public void setPrice(double price)
	{
		this.price = price;
	}

/**
*  	Setter method for discountedPrice
*   @param discountedPrice double 
*/	
	public void setDiscountedPrice(double discountedPrice)
	{
		this.discountedPrice = discountedPrice;
	}

/**
*  	Setter method for description
*   @param description String 
*/		
	public void setDescription(String description)
	{
		this.description = description;
	}

/**
*  	Setter method for trader
*   @param trader String 
*/	
	public void setTrader(String trader)
	{
		this.trader = trader;
	}

}