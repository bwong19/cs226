package hw9.tests;

import hw9.Graph;
import hw9.SparseGraph;

/**
 * Class for testing SparseGraph, an
 * implementation of GraphTest.
 */
public class SparseGraphTest extends GraphTest {
    @Override
    protected Graph<String, String> createGraph() {
        return new SparseGraph<>();
    }
}
