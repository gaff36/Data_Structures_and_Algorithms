/** TwoThreeTree Class. Uses BTree as private data field
*/
public class TwoThreeTree<E extends Comparable<E>>
{
	private BTree <E> data; 

/** 
 * Constructor method
*/
	public TwoThreeTree()
	{
		data = new BTree<E>(3);
	}

/** 
 *  add method
 *  @param item E
*/
	public void add(E item)
	{
		data.add(item);
	}
}