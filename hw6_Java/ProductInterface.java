import java.io.*;
import java.lang.*;
import java.util.*;

/**
*  @author Emre Sezer
*  Interface For Product Class
*/
public interface ProductInterface
{
	public String getID();
	public String getName();
	public String getCategory();
	public double getPrice();
	public double getDiscountedPrice();
	public String getDescription();
	public String getTrader();

	public void setID(String id);
	public void setName(String name);
	public void setCategory(String category);
	public void setPrice(double price);
	public void setDiscountedPrice(double discountedPrice);
	public void setDescription(String description);
	public void setTrader(String trader);
	public void setCategoryTree();
	public LinkedList<String> getCategoryTree();
	public String getFromCategoryTree(int i);



}