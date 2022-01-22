/**
*  @author Emre Sezer
*  Interface For Company Class
*/

public interface CompanyInterface
{
	public void setUserData();
	public void readCSVFile();
	public User login(Integer id, String password);
	public void registerCustomer(String name, Integer id, String password) throws Exception;
	public void menu();


}