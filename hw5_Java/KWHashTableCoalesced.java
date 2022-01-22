/**
*  @author Emre Sezer
*  Hash table implementation using chaining with TreeSet. 
*  This class is for PART 2.
*  This class implements KWHashMap. 
*/

@SuppressWarnings("uncheked")
public class KWHashTableCoalesced <K extends Comparable<K>,V>	implements KWHashMap <K,V> 
{
	//////////////////////////////////////////////////////
	
	/** The table */
	private Entry<K, V>[] table;
	/** The number of keys */
	private int numKeys;
	/** The capacity */
	private static final int CAPACITY = 10;
	/** The maximum load factor */
	private static final double LOAD_THRESHOLD = 0.75;

	//////////////////////////////////////////////////////

/** Default Constructor for KWHashTableCoalesced.
*/
	public KWHashTableCoalesced()
	{
		table = new Entry[CAPACITY];
		numKeys = 0;
	}

/** Constructor with parameter for KWHashTableTree.
* @param size int
*/
	public KWHashTableCoalesced(int size)
	{
		table = new Entry[size];
		numKeys = 0;
	}	

/** Method get for class KWHashTableCoalesced.
* It follows table[index]'s nexts if finds returns the Value
* Else returns null
* @param key The key being sought
* @return The value associated with this key if found; otherwise, null
*/
	public V get(Object key)
	{
		int index = hashMethod((K)key, 0);
		if (index < 0)	index += table.length;

		if(table[index] == null)	return null;

		while(table[index].getNext() != null)
		{
			if(table[index].getKey().equals(key))	
			{
				return table[index].getValue();
			}			
			index = table[index].getNext();
		}
		if(table[index].getKey().equals(key))	return table[index].getValue();

		return null;
	}

/** Method put for class KWHashTableLinkedList.
*   This key value pair is inserted in the
* 	table and numKeys is incremented. If the key is already
* 	in the table, its value is changed to the argument
* 	value and numKeys is not changed.
*   Each Entry keeps an Integer next.next is initially null.
*   If collision happenes while puting, collided element(key that already in the table)'s next will be the index that input is put at the table.
*   If collided element(key that already in the table) has already a next value.It will check the table[collided element.next]
*   This will repeat until it finds an element that next value is null.
* 	@param key The key of item being inserted
* 	@param value The value for this key
* 	@return The old value associated with this key if found; otherwise, null
*/
	public V put(K key, V value)
	{
		V result = null;
		int counter = 0;
		int index = hashMethod(key, counter);
		if (index < 0)	index += table.length;
		int previousIndex;

		if(table[index] == null)		// If finds empty position at first try puts item to that position.
		{
			table[index] = new Entry<>(key, value);
			numKeys++;
			return result; 
		}
		
		while(table[index].getNext() != null)			// Follows nexts
		{			
			if(table[index].getKey().equals(key))		// If finds key equals to entered key
			{
				result = table[index].getValue();
				table[index].setValue(value);			// Changes it's value to entered value
				return result;							// Returns previous value
			}	
			index = table[index].getNext();				// changes index to current elements next.
		}
		if(table[index].getKey().equals(key))			// Checks if element that has no next's key is equal to entered key.	
		{
			result = table[index].getValue();
			table[index].setValue(value);				// Changes it's value to entered value		
			return result;								// Returns previous value
		}
		previousIndex = index;

		while(table[index] != null)						// Searches for empty position
		{
			if(key.hashCode() < 0)System.out.println(index);
			if(counter > table.length)	break;			// If can't find a suitable position	
			counter++;
			index = hashMethod(key, counter);			// Changes index at each step
			if(key.hashCode() < 0)System.out.println(index);
		}	

		if(counter > table.length)						// If can't find a suitable position
		{
			rehash();									// Resizes the table
			put(key, value);							// Calls itself again and puts that value to table.
			return value;
		}
		table[index] = new Entry<>(key, value);			// When empty position is found adds new Entry to that position
		table[previousIndex].setNext(index);
		numKeys++;
		if (numKeys > (LOAD_THRESHOLD * table.length)) rehash();	// Resizes the table	
		return result;									// Returns entered Entry's value
	}

/** 
*  hashMethod for KWHashTableCoalesced.It calculates an index at the table(using Quadratic probing).
*  This is a support method.
*   @param key K
*   @param counter int
*   @return an index at the table
*/
	private int hashMethod(K key, int counter)			
	{
		int index = (key.hashCode() + (counter * counter)) % table.length;		
		if (index < 0)	index += table.length;
		return index;
	}

/** 
*   Private Rehash Method for KWHashTableCoalesced.
*   It resizes the table and puts all elements in the old table to the new sized array
*/
	private void rehash()
	{
		Entry<K,V> [] oldTable = table;
		table = new Entry [2 * oldTable.length];
		int oldSize = numKeys;

		for(int i = 0; i < oldTable.length; i++)
		{
			if(oldTable[i] != null)
			{
				put(oldTable[i].getKey(), oldTable[i].getValue());
				numKeys--;
			}
		}
		numKeys = oldSize;
	}

/** 
*   If there is Entry with Key "key", removes it and returns it's value.
*   If there is no Entry with Key "key", returns null.
*   If there is no next for the first calculated index removes that and returns its Value
*   If there is next for the first calculated index it follows nexts(index = table[index].getNext() and table[index])
*   If finds a key equals to input removes it.
*   Then it moves the table[table[index].getNext()] to table[index]. It repeats that until finds an element that it's next is null.
*   Basically, it moves current position's next element to current position.
*	@param key Object
*	@return V
*/
	public V remove(Object key)
	{
		if(numKeys == 0)	return null;	// If there is no elements inside the table returns null

		int index = hashMethod((K)key, 0);
		if (index < 0)	index += table.length;

		if(table[index] == null)	return null;

		int counter = 0;
		int prevIndex;				// index that table[prevIndex].next = index.
		int afterIndex;				// index that table[index].next = afterIndex.
		boolean flag1 = false;		// For checking equality
		boolean flag2 = false;		// For checking equality
		V result = null;

		
			index = hashMethod((K)key, counter);	
				
				prevIndex = index;
				while(table[index].getNext() != null)		// Iterates until there is no next
				{
					prevIndex = index;	
					
					if(table[index].getKey().equals(key))		// If finds an Entry that key is equal to input key
					{
						flag1 = true;
						result = table[index].getValue();		// Keeps that Entry's Value
						while(table[index].getNext() != null)	// This part removes the wanted Entry and shifts all of the elements at the chain.
						{							
							flag2 = true;
							prevIndex = index;						
							afterIndex = table[index].getNext();							
							table[index] = new Entry<>(table[table[index].getNext()].getKey(), table[table[index].getNext()].getValue());
							table[index].setNext(afterIndex);					
							table[prevIndex].setNext(table[index].getNext());				
							index = table[index].getNext();
						}
						if(flag1)			// If Entry that wanted to be deleted's next is not null
						{
							if(flag2)
							{
								table[prevIndex].setNext(null);		// Chain's last element (Entry that has no next) will not be pointed with that part.
							}										// It's previous Entry's next will become null
							table[index].setNext(null);				// Chain's last element (Entry that has no next) will be null		
							table[index] = null;
						}
						numKeys--;									// Decreases the size by 1
						return result;
					}
									
					index = table[index].getNext();

				}		
				if(table[index].getKey().equals(key) && flag1 == false)		// If Entry that wanted to be deleted's next is null
				{						
					result = table[index].getValue();		// Keeps that Entry's Value
					table[prevIndex].setNext(null);
					table[index] = null;					// Chain's last element (Entry that has no next) will be null		
					numKeys--;								// Decreases the size by 1
					return result;
				}
				else
				{
					return null;
				}	
	}		

/** 
*   Return the total Entrys in the table
*	@return numKeys
*/	
	public int size()
	{
		return numKeys;
	}

/** 
*   Returns true if numkeys is equal to 0.Returns false if numkeys is not equal to 0.
*	@return true if there is no Entrys in the table; otherwise returns true
*/
	public boolean isEmpty()
	{
		if(numKeys == 0)	return true;
		return false;
	}

/** 
*   Method that prints the table
*/
	public void printTable()
	{
		for(int i = 0; i < table.length; i++)
		{	
			if(table[i] != null)System.out.println("HASHCODE: " + i + " KEY: " + table[i].getKey() + " VALUE: " + table[i].getValue());	
			if(table[i] != null && table[i].getNext() != null)	System.out.println("NEXT: " + table[i].getNext());
		}
	}	
}