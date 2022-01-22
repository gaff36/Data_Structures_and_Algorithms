import java.util.ArrayList;

/**
*  @author Emre Sezer
*  This class is for PART 2.
*  This is the BSTHeapTree class that represents a Binary Search Tree which node's keep MaxHeaps.
*  BSTHeapTree's nodes are named TreeNode's.
*/
@SuppressWarnings("unchecked")
public class BSTHeapTree <E extends Comparable<E>>
{	
	private TreeNode <E> root;
	private final int MAX_SIZE = 7;

/**
*  Add method for BSTHeapTree.First it searches if element is already in the tree.
* If that is true increases that elements occurences by 1.
* If not adds that element to appropriate position.
* @param item E
* @return added element's number of occurences
*/
	public int add (E item)
	{
		int result = -1;
		TreeNode<E> localRoot = this.root; 
		int searchResult = find(item);

		if(searchResult > 0)				// If that element already exists in the tree
		{
			TreeNode<E> tempRoot = root;

			while(true)
			{
				if(tempRoot.data.search(item) > 0)		// Searches if current node has that element.(Uses MaxHeap's Search Method)
				{
					result = searchResult + 1;			// If found that element in the MaxHeap, increases that elements occurences by 1.
					tempRoot.data.add(item);		
					break;
				}
				else if (item.compareTo(tempRoot.data.peek().getValue()) < 0)
				{
					tempRoot = tempRoot.left;
				}
				else if (item.compareTo(tempRoot.data.peek().getValue()) > 0)
				{
					tempRoot = tempRoot.right;
				}
			}
		}
		else 				// If element doesn't exist in the tree
		{

		while(true)
		{
			if(root == null)								// If there is a need for creating a new node
			{
		 		root = new TreeNode<E>();					// Creates a new node
		 		result = root.add(item);					// Adds entry to that new created TreeNode		
		 		localRoot = root;
		 		break;
			}
			else if(localRoot.data.size() < MAX_SIZE)		// If appropriate node is not out of space, adds that element to the MaxHeap
			{	
				result = localRoot.add(item);
					
					while(localRoot.right != null && item.compareTo(localRoot.right.data.peek().getValue()) > 0)	// If item is bigger than right Node's head
					{
						localRoot.data.swapRoots(localRoot.right.data);				// It modifies the tree by changing heads of the current node and right node.
					}																// This continues until current nodes is a leaf.
								
				break;
			}
			else
			{								
				if(item.compareTo(localRoot.data.peek().getValue()) < 0 && localRoot.left == null) 		
				{
					TreeNode<E> temp = new TreeNode<E>();
					localRoot.left = temp;
					localRoot = localRoot.left;

				}			
				else if(item.compareTo(localRoot.data.peek().getValue()) < 0)	localRoot = localRoot.left;		// If left TreeNode exists, moves to left TreeNode
				else if(item.compareTo(localRoot.data.peek().getValue()) > 0 && localRoot.right == null) 
				{
					TreeNode<E> temp = new TreeNode<E>();
					localRoot.right = temp;
					localRoot = localRoot.right;
				}			
				else if(item.compareTo(localRoot.data.peek().getValue()) > 0)	localRoot = localRoot.right;	// If right TreeNode exists, moves to right TreeNode
			}
		}
	}

		return result;			// returns added element's number of occurences
	}

/**
*  Remove method for BSTHeapTree.First it searches if element is already in the tree.
* If that is true decreases that elements occurences by 1.
* If after removal there is no element left on the TreeMode's Maxheap , removes that TreeNode from Tree.
* @param item E
* @return removed element's number of occurences
*/
	public int remove (E item)
	{
		if(root == null) throw new IndexOutOfBoundsException();

		if(find(item) <= 0)	return 0;

		TreeNode<E> localRoot = root;
		ArrayList<MaxHeap.HeapNode> temp1 = new ArrayList<MaxHeap.HeapNode>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();

		MaxHeap.HeapNode x;
		
		int result  = 0;
		boolean done = false;
		boolean flag = false;

		while(true)
		{
			if(localRoot.data.search(item) > 0)			// Searches is the entry exists in the current MaxHeap	
			{											
				int length = localRoot.data.size();
				int nodeLength;

				for(int i = 0; i < length; i++)
				{
					if(localRoot.data.size() == 1 && localRoot.data.peek().getNum() == 1)		
					{
						result = 0;											// This part is for : If the item is the only element in the MaxHeap and it's occurence is 1.
						this.root = delete(this.root, localRoot.data);		// When it deletes that element from the MaxHeap, there will be no element left on the MaxHeap.So, the empty TreeNode will be deleted.
						return result;
					}	

					x = localRoot.data.remove();
					nodeLength = x.getNum() + 1;

					if(item.equals(x.getValue()))					// If item is found in the current MaxHeap
					{
						if(i == 0 && x.getNum() == 0) flag = true;		// If item is at the top of the MaxHeap
						result = x.getNum();
						done = true;
						break;
					}

					if(nodeLength > 1)		
					{
						for(int j = 0; j < nodeLength - 1; j++)	localRoot.data.remove();
					}	

					temp2.add(nodeLength);
					temp1.add(x);

				}

				for(int i = 0; i < temp1.size(); i++)		// It addes the removed HeapNodes back to the MaxHeap
				{
					temp1.get(i).setNum(temp2.get(i));
					localRoot.data.add(temp1.get(i));
				}
				if(done)	break;

			}
			else if(done)	break;
			else if(item.compareTo(localRoot.data.peek().getValue()) < 0)	// It moves current TreeNode to the left Node of the current TreeNode
			{
				localRoot = localRoot.left;
			}
			else if(item.compareTo(localRoot.data.peek().getValue()) > 0)	// It moves current TreeNode to the right Node of the current TreeNode
			{
				localRoot = localRoot.right;
			}

		}

		while(flag && localRoot.left != null)			// This part is for keeping tree in order.
		{

			if(localRoot.data.peek().compareTo(localRoot.left.data.peek()) > 0)	// If removed element is top of the Maxheap and new top element is smaller than current TreeNode's left node's top element.
			{																	// It swaps the top elements of these MaxHeaps.(current and current's left node)
				localRoot.data.swapRoots(localRoot.left.data);
				localRoot = localRoot.left;
			}
			else break;
		}

		return result;

	}

	//////////////////////////////////////////////////////////

/**
* Modified Delete Helper Method For BinarySearchTree 
* This method is for removing node from tree and still keeping the tree order
* @param data MaxHeap
* @param root TreeNode
* @return TreeNode
*/
	private TreeNode deleteHelper(MaxHeap data, TreeNode root)
	{
		if(root == null)	return null;

		else if(data.compareTo(root.data) < 0)	root.left = deleteHelper(data, root.left);
		else if(data.compareTo(root.data) > 0)	root.right = deleteHelper(data, root.right);
		else
		{
			if(root.left == null && root.right == null)	root = null;
			else if(root.left == null || root.right == null)
			{
				root = root.left == null ? root.right : root.left;
			}
			else
			{
				TreeNode predecessorNode = root.left.findMax();
				swapData(predecessorNode, root);
				root.left = deleteHelper(predecessorNode.data, root.left);
			}
		}

		return root;
	}

/**
* Modified Delete Method For BinarySearchTree. It uses Helper method  
* This method is for removing node from tree and still keeping the tree order
* @param data MaxHeap
* @param root TreeNode
* @return TreeNode
*/
	public TreeNode delete(TreeNode node, MaxHeap data)
	{
		return node = deleteHelper(data, node);
	}


	//////////////////////////////////////////////////////////

/**
* Find Method For BSTHeapTree.It returns number of occurences of the item
* @param item E
* @return number of occurences of the item
*/
	public int find(E item)
	{
		if(root == null) return 0;

		TreeNode<E> localRoot = root;
		int result = 0;

		while(true)
		{	
			if(localRoot == null)
			{
				result = 0;
				break;
			}

			result = localRoot.data.search(item);
			if(result > 0) break;
			else if (item.compareTo(localRoot.data.peek().getValue()) < 0)
			{
				localRoot = localRoot.left;
			}
			else if (item.compareTo(localRoot.data.peek().getValue()) > 0)
			{
				localRoot = localRoot.right;
			}
				
		}
		return result;
	}

/**
* Support Method For delete, swaps 2 TreeeNodes data
* @param node1 TreeNode
* @param node2 TreeNode
*/
	private void swapData(TreeNode node1, TreeNode node2)
	{
		MaxHeap temp = node1.data;
		node1.data = node2.data;
		node2.data = temp;
	}

/**
* Support Method For find_mode()
* @param localRoot TreeNode <E>
* @param localModeList ArrayList<MaxHeap.HeapNode>
*/
	public void modeSupport(TreeNode <E> localRoot,ArrayList<MaxHeap.HeapNode> localModeList)
	{
		if(localRoot == null)	return;
			
		modeSupport(localRoot.left, localModeList);
		modeSupport(localRoot.right, localModeList);

		localModeList.add(localRoot.data.findLocalMode());
	}

/**
*    Method for finding mode
*/  
	public void find_mode()
	{
		ArrayList<MaxHeap.HeapNode> localModeList = new ArrayList<MaxHeap.HeapNode>();
		modeSupport(root, localModeList);
		MaxHeap.HeapNode mode = localModeList.get(0);
		int max = 0;

		for(int i = 0;i < localModeList.size(); i++)
		{
			if(localModeList.get(i).getNum() > max)	max = localModeList.get(i).getNum();
		}

		for(int i = 0; i < localModeList.size(); i++)
		{
			if(localModeList.get(i).getNum() == max) System.out.println(localModeList.get(i).getValue() + " with " + localModeList.get(i).getNum() + " occurences");
		}

	}

	///////////////////////////////////////////////////////

/**
*  Inner class for representing Nodes of the Tree
*  It keeps Maxheap for data, left and right TreeNodes for children
*/
	protected class TreeNode <E extends Comparable<E>> 
	{
		public MaxHeap <E> data;
		public TreeNode <E> left; 
		public TreeNode <E> right;

/**
*  Constructor
*/
		public TreeNode()
		{
			data = new MaxHeap();
			left = null;
			right = null;
		}	

/**
*  add Method for TreeNode
*  @param entry E
*  @return number of occurences of the entry
*/
		public int add (E entry)
		{
			return data.add(entry);
		}

/**
*  Support method for delete method
*  @param entry E
*  @return Treenode
*/
		public TreeNode findMax()
		{
			if(right != null)	return right.findMax();
			return this;
		}

	}

	public static void main(String [] argc)
	{
		BSTHeapTree <Integer> bt = new BSTHeapTree<Integer>();

		bt.add(50);
		bt.add(11);
		bt.add(34);
		bt.add(0);
		bt.add(7);
		bt.add(45);
		bt.add(46);
		bt.add(22);

		bt.add(40);
		bt.add(33);
		bt.add(17);

		bt.add(70);
		bt.add(65);
		bt.add(55);
		bt.add(59);

		bt.remove(50);

		bt.add(80);

		bt.root.data.printHeap();
		bt.root.left.data.printHeap();
		bt.root.right.data.printHeap();

	}

}