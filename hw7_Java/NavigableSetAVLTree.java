import java.util.Iterator;
import java.util.SortedSet;

/** NavigableSetAVLTree Class
*/
public class NavigableSetAVLTree <E extends Comparable<E>> implements NavigableSetAVLTreeInterface<E>
{
	private AVLTree <E> data;
	private int size;

	////////////////////////////////////////////////////////////////////////////////////////////

/** NavigableSetAVLTree Constructor
*/
	NavigableSetAVLTree()
	{
		data = new AVLTree<E>();
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** insert Method
 * @param item E
 * @return boolean
*/
	public boolean insert(E item)
	{
		size++;
		return data.add(item);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** headSet Method with inclusive
 * @param inclusive boolean
 * @param toElement E
 * @return NavigableSetAVLTree
*/
	public NavigableSetAVLTree headSet(E toElement, boolean inclusive)
	{
		NavigableSetAVLTree result = new NavigableSetAVLTree();
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			E temp = (E)iter.next();
			if(temp.compareTo(toElement) > 0) break;
			if(temp.compareTo(toElement) == 0 && inclusive == false) break;
			result.insert(temp);
		}

		return result;
	}

/** headSet Method without inclusive
 * @param toElement E
 * @return NavigableSetAVLTree
*/
	public NavigableSetAVLTree headSet(E toElement)
	{
		NavigableSetAVLTree result = new NavigableSetAVLTree();
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			E temp = (E)iter.next();
			if(temp.compareTo(toElement) > 0 || temp.compareTo(toElement) == 0) break;
			result.insert(temp);
		}

		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** tailSet Method with inclusive
 * @param inclusive boolean
 * @param toElement E
 * @return NavigableSetAVLTree
*/
	public NavigableSetAVLTree tailSet(E toElement, boolean inclusive)
	{
		NavigableSetAVLTree result = new NavigableSetAVLTree();
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			E temp = (E)iter.next();
			if(temp.compareTo(toElement) > 0) result.insert(temp);	
			if(temp.compareTo(toElement) == 0) result.insert(temp);		
		}
		return result;
	}

/** tailSet Method without inclusive
 * @param toElement E
 * @return NavigableSetAVLTree
*/
	public NavigableSetAVLTree tailSet(E toElement)
	{
		NavigableSetAVLTree result = new NavigableSetAVLTree();
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			E temp = (E)iter.next();
			if(temp.compareTo(toElement) > 0) result.insert(temp);		
		}
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** iterator Method
 * @return Iterator
*/
	public Iterator iterator()
	{
		return new NavigableSetIterator();
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Inner NavigableSetIterator Class
*/
	public class NavigableSetIterator <E extends Comparable<E>> implements Iterator
	{
		Iterator iter;

/** Inner NavigableSetIterator Constructor
*/
		public NavigableSetIterator()
		{
			iter = data.iterator();
		}

/** hasNext Method
 * @return boolean
*/
		public boolean hasNext()
		{
			return iter.hasNext();
		}

/** next Method
 * @return E
*/
		public E next()
		{
			return (E)iter.next();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////

}