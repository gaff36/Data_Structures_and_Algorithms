/**
*  @author Emre Sezer
*  User Abstarct Class Represents Users
*/
public abstract class User 
{
	private String name;
	private Integer id;
	private String password;

/**
*  	Constructor For User
*/
	public User(String name, Integer id, String password)
	{
		setName(name);
		setID(id);
		setPassword(password);
	}

/**
*  	Getter method for name
*   @return name String
*/
	public String getName()
	{
		return name;
	}

/**
*  	Getter method for id
*   @return id String
*/
	public Integer getID()
	{
		return id;
	}

/**
*  	Getter method for password
*   @return password String
*/
	public String getPassword()
	{
		return password;
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
*  	Setter method for id
*   @param id String 
*/
	public void setID(Integer id)
	{
		this.id = id;
	}

/**
*  	Setter method for password
*   @param password String 
*/
	public void setPassword(String password)
	{
		this.password = password;
	}

}