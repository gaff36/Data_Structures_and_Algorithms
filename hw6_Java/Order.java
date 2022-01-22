import java.io.*;
import java.lang.*;
import java.util.*;

/**
*   @author Emre Sezer
*  	Order Class That Represents Customer's Orders.
*   Each Order has an unique ID, Product's ID, Trader's Name, Customer's ID and Order Status(PENDING/CANCELED/MET)
*/
public class Order implements Comparable <Order>
{
	private int orderID;
	private String productID;
	private String traderName;
	private Integer customerID;
	private String status;

/**
*  	Constructor For Order
*/
	public Order(int orderID, String productID, String traderName, Integer customerID) 
	{
		this.orderID = orderID;
		this.productID = productID;
		this.traderName = traderName;
		this.customerID = customerID;
		status = "PENDING";
	}

/**
*  	Equals Method For Order
*   @param o Object
*   @return boolean
*/
	public boolean equals(Object o)
	{	
		 if (o == this)	return true; 
                 
		 if (!(o instanceof Order))	return false;
            
         Order temp = (Order) o;      
     
         if(temp.orderID == orderID)	return true;
         else return false;
	}

/**
*  	compareTo Method For Order
*   @param o Object
*   @return int
*/
	public int compareTo(Order o)
	{
		if(orderID < o.orderID)	return -1;
		else if(orderID == o.orderID) return 0;
		else return 1;
	}

/**
*  	toString Method For Order
*   @return String
*/
	public String toString()
	{
		return String.valueOf(orderID) + ";" + productID + ";" + traderName + ";" + String.valueOf(customerID) + ";" + status;
	}

	// GETTERS

/**
*  	Getter method for traderName
*   @return String
*/
	public String getTraderName()
	{
		return traderName;
	}

/**
*  	Getter method for orderID
*   @return Integer
*/
	public Integer getOrderID()
	{
		return orderID;
	}

/**
*  	Getter method for productID
*   @return String
*/
	public String getProductID()
	{
		return productID;
	}

/**
*  	Getter method for customerID
*   @return Integer
*/
	public Integer getCustomerID()
	{
		return customerID;
	}

/**
*  	Getter method for status
*   @return String
*/
	public String getStatus()
	{
		return status;
	}

	// SETTERS

/**
*  	Setter method for status
*   @param status String 
*/
	public void setStatus(String status)
	{
		this.status = status;
	}

/**
*  	Setter method for traderName
*   @param traderName String 
*/
	public void setTraderName(String traderName)
	{
		this.traderName = traderName;
	}

/**
*  	Setter method for orderID
*   @param orderID Integer 
*/
	public void setOrderID(Integer orderID)
	{
		this.orderID = orderID;
	}

/**
*  	Setter method for productID
*   @param productID String 
*/
	public void setProductID(String productID)
	{
		this.productID = productID;
	}

/**
*  	Setter method for customerID
*   @param customerID String 
*/
	public void setCustomerID(Integer customerID)
	{
		this.customerID = customerID;
	}

}