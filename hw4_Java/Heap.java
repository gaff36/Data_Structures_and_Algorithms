import java.util.ArrayList;

/**
*  @author Emre Sezer
*  This class is for PART 1.
*  This is a Max Heap class implemented with ArrayList
*  It has Heap and requested methods.
*/

public class Heap <E extends Comparable<E>>
{
	private ArrayList<E> data;
	
/**
* Heap constructor
*/
	public Heap()
	{
		data = new ArrayList<E>();
	}

/**
* Size method for Heap
* @return  size of the Heap
*/
	public int size()
	{
		return data.size();
	}

/**
* inserts entry to end of the heap which is also end of the ArrayList
* @param  entry E
*/
	public void add(E entry)
	{
	 	int child;
		int parent;
		data.add(entry);
		child = size() - 1;
		parent = (child - 1) / 2;

		while(parent >= 0 && data.get(parent).compareTo(data.get(child)) < 0) 
		{
			swap(child, parent);
			child = parent;
			parent = (child - 1) / 2;
		}	
	}

/**
* Removes root of the Heap which is 0'th element of the ArrayList
* @return root of the Heap which is 0'th element of the ArrayList
*/
	public E remove()
	{
		if(size() <= 0)	throw new IndexOutOfBoundsException("SIZE IS ZERO");

		int parent;
		int leftChild;
		int rightChild;
		int maxChild;

		E result = data.get(0);
		E x = data.remove(size() - 1);

		if(size() == 0)	return result;

		data.set(0, x);
		parent = 0;
		while(true)
		{
			leftChild = (2 * parent) + 1;
			rightChild = leftChild + 1;

			if(leftChild >= size())	break;

			maxChild = leftChild;

			if(rightChild < size() && data.get(rightChild).compareTo(data.get(leftChild)) > 0)	maxChild = rightChild;

			if(data.get(parent).compareTo(data.get(maxChild)) < 0)	
			{
				swap(parent, maxChild);
				parent = maxChild;
			}
			else break;

		}
		return result;
	}

/**
* Swaps x'th and y'th elements of the ArrayList
* @param  int x
* @param  int y
*/
	private void swap(int x, int y)
	{
		if(size() >= x && size() >= y)	
		{
			E temp = data.get(x);
			data.set(x, data.get(y));
			data.set(y, temp);
		}
		else return;
	}

/**
* This is a method for printing Heap.It makes it makes testing easier.
* Prints the Heap's elements
*/
	public void printHeap()
	{
		if(size() == 0)	System.out.println("HEAP IS EMPTY");
		for(int i = 0; i < size(); i++)
		{
			System.out.printf("%d ", data.get(i));
		}
	}

/**
* Method that returns a new HeapIterator
* @return New HeapIterator
*/
	public HeapIterator iterator()
	{
		return new HeapIterator();
	}

	///////////////////////////////////////////////////////
	
/**
* Searches for an element if it is stored on the heap.
* @param  entry E
* @return true if entry is on the heap, false if entry is not on the heap
*/
	public boolean search(E entry)
	{
		if(size() == 0) throw new IndexOutOfBoundsException("HEAP IS EMPTY");

		int length = size();
		boolean result = false;
		ArrayList<E> temp = new ArrayList<E>();

		for(int i = 0; i < length; i++)
		{
			E x = remove();
			temp.add(x);
			if(x.equals(entry))
			{	
				result = true;
				break;
			}
		}

		for(int i = 0; i < temp.size(); i++)	add(temp.get(i));
		return result;
	}

/**
* Merges 2 Heaps
* Heap structure will be preserved after this operation.
* @param  entry Heap<E>
*/
	public void merge(Heap<E> entry)
	{
		ArrayList<E> temp = new ArrayList<E>();
		int length = entry.size();

		for(int i = 0; i < length; i++)
		{
			E x = entry.remove();
			this.add(x);
			temp.add(x);	
		}

		for(int i = 0; i < temp.size(); i++)
		{
			entry.add(temp.get(i));
		}	
	}

/**
* Removes the index'th largest element from the heap.It calls remove method index times and inserts the removed elements back except the target
* @param  index int
*/
	public E removeithLargest(int index)
	{
		if(index <= 0 || size() == 0 || index >= size())	throw new IndexOutOfBoundsException();

		ArrayList<E> temp = new ArrayList<E>();
		E result;
		result = remove();
		if(index != 1) temp.add(result);

		for(int i = 2; i <= index; i++)
		{	
			result = remove();
			if(i != index)
			temp.add(result);						
		}

		for(int i = 0; i < temp.size(); i++)
		{
			add(temp.get(i));
		}
		return result;
	}

	///////////////////////////////////////////////////////

/**
*  @author Emre Sezer
*  Iterator class for Heap
*/
	public class HeapIterator 
	{
		private int current;

/**
* HeapIterator constructor
*/
		public HeapIterator()
		{
			current = 0;
		}

/**
* Method that returns true if current is smaller than size, else false
* @return true if current is smaller than size, else false
*/
		public boolean hasNext()
		{
			if(current >= 0 && current < size())	return true;
			else return false;
		}

/**
* Iterator next method
* @return the next element on the Heap
*/
		public E next()
		{
			E result = data.get(current);
			current++;
			return result;
		}

/**
* Iterator set method. Sets the current element to entry.Then sets Heap to Heap structure.
* After set operation Heap structure will be preserved.
* @param entry E
*/
		public void set(E entry)
		{
			data.set(current, entry);

			int child = current;
			
			while(2 * child + 2 < size())
			{
				if(data.get(2 * child + 1).compareTo(data.get(2 * child + 2)) > 0)	child = 2 * child + 1;
				else child = 2 * child + 2;
			}

			int parent = (child - 1) / 2;

			while(parent >= 0 && data.get(parent).compareTo(data.get(child)) < 0)
			{
				swap(child, parent);
				child = parent;
				parent = (child - 1) / 2;
			}
			
		}
	}

}