import java.util.*;

/** 
 * @author Emre Sezer
 * Generic KWArrayList Class
*/
@SuppressWarnings("unchecked")
public class KWArrayList <E>
{
	private final int INITAL_CAPACITY = 6;
	private int capacity;
	private int size = 0;
	private E[] data;

/** 
 * Constructor for KWArrayList
*/
	public KWArrayList()
	{
		capacity = INITAL_CAPACITY;
		data = (E[]) new Object[capacity];
	}

/** 
 * Reallocate method for KWArrayList
*/	
	private void reallocate()
	{
		
		capacity *= 2;
		Object [] temp = (E[]) new Object[capacity];		

		for(int i = 0; i < size; i++)
		{
			temp[i] = (E)data[i];
		}

		data = (E[])temp;
	}

/** 
 * 	 gets the index'th element from the list
 *   @param index int
 *   @return the index'th element from the list
*/
	public E get(int index)
	{
		if (index < 0 || index >= size) 
		{
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return data[index];
	}

/** 
 * 	 adds at the selected position.
 *   @param index int
 *   @param entry E
*/	
	public void add(int index, E entry)
	{
		if (index < 0 || index > size) 	throw new IndexOutOfBoundsException();

		if(size >= capacity)
		{
			reallocate();
		}

		for(int i = size - 1 ; i >= index ; i--)
		{
			data[i + 1] = data[i];
		}
		data[index] = entry;
		size++;

	}

/** 
 * 	 adds entry to the end of the list.
 *   @param entry E
 *   @return entry      
*/	
	public boolean add(E entry)
	{
		add(size, entry);
		return true;
	}

/** 
 * 	 removes index'th element from the list.
 *   @param index int
 *   @return removed element at the index 
*/	
	public E remove(int index)
	{
		if (index < 0 || index >= size) 	throw new IndexOutOfBoundsException();
		E result = data[index];
		for(int i = index + 1; i < size; i++)
		{
			data[i - 1] = data[i];
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
	public E set(int index, E entry)
	{
		if (index < 0 || index >= size) 
		{
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E old = data[index];
		data[index] = entry;
		return old;
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
 * 	 returns the size of the list
 *   @return size
*/
	public int size()
	{
		return size;
	}

/** 
 * 	 returns a ListIterator 
 *   @return ListIterator
*/
	public ListIterator iterator()
	{
		return new MyIterator();
	}
	
/** 
 *  It is a custom inner ListIterator class for KWArrayList.
*/
	@SuppressWarnings("unchecked")
	private class MyIterator implements ListIterator <E>
	{
		private int index;

/** 
 *  ListIterator Constructor
*/
		public MyIterator()
		{
			index = 0;
		}

/** 
 *  Returns true if iterator is not at the end of the list and vice versa.
 *  @return true if iterator is not at the end of the list and vice versa.
*/		
		public boolean hasNext()
		{
			return index < size();
		}

/** 
 *  Returns the next element of the current position.
 *  @return the next element of the current position.
*/
		public E next()
		{
			if (index < 0 || index >= size) 	throw new IndexOutOfBoundsException();
			E x = data[index];
			index++;
			return x;
		}

/** 
 *  Removes the current element
*/
		public void remove()
		{
			for(int i = index + 1; i < size; i++)
			{
				data[i - 1] = data[i];
			}
			size--;
		}

/** 
 *  Adds the entry to the current position
 *  @param entry E
*/
		public void add(E entry)
		{
			if (index < 0 || index > size) 	throw new IndexOutOfBoundsException();

			if(size >= capacity)
			{
				reallocate();
			}
			
			for(int i = size - 1 ; i >= index ; i--)
			{
				data[i + 1] = data[i];
			}
			
			data[index] = entry;
			size++;
		}

/** 
 *  Sets the current element to entry
 *  @param entry E
*/
		public void set(E entry)
		{
			if(index < 0 || (index != 0 && index > size))	throw new IndexOutOfBoundsException();
			data[index] = entry;
		}

/** 
 *  Returns the previous element of the current position.
 *  @return the previous element of the current position.
*/
		public E previous()
		{
			if(index < 0 || (index != 0 && index > size))	throw new IndexOutOfBoundsException();
			E x = data[index];
			index--;
			return x;
		}

/** 
 *  Returns true if iterator is not at the beginning of the list and vice versa.
 *  @return true if iterator is not at the beginning of the list and vice versa.
*/
		public boolean hasPrevious()
		{
			return index > 0;
		}

/** 
 *  Returns the next index of the current position
 *  @return the next index of the current position
*/
		public int nextIndex()
		{
			int result = -1;
			if(index < size - 1 && index >= 0)	result = index++;
			return result;
		}

/** 
 *  Returns the previous index of the current position
 *  @return the previous index of the current position
*/
		public int previousIndex()
		{
			int result = -1;
			if(index > 1)	result = index++;
			return result;
		}
	}
}