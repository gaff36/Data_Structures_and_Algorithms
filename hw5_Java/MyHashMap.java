import java.util.HashMap;
import java.util.Set;

/**
*  @author Emre Sezer
*  This class is for PART 1.
*  This class extends HashMap. It has MapIterator inner class.
*/
@SuppressWarnings("uncheked")
public class MyHashMap <K, V> extends HashMap
{

	//////////////////////////////////////////////////

/**
*  Default iterator method for MyHashMap.
*  @return MyIterator 
*/	
	public MapIterator iterator()
	{
		return new MapIterator();
	}

/**
*  iterator method with parameter for MyHashMap.
* @param key K
* @return MyIterator 
*/	
	public MapIterator iterator(K key)
	{
		return new MapIterator(key);
	}

	//////////////////////////////////////////////////

/**
*  Inner Iterator class for MyHashMap.
*  This is the Class that requested for PART1.
*/
	public class MapIterator <K, V>
	{
		Set <K> keySet;		// For storing Map's Keys
		K [] keys;			// For storing Map's Keys
		int index;			// For keeping current Key
		int counter;		// Counter is for counting visited Keys

/**
*   Default constructor
*/
		public MapIterator()
		{
			index = 0;	
			counter = 0;
			keySet = keySet();					// Stores Keys of the Map to the Set
			keys = (K []) keySet.toArray();		// Transfers data in the Set to Array
		}

/**
*   Constructor with parameter
*   @param key K
*/		
		public MapIterator(K key)
		{
			this();

			for(int i = 0; i < keys.length; i++)
			{
				if(key.equals(keys[i])) 
				{
					index = i;
					break;
				}
			}
		}

/**
*   MyIterator's next method.
*   @return next Key
*/			
		public K next()
		{
			K result = keys[index];
			index++;
			counter++;
			index = index % keys.length;
			return result;
		}

/**
*   MyIterator's previous method.
*   @return previous Key
*/
		public K prev()
		{
			K result = keys[index];
			index--;
			counter--;
			if(index < 0)	index = keys.length - 1;
			return result;
		}

/**
*   MyIterator's hasNext method. Returns true if there are elements that not visited yet in the Map.
*   Returns false if there are non-visited elements in the Map.
*   @return boolean
*/
		public boolean hasNext()
		{
			if(counter >= keys.length || counter <= (-1) * (keys.length))	return false;
			return true;
		}

	}

	//////////////////////////////////////////////////

}