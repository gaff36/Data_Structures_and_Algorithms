import java.io.*;
import java.lang.*;
import java.util.*;

/**
*  @author Emre Sezer
*  Sorts Class That Includes Sort Methods For Products.
*/
public class Sorts
{

	// INSERTION SORT

/**
*  Insertion Sort Method
*  @param table ArrayList<Product>
*  @param c Comparator<Product>
*/
	public static void insertionSort(ArrayList<Product> table, Comparator<Product> c)
	{
		for(int nextPos = 1; nextPos < table.size(); nextPos++)
		{
			Product nextVal = table.get(nextPos);

			while(nextPos > 0 && c.compare(nextVal, table.get(nextPos - 1)) < 0)	
			{
				table.set(nextPos, table.get(nextPos - 1));
				nextPos--;
			}
			table.set(nextPos, nextVal);
		}
	}

	////////////////////////////////////////////////////////////////////////////

	// HEAP SORT

/**
*  Support Method For Heap Sort. This method is For satisfying the Heap Structure
*  @param table ArrayList<Product>
*  @param c Comparator<Product>
*/
	public static void heapify(ArrayList<Product> table, Comparator<Product> c)
	{
		int n = 1;

		while(n < table.size())
		{
			n++;
			int child = n - 1;
			int parent = (child - 1) / 2;
			while(parent >= 0 && c.compare(table.get(parent), table.get(child)) < 0)	
			{
				swap(table, parent, child);
				child = parent;
				parent = (child - 1) / 2;
			}
		}
	}

/**
*  Support Method For Heap Sort.
*  @param table ArrayList<Product>
*  @param c Comparator<Product>
*/
	public static void shrinkHeap(ArrayList<Product> table, Comparator<Product> c)
	{
		int n = table.size();
			
		while(n > 0)
		{
			n--;
			swap(table, 0, n);
			int parent = 0;

			while(true)
			{
				int leftChild = 2 * parent + 1;
				if(leftChild >= n)	break;

				int rightChild = leftChild + 1;

				int maxChild = leftChild;

				if(rightChild < n && c.compare(table.get(leftChild), table.get(rightChild)) < 0)	maxChild = rightChild;

				if(c.compare(table.get(parent), table.get(maxChild)) < 0)
				{
					swap(table, parent, maxChild);
					parent = maxChild;
				}
				else break;

			}

		}
	}

/**
*  This method swaps the elements at parent and child
*  @param table ArrayList<Product>
*  @param parent int
*  @param child int
*/
	private static void swap(ArrayList<Product> table, int parent, int child)
	{		
		Product temp = table.get(child);
		table.set(child, table.get(parent));
		table.set(parent, temp);
	}

/**
*  Heap Sort Method
*  @param table ArrayList<Product>
*  @param c Comparator<Product>
*/
	public static void heapSort(ArrayList<Product> table, Comparator<Product> c)
	{
		heapify(table, c);
		shrinkHeap(table, c);
	}

	////////////////////////////////////////////////////////////////////////////

/**
*  Quick Sort Method
*  @param table ArrayList<Product>
*  @param c Comparator<Product>
*/
	public static void quickSort(ArrayList<Product> table, Comparator<Product> c)
	{
		quickSort(table, 0, table.size() - 1, c);
	}

/**
*  Recursive Quick Sort Method
*  @param table ArrayList<Product>
*  @param first int
*  @param last int
*  @param c Comparator<Product>
*/
	protected static void quickSort(ArrayList<Product> table, int first, int last, Comparator<Product> c)
	{
		if (first < last)
		{
			int pivIndex = partition(table, first, last, c);
			quickSort(table, first, pivIndex - 1, c);
			quickSort(table, pivIndex + 1, last, c);
		}
	}

/**
*  Partition Support Method For Quick Sort
*  @param table ArrayList<Product>
*  @param first int
*  @param last int
*  @param c Comparator<Product>
*/
	protected static int partition(ArrayList<Product> table, int first, int last, Comparator<Product> c)
	{
		Product pivot = table.get(first);
		int up = first;
		int down = last;

		do
		{
			while((up < last ) && (c.compare(pivot, table.get(up)) >= 0))	up++;

			while(c.compare(pivot, table.get(down) )< 0) down--;

			if (up < down) swap(table, up, down);
		}
		while(up < down);

		swap(table, first, down);
		return down;		
	}

}