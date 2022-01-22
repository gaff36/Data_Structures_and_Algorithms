import java.util.Iterator;

/** NavigableSetSkipListInterface
*/
public interface NavigableSetSkipListInterface <E>
{
	public boolean insert(E item);
	public boolean delete(E item);
	public Iterator descendingIterator();
}