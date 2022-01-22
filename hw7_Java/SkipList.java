import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;
import java.util.Stack;

/** SkipList Class
*/
public class SkipList <E extends Comparable<E>>
{
	private SLNode <E> head;
	public int maxLevel;
	private int maxCap;
	private int size;

	////////////////////////////////////////////////////////////////////////////

/** SkipList Constructor
*/
	public SkipList()
	{
		size = 0;
		maxLevel = 1;
		head = null;
		maxCap = computeMaxCap(maxLevel);
	}

	////////////////////////////////////////////////////////////////////////////

/** Inner SLNode Class
*/
	public static class SLNode<E> 
	{
		private SLNode<E>[] links;
		public E data;
		/** Create a node of level m */
		SLNode (int m, E data)
		{
			links = (SLNode<E>[]) new SLNode[m]; // create links
			this.data = data; // store item
		}
	}

	////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
/** Search for an item in the list
@param item The item being sought
@return A SLNode array which references the predecessors
of the target at each level.
*/
	public SLNode<E>[] search (E target) 
	{
		SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
		SLNode<E> current = head;
		for (int i = current.links.length - 1; i >= 0; i--) 
		{
			while (current.links[i] != null && current.links[i].data.compareTo(target) < 0) 			
			{
				current = current.links[i];
			}
			pred[i] = current;
		}
		return pred;
	}

/** Find an object in the skip list
@param target The item being sought
@return A reference to the object in the skip list that matches
the target. If not found, null is returned.
*/
	public E find(E target) 
	{
		SLNode<E>[] pred = search(target);
		if (pred[0].links[0] != null &&
		pred[0].links[0].data.compareTo(target) == 0) 
		{
			return pred[0].links[0].data;
		} 
		else 
		{
			return null;
		}
	}

	////////////////////////////////////////////////////////////////////////////

/** Add Method
 * @param item E
 * @return boolean
*/
	public boolean add (E item)
	{
		int m = logRandom();
		SLNode<E> newNode = new SLNode<E>(m, item);
		if(head == null)
		{
			head = new SLNode<E>(maxLevel, item);
			head.links[maxLevel - 1] = newNode;
			size++;
			return true;
		}

		E temp = find(item);
		if(temp != null && temp.equals(item) == false)	return false;

		SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];

		if (size > maxCap)
		{
			maxLevel++;
			maxCap = computeMaxCap(maxLevel); 
			head.links = Arrays.copyOf(head.links , maxLevel);
			pred = Arrays.copyOf (pred, maxLevel);
			pred[maxLevel - 1] = head;
		}

		pred = search(item);
		int j = logRandom();

		size++;
				
		for(int i = 0; i < newNode.links.length; i++)
		{
			newNode.links[i] = pred[i].links[i];
			pred[i].links[i] = newNode; 
		}
		return true;
		
	}

	////////////////////////////////////////////////////////////////////////////

/** private logRandom Method
 * @return int
*/
	private int logRandom ()
	{
		Random rand = new Random();
		int r = rand.nextInt(maxCap);
		int k = (int)(Math.log(r + 1) / Math.log(2.0));
		if (k > maxLevel - 1)	k = maxLevel - 1;	
		return maxLevel - k;
	}

	////////////////////////////////////////////////////////////////////////////

/** computeMaxCap Method
 * @param maxLevel int
 * @return int
*/
	private int computeMaxCap (int maxLevel)
	{
		return (int)(Math.pow(2,maxLevel) - 1);
	}

	////////////////////////////////////////////////////////////////////////////

/** remove Method
 * @param item E
 * @return boolean
*/
	public boolean remove (E item)
	{
		if(head == null)	return false;
		if(find(item) == null)	return false;	
		SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
		pred = search(item);

		for(int i = 0; i < pred.length; i++)
		{
			if(pred[i].links[i] != null && pred[i].links[i].data.equals(item))
			{
				pred[i].links[i] = pred[i].links[i].links[i];
			}
		}
		size--;
		return true;
	}

	////////////////////////////////////////////////////////////////////////////

/** iterator method
 * @return Iterator
*/
	public Iterator iterator()
	{
		return new SkipListIterator();
	}

	////////////////////////////////////////////////////////////////////////////

/** Inner SkipListIterator Class
*/
	public class SkipListIterator implements Iterator
	{
		int index;
		SLNode<E> current;
		Stack<SLNode<E>> stack;

/** SkipListIterator Constructor
*/
		public SkipListIterator()
		{
			if(size != 0)
			{
				stack = new Stack<SLNode<E>>();
				current = head.links[0];
				for(int i = 0; i < size -1; i++)
				{
					current = current.links[0];
					stack.push(current);
				}			
				index = size - 1;
			}
			else
			{
				index = 0;
			}
		}

/** hasNext method
 * @return boolean
*/
		public boolean hasNext()
		{
			if(index > 0)	return true;
			return false;
		}

/** next method
 * @return E
*/
		public E next()
		{	
			E temp = stack.pop().data;
			index--;
			return temp;
		}
	}

	////////////////////////////////////////////////////////////////////////////

}