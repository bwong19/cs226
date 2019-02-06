/** Index Exception custom Class.
 */
public class IndexException extends RuntimeException {

    /** This is to avoid a java compiler warning. */
    private static final long serialVersionUID = 0L;

    /** Custom constructor. */
    public IndexException() {
        super("bad index value");
    }

}