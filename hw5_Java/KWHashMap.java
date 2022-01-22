/**
*  @author Emre Sezer
*  KWHashMap interface at the book.
*  This interface is for PART 2.
*/
public interface KWHashMap <K,V>
{
/**
*  Inner class Entry for KWHashMap
*/
	public static class Entry<K extends Comparable<K>,V> implements Comparable <Entry>
	{
		private final K key;
		private V value;
		private Integer next;		// This is for KWHashTableCoalesced

		/** 
		@param key The key
		@param value The value
		*/
		public Entry(K key, V value) 
		{
			this.key = key;
			this.value = value;
			next = null;
		}
		public int compareTo(Entry other){
            	return other.getKey().compareTo(getKey());
        	}

		/** Retrieves the key.
		@return The key
		*/
		public K getKey() 
		{
			return key;
		}

		/** Retrieves the value.
		@return The value
		*/

		public V getValue() 
		{
			return value;
		}

		/** Retrieves the next.
		@return The next
		*/

		public Integer getNext() 
		{
			return next;
		}

		/** Sets the value.
		@param val The new value
		@return The old value
		*/
		public V setValue(V val)
		{
			V oldVal = value;
			value = val;
			return oldVal;
		}
		public void setNext(Integer next)
		{
			this.next = next;
		}
	}

	//////////////////////////////////////////////////////

	public V get(Object key);	
	public V put(K key, V value);
	public V remove(Object key);
	public boolean isEmpty();
	public int size();
	public void printTable();

	//////////////////////////////////////////////////////
}