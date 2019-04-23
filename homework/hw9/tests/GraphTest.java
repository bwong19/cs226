package hw9.tests;

import hw9.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public abstract class GraphTest {

    protected Graph<String, String> graph;

    protected abstract Graph<String, String> createGraph();

    @Before
    public void setupGraph() {
        this.graph = createGraph();
    }

    // TODO - Add tests
}
