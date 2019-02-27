/** Instantiate the LinkedList to test. */
public class LinkedListTest extends ListTest {
    @Override
    protected List<String> createList() {
        return new LinkedList<>();
    }
}
