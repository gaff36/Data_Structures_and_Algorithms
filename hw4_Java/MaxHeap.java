import java.util.ArrayList;

/**
*  @author Emre Sezer
*  This class is for PART 2
*  This is a Max Heap class implemented with ArrayList
*  This class is for implementing BSTHeapTree class
*/
@SuppressWarnings("unchecked")
public class MaxHeap <E extends Comparable<E>> implements Comparable <MaxHeap>
{
	private ArrayList<HeapNode<E>> data;
	private final int MAX_SIZE = 7;

	public MaxHeap()
	{
		data = new ArrayList<HeapNode<E>>();
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
* Method For Getting Top Element of the Heap
* @return  Top Element of the Heap
*/
	public HeapNode<E> peek()
	{
		if(size() == 0)	return null;
		return data.get(0);
	}

/**
* Searches for an element if it is stored on the heap.
* Support Method For BSTHeapTree's add Method.
* @param  entry E
* @return true if entry is on the heap, false if entry is not on the heap
*/
	public int search(E entry)
	{
		if(size() == 0) return 0;

		ArrayList<HeapNode<E>> temp1 = new ArrayList<HeapNode<E>>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		int result  = 0;
		HeapNode<E> x;
		int length = size();
		int nodeLength;

		for(int i = 0; i < length; i++)
		{
			x = remove();
			nodeLength = x.num + 1;

			if(nodeLength > 1)
			{
				for(int j = 0; j < nodeLength - 1; j++)	remove();
			}

			temp2.add(nodeLength);
			temp1.add(x);

			if(entry.compareTo(x.getValue()) == 0)	
			{
				result = nodeLength;
				break;
			}
		}

		for(int i = 0; i < temp1.size(); i++)
		{
			temp1.get(i).num = temp2.get(i);
			add(temp1.get(i));
		}
		
		return result;
	}


/**
*  Finds Mode of the MaxHeap
*  @return Mode of the MaxHeap
*/
	public HeapNode<E> findLocalMode()
	{
		if(size() == 0) return null;

		ArrayList<HeapNode<E>> temp1 = new ArrayList<HeapNode<E>>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		HeapNode<E> result;
		HeapNode<E> x;
		int length = size();
		int nodeLength;

		for(int i = 0; i < length; i++)
		{
			x = remove();
			nodeLength = x.num + 1;

			if(nodeLength > 1)
			{
				for(int j = 0; j < nodeLength - 1; j++)	remove();
			}

			temp2.add(nodeLength);
			temp1.add(x);		
		}

		for(int i = 0; i < temp1.size(); i++)
		{
			temp1.get(i).num = temp2.get(i);
			add(temp1.get(i));
		}

		result = temp1.get(0);
		for(int i = 0; i < temp1.size(); i++)
		{
			if(temp1.get(i).getNum() > result.getNum())	result = temp1.get(i);	
		}
		return result;
	}

/**
*  compareTo Method
*/
	public int compareTo(MaxHeap other)
	{
		return other.peek().compareTo(peek());		
	}

/**
* If heap has a node with value 'entry' . Incereases this nodes num by 1.
* If heap has not a node with value 'entry'.Inserts entry to end of the heap which is also end of the ArrayList
* @param  entry E
*/
	public int add(E entry)
	{	
		return add(new HeapNode(entry));
	}

	public int add(HeapNode<E> entry)
	{	
		int child;
		int parent;

		for(int i = 0; i < size(); i++)	
		{
			if(data.get(i).getValue().equals(entry.getValue()))
			{
				data.get(i).num++;
				return data.get(i).num;
			}
		}

		if(size() != 7)
		{  			
			data.add(entry);
			child = size() - 1;
			parent = (child - 1) / 2;

			while(parent >= 0 && data.get(parent).getValue().compareTo(data.get(child).getValue()) < 0)
			{
				swap(child, parent);
				child = parent;
				parent = (child - 1) / 2;
			}	
			return 1;
		}
		else
		{
			System.out.println("Reached to MAX_SIZE");
			return -1;
		}
	}

/**
* Remove method for MaxHeap.If Head Node's Value's Occurences are bigger than 1.Decreases it by 1.
* If Head Node's Value's Occurence is 1. Removes the Head Node. 
* @return root of the Heap which is 0'th element of the ArrayList
*/
	public HeapNode<E> remove()
	{
		if(data.size() == 0)	throw new IndexOutOfBoundsException();

		HeapNode<E> result;
		int parent;
		int leftChild;
		int rightChild;
		int maxChild;

		if(data.get(0).num != 1)
		{
			data.get(0).num--;
			result = data.get(0);
		}
		else if (size() == 1)
		{
			data.get(0).num--;
			result = data.get(0);
			data.remove(0);
		}
		else
		{	
			data.get(0).num--;
			result = data.get(0);
			HeapNode<E> x = data.remove(size() - 1);
			data.set(0, x);
			parent = 0;
			while(true)
			{
				leftChild = (2 * parent) + 1;
				rightChild = leftChild + 1;

				if(leftChild >= size())	break;

				maxChild = leftChild;

				if(rightChild < size() && data.get(rightChild).getValue().compareTo(data.get(leftChild).getValue()) > 0)	maxChild = rightChild;

				if(data.get(parent).getValue().compareTo(data.get(maxChild).getValue()) < 0)	
				{
					swap(parent, maxChild);
					parent = maxChild;
				}
				else break;

			}
			
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
			HeapNode<E> temp = data.get(x);
			data.set(x, data.get(y));
			data.set(y, temp);
		}
		else return;
	}

/**
* Swaps Head's of the 2 MaxHeap
* This a support method for 
* @param  other MaxHeap<E>
*/
	public void swapRoots(MaxHeap <E> other)
	{
		HeapNode<E> temp1 = peek();
		HeapNode<E> temp2 = other.peek();

		if(temp1 == null || temp2 == null)	return;

		int length1 = peek().getNum();
		int length2 = other.peek().getNum();

		for(int i = 0; i < length2; i++)
		{
			temp2 = other.remove();
		}

		for(int i = 0; i < length1; i++)
		{
			temp1 = remove();
		}

		temp1.num = length1;
		temp2.num = length2;

		add(temp2);
		other.add(temp1);
	}

/**
* Prints the Heap's elements
*/
	public void printHeap()
	{
		System.out.printf("NODE: ");
		for(int i = 0; i < size(); i++)
		{
			System.out.printf("(%d,%d) ", data.get(i).getValue(), data.get(i).getNum());
		}
		System.out.println();
	}

	///////////////////////////////////////////////////////

/**
*  Inner Class For MaxHeap
*  Stores Value and Number of Occurences
*/
	public class HeapNode <E extends Comparable<E>> implements Comparable <HeapNode>
	{
		private E value;
		private int num = 1;

		public HeapNode(E value)
		{
			this.value = value;
			num = 1;
		}

/**
* Getter Method For Value
* @return  value 
*/
		public E getValue()
		{
			return value;
		}

/**
* Getter Method For Number of Occurences
* @return  Number of Occurences 
*/
		public int getNum()
		{
			return num;
		}

/**
* Setter Method For Number of Occurences
* @param  num Number of Occurences 
*/
		public void setNum(int num)
		{
			this.num = num;
		}

/**
*  toString Method
*/
		public String toString()
		{
			return "(" + getValue() + "," + getNum() + ")";
		}

/**
*  compareTo Method
*/
		public int compareTo(HeapNode other)
		{
			return other.getValue().compareTo(getValue());			
		}
	}


}