/** @author Emre Sezer
 * User Interface
*/
public interface User
{
	public String getEmail();	
	public String getName();
	public String getSurname();	
	public String getPassword();
	public void setName(String name);	
	public void setSurname(String surname);
	public void setEmail(String email);	
	public void setPassword(String password);
	public void showProductList(Furniture [] furnitures)	throws Error;	
}