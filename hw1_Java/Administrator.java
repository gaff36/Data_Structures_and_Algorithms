/** 
 * @author Emre Sezer
 * Administrator class.Represents admins.
*/

public class Administrator implements User
{
	private int wishNum;
	private wish [] wishList;

	private String email;
	public String name;
	private String surname;
	private String password;

	// GETTERS
	public String getEmail()
	{
		return email;
	}
	public String getName()
	{
		return name;
	}
	public String getSurname()
	{
		return surname;
	}
	public String getPassword()
	{
		return password;
	}

	// SETTERS
	public void setName(String name)
	{	
		this.name = name;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
/** Takes a Furniture array.
 *  Prints the furniture's properties in the array
*/
	public void showProductList(Furniture [] furnitures)	throws Error
	{
		System.out.println("BRANCH " + furnitures[0].getWhichBranch() + " :");
		if(furnitures == null)	throw new Error("There is no furnitures");
		else
		{	
			for(int i = 0; i < furnitures.length; i++)
			{
				System.out.println(furnitures[i]);
			}
		}
		System.out.println();
	}

	public int getWishNum()
	{	
		return wishNum;
	}

	public wish[] getWishList()	
	{
		return wishList;
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

/** Login function for admin
*	Takes email and password as arguments.Returns true if inputs are true and vice versa.
*/	
	protected boolean adminLogin(String email, String password)
	{
		if(email.equals(getEmail()) && password.equals(getPassword()))
		{
			return true;
		}
		else 
		{
			System.out.println("LOGIN FAILED");
			return false;
		}
	}

/** 
*	Adds a new branch to branch array
*/
	protected Branch [] addBranch(Branch [] branchList, Branch newBranch)
	{
		int lim;

		if(branchList == null)	lim = 0;
		else lim = branchList.length;

		Branch [] temp = new Branch[lim + 1];

		if(lim == 0)
		{
			temp[0] = newBranch;
		}
		else
		{
			System.arraycopy(branchList, 0, temp, 0, lim);
			temp[lim] = newBranch;
		}
		return temp;
	}

/** 
*	Removes the last element of the branch array
*/
	protected Branch [] removeBranch(Branch [] list)	throws Error
	{
		if(list == null)	throw new Error("There is no branches in the company");
		else
		{
			Branch [] temp = new Branch[list.length - 1];

			for(int i = 0; i < list.length - 1; i++)
			{
				temp[i] = list[i];	
			}

			return temp;
		}

	}

/** 
*	Adds a new wish to admin's wishlist.
*/
	protected void addWish(wish w)
	{
		if(wishNum > 0)
		{
			wishNum++;
			wish [] temp = new wish[wishNum];
			
			for(int i = 0; i < wishNum - 1; i++)
			{	
				temp[i] = wishList[i];
			}
			temp[wishNum - 1] = w;
			wishList = temp;
		}
		else
		{
			wishList = new wish[1];
			wishList[0] = w;
			wishNum++;
		}
	}
/** 
*	Outer class for Administrator.Represents wishes.Wish is needed when there is a lack of requested furnitures in the branch.
*   Branch Employee sends a wish to Administrator.So, Administrator can see the situation.
*/
	public class wish
	{
		private int num;
		private Furniture furn;

		public wish(int num, Furniture f)
		{
			setNum(num);
			setFurn(f);
		}

		public int getNum()
		{
			return num;
		}
		public Furniture getFurn()
		{
			return furn;
		}

		public void setNum(int num)
		{
			this.num = num;
		}
		public void setFurn(Furniture furn)
		{
			this.furn = furn;
		}

/** 
*	Overriden toString Method
*/
		public String toString()
		{		
			return String.format("Item number: " + getNum() + "Furniture : " + getFurn());
		}

	}

}
