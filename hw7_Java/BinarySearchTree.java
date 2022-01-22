import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

/** BinarySearchTree Class
*/
public class BinarySearchTree <E extends Comparable<E>>
{
	protected Node root;
	protected int size;
	protected boolean addReturn;

	////////////////////////////////////////////////////////////////////////////////////////////

/** BinarySearchTree Constructor
*/
	public BinarySearchTree() 
	{
		root = null;
		size = 0;
	}

/** Size method
 * @return size
*/
	public int size()
	{
		return size;
	}	

/** Starter add method
 * @param item E
 * @param red boolean
 * @return boolean
*/
	public boolean add(E item, boolean red) 
	{
		root = add(root, item, red);
		return addReturn;
	}

/** Starter add method
 * @param item E
 * @return boolean
*/
	public boolean add(E item) 
	{
		root = add(root, item, false);
		return addReturn;
	}

/** private add method
 * @param localRoot Node<E>
 * @param item E
 * @param red boolean
 * @return Node<E>
*/
 	private Node<E> add(Node<E> localRoot, E item, boolean red) 
 	{
 		if (localRoot == null) 
 		{
	 		// item is not in the tree — insert it.
	 		addReturn = true;
	 		size++;
	 		return new Node<>(item, red);
	 	} 
	 	else if (item.compareTo(localRoot.data) == 0) 
	 	{
	 		// item is equal to localRoot.data
	 		addReturn = false;
	 		return localRoot;
	 	} 
 		else if (item.compareTo(localRoot.data) < 0) 
	 	{
	 		// item is less than localRoot.data
	 		localRoot.left = add(localRoot.left, item, red);
	 		return localRoot;
	 	} 
 		else 
 		{
	 		// item is greater than localRoot.data
	 		localRoot.right = add(localRoot.right, item, red);
	 		return localRoot;
	 	}
 	}

 	///////////////////////////////////////////////////////////////////////////////

/** Starter find method
 * @param item E
 * @return E
*/
 	public E find(E item)
 	{
 		Node <E> temp = search(item);
 		return temp.data;
 	}

/** Search method
 * @param item E
 * @return Node<E>
*/
 	public Node<E> search(E item)
	{
		return find(root, item);
	}

/** Private find method
 * @param localRoot Node<E>
 * @param item E
 * @return Node <E>
*/
	private Node <E> find(Node<E> localRoot, E item)
	{
		if(localRoot == null)	return null;

		if(item.compareTo(localRoot.data) == 0)	return localRoot;
		else if(item.compareTo(localRoot.data) < 0)	return find(localRoot.left, item);
		else  return find(localRoot.right, item);
	}


 	///////////////////////////////////////////////////////////////////////////////

/** Starter height method
 * @param item E
 * @return int
*/
 	public int height(E item) 
	{
		Node<E> temp = search(item);
		return height(temp);		
	}

/** private height method
 * @param localRoot Node<E>
 * @return int
*/
	private int height(Node<E> localRoot)
	{
		if(localRoot == null)	return 0;

		int numLeft = 0, numRight = 0;
		numLeft = height(localRoot.left);
		numRight = height(localRoot.right);
		return Math.max(numRight, numLeft) + 1;
	}

/** private isAVLTree method
 * @param node Node<E>
 * @return boolean
*/
	private boolean isAVLTree(Node <E> node)
	{
		if(node == null) return true;

		int numLeft = 0;
		int numRight = 0;

		numLeft = height(node.left);
		numRight = height(node.right);

		if(Math.abs(numLeft - numRight ) < 2 && isAVLTree(node.left) && isAVLTree(node.right))	return true;
		return false;
	}

/** Starter isAVLTree method. checks if any node’s balance is greater than 1 or smaller than -1.
 * @return boolean
*/
	public boolean isAVLTree()
	{
		if(root == null) return false;
		return this.isAVLTree(root);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Starter print method
 * @return boolean
*/
	public void print() {
        print("", root, false);
    }

/** private print method
 * @param prefix String
 * @param n Node
 * @param isLeft boolean
*/
    private void print(String prefix, Node n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }

	////////////////////////////////////////////////////////////////////////////////////////////

/** Returns true if root is RED, retuerns false if root is BLACK
 * @return true if root is RED, retuerns false if root is BLACK
*/	
	public boolean isRed()
	{
		if(root.isRed == true)	return true;
		return false;
	} 

/** private countBlackNodes method
 * Checks if any route from root to any leaf has equal Black Nodes.
 * @param node Node <E>
 * @param list ArrayList<Integer>
 * @param counter int
*/
	private void countBlackNodes(Node <E> node, ArrayList<Integer> list, int counter)
	{
		if(node == null)	return;
		if(node.left == null && node.right == null)	
		{	
			if(node.isRed == false)
			{
				counter++;
			}	

			list.add(counter);	

			if(node.isRed == false)
			{
				counter--;
			}
			return;
		}

		if(node.isRed == false)	
		{
			counter++;
		}

		countBlackNodes(node.left, list, counter);		
		countBlackNodes(node.right, list, counter);		

		if((node.left != null && node.right == null) || (node.left == null && node.right != null) || (node.left != null && node.right != null) && isRed() == false)	
		{
			counter--;
		}
	}

/** checkChildren method: checks if any Red Node’s child id Red.
 * @param node Node <E>
 * @param isParentRed boolean
 * @return boolean
*/
	public boolean checkChildren(Node <E> node, boolean isParentRed)
	{
		if(node == null)	return true;

		if(isParentRed == true && node.isRed == true)	
		{
			isParentRed = true;
			return false;
		}
		else if(isParentRed == false && node.isRed == false)	
		{
			isParentRed = false;
		}	
		else if(isParentRed == true && node.isRed == false)	
		{
			isParentRed = false;
		}	
		else if(isParentRed == false && node.isRed == true)
		{
			isParentRed = true;
		}

		return (checkChildren(node.left, isParentRed) && checkChildren(node.right, isParentRed));
	}

/** main isRedBlackTree method
 * @return boolean
*/
	public boolean isRedBlackTree()
	{
		if(root == null)	return false;
		if(isRed() == true)	return false;
		if(checkChildren(root, false) == false)	return false;
		
		ArrayList<Integer> blackNodes = new ArrayList<Integer>();
		countBlackNodes(root, blackNodes, 0);

		for(int i = 0; i < blackNodes.size(); i++)
		{
			for(int j = 0; j < blackNodes.size(); j++)
			{
				if(blackNodes.get(i).equals(blackNodes.get(j)) == false)	return false;
			}
		}
		return true;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Iterator Method
 *  @return Iterator
*/
	Iterator iterator()
	{
		return new BSTIterator(root);
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Inner Node Class
*/	
 	protected static class Node <E>
 	{
		// Data Fields
		/** The information stored in this node. */
		protected E data;
		/** Reference to the left child. */
		protected Node<E> left;
		/** Reference to the right child. */
		protected Node<E> right;
		// Constructors
		/** Construct a node with given data and no children.
		@param data The data to store in this node
		*/
		/** Color indicator. True if red, false if black. */
		protected boolean isRed;

	////////////////////////////////////////////////////////////////////////////////////////////

/** Node Constructor
 * @param data E
*/
		public Node(E data) 
		{
			this.data = data;
			left = null;
			right = null;
			isRed = true;
		}

/** Node Constructor
 * @param data E
 * @param red boolean
*/
		public Node(E data, boolean red) 
		{
			this.data = data;
			left = null;
			right = null;
			isRed = red;
		}

/** Node toString Method
 * @return String
*/
		public String toString()
		{
			if(isRed == false)
			{
				return (data + " B ");
			}
			else
			{
				return(data + " R");
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Inner Iterator Class For BinarySearchTree
*/
	public class BSTIterator implements Iterator 
	{
	Stack<Node> stack;
 
/** BSTIterator Constructor
 *  @param root Node
*/
	public BSTIterator(Node root) {
		stack = new Stack<Node>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}
 
/** hasNext Method
*/
	public boolean hasNext() {
		return !stack.isEmpty();
	}
 /** next Method
*/
	public E next() {
		Node node = stack.pop();
		E result = (E)node.data;
		if (node.right != null) {
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}
		return result;
	}
	}

}