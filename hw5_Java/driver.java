import java.lang.Math;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
*  @author Emre Sezer
*  Driver class for testing homework
*/
@SuppressWarnings("uncheked")
public class driver
{
/**
*  test method for testing
*/
	public static void test()
	{
		//////////////////////////////////////////////////////////////////////////////////////////////

		Scanner myObj = new Scanner(System.in);

		System.out.println("PART1:");

		MyHashMap <Integer, String> myMap = new MyHashMap<Integer, String>();

		MyHashMap.MapIterator iter = myMap.iterator(32);	// Map is empty so, there is no element with key 32.MapIterator,s index will be 0.	

		System.out.println("TESTING next and prev methods with an empty HashMap");	

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		while(iter.hasNext())
		{
			System.out.println(iter.prev());
		}

		System.out.println("As expected nothing is printed.Because, There is no elements inside the HashMap");

		// ADDING ELEMENTS TO THE MAP
		myMap.put(3, "Value:3");
		myMap.put(1, "Value:1");
		myMap.put(11, "Value:11");
		myMap.put(2, "Value:2");
		myMap.put(32, "Value:32");
		myMap.put(42, "Value:42");
		myMap.put(13, "Value:13");
		myMap.put(7, "Value:7");
		myMap.put(99, "Value:99");

		System.out.println("\nTESTING next method with HashMap that not empty(with default constructor)");

		iter = myMap.iterator();
		while(iter.hasNext())
		{
			System.out.println("KEY: " + iter.next());
		}

		System.out.println("As you can see it started printing from begining of the Map");

		System.out.println("\nTESTING constructor with parameter: Entered key is not at the map");

		iter = myMap.iterator(202);
		while(iter.hasNext())
		{
			System.out.println("KEY: " + iter.next());
		}
		System.out.println("As you can see it started printing from beginning of the Map");

		System.out.println("\nTESTING next method with HashMap that not empty(with constructor that takes parameter)");

		iter = myMap.iterator(42);
		while(iter.hasNext())
		{
			System.out.println("KEY: " + iter.next());
		}
		System.out.println("As you can see it started printing from entered key");

		System.out.println("\nTESTING prev method with HashMap that not empty(with default constructor)");

		iter = myMap.iterator();
		while(iter.hasNext())
		{
			System.out.println("KEY: " + iter.prev());
		}

		System.out.println("As you can see it started printing from begining of the Map");

		System.out.println("\nTESTING prev method with HashMap that not empty(with constructor that takes parameter)");

		iter = myMap.iterator(42);
		while(iter.hasNext())
		{
			System.out.println("KEY: " + iter.prev());
		}
		System.out.println("As you can see it started printing from entered key");

		//////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		System.out.println("PART2:\n");

		System.out.println("TESTING KWHashTableLinkedList:\n");

		KWHashTableLinkedList<Integer,String> linkedList = new KWHashTableLinkedList<Integer,String>(7);	// Initial size of KWHashTableLinkedList is 7

		// ADDING ELEMENTS TO THE TABLE (Initial Table Length is 7)

		System.out.println("TESTING put() method (returns null if there is no elements with entered key, if there is returns old value):\n");

		System.out.println("RETURNS: " + linkedList.put(3, "Value:3"));
		System.out.println("RETURNS: " + linkedList.put(12, "Value:12"));
		System.out.println("RETURNS: " + linkedList.put(13, "Value:13"));
		System.out.println("RETURNS: " + linkedList.put(25, "Value:25"));
		System.out.println("RETURNS: " + linkedList.put(23, "Value:23"));
		System.out.println("RETURNS: " + linkedList.put(51, "Value:42"));
		System.out.println("RETURNS: " + linkedList.put(6, "Value:6"));
		System.out.println("RETURNS: " + linkedList.put(7, "Value:7"));
		System.out.println("RETURNS: " + linkedList.put(31, "Value:31"));
		System.out.println("RETURNS: " + linkedList.put(41, "Value:41"));
		System.out.println("RETURNS: " + linkedList.put(41, "Value:NEW41"));
		System.out.println("RETURNS: " + linkedList.put(3, "Value:NEW3") + "\n");

		System.out.println("TESTING get() with elements added with put() method:\n");

		System.out.println("KEY: 3 , VALUE: " + linkedList.get(3));
		System.out.println("KEY: 12 , VALUE: " + linkedList.get(12));
		System.out.println("KEY: 13 , VALUE: " + linkedList.get(13));
		System.out.println("KEY: 25 , VALUE: " + linkedList.get(25));
		System.out.println("KEY: 23 , VALUE: " + linkedList.get(23));
		System.out.println("KEY: 51 , VALUE: " + linkedList.get(51));
		System.out.println("KEY: 6 , VALUE: " + linkedList.get(6));
		System.out.println("KEY: 7 , VALUE: " + linkedList.get(7));
		System.out.println("KEY: 31 , VALUE: " + linkedList.get(31));
		System.out.println("KEY: 41 , VALUE: " + linkedList.get(41));

		System.out.println("\nPRINTING TABLE BEFORE REMOVES:\n");
		linkedList.printTable();

		System.out.println("TESTING size() BEFORE REMOVES:\n");
		System.out.println("SIZE: " + linkedList.size() + "\n");

		System.out.println("\nTESTING remove() with existing elements:\n");
		System.out.println("REMOVING KEY: 7 , VALUE: " + linkedList.remove(7));
		System.out.println("REMOVING KEY: 13 , VALUE: " + linkedList.remove(13));
		System.out.println("REMOVING KEY: 41 , VALUE: " + linkedList.remove(41));

		System.out.println("\nTESTING remove() with non-existing elements:\n");
		System.out.println("REMOVING KEY: 202 , VALUE: " + linkedList.remove(202));
		System.out.println("REMOVING KEY: -25 , VALUE: " + linkedList.remove(-25));
		System.out.println("REMOVING KEY: 99 , VALUE: " + linkedList.remove(99));

		System.out.println("\nPRINTING TABLE AFTER REMOVES:\n");
		linkedList.printTable();

		System.out.println("TESTING size() AFTER REMOVES:\n");
		System.out.println("SIZE: " + linkedList.size() + "\n");

		System.out.println("TESTING get() method After Remove Operations:\n");	

		System.out.println("KEY: 3 , VALUE: " + linkedList.get(3));
		System.out.println("KEY: 12 , VALUE: " + linkedList.get(12));
		System.out.println("KEY: 13 , VALUE: " + linkedList.get(13));
		System.out.println("KEY: 25 , VALUE: " + linkedList.get(25));
		System.out.println("KEY: 23 , VALUE: " + linkedList.get(23));
		System.out.println("KEY: 51 , VALUE: " + linkedList.get(51));
		System.out.println("KEY: 6 , VALUE: " + linkedList.get(6));
		System.out.println("KEY: 7 , VALUE: " + linkedList.get(7));
		System.out.println("KEY: 31 , VALUE: " + linkedList.get(31));
		System.out.println("KEY: 41 , VALUE: " + linkedList.get(41));

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		//////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("TESTING KWHashTableTree:\n");

		KWHashTableTree<Integer,String> tree = new KWHashTableTree<Integer,String>(7);	// Initial size of KWHashTableTree is 7

		// ADDING ELEMENTS TO THE TABLE (Initial Table Length is 7)

		System.out.println("TESTING put() method (returns null if there is no elements with entered key, if there is returns old value):\n");
		
		System.out.println("RETURNS: " + tree.put(3, "Value:3"));
		System.out.println("RETURNS: " + tree.put(12, "Value:12"));
		System.out.println("RETURNS: " + tree.put(13, "Value:13"));
		System.out.println("RETURNS: " + tree.put(25, "Value:25"));
		System.out.println("RETURNS: " + tree.put(23, "Value:23"));
		System.out.println("RETURNS: " + tree.put(51, "Value:42"));
		System.out.println("RETURNS: " + tree.put(6, "Value:6"));
		System.out.println("RETURNS: " + tree.put(7, "Value:7"));
		System.out.println("RETURNS: " + tree.put(31, "Value:31"));
		System.out.println("RETURNS: " + tree.put(41, "Value:41"));
		System.out.println("RETURNS: " + tree.put(41, "Value:NEW41"));
		System.out.println("RETURNS: " + tree.put(3, "Value:NEW3") + "\n");

		System.out.println("TESTING get() with elements added with put() method:\n");

		System.out.println("KEY: 3 , VALUE: " + tree.get(3));
		System.out.println("KEY: 12 , VALUE: " + tree.get(12));
		System.out.println("KEY: 13 , VALUE: " + tree.get(13));
		System.out.println("KEY: 25 , VALUE: " + tree.get(25));
		System.out.println("KEY: 23 , VALUE: " + tree.get(23));
		System.out.println("KEY: 51 , VALUE: " + tree.get(51));
		System.out.println("KEY: 6 , VALUE: " + tree.get(6));
		System.out.println("KEY: 7 , VALUE: " + tree.get(7));
		System.out.println("KEY: 31 , VALUE: " + tree.get(31));
		System.out.println("KEY: 41 , VALUE: " + tree.get(41));

		System.out.println("\nPRINTING TABLE BEFORE REMOVES:\n");
		tree.printTable();

		System.out.println("TESTING size() BEFORE REMOVES:\n");
		System.out.println("SIZE: " + tree.size() + "\n");

		System.out.println("\nTESTING remove() with existing elements:\n");
		System.out.println("REMOVING KEY: 7 , VALUE: " + tree.remove(7));
		System.out.println("REMOVING KEY: 13 , VALUE: " + tree.remove(13));
		System.out.println("REMOVING KEY: 41 , VALUE: " + tree.remove(41));
		
		System.out.println("\nTESTING remove() with non-existing elements:\n");
		System.out.println("REMOVING KEY: 202 , VALUE: " + tree.remove(202));
		System.out.println("REMOVING KEY: -10 , VALUE: " + tree.remove(-10));
		System.out.println("REMOVING KEY: 99 , VALUE: " + tree.remove(99));

		System.out.println("\nPRINTING TABLE AFTER REMOVES:\n");
		tree.printTable();

		System.out.println("TESTING size() AFTER REMOVES:\n");
		System.out.println("SIZE: " + tree.size() + "\n");

		System.out.println("TESTING get() method After Remove Operations:\n");	

		System.out.println("KEY: 3 , VALUE: " + tree.get(3));
		System.out.println("KEY: 12 , VALUE: " + tree.get(12));
		System.out.println("KEY: 13 , VALUE: " + tree.get(13));
		System.out.println("KEY: 25 , VALUE: " + tree.get(25));
		System.out.println("KEY: 23 , VALUE: " + tree.get(23));
		System.out.println("KEY: 51 , VALUE: " + tree.get(51));
		System.out.println("KEY: 6 , VALUE: " + tree.get(6));
		System.out.println("KEY: 7 , VALUE: " + tree.get(7));
		System.out.println("KEY: 31 , VALUE: " + tree.get(31));
		System.out.println("KEY: 41 , VALUE: " + tree.get(41));

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		System.out.println("TESTING KWHashTableCoalesced:\n");

		KWHashTableCoalesced<Integer,String> coalesced = new KWHashTableCoalesced<Integer,String>();

		// ADDING ELEMENTS TO THE TABLE (Initial Table Length is 10)

		System.out.println("TESTING put() method (returns null if there is no elements with entered key, if there is returns old value):\n");
		
		System.out.println("RETURNS: " + coalesced.put(3, "Value:3"));
		System.out.println("RETURNS: " + coalesced.put(12, "Value:12"));
		System.out.println("RETURNS: " + coalesced.put(13, "Value:13"));
		System.out.println("RETURNS: " + coalesced.put(25, "Value:25"));
		System.out.println("RETURNS: " + coalesced.put(23, "Value:23"));
		System.out.println("RETURNS: " + coalesced.put(52, "Value:42"));
		System.out.println("RETURNS: " + coalesced.put(4, "Value:4"));

		System.out.println("RETURNS: " + coalesced.put(6, "Value:NEW6"));
		System.out.println("RETURNS: " + coalesced.put(12, "Value:NEW12"));

		System.out.println("TESTING get() with elements added with put() method:\n");

		System.out.println("KEY: 3 , VALUE: " + coalesced.get(3));
		System.out.println("KEY: 12 , VALUE: " + coalesced.get(12));
		System.out.println("KEY: 13 , VALUE: " + coalesced.get(13));
		System.out.println("KEY: 25 , VALUE: " + coalesced.get(25));
		System.out.println("KEY: 23 , VALUE: " + coalesced.get(23));
		System.out.println("KEY: 52 , VALUE: " + coalesced.get(52));
		System.out.println("KEY: 4 , VALUE: " + coalesced.get(4));

		System.out.println("\nPRINTING TABLE BEFORE REMOVES:\n");
		coalesced.printTable();

		System.out.println("TESTING size() BEFORE REMOVES:\n");
		System.out.println("SIZE: " + coalesced.size() + "\n");

		System.out.println("\nTESTING remove() with existing elements:\n");
		System.out.println("REMOVING KEY: 12 , VALUE: " + coalesced.remove(12));
		System.out.println("REMOVING KEY: 13 , VALUE: " + coalesced.remove(13));
		System.out.println("REMOVING KEY: 52 , VALUE: " + coalesced.remove(52));
		
		System.out.println("\nTESTING remove() with non-existing elements:\n");
		System.out.println("REMOVING KEY: 202 , VALUE: " + coalesced.remove(202));
		System.out.println("REMOVING KEY: -10 , VALUE: " + coalesced.remove(-10));
		System.out.println("REMOVING KEY: 99 , VALUE: " + coalesced.remove(99));

		System.out.println("\nPRINTING TABLE AFTER REMOVES:\n");
		coalesced.printTable();

		System.out.println("TESTING size() AFTER REMOVES:\n");
		System.out.println("SIZE: " + coalesced.size() + "\n");

		System.out.println("TESTING get() method After Remove Operations:\n");	

		System.out.println("KEY: 3 , VALUE: " + coalesced.get(3));
		System.out.println("KEY: 12 , VALUE: " + coalesced.get(12));
		System.out.println("KEY: 13 , VALUE: " + coalesced.get(13));
		System.out.println("KEY: 25 , VALUE: " + coalesced.get(25));
		System.out.println("KEY: 23 , VALUE: " + coalesced.get(23));
		System.out.println("KEY: 51 , VALUE: " + coalesced.get(51));
		System.out.println("KEY: 6 , VALUE: " + coalesced.get(6));
		System.out.println("KEY: 7 , VALUE: " + coalesced.get(7));
		System.out.println("KEY: 31 , VALUE: " + coalesced.get(31));
		System.out.println("KEY: 41 , VALUE: " + coalesced.get(41));

		// ADDING MORE ELEMENTS TO TABLE IN ORDER TO TEST REHASH METHOD

		System.out.println("RETURNS: " + coalesced.put(98, "Value:98"));
		System.out.println("RETURNS: " + coalesced.put(-34, "Value:-34"));
		System.out.println("RETURNS: " + coalesced.put(-1, "Value:-1"));
		System.out.println("RETURNS: " + coalesced.put(101, "Value:101"));
		System.out.println("RETURNS: " + coalesced.put(333, "Value:333"));
		System.out.println("RETURNS: " + coalesced.put(9, "Value:9"));

		System.out.println("\nPRINTING TABLE AFTER REHASH:\n");
		coalesced.printTable();

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		System.out.println("TESTING PERFORMANCES:\n");





		// For that part:
		// Initially all of the HashTable's lengths are same.
		// I put randomly generated numbers to each type of HashTable's combinations.(Numbers are in 0-20000 range)
		// I calculate elapsed time of each operation by substracting startTime from endTime.
		// I perform each operation 25 times and take average

		Integer rand = 0;
		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;

		KWHashTableLinkedList<Integer,String> listSmall = new KWHashTableLinkedList<Integer,String>();
		KWHashTableTree	<Integer,String>	treeSmall = new KWHashTableTree <Integer,String>();
		KWHashTableCoalesced <Integer,String>	coalescedSmall = new KWHashTableCoalesced <Integer,String>(101);
		Integer [] arraySmall = new Integer[50];

		for(int i = 0; i < 50; i++)
		{
			rand = (int)(Math.random() * 20000);
			listSmall.put(rand, "X");
			treeSmall.put(rand, "X");
			coalescedSmall.put(rand, "X");
			arraySmall[i] = rand;
		}

		KWHashTableLinkedList<Integer,String> listMed = new KWHashTableLinkedList<Integer,String>();
		KWHashTableTree	<Integer,String>	treeMed = new KWHashTableTree <Integer,String>();
		KWHashTableCoalesced <Integer,String>	coalescedMed = new KWHashTableCoalesced <Integer,String>(101);
		Integer [] arrayMed = new Integer[500];

		for(int i = 0; i < 500; i++)
		{
			rand = (int)(Math.random() * 20000);
			listMed.put(rand, "X");
			treeMed.put(rand, "X");
			coalescedMed.put(rand, "X");
			arrayMed[i] = rand;
		}

		KWHashTableLinkedList<Integer,String> listLarge = new KWHashTableLinkedList<Integer,String>();
		KWHashTableTree	<Integer,String>	treeLarge = new KWHashTableTree <Integer,String>();
		KWHashTableCoalesced <Integer,String>	coalescedLarge = new KWHashTableCoalesced <Integer,String>(101);
		Integer [] arrayLarge = new Integer[5000];

		for(int i = 0; i < 5000; i++)
		{
			rand = (int)(Math.random() * 20000);
			listLarge.put(rand, "X");
			treeLarge.put(rand, "X");
			coalescedLarge.put(rand, "X");
			arrayLarge[i] = rand;
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("ACCESSING EXISTING ELEMENTS WITH KWHashTableLinkedList:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			listSmall.get(arraySmall[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 500);
			startTime = System.nanoTime();
			listMed.get(arrayMed[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			listLarge.get(arrayLarge[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;


		System.out.println("ACCESSING NOT EXISTING ELEMENTS WITH KWHashTableLinkedList:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			listSmall.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			listMed.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			listLarge.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("ADDING ELEMENTS WITH KWHashTableLinkedList:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			listSmall.put(rand, "X");
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			listMed.put(rand, "X");
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			listLarge.put(rand,"X");
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("REMOVING ELEMENTS WITH KWHashTableLinkedList:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			listSmall.remove(arraySmall[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 500);
			startTime = System.nanoTime();
			listMed.remove(arrayMed[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			listLarge.remove(arrayLarge[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("ACCESSING EXISTING ELEMENTS WITH KWHashTableTree:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			treeSmall.get(arraySmall[rand]);
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 500);
			startTime = System.nanoTime();
			treeMed.get(arrayMed[rand]);
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			treeLarge.get(arrayLarge[rand]);
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;


		System.out.println("ACCESSING NOT EXISTING ELEMENTS WITH KWHashTableTree:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			treeSmall.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			treeMed.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			treeLarge.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("ADDING ELEMENTS WITH KWHashTableTree:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			treeSmall.put(rand, "X");
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			treeMed.put(rand, "X");
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			treeLarge.put(rand,"X");
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("REMOVING ELEMENTS WITH KWHashTableTree:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			treeSmall.remove(arraySmall[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: ");

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 500);
			startTime = System.nanoTime();
			treeMed.remove(arrayMed[rand]);
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			treeLarge.remove(arrayLarge[rand]);
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("\nType anything to continue testing");
		myObj.nextLine();

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		System.out.println("ACCESSING EXISTING ELEMENTS WITH KWHashTableCoalesced:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			coalescedSmall.get(arraySmall[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			coalescedMed.get(arrayMed[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			coalescedLarge.get(arrayLarge[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;


		System.out.println("ACCESSING NOT EXISTING ELEMENTS WITH KWHashTableCoalesced:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			coalescedSmall.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			coalescedMed.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 250) + 20000;
			startTime = System.nanoTime();
			coalescedLarge.get(rand);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("ADDING ELEMENTS WITH KWHashTableCoalesced:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			coalescedSmall.put(rand, "X");
			endTime =  System.nanoTime();
			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			coalescedMed.put(rand, "X");
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 20000);
			startTime = System.nanoTime();
			coalescedLarge.put(rand,"X");
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("REMOVING ELEMENTS WITH KWHashTableCoalesced:");
		System.out.println("SMALL-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 50);
			startTime = System.nanoTime();
			coalescedSmall.remove(arraySmall[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("MEDIUM-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 500);
			startTime = System.nanoTime();
			coalescedMed.remove(arrayMed[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		System.out.println("LARGE-SIZE: " );

		for(int i = 0; i < 25; i++)
		{	
			rand = (int)(Math.random() * 5000);
			startTime = System.nanoTime();
			coalescedLarge.remove(arrayLarge[rand]);
			endTime =  System.nanoTime();

			long temp = endTime - startTime;
			totalTime += temp;
		}
						
		System.out.println("Average: " + (long)totalTime / 25.0 + "\n");
		totalTime = 0;

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

/**
*  menu method for testing
*/
	public static void menu()		// Menu For testing Part2
	{
		Scanner myObj = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		int tableChoice;
		int choice;
		int key;
		String value;

		KWHashTableLinkedList  <Integer,String>table1 = new KWHashTableLinkedList<Integer, String>();
		KWHashTableTree  <Integer,String>table2 = new KWHashTableTree<Integer, String>();
		KWHashTableCoalesced  <Integer,String>table3 = new KWHashTableCoalesced<Integer, String>();
		
		while(true)
		{
			System.out.println("0) EXIT:");
			System.out.println("1) KWHashTableLinkedList:");
			System.out.println("2) KWHashTableTree:");
			System.out.println("3) KWHashTableCoalesced:");
			System.out.println("Enter a number:");

			tableChoice = myObj.nextInt();

			if(tableChoice == 0) System.exit(0);
			else if(tableChoice == 1)
			{
				while(true)
				{
					System.out.println("0) BACK:");
					System.out.println("1) PUT:");
					System.out.println("2) REMOVE:");
					System.out.println("3) GET:");
					System.out.println("4) PRINT TABLE:");
					System.out.println("Enter a number:");
					choice = myObj.nextInt();
					if(choice == 0)	break;
					else if(choice == 1)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						System.out.println("Enter the value:");
						value = myObj2.nextLine();
						table1.put(key, value);
						System.out.println(key +" is added");
					}
					else if(choice == 2)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();						
						if(table1.remove(key) != null)
						System.out.println(key +" is removed");
						else System.out.println("There is no such Entry");
					}
					else if(choice == 3)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						System.out.println("Value :" + table1.get(key));
					}
					else if(choice == 4)
					{
						System.out.println("TABLE:\n");
						table1.printTable();
						System.out.println("\n");
					}
				}
			}
			else if(tableChoice == 2)
			{
				while(true)
				{
					System.out.println("0) BACK:");
					System.out.println("1) PUT:");
					System.out.println("2) REMOVE:");
					System.out.println("3) GET:");
					System.out.println("4) PRINT TABLE:");
					System.out.println("Enter a number:");
					choice = myObj.nextInt();
					if(choice == 0)	break;
					else if(choice == 1)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						System.out.println("Enter the value:");
						value = myObj2.nextLine();
						table2.put(key, value);
						System.out.println(key +" is added");
					}
					else if(choice == 2)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						if(table2.remove(key) != null)
						System.out.println(key +" is removed");
						else System.out.println("There is no such Entry");
					}
					else if(choice == 3)
					{
						System.out.println("Enter the key:");
						key = myObj2.nextInt();
						System.out.println("Value :" + table2.get(key));
					}
					else if(choice == 4)
					{
						System.out.println("TABLE:\n");
						table2.printTable();
						System.out.println("\n");
					}
				}
			}
			if(tableChoice == 3)
			{
				while(true)
				{
					System.out.println("0) BACK:");
					System.out.println("1) PUT:");
					System.out.println("2) REMOVE:");
					System.out.println("3) GET:");
					System.out.println("4) PRINT TABLE:");
					System.out.println("Enter a number:");
					choice = myObj.nextInt();
					if(choice == 0)	break;
					else if(choice == 1)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						System.out.println("Enter the value:");
						value = myObj2.nextLine();
						table3.put(key, value);
						System.out.println(key +" is added");
					}
					else if(choice == 2)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						if(table3.remove(key) != null)
						System.out.println(key +" is removed");
						else System.out.println("There is no such Entry");
					}
					else if(choice == 3)
					{
						System.out.println("Enter the key:");
						key = myObj.nextInt();
						System.out.println("Value :" + table2.get(key));				
					}
					else if(choice == 4)
					{
						System.out.println("TABLE:\n");
						table3.printTable();
						System.out.println("\n");
					}
				}
			}



		}
				
	}

	@SuppressWarnings("uncheked")
	public static void main(String [] argc)	throws InterruptedException
	{
		test();
		menu();
	
	}
}