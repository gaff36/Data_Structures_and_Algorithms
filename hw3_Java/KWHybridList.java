import java.util.*;

/** 
 * @author Emre Sezer
 * Generic KWHybridList Class
*/
@SuppressWarnings("unchecked")
public class KWHybridList <E> 
{
	public KWLinkedList<KWArrayList<E>> data;
	private final int MAX_NUMBER = 10;
	public int linkedListSize = 0;
	public int size = 0;
	private int INITIAL_CAPACITY = 5;

/** 
 * KWHybridList Constructor
*/
	public KWHybridList()
	{
		data = new KWLinkedList<KWArrayList<E>>();
	}

/** 
 * 	 gets the index'th element from the list
 *   @param index int
 *   @return the index'th element from the list
*/
	public E get(int index)
	{
		int nodeIndex;
		int arrayListIndex;
		if(index > MAX_NUMBER && index % MAX_NUMBER == 0)	nodeIndex = index / MAX_NUMBER + 1;
		else nodeIndex = index / MAX_NUMBER;

		arrayListIndex = index - nodeIndex * MAX_NUMBER;

		return data.get(nodeIndex).get(arrayListIndex);
	}

/** 
 * 	 adds at the selected position.
 *   @param index int
 *   @param entry E
*/
	public void add(int index, E entry)
	{
		int nodeIndex;
		int arrayListIndex;
		
		nodeIndex = index / MAX_NUMBER;
		arrayListIndex = index - nodeIndex * MAX_NUMBER;

		if(index == size)	add(entry);
		else if(size % MAX_NUMBER == 0)
		{
			data.add(data.size(), new KWArrayList<E>());
			linkedListSize++;
		}
		else
		{
			for(int i = nodeIndex; i < linkedListSize; i++)
			{
				if(i == nodeIndex)
				{
					data.get(i).add(arrayListIndex, entry);	
				}
				else
				{
					data.get(i).add(0, data.get(i - 1).get(MAX_NUMBER));	// It exceeds MAX_NUMBER temporarily.Then, adds that exceeding element to the begining of the next node.
					data.get(i - 1).remove(MAX_NUMBER);					// It removes that exceeding element from the first list. That element stays at the begining of the next node.
				}
			}
		}
		size++;

	}

/** 
 * 	 adds entry to the end of the list.
 *   @param entry E
 *   @return entry      
*/
	public boolean add (E entry)
	{
		if(size % MAX_NUMBER == 0)	
		{
			data.add(data.size(), new KWArrayList<E>());
			linkedListSize++;
		}
		data.get(linkedListSize - 1).add(entry);
		size++;
		return true;
	}

/** 
 * 	 removes index'th element from the list.
 *   @param index int
 *   @return removed element at the index 
*/		
	public E remove(int index)
	{
		if(index < 0 || index > size)	throw new IndexOutOfBoundsException(Integer.toString(index));

		int nodeIndex;
		int arrayListIndex;	
		nodeIndex = index / MAX_NUMBER;
		arrayListIndex = index - nodeIndex * MAX_NUMBER;
		E result = get(index);

		if(arrayListIndex == 0 && nodeIndex == linkedListSize - 1 && size % MAX_NUMBER == 0)	
		{	
			data.get(nodeIndex).remove(0);
			data.remove(nodeIndex);
			linkedListSize--;
		}
		else if(index == size - 1)
		{
			data.get(linkedListSize - 1).remove(size - (linkedListSize - 1) * MAX_NUMBER - 1);
		}
		else if (nodeIndex == linkedListSize - 1)
		{
			for(int j = arrayListIndex + 1; j < data.get(nodeIndex).size(); j++) 
			{
				data.get(nodeIndex).set(j - 1, data.get(nodeIndex).get(j));
			}
			data.get(nodeIndex).remove(data.get(nodeIndex).size() - 1);
		}
		else
		{	
			for(int i = nodeIndex; i < linkedListSize ; i++)
			{
				if(i == nodeIndex)
				{
					for(int j = arrayListIndex + 1; j < MAX_NUMBER; j++) 
					{
						data.get(i).set(j - 1, data.get(i).get(j));
					}
				}
				else
				{
					data.get(i - 1).set(MAX_NUMBER - 1, data.get(i).get(0));
					for(int j = 1; j < data.get(i).size(); j++) 
					{
						data.get(i).set(j - 1, data.get(i).get(j));
					}
				}
			}
			data.get(linkedListSize - 1).remove(size - (linkedListSize - 1) * MAX_NUMBER - 1);
		}

		size--;
		return result;
	}			

/** 
 * 	 sets index'th element from the list.
 *   @param index int
 *   @param entry E
 *   @return switched element  
*/	
	public E set (int index, E entry)
	{
		if (index < 0 || index > size) 	throw new IndexOutOfBoundsException();
		E result = data.get(index / MAX_NUMBER).get(index % MAX_NUMBER);
		data.get(index / MAX_NUMBER).set(index % MAX_NUMBER, entry);
		return result;
	}

/** 
 * 	 returns the size of the list
 *   @return size
*/
	public int size()
	{
		return size;
	}

/** 
 * 	 returns the index of the selected object.
 *   @param target Object
 *   @return position of the entered object 
*/
	public int indexOf (Object target)
	{
		for(int i = 0; i < size; i++)
		{
			E temp = (E) target;
			if(temp.equals(get(i)))	return i;
		}
		return -1;
	}

/** 
 * 	 returns a ListIterator at the begining of the list 
 *   @return ListIterator at the begining of the list 
*/
	public Iterator iterator()
	{
		return new hybridIterator();
	}

	public class hybridIterator implements Iterator
	{
		private int index;

		public hybridIterator()
		{
			index = 0;
		}

		public boolean hasNext()
		{
			return index < size();
		}

		public E next()
		{
			if (index < 0 || index >= size) 	throw new IndexOutOfBoundsException();
			E x = get(index);
			index++;
			return x;
		}

		public void remove()
		{
			if(index < 0 || index > size)	throw new IndexOutOfBoundsException(Integer.toString(index));

		int nodeIndex;
		int arrayListIndex;	
		nodeIndex = index / MAX_NUMBER;
		arrayListIndex = index - nodeIndex * MAX_NUMBER;
		E result = get(index);

		if(arrayListIndex == 0 && nodeIndex == linkedListSize - 1 && size % MAX_NUMBER == 0)	
		{	
			data.get(nodeIndex).remove(0);
			data.remove(nodeIndex);
			linkedListSize--;
		}
		else if(index == size - 1)
		{
			data.get(linkedListSize - 1).remove(size - (linkedListSize - 1) * MAX_NUMBER - 1);
		}
		else if (nodeIndex == linkedListSize - 1)
		{
			for(int j = arrayListIndex + 1; j < data.get(nodeIndex).size(); j++) 
			{
				data.get(nodeIndex).set(j - 1, data.get(nodeIndex).get(j));
			}
			data.get(nodeIndex).remove(data.get(nodeIndex).size() - 1);
		}
		else
		{	
			for(int i = nodeIndex; i < linkedListSize ; i++)
			{
				if(i == nodeIndex)
				{
					for(int j = arrayListIndex + 1; j < MAX_NUMBER; j++) 
					{
						data.get(i).set(j - 1, data.get(i).get(j));
					}
				}
				else
				{
					data.get(i - 1).set(MAX_NUMBER - 1, data.get(i).get(0));
					for(int j = 1; j < data.get(i).size(); j++) 
					{
						data.get(i).set(j - 1, data.get(i).get(j));
					}
				}
			}
			data.get(linkedListSize - 1).remove(size - (linkedListSize - 1) * MAX_NUMBER - 1);
		}

		size--;
		}

	}

}