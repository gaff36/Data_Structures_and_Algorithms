
/** RedBlackTree Class
*/
public class RedBlackTree <E extends Comparable<E>> extends BinarySearchTreeWithRotate <E >
{

/** RedBlackTree Constructor
*/
	public RedBlackTree()
	{
		super();
	}

	////////////////////////////////////////////////////////////////////////////////////////////

/** Starter add Method
 * @return boolean
*/
	@Override
	public boolean add(E item) 
	{
		if (root == null) 
		{
			root = new Node<E>(item, false);
			return true;
		}
		else
		{
			root = add(root, item);
			(root).isRed = false;
			return addReturn;
		}
	}

/** private add Method
 * @param localRoot Node<E>
 * @param item E
 * @return Node<E>
*/
	private Node<E> add(Node<E> localRoot, E item) 
	{
		if (item.compareTo(localRoot.data) == 0) 
		{
			// item already in the tree.
			addReturn = false;
			return localRoot;
		}
		else if (item.compareTo(localRoot.data) < 0) 
		{
			// item < localRoot.data.
			if (localRoot.left == null) 
			{
				// Create new left child.
				localRoot.left = new Node<E>(item);
				addReturn = true;
				return localRoot;
			}
			else 
			{ 
				// Need to search.
				// Check for two red children, swap colors if found.
				moveBlackDown(localRoot);
				// Recursively add on the left.
				localRoot.left = add((Node<E>) localRoot.left, item);	

				if (((Node<E>) localRoot.left).isRed) 
				{
					if (localRoot.left.left != null && ((Node<E>) localRoot.left.left).isRed) 
					{
						// Single rotation is necessary.
						((Node<E>) localRoot.left).isRed = false;
						localRoot.isRed = true;
						return rotateRight(localRoot);
					}
					else if (localRoot.left.right != null && ((Node<E>) localRoot.left.right).isRed) 
					{
						// Left right grandchild is also red.
						// Double rotation is necessary.
						localRoot.left = rotateLeft(localRoot.left);
						((Node<E>) localRoot.left).isRed = false;
						localRoot.isRed = true;
						return rotateRight(localRoot);
					}
				}		
			}
		}
		else
		{
			// item > localRoot.data.
			if (localRoot.right == null) 
			{
				// Create new right child.
				localRoot.right = new Node<E>(item);
				addReturn = true;
				return localRoot;
			}
			else 
			{
				// Need to search.
				// Check for two red children, swap colors if found.
				moveBlackDown(localRoot);
				// Recursively add on the right.
				localRoot.right = add((Node<E>) localRoot.right, item);	

				if (((Node<E>) localRoot.right).isRed) 
				{
					if (localRoot.right.right != null && ((Node<E>) localRoot.right.right).isRed) 
					{
						// Single rotation is necessary.
						((Node<E>) localRoot.right).isRed = false;
						localRoot.isRed = true;
						return rotateLeft(localRoot);
					}
					else if (localRoot.right.left != null && ((Node<E>) localRoot.right.left).isRed) 
					{
						// Left right grandchild is also red.
						// Double rotation is necessary.
						localRoot.right = rotateRight(localRoot.right);
						((Node<E>) localRoot.right).isRed = false;
						localRoot.isRed = true;
						return rotateLeft(localRoot);
					}
				}		
			}
		}
		return localRoot;
		
	}

/** Support Method For Insertion
 * @param tempRoot Node<E>
 * @return boolean
*/
	private boolean moveBlackDown(Node<E> tempRoot)
	{
        if(tempRoot.left != null && tempRoot.right != null && tempRoot.left.isRed && tempRoot.right.isRed){
            tempRoot.left.isRed = false;
            tempRoot.right.isRed = false;
            tempRoot.isRed = true;
            return true;
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////////////////

}