import java.util.Iterator;

/** NavigableSetSkipList uses SkipList as private data field.
*/
public class NavigableSetSkipList <E extends Comparable<E>> implements NavigableSetSkipListInterface <E>
{
	private SkipList <E> data;

/** NavigableSetSkipList Constructor
*/
	public NavigableSetSkipList()
	{
		data = new SkipList <E>();
	}

/** insert method.uses SkipList's add method.
 * @param item E
 * @return boolean
*/
	public boolean insert(E item)
	{
		return data.add(item);
	}

/** delete method.uses SkipList's remove method.
 * @param item E
 * @return boolean
*/
	public boolean delete(E item)
	{
		return data.remove(item);
	}

/** descendingIterator returns Iterator
 * @return Iterator
*/
	public Iterator descendingIterator()
	{
		return data.iterator();
	}


}