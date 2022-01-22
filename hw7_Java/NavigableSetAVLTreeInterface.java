import java.util.Iterator;

/** NavigableSetAVLTreeInterface
*/
public interface NavigableSetAVLTreeInterface <E>
{
	public boolean insert(E item);
	public NavigableSetAVLTree headSet(E toElement, boolean inclusive);
	public NavigableSetAVLTree headSet(E toElement);
	public NavigableSetAVLTree tailSet(E toElement, boolean inclusive);
	public NavigableSetAVLTree tailSet(E toElement);
	public Iterator iterator();
}