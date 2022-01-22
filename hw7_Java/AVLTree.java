import java.util.Iterator;

/** 
*  AVLTree Class extends BinarySearchTreeWithRotate
*/
public class AVLTree <E extends Comparable<E>> extends BinarySearchTreeWithRotate <E>
{
	private boolean increase;
	private AVLNode<E> root;

/** 
*  AVLTree Constructor
*/
	public AVLTree()
	{
		super();
		root = null;
	}

/** 
*   Support Method For insertion
*   @param node AVLNode<E>
*/
	private void incrementBalance(AVLNode<E> node) 
	{
		//Decrement the balance.
		node.balance++;
		if (node.balance == AVLNode.BALANCED) 
		{
		/**
		If now balanced, overall height has not increased. */
			increase = true;
		}
	}

/** 
*   Support Method For insertion
*   @param node AVLNode<E>
*/
	private void decrementBalance(AVLNode<E> node) 
	{
		//Decrement the balance.
		node.balance--;
		if (node.balance == AVLNode.BALANCED) 
		{
		/**
		If now balanced, overall height has not increased. */
			increase = false;
		}
	}

/** Method to rebalance left.
*  localRoot is the root of an AVL subtree that is critically left heavy.
*  Balance is restored.
* @param localRoot Root of the AVL subtree that needs rebalancing
* @return a new localRoot
*/
	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) 
	{
		// Obtain reference to left child.
		AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
		// See whether left right heavy.
		if (leftChild.balance > AVLNode.BALANCED) 
		{
			// Obtain reference to leftright child.
			AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
			/** Adjust the balances to be their new values after the rotations are performed.		
			*/

			if (leftRightChild.balance < AVLNode.BALANCED) 
			{
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.RIGHT_HEAVY;
			} 
			else 
			{
				leftChild.balance = AVLNode.LEFT_HEAVY;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			// Perform left rotation.
			localRoot.left = rotateLeft(leftChild);
		} 
		else
 		{ 
			// Left Left case
			/** In this case the leftChild (the new root) and the root
			(new right child) will both be balanced after the rotation.
			*/
			leftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
			// Now rotate the local root right.
			return (AVLNode<E>) rotateRight(localRoot);
	}

/** Method to rebalance right.
*  localRoot is the root of an AVL subtree that is critically right heavy.
*  Balance is restored.
* @param localRoot Root of the AVL subtree that needs rebalancing
* @return a new localRoot
*/
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) 
	{
		// Obtain reference to right child.
		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
		// See whether right left heavy.
		if (rightChild.balance < AVLNode.BALANCED) 
		{
			// Obtain reference to leftright child.
			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
			/** Adjust the balances to be their new values after the rotations are performed.		
			*/

			if (rightLeftChild.balance > AVLNode.BALANCED) 
			{
				rightChild.balance = AVLNode.BALANCED;
				rightLeftChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.LEFT_HEAVY;
			} 
			else 
			{
				rightChild.balance = AVLNode.RIGHT_HEAVY;
				rightLeftChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			// Perform left rotation.
			localRoot.right = rotateRight(rightChild);
		} 
		else
 		{ 
			// Right Right case
			/** In this case the leftChild (the new root) and the root
			(new right child) will both be balanced after the rotation.
			*/
			rightChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
			// Now rotate the local root right.
			return (AVLNode<E>) rotateLeft(localRoot);
	}	

/** Recursive add method. Inserts the given object into the tree.
@post addReturn is set true if the item is inserted,
false if the item is already in the tree.
@param localRoot The local root of the subtree
@param item The object to be inserted
@return The new local root of the subtree with the item inserted
*/
	private AVLNode<E> add(AVLNode<E> localRoot, E item)
	{
		if (localRoot == null)
		{
			addReturn = true;
			increase = true;
			size++;
			return new AVLNode<E>(item);
		}
		if (item.compareTo(localRoot.data) == 0) 
		{
			// Item is already in the tree.
			increase = false;
			addReturn = false;
			return localRoot;
		}
		else if (item.compareTo(localRoot.data) < 0) 
		{
			// item < data
			localRoot.left = add((AVLNode<E>) localRoot.left, item);
			if(increase) 
			{
				decrementBalance(localRoot);
				if(localRoot.balance < AVLNode.LEFT_HEAVY) 
				{
					increase = false;
					return rebalanceLeft(localRoot);
				}
			}
			return localRoot; //Rebalance not needed.
		}
		else //if (item.compareTo(localRoot.data) < 0) 
		{
			// item > data
			localRoot.right = add((AVLNode<E>) localRoot.right, item);
			if(increase) 
			{
				incrementBalance(localRoot);
				if(localRoot.balance > AVLNode.RIGHT_HEAVY) 
				{
					increase = false;
					return rebalanceRight(localRoot);
				}
			}
			return localRoot; //Rebalance not needed.
		}
		
	}

/** add
* starter method.
* pre: the item to insert implements the
* Comparable interface.
* @param entry The item being inserted.
* @return true
* if the object is inserted; false
* if the object already exists in the tree
* @throws ClassCastException if item is not Comparable
*/
	@Override
	public boolean add(E entry) 
	{
		increase = false;
		root = this.add((AVLNode<E>) root, entry);
		return addReturn;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////

/** Iterator method
 * @return iterator
*/
	Iterator iterator()
	{
		return new BSTIterator(root);
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

/** Class to represent an AVL Node. It extends the
BinaryTree.Node by adding the balance field.
*/
	private static class AVLNode <E> extends Node<E> 
	{	
		public static final int LEFT_HEAVY = -1;	
		public static final int BALANCED = 0;
		public static final int RIGHT_HEAVY = 1;	
		private int balance;

	// Methods
	/** Construct a node with the given item as the data field.
	@param item The data field
	*/
		public AVLNode(E item) 
		{
        	super(item);
			balance = BALANCED;
		}

/** Return a string representation of this object.
* The balance value is appended to the contents.
* @return String representation of this object
*/
		@Override
		public String toString()
		{
			return balance + ": " + super.toString();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////

}