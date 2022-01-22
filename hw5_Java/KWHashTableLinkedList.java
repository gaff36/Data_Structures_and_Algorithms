import java.util.LinkedList;

/**
*  @author Emre Sezer
*  Hash table implementation using chaining with LinkedList. 
*  This class is for PART 2.
*  This class implements KWHashMap. 
*/
@SuppressWarnings("uncheked")
public class KWHashTableLinkedList  <K extends Comparable<K>,V> implements KWHashMap <K,V> 
{

	
	//////////////////////////////////////////////////////

	/** The table */
	private LinkedList<Entry<K, V>>[] table;
	/** The number of keys */
	private int numKeys;
	/** The capacity */
	private static final int CAPACITY = 101;
	/** The maximum load factor */
	private static final double LOAD_THRESHOLD = 3.0;

	//////////////////////////////////////////////////////

/** Default Constructor for KWHashTableLinkedList.
*/
	public KWHashTableLinkedList()
	{
		table = new LinkedList[CAPACITY];
		numKeys = 0;
	}

/** Constructor with parameter for KWHashTableTree.
* @param size int
*/
	public KWHashTableLinkedList(int size)
	{
		table = new LinkedList[size];
		numKeys = 0;
	}	

/** Method get for class KWHashTableLinkedList.
* @param key The key being sought
* @return The value associated with this key if found; otherwise, null
*/
	public V get(Object key)
	{
		int index = key.hashCode() % table.length;

		if (index < 0)	index += table.length;
		
		if (table[index] == null)	return null;

		for (Entry<K, V> nextItem : table[index])
		{
			if (nextItem.getKey().equals(key))	return nextItem.getValue();
		}	

		return null;	
	}

/** Method put for class KWHashTableLinkedList.
*   This key value pair is inserted in the
* 	table and numKeys is incremented. If the key is already
* 	in the table, its value is changed to the argument
* 	value and numKeys is not changed.
* 	@param key The key of item being inserted
* 	@param value The value for this key
* 	@return The old value associated with this key if found; otherwise, null
*/
	public V put(K key, V value)
	{
		int index = key.hashCode() % table.length;

		if (index < 0)	index += table.length;
		if (table[index] == null)	table[index] = new LinkedList<>();

		for (Entry<K, V> nextItem : table[index])
		{
			if (nextItem.getKey().equals(key)) 
			{
				V oldVal = nextItem.getValue();
				nextItem.setValue(value);
				return oldVal;
			}
		}

		table[index].addFirst(new Entry<>(key, value));
		numKeys++;
		if (numKeys > (LOAD_THRESHOLD * table.length)) rehash();
		return null;
	}

/** 
*   Private Rehash Method for KWHashTableLinkedList.
*   It resizes the table and puts all elements in the old table to the new sized array
*/
	private void rehash()
	{
		LinkedList<Entry<K, V>>[] oldTable = table;
		table = new LinkedList[nextPrime(2 * table.length)];
		int oldSize = numKeys;

		for(int i = 0; i < oldTable.length; i++)
		{
			if(oldTable[i] != null)
			{
				for(int j = 0; j < oldTable[i].size(); j++)
				put(oldTable[i].get(j).getKey(), oldTable[i].get(j).getValue());
			}
		}
		numKeys = oldSize;
	}

/** 
*   Finds the next prime number after num
*	@param num int
*	@return next prime number after num
*/
	public static int nextPrime(int num) 
	{
      num++;
      for (int i = 2; i < num; i++) 
      {
         if(num % i == 0) 
         {
            num++;
            i=2;
         } 
         else 
         {
            continue;
         }
      }
      return num;
   }

/** 
*   If there is Entry with Key "key", removes it and returns it's value.
*   If there is no Entry with Key "key", returns null.
*	@param key Object
*	@return V
*/
	public V remove(Object key)
	{
		int index = key.hashCode() % table.length;
		if (index < 0)	index += table.length;
		V result = null;
		if(numKeys == 0)	return null;

		if(table[index] == null)	return null;

		for(int i = 0; i < table[index].size(); i++)
		{			
			if(table[index].get(i).getKey().equals(key))
			{
				numKeys--;
				result = (V)table[index].get(i).getValue();
				table[index].remove(i);
				if(table[index].size() == 0)	table[index] = null;
				break;
			}						
		}
		return result;
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
			if(table[i] != null)
			{
				System.out.println("HASHCODE: " + i);
				for(Entry temp : table[i])
				System.out.println("KEY: " + temp.getKey() + " VALUE: " + temp.getValue());	
			}
			
		}
	}	

	//////////////////////////////////////////////////////

}