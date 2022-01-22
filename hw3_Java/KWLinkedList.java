import java.util.*;

/** 
 * @author Emre Sezer
 * Generic KWLinkedList Class
*/
@SuppressWarnings("unchecked")
public class KWLinkedList <E>
{
	private Node <E> head;
	private Node <E> tail;
	private int size = 0;

/** 
 * 	 gets the index'th element from the list
 *   @param index int
 *   @return the index'th element from the list
*/	
	public E get (int index)
	{
		return iterator(index).next();
	}

/** 
 * 	 adds at the selected position.
 *   @param index int
 *   @param entry E
*/	
	public void add(int index, E entry)
	{
		iterator(index).add(entry);
	}

/** 
 * 	 adds to the end of the list.
 *   @param entry E
*/	
	public void add(E entry)
	{
		iterator(size).add(entry);
	}

/** 
 * 	 removes index'th element from the list.
 *   @param index int
 *   @return removed element at the index 
*/
	public E remove(int index)
	{
		if(size() <= 0) throw new IndexOutOfBoundsException();
		else 
		{
			E result = get(index);
			iterator(index).remove();
			return result;
		}	
	}

/** 
 * 	 sets index'th element from the list.
 *   @param index int
 *   @param entry E
 *   @return switched element  
*/	
	public E set (int index, E entry)
	{
		E result = get(index);
		iterator(index).set(entry);
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
	public int indexOf (E entry)
	{
		for(int i = 0; i < size; i++)
		{
			if(entry.equals(get(i)))	return i;
		}
		return -1;
	}

/** 
 * 	 returns a ListIterator at the begining of the list 
 *   @return ListIterator at the begining of the list 
*/
	public ListIterator <E> iterator()
	{
		return new KWListIter();
	}

/** 
 * 	 returns a ListIterator at the selected position
 *   @return ListIterator at the selected position
*/
	public ListIterator <E> iterator(int index)
	{
		return new KWListIter(index);
	}	

/** 
 *  It is a custom inner ListIterator class for KWLinkedList.
*/
	private class KWListIter implements ListIterator<E>
	{
		private int index = 0;
		private Node <E> nextItem;
		private Node <E> lastItemReturned;

/** 
 *  ListIterator Constructor
*/
		public KWListIter()
		{
			if(head != null)
			{
				index = 0;
				nextItem = head;
				lastItemReturned = null;
			}
		}

/** 
 *  ListIterator Constructor 
*/											
		public KWListIter (int i)	
		{
			if(i < 0 || i > size)	throw new IndexOutOfBoundsException(Integer.toString(index));
			lastItemReturned = null;

			if(i == size)
			{
				index = size;
				nextItem = null;
			}
			else if(i < size && i > size / 2)
			{
				nextItem = tail;
				for(index = size - 1; index > i; index--)
				{
					nextItem = nextItem.previous;
				}
			}
			else
			{
				nextItem = head;
				for(index = 0; index < i; index++)
				{
					nextItem = nextItem.next;
				}
			}
		}								

/** 
 *  Returns true if iterator is not at the end of the list and vice versa.
 *  @return true if iterator is not at the end of the list and vice versa.
*/
        public boolean hasNext() 
        {
			return nextItem != null;
		}

/** 
 *  Returns true if iterator is not at the beginning of the list and vice versa.
 *  @return true if iterator is not at the beginning of the list and vice versa.
*/
        public boolean hasPrevious() 
        {
			if(index < size && index >= 0)	return true;
			return false;
		}

/** 
 *  Returns the previous element of the current position.
 *  @return the previous element of the current position.
*/
		public E previous()
		{
			if(!hasPrevious())	throw new IndexOutOfBoundsException(Integer.toString(index));
			lastItemReturned = nextItem;
			nextItem = nextItem.previous;
			index--;
			return lastItemReturned.data;
		}	

/** 
 *  Returns the next element of the current position.
 *  @return the next element of the current position.
*/
		public E next()
		{
			if(!hasNext())	throw new IndexOutOfBoundsException(Integer.toString(index));
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.data;
		}

/** 
 *  Returns the next index of the current position
 *  @return the next index of the current position
*/
		public int nextIndex()
		{
			if(index + 1 >= size)	return -1;
			return index + 1;
		}

/** 
 *  Returns the previous index of the current position
 *  @return the previous index of the current position
*/		
		public int previousIndex()
		{
			if(index - 1 < 0)	return -1;
			return index - 1;
		}

/** 
 *  Removes the current element
*/
        public void remove()
        {   	
        	if (head == null)
			{
				throw new IndexOutOfBoundsException();	
			}
        	else if(nextItem == head)	
			{
				//head.next.previous = null;
				head = head.next;
				//head.previous = null;
			}
			else if(nextItem == tail)
			{
				tail.previous.next = null;
				tail = tail.previous;
			}
			else
			{
				nextItem.previous.next = nextItem.next;
				nextItem.next.previous = nextItem.previous;
			}
			size--;
        }

/** 
 *  Adds the entry to the current position
 *  @param entry E
*/
        public void add (E entry)
        {
        	if(head == null)
        	{
        		head = new Node<E>(entry);
				tail = head;
				size++;
        	}
        	else if (nextItem == head)
        	{
				Node<E> temp = new Node<E>(entry);
				temp.next = nextItem;
				nextItem.previous = temp;
				head = temp;
				size++;
				index++;
			}
			else if (nextItem == null) 
			{
				Node<E> temp = new Node<E>(entry);
				tail.next = temp;
				temp.previous = tail;
				tail = temp;
				size++;
				index++;
			}
			else 
			{
				Node<E> temp = new Node<E>(entry);
				temp.previous = nextItem.previous;
				nextItem.previous.next = temp;
				temp.next = nextItem;
				nextItem.previous = temp;
				size++;
				index++;
			}
        }

/** 
 *  Sets the current element to entry
 *  @param entry E
*/
        public void set(E entry)
        {
        	if(head == null)	throw new IndexOutOfBoundsException();
        	else if(nextItem == head)
        	{
        		Node <E> temp = new Node <E>(entry);
        		temp.next = nextItem.next;
        		nextItem.next.previous = temp;
        		head = temp;
        	}
        	else if (nextItem == tail)
        	{
        		Node <E> temp = new Node <E>(entry);
        		temp.previous = nextItem.previous;
        		nextItem.previous.next = temp;
        		tail = temp;
        	}
        	else
        	{
        		Node <E> temp = new Node <E>(entry);
        		temp.previous = nextItem.previous;
        		temp.next = nextItem.next;
        		nextItem.next.previous = temp;
        		nextItem.previous.next = temp;
        		nextItem = temp;
        	}
        }
    }

/** 
 *  It is a custom inner Node class for KWLinkedList.
*/
	private class Node <E> 
	{
		private E data;
		private Node <E> previous = null;
		private Node <E> next = null;

/** 
 *  Node Constructor
*/
		private Node (E item)
		{
			data = item;
		}

/** 
 *  Node Constructor
*/
		private Node(E item, Node<E> nextNode)
	 	{
			data = item;
			next = nextNode;
		}

	}

}