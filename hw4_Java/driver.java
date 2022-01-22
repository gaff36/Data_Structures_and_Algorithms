import java.util.Arrays;
import java.lang.Math;
import java.util.Scanner;

/**
*  @author Emre Sezer
*  Driver method for testing
*/
public class driver
{

/**
* This is a support method for testing.This method counts number of occurences of item in the Array
* @param arr Integer []
* @param item int
* @return number of occurences of item in the Array
*/	
	public static int countInArray(Integer [] arr, int item)
	{
		int count = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i].equals(item))	count++;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public static void main(String [] argc)
	{
		Scanner myObj = new Scanner(System.in);
		int rand;

		System.out.println("PART 1 TESTS:");	// You can see that at any situation heap structure will be preserved.

		Heap <Integer> heap1 = new Heap();
		Heap <Integer> heap2 = new Heap();

		System.out.println("TRYING TO SEARCH ON EMPTY HEAP:");
		try
		{
			heap1.search(32);
		}
		catch (IndexOutOfBoundsException e)	{System.out.println("HEAP IS EMPTY");}	

		System.out.println("TRYING TO MERGE 2 EMPTY HEAPS.AFTER THAT OPERATION MERGED HEAP WILL STILL BE EMPTY");
		heap1.merge(heap2);										// heap1's size will be 0.Because both heaps are empty.
		heap1.printHeap();										

		// Adding elements to the Heaps

		heap1.add(14);		
		heap1.add(13);
		heap1.add(34);
		heap1.add(23);
		heap1.add(43);
		heap1.add(55);
		heap1.add(1);

		heap2.add(34);
		heap2.add(12);
		heap2.add(6);
		heap2.add(99);
		heap2.add(66);
		heap2.add(7);
		heap2.add(55);
		heap2.add(8);
		heap2.add(72);
		heap2.add(87);

		System.out.println("\nHEAP1:");
		heap1.printHeap();	

		System.out.println("\nHEAP1 SEARCH TESTS:");
		if(heap1.search(13))	System.out.println("13 IS FOUND ON HEAP");		// Searching element exists on heap
		else System.out.println("13 IS NOT FOUND ON HEAP" );

		if(heap1.search(32))	System.out.println("32 IS FOUND ON HEAP");		// Searching element doesn't exist on heap.You can see that at next step that prints heap1
		else System.out.println("32 IS NOT FOUND ON HEAP" );

		System.out.println("\nHEAP1 MERGE TESTS:");

		System.out.println("\nHEAP1 BEFORE MERGE:");
		heap1.printHeap();	

		System.out.println("\n\nHEAP2 BEFORE MERGE:");
		heap2.printHeap();

		heap1.merge(heap2);
		System.out.println("\n\nHEAP1 AFTER MERGE:(AS YOU CAN SEE HEAP2 IS SUCCESFULLY ADDED TO HEAP1 AND HEAP STRUCTURE IS PRESERVED)");	// MERGING 2 HEAPS
		heap1.printHeap();

		System.out.println("\n\nHEAP2 BEFORE REMOVAL:");
		heap2.printHeap();
		System.out.println("\n\nRemoving 3'rd Largest Element From The heap2 :");
		System.out.println("Removed Element : " + heap2.removeithLargest(3));		// As you can see 72 is third largest element on the Heap and it is removed from the Heap

		System.out.println("\nHEAP2 AFTER REMOVAL:(AS YOU CAN SEE 72 IS REMOVED FROM THE HEAP2)");
		heap2.printHeap();

		System.out.println("\n\nRemoving 10'th Largest Element From The heap2 (Since size of the heap2 is less than 10 exception will be thrown):");	// Since size of the heap2 is less than 10.

		try
		{
			System.out.println("Removed Element : " + heap2.removeithLargest(10));
		}		
		catch (IndexOutOfBoundsException e)	{System.out.println("INDEX OUT OF BOUNDS");}	

		System.out.println();

		System.out.println("\nHEAP2 BEFORE SET OPERATION:");
		heap2.printHeap();

		System.out.println("\nHEAP2 AFTER SET OPERATION:((AS YOU CAN SEE 6 IS SET TO 101)");


		Heap.HeapIterator iter = heap2.iterator();

		for(int i = 0; i < 4; i++) iter.next();

		iter.set(101);							// As you can see '6' is set to '101'. Then, '101' is located to the appropriate position.
												// After set operation Heap structure will be preserved.
		heap2.printHeap();
		System.out.println("\n");

		/////////////////////////////////////////////////

		System.out.println("PART 2 TESTS:");

		BSTHeapTree <Integer> bt = new BSTHeapTree <Integer>();		// Adding random elements to both Array and BSTHeapTree
		Integer [] array = new Integer[3000];
		Integer [] removedElements = new Integer[100];

		for(int i = 0; i < 3000; i++)
		{
			rand = (int)(Math.random() * 5000);	
			bt.add(rand);
			array[i] = rand;
		}

		Arrays.sort(array);

		System.out.println("TYPE ANYTHING TO CONTINUE TESTING:");	
		myObj.nextLine();

		System.out.println("SORTED ARRAY:");
		for(int i = 0; i < 3000; i++)	System.out.printf("%d\n", array[i]);		// It prints every element that stored in the Array and also BSTHeapTree
																					// At next steps i wrote a support method to make testing easier.But you can also check
		System.out.println("TYPE ANYTHING TO CONTINUE TESTING:");					// from the list of elements this part prints if program works correctly.
		System.out.println("FINDING ELEMENTS THERE ARE BOTH ON TREE AND ARRAY");
		myObj.nextLine();

		for(int i = 0; i < 100; i++)	// I wrote countInArray method that counts number of occurences of an item in the Array.With help of that method you can check whether program works correctly.
		{
			rand = (int)(Math.random() * 3000);
			System.out.println(array[rand] + " : (" + "In Array : "+ countInArray(array, array[rand]) + " In Tree : " + bt.find(array[rand]) + ")");
		}

		System.out.println("\nTYPE ANYTHING TO CONTINUE TESTING:");
		System.out.println("FINDING ELEMENTS THERE ARE NOT ON TREE:");		// FINDING ELEMENTS THERE ARE NOT ON TREE
		myObj.nextLine();

		for(int i = 0; i < 10; i++)
		{
			rand = (int)(Math.random() * 100 + 5000) ;
			System.out.println(rand + " : (" + "In Array : "+ countInArray(array, rand) + " In Tree : " + bt.find(rand) + ")");
		}	

		System.out.println("\nTYPE ANYTHING TO CONTINUE TESTING:");
		myObj.nextLine();

		System.out.printf("MODE OF THE TREE IS: \n");		// Finding mode(s)
		bt.find_mode();


		System.out.println("\nTYPE ANYTHING TO CONTINUE TESTING:");
		System.out.println("REMOVING ELEMENTS THERE ARE ON TREE:");
		myObj.nextLine();

		for(int i = 0; i < 100; i++)
		{
			rand = (int)(Math.random() * 3000);
			System.out.println("After remove " + bt.remove(array[rand]) + " " + array[rand] + " left");				// I wrote countInArray method that counts number of occurences of an item in the Array.With help of that method you can check whether program works correctly.
			removedElements[i] = array[rand];
			System.out.println(array[rand] + " (" + "In Array : "+ countInArray(array, array[rand]) + " In Tree : " + bt.find(array[rand]) + ")");
		}

		System.out.println("\nTYPE ANYTHING TO CONTINUE TESTING:");
		System.out.println("REMOVING ELEMENTS THERE ARE NOT ON TREE:");		// REMOVING ELEMENTS THERE ARE NOT ON TREE
		myObj.nextLine();

		for(int i = 0; i < 10; i++)
		{
			rand = (int)(Math.random() * 100 + 5000);
			System.out.println(rand + " : (Before Remove)(In Tree : " + bt.find(rand) + ")");	// Prints before and after remove.
			bt.remove(rand);																	// So, you can see that nothing is removed from the tree.
			System.out.println(rand + " : (After Remove)(In Tree : " + bt.find(rand) + ")\n");	// Since, there is no such an element in the tree.
		}
		
	}
	
}