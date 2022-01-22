import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/** 
*  driver class for testing
*/
public class driver 
{
/** 
*  driver method for testing
*/
	public static void driver()
	{
		////////////////////////////////////////////////////////////////////////////////////////////
	
		Scanner myObj = new Scanner(System.in);
		System.out.println("TESTING PART1: \n");

		System.out.println("TESTING NavigableSet With AVLTree: \n");

		NavigableSetAVLTree <Integer> nsat = new NavigableSetAVLTree <Integer>();

		if(nsat.insert(15) == true)	System.out.println("15 is added");
		if(nsat.insert(20) == true)	System.out.println("20 is added");
		if(nsat.insert(5) == true)	System.out.println("5 is added");
		if(nsat.insert(0) == true)	System.out.println("0 is added");
		if(nsat.insert(25) == true)	System.out.println("25 is added");
		if(nsat.insert(45) == true)	System.out.println("45 is added");
		if(nsat.insert(50) == true)	System.out.println("50 is added");
		if(nsat.insert(100) == true)System.out.println("100 is added");
		if(nsat.insert(65) == true)	System.out.println("65 is added");
		if(nsat.insert(35) == true)	System.out.println("35 is added");
		if(nsat.insert(10) == true)	System.out.println("10 is added");

		System.out.println("");
		System.out.println("Trying To Insert Same Item To to NavigableSet With AVLTree:");

		if(nsat.insert(10) == false)	System.out.println("10 is already in the NavigableSet\n");

		System.out.println("Trying Iterator");

		Iterator iter = nsat.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("Trying HeadSet Method Without inclusive");
		NavigableSetAVLTree temp2 = nsat.headSet(45);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("Trying HeadSet Method With inclusive");
		temp2 = nsat.headSet(45, true);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("Trying TailSet Method Without inclusive");
		temp2 = nsat.tailSet(45);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("Trying TailSet Method With inclusive");
		temp2 = nsat.tailSet(45, true);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("Trying HeadSet Method With Element that Smaller than Minimum Element of NavigableSet");
		temp2 = nsat.headSet(-10);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}	

		System.out.println("As you can see nothing is printed\n");

		System.out.println("Trying TailSet Method With Element that Larger than Maximum Element of NavigableSet");
		temp2 = nsat.tailSet(666);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}	

		System.out.println("As you can see nothing is printed\n");

		NavigableSetAVLTree <Integer> nsat2 = new NavigableSetAVLTree <Integer>();

		iter = nsat2.iterator();

		System.out.println("Trying Iterator with NavigableSet has no elements");

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("As you can see nothing is printed\n");

		System.out.println("Trying HeadSet Method with NavigableSet has no elements");
		temp2 = nsat2.headSet(45);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("As you can see nothing is printed\n");

		System.out.println("Trying TailSet Method with NavigableSet has no elements");
		temp2 = nsat2.headSet(45);

		iter = temp2.iterator();

		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}

		System.out.println("As you can see nothing is printed\n");

		myObj.nextLine();

		/////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("TESTING NavigableSet With Skip List: \n");

		NavigableSetSkipList <Integer> nssl = new NavigableSetSkipList<Integer>();
		if(nssl.insert(15) == true)	System.out.println("15 is added");
		if(nssl.insert(20) == true)	System.out.println("20 is added");
		if(nssl.insert(5) == true)	System.out.println("5 is added");
		if(nssl.insert(0) == true)	System.out.println("0 is added");
		if(nssl.insert(25) == true)	System.out.println("25 is added");
		if(nssl.insert(45) == true)	System.out.println("45 is added");
		if(nssl.insert(50) == true)	System.out.println("50 is added");
		if(nssl.insert(100) == true)System.out.println("100 is added");
		if(nssl.insert(65) == true)	System.out.println("65 is added");
		if(nssl.insert(35) == true)	System.out.println("35 is added");
		if(nssl.insert(10) == true)	System.out.println("10 is added");

		System.out.println("");
		System.out.println("Trying To Insert Same Item To to NavigableSet With Skip List:");

		if(nsat.insert(10) == false)	System.out.println("10 is already in the NavigableSet\n");

		System.out.println("Trying Descending Iterator:");
		Iterator it = nssl.descendingIterator();

		while(it.hasNext())
		{
			System.out.println(it.next());
		}

		System.out.println("\nTrying To Delete an Element That Exists on NavigableSet:");
		if(nssl.delete(10) == true)	System.out.println("10 is deleted");
		System.out.println("\nTrying To Delete an Element That Exists on NavigableSet:");
		if(nssl.delete(25) == true)	System.out.println("25 is deleted");
		System.out.println("\nTrying To Delete an Element That Doesn't Exist on NavigableSet:");
		if(nssl.delete(666) == false)	System.out.println("41 is not deleted");

		System.out.println("Trying Descending Iterator After Removals:");

		it = nssl.descendingIterator();

		while(it.hasNext())
		{
			System.out.println(it.next());
		}

		System.out.println("As you can see 10 and 25 is not printed:");
		System.out.println("Trying Delete at the Empty NavigableSet:");
		NavigableSetSkipList<Integer> gg = new NavigableSetSkipList<Integer>();
		if(gg.delete(4545) == false)	System.out.println("4545 is not deleted");

		myObj.nextLine();

		System.out.println("TESTING PART2: \n");

		System.out.println("\n'\\' means right child and '|' means left child in prints\n");		

		BinarySearchTree<Integer> binaryTree1 = new BinarySearchTree<Integer>();
		binaryTree1.add(40);

		System.out.println("Trying isAVLTree with Tree That Has 1 Element:");

		binaryTree1.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		binaryTree1.add(10);
		binaryTree1.add(20);
		binaryTree1.add(60);
		binaryTree1.add(0);
		binaryTree1.add(50);
		binaryTree1.add(70);

		System.out.println("Trying isAVLTree with perfect tree:");

		binaryTree1.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		binaryTree1.add(-10);
		binaryTree1.add(-15);

		System.out.println("Trying isAVLTree with tree that left heavy(left-left):");

		binaryTree1.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		BinarySearchTree<Integer> binaryTree2 = new BinarySearchTree<Integer>();
		binaryTree2.add(40);
		binaryTree2.add(10);
		binaryTree2.add(20);
		binaryTree2.add(60);
		binaryTree2.add(0);
		binaryTree2.add(50);
		binaryTree2.add(70);
		binaryTree2.add(-10);
		binaryTree2.add(-5);

		System.out.println("Trying isAVLTree with tree that left heavy(left-right):");

		binaryTree2.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		BinarySearchTree<Integer> binaryTree3 = new BinarySearchTree<Integer>();
		binaryTree3.add(40);
		binaryTree3.add(10);
		binaryTree3.add(20);
		binaryTree3.add(60);
		binaryTree3.add(0);
		binaryTree3.add(50);
		binaryTree3.add(70);
		binaryTree3.add(80);
		binaryTree3.add(90);

		System.out.println("Trying isAVLTree with tree that right heavy(right-right):");

		binaryTree3.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		BinarySearchTree<Integer> binaryTree4 = new BinarySearchTree<Integer>();
		binaryTree4.add(40);
		binaryTree4.add(10);
		binaryTree4.add(20);
		binaryTree4.add(60);
		binaryTree4.add(0);
		binaryTree4.add(50);
		binaryTree4.add(70);
		binaryTree4.add(90);
		binaryTree4.add(80);

		System.out.println("Trying isAVLTree with tree that right heavy(right-left):");

		binaryTree4.print();
		if(binaryTree1.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		System.out.println("Trying isAVLTree with empty tree:");
		BinarySearchTree<Integer> binaryTree5 = new BinarySearchTree<Integer>();
		binaryTree5.print();
		if(binaryTree5.isAVLTree())	System.out.println("TREE IS AVL TREE");
		else	System.out.println("TREE IS NOT AVL TREE");

		myObj.nextLine();

		////////////////////////////////////////////////////////////////////////////////////////////

		BinarySearchTree<Integer> redBlack1 = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> redBlack2 = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> redBlack3 = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> redBlack4 = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> redBlack5 = new BinarySearchTree<Integer>();

		System.out.println("\n'\\' means right child and '|' means left child in prints\n");

		System.out.println("Trying isRedBlackTree with 1 element and it's root is RED:");

		redBlack1.add(40,true);

		redBlack1.print();
		if(redBlack1.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		redBlack2.add(40, false);
		redBlack2.add(10, true);
		redBlack2.add(20, false);
		redBlack2.add(60, true);
		redBlack2.add(0, false);
		redBlack2.add(50, false);
		redBlack2.add(70, false);

		System.out.println("Trying isRedBlackTree with Tree Order of Red-Black Tree:");

		redBlack2.print();
		if(redBlack2.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		redBlack3.add(40, false);
		redBlack3.add(60, true);
		redBlack3.add(50, false);
		redBlack3.add(70, false);
		redBlack3.add(10, false);
		redBlack3.add(5, false);

		System.out.println("Trying isRedBlackTree with tree that any route from root to leaf is not equal for every leaf:");

		redBlack3.print();
		if(redBlack3.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		redBlack4.add(40, false);
		redBlack4.add(10, true);
		redBlack4.add(20, true);
		redBlack4.add(60, true);
		redBlack4.add(0, false);
		redBlack4.add(50, false);
		redBlack4.add(70, false);

		System.out.println("Trying isRedBlackTree with RED Node has RED Child:");

		redBlack4.print();
		if(redBlack4.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		redBlack5.add(70, false);

		System.out.println("Trying isRedBlackTree with 1 element and it's root is BLACK:");

		redBlack4.print();
		if(redBlack4.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		BinarySearchTree<Integer> redBlack7 = new BinarySearchTree<Integer>();
		System.out.println("Trying isRedBlackTree Empty Tree:");
		redBlack7.print();
		if(redBlack7.isRedBlackTree())	System.out.println("TREE IS RED-BLACK TREE");
		else	System.out.println("TREE IS NOT RED-BLACK TREE");

		
					

		System.out.println("TESTING PART3:");
		System.out.println("IT MAY TAKE APPROXIMATELY 1 MINUTES TO COMPILE(Since, it is generating a lot of non repeating numbers)\n");

		////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<ArrayList<Integer>> list1 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 10; i++)	list1.add(new ArrayList<Integer>());

		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 10; i++)	list2.add(new ArrayList<Integer>());

		ArrayList<ArrayList<Integer>> list3 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 10; i++)	list3.add(new ArrayList<Integer>());

		ArrayList<ArrayList<Integer>> list4 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 10; i++)	list4.add(new ArrayList<Integer>());

		ArrayList<ArrayList<Integer>> list5 = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 10; i++)	list5.add(new ArrayList<Integer>());
			
		for(int i = 0; i < 10; i++)	
		{
			while(list4.get(i).size() < 80000)
			{
				int rand = (int)(Math.random() * 1000000);
				if(list4.get(i).contains(rand) == false)
				{	
					list4.get(i).add(rand);
				}
			}
		}

		for(int i = 0; i < 10; i++)	
		{
			int counter = 40000;
			while(counter < 80000)
			{
				list3.get(i).add(list4.get(i).get(counter));			
				counter++;
			}
		}

		for(int i = 0; i < 10; i++)	
		{
			int counter = 0;
			while(counter < 20000)
			{
				list2.get(i).add(list4.get(i).get(counter));			
				counter++;
			}
		}

		for(int i = 0; i < 10; i++)	
		{
			int counter = 20000;
			while(counter < 30000)
			{
				list1.get(i).add(list4.get(i).get(counter));			
				counter++;
			}
		}

		for(int i = 0; i < 10; i++)	
		{
			int counter = 20000;
			while(list5.get(i).size() < 100)
			{
				int rand = (int)(Math.random() * 1000000);
				if(list4.get(i).contains(rand) == false)
				{	
					list5.get(i).add(rand);
				}
			}
		}

		for(int i = 0; i < 10; i++)	
		{
			while(list5.get(i).size() < 100)
			{
				int rand = (int)(Math.random() * 1000000);
				if(list5.get(i).contains(rand) == false)
				{	
					list5.get(i).add(rand);
				}
			}
		}		

		////////////////////////////////////////////////////////////////////////////////////////////
		
		ArrayList<BinarySearchTree<Integer>> bst1 = new ArrayList<BinarySearchTree<Integer>>();
		for(int i = 0; i < 10; i++)	bst1.add(new BinarySearchTree<Integer>());

		ArrayList<BinarySearchTree<Integer>> bst2 = new ArrayList<BinarySearchTree<Integer>>();
		for(int i = 0; i < 10; i++)	bst2.add(new BinarySearchTree<Integer>());

		ArrayList<BinarySearchTree<Integer>> bst3 = new ArrayList<BinarySearchTree<Integer>>();
		for(int i = 0; i < 10; i++)	bst3.add(new BinarySearchTree<Integer>());
		
		ArrayList<BinarySearchTree<Integer>> bst4 = new ArrayList<BinarySearchTree<Integer>>();
		for(int i = 0; i < 10; i++)	bst4.add(new BinarySearchTree<Integer>());

		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list1.get(j).size(); i++)	bst1.get(j).add(list1.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list2.get(j).size(); i++)	bst2.get(j).add(list2.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list3.get(j).size(); i++)	bst3.get(j).add(list3.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list4.get(j).size(); i++)	bst4.get(j).add(list4.get(j).get(i));
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<RedBlackTree<Integer>> rbt1 = new ArrayList<RedBlackTree<Integer>>();
		for(int i = 0; i < 10; i++)	rbt1.add(new RedBlackTree<Integer>());

		ArrayList<RedBlackTree<Integer>> rbt2 = new ArrayList<RedBlackTree<Integer>>();
		for(int i = 0; i < 10; i++)	rbt2.add(new RedBlackTree<Integer>());

		ArrayList<RedBlackTree<Integer>> rbt3 = new ArrayList<RedBlackTree<Integer>>();
		for(int i = 0; i < 10; i++)	rbt3.add(new RedBlackTree<Integer>());
		
		ArrayList<RedBlackTree<Integer>> rbt4 = new ArrayList<RedBlackTree<Integer>>();
		for(int i = 0; i < 10; i++)	rbt4.add(new RedBlackTree<Integer>());

		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list1.get(j).size(); i++)	rbt1.get(j).add(list1.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list2.get(j).size(); i++)	rbt2.get(j).add(list2.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list3.get(j).size(); i++)	rbt3.get(j).add(list3.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list4.get(j).size(); i++)	rbt4.get(j).add(list4.get(j).get(i));
		}

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<SkipList<Integer>> sl1 = new ArrayList<SkipList<Integer>>();
		for(int i = 0; i < 10; i++)	sl1.add(new SkipList<Integer>());

		ArrayList<SkipList<Integer>> sl2 = new ArrayList<SkipList<Integer>>();
		for(int i = 0; i < 10; i++)	sl2.add(new SkipList<Integer>());

		ArrayList<SkipList<Integer>> sl3 = new ArrayList<SkipList<Integer>>();
		for(int i = 0; i < 10; i++)	sl3.add(new SkipList<Integer>());
		
		ArrayList<SkipList<Integer>> sl4 = new ArrayList<SkipList<Integer>>();
		for(int i = 0; i < 10; i++)	sl4.add(new SkipList<Integer>());

		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list1.get(j).size(); i++)	sl1.get(j).add(list1.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list2.get(j).size(); i++)	sl2.get(j).add(list2.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list3.get(j).size(); i++)	sl3.get(j).add(list3.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list4.get(j).size(); i++)	sl4.get(j).add(list4.get(j).get(i));
		}

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<TwoThreeTree<Integer>> ttt1 = new ArrayList<TwoThreeTree<Integer>>();
		for(int i = 0; i < 10; i++)	ttt1.add(new TwoThreeTree<Integer>());

		ArrayList<TwoThreeTree<Integer>> ttt2 = new ArrayList<TwoThreeTree<Integer>>();
		for(int i = 0; i < 10; i++)	ttt2.add(new TwoThreeTree<Integer>());

		ArrayList<TwoThreeTree<Integer>> ttt3 = new ArrayList<TwoThreeTree<Integer>>();
		for(int i = 0; i < 10; i++)	ttt3.add(new TwoThreeTree<Integer>());
		
		ArrayList<TwoThreeTree<Integer>> ttt4 = new ArrayList<TwoThreeTree<Integer>>();
		for(int i = 0; i < 10; i++)	ttt4.add(new TwoThreeTree<Integer>());

		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list1.get(j).size(); i++)	ttt1.get(j).add(list1.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list2.get(j).size(); i++)	ttt2.get(j).add(list2.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list3.get(j).size(); i++)	ttt3.get(j).add(list3.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list4.get(j).size(); i++)	ttt4.get(j).add(list4.get(j).get(i));
		}

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<BTree<Integer>> bt1 = new ArrayList<BTree<Integer>>();
		for(int i = 0; i < 10; i++)	bt1.add(new BTree<Integer>(10));

		ArrayList<BTree<Integer>> bt2 = new ArrayList<BTree<Integer>>();
		for(int i = 0; i < 10; i++)	bt2.add(new BTree<Integer>(10));

		ArrayList<BTree<Integer>> bt3 = new ArrayList<BTree<Integer>>();
		for(int i = 0; i < 10; i++)	bt3.add(new BTree<Integer>(10));
		
		ArrayList<BTree<Integer>> bt4 = new ArrayList<BTree<Integer>>();
		for(int i = 0; i < 10; i++)	bt4.add(new BTree<Integer>(10));

		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list1.get(j).size(); i++)	bt1.get(j).add(list1.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list2.get(j).size(); i++)	bt2.get(j).add(list2.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list3.get(j).size(); i++)	bt3.get(j).add(list3.get(j).get(i));
		}
		for(int j = 0; j < 10; j++)
		{
			for(int i = 0; i < list4.get(j).size(); i++)	bt4.get(j).add(list4.get(j).get(i));
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		double avg;
		Long total = 0L;
		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bst1.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("BINARY SEARCH TREE:");
		System.out.println("PROBLEM SIZE: 10000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bst2.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 20000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bst3.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 40000:");		
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;	

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bst4.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 80000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	rbt1.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("\nRED-BLACK TREE:");
		System.out.println("PROBLEM SIZE: 10000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	rbt2.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 20000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	rbt3.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 40000:");		
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;	

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	rbt4.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 80000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	ttt1.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("\n2-3 TREE:");
		System.out.println("PROBLEM SIZE: 10000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	ttt2.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 20000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	ttt3.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 40000:");		
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;	

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	ttt4.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 80000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bt1.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("\nB-TREE(ORDER 10):");
		System.out.println("PROBLEM SIZE: 10000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bt2.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 20000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bt3.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 40000:");		
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;	

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	bt4.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 80000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	sl1.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("\nSKIP LIST:");
		System.out.println("PROBLEM SIZE: 10000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	sl2.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 20000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	sl3.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}

		System.out.println("PROBLEM SIZE: 40000:");		
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;	

		for(int j = 0; j < 10; j++)
		{
			Long startTime = System.nanoTime();
			for(int i = 0; i < list5.get(j).size(); i++)	sl4.get(j).add(list5.get(j).get(i));
			Long endTime =  System.nanoTime();
			Long temp = endTime - startTime;
			total += temp;
		}
		System.out.println("PROBLEM SIZE: 80000:");
		avg = (double)(total / 1000);
		System.out.println("AVG TIME(ns): " + avg);
		total = 0L;
																	
	}			

	public static void main(String [] argc)
	{
		driver();
	}
}