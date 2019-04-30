package hw9.tests;

import exceptions.PositionException;
import exceptions.RemovalException;
import exceptions.InsertionException;

import hw9.Graph;
import hw9.Vertex;
import hw9.Edge;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Testing suite for Graphs.
 */
public abstract class GraphTest {

    /**
     * Graph that is used for testing.
     */
    protected Graph<String, String> graph;

    /**
     * Creates graph based on what implementation is used.
     * @return graph that is created
     */
    protected abstract Graph<String, String> createGraph();

    /**
     * Creates new graph before every test.
     */
    @Before
    public void setupGraph() {
        this.graph = createGraph();
    }

    /**
     * Inserts multiple disconnected vertices.
     * Also tests vertices() function.
     */
    @Test
    public void insertVertex() {
        graph.insert("a");
        graph.insert("b");
        graph.insert("c");

        Iterable<Vertex<String>> vtcs = graph.vertices();
        Iterator<Vertex<String>> it = vtcs.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "a");
        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "b");
        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "c");

        assertFalse(it.hasNext());
    }

    /**
     * Inserts edges between multiple vertices.
     * Also tests edges() function.
     */
    @Test
    public void insertEdge() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, c, null);

        assertEquals(e1.get(), "ab");
        assertEquals(e2.get(), null);

        Iterable<Edge<String>> edges = graph.edges();
        Iterator<Edge<String>> it = edges.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "ab");
        assertTrue(it.hasNext());
        assertEquals(it.next().get(), null);

        assertFalse(it.hasNext());
    }

    /**
     * Inserts undirected edge.
     */
    @Test
    public void insertUndirectedEdge() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, a, "ba");

        assertEquals(e1.get(), "ab");
        assertEquals(e2.get(), "ba");
    }

    /**
     * Removes a valid vertex.
     */
    @Test
    public void removeVertex() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(b, c, "bc");

        String data = graph.remove(a);
        assertEquals(data, "a");

        Iterable<Vertex<String>> vtcs = graph.vertices();
        Iterator<Vertex<String>> it = vtcs.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "b");
        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "c");

        assertFalse(it.hasNext());
    }

    /**
     * Removes a valid edge.
     */
    @Test
    public void removeEdge() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, c, "bc");

        assertEquals(e1.get(), "ab");
        assertEquals(e2.get(), "bc");

        String data = graph.remove(e1);
        assertEquals(data, "ab");

        Iterable<Edge<String>> aOut = graph.outgoing(a);
        for (Object o: aOut) {
            assertFalse(true);
        }
        Iterable<Edge<String>> aIn = graph.incoming(a);
        for (Object o: aIn) {
            assertFalse(true);
        }

        Iterable<Edge<String>> bOut = graph.outgoing(b);
        for (Edge<String> o: bOut) {
            assertEquals(o.get(), "bc");
        }
        Iterable<Edge<String>> bIn = graph.incoming(b);
        for (Object o: bIn) {
            assertFalse(true);
        }

        Iterable<Edge<String>> edges = graph.edges();
        Iterator<Edge<String>> it = edges.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next().get(), "bc");

        assertFalse(it.hasNext());
    }

    /**
     * Checks outgoing edges of a vertex.
     */
    @Test
    public void testOutgoing() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");
        Vertex<String> d = graph.insert("d");
        Vertex<String> e = graph.insert("e");
        Vertex<String> f = graph.insert("f");

        // "a" has degree 3,
        // "b" has degree 3,
        // "c" has degree 2,
        // "d" has degree 1,
        // "e" has degree 1,
        // "f" has degree 0.
        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(a, c, "ac");
        Edge<String> e3 = graph.insert(a, d, "ad");
        Edge<String> e4 = graph.insert(b, c, "bc");
        Edge<String> e5 = graph.insert(b, e, "be");

        Iterable<Edge<String>> aOut = graph.outgoing(a);
        Iterator<Edge<String>> itA = aOut.iterator();
        assertEquals(itA.next().get(), "ab");
        assertEquals(itA.next().get(), "ac");
        assertEquals(itA.next().get(), "ad");

        Iterable<Edge<String>> bOut = graph.outgoing(b);
        Iterator<Edge<String>> itB = bOut.iterator();
        assertEquals(itB.next().get(), "bc");
        assertEquals(itB.next().get(), "be");

        Iterable<Edge<String>> cOut = graph.outgoing(c);
        for (Object o: cOut) {
            assertFalse(true);
        }

        Iterable<Edge<String>> dOut = graph.outgoing(d);
        for (Object o: dOut) {
            assertFalse(true);
        }

        Iterable<Edge<String>> eOut = graph.outgoing(e);
        for (Object o: eOut) {
            assertFalse(true);
        }

        Iterable<Edge<String>> fOut = graph.outgoing(f);
        for (Object o: fOut) {
            assertFalse(true);
        }
    }

    /**
     * Checks incoming edges of a vertex.
     */
    @Test
    public void testIncoming() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");
        Vertex<String> d = graph.insert("d");
        Vertex<String> e = graph.insert("e");
        Vertex<String> f = graph.insert("f");

        // "a" has degree 3,
        // "b" has degree 3,
        // "c" has degree 2,
        // "d" has degree 1,
        // "e" has degree 1,
        // "f" has degree 0.
        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(a, c, "ac");
        Edge<String> e3 = graph.insert(a, d, "ad");
        Edge<String> e4 = graph.insert(b, c, "bc");
        Edge<String> e5 = graph.insert(b, e, "be");

        Iterable<Edge<String>> aIn = graph.incoming(a);
        for (Object o: aIn) {
            assertFalse(true);
        }

        Iterable<Edge<String>> bIn = graph.incoming(b);
        Iterator<Edge<String>> itB = bIn.iterator();
        assertEquals(itB.next().get(), "ab");

        Iterable<Edge<String>> cIn = graph.incoming(c);
        Iterator<Edge<String>> itC = cIn.iterator();
        assertEquals(itC.next().get(), "ac");
        assertEquals(itC.next().get(), "bc");

        Iterable<Edge<String>> dIn = graph.incoming(d);
        Iterator<Edge<String>> itD = dIn.iterator();
        assertEquals(itD.next().get(), "ad");

        Iterable<Edge<String>> eIn = graph.incoming(e);
        Iterator<Edge<String>> itE = eIn.iterator();
        assertEquals(itE.next().get(), "be");

        Iterable<Edge<String>> fIn = graph.incoming(f);
        for (Object o: fIn) {
            assertFalse(true);
        }
    }

    /**
     * Checks from() vertices of an edge.
     */
    @Test
    public void testFrom() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");
        Vertex<String> d = graph.insert("d");
        Vertex<String> e = graph.insert("e");
        Vertex<String> f = graph.insert("f");

        // "a" has degree 3,
        // "b" has degree 3,
        // "c" has degree 2,
        // "d" has degree 1,
        // "e" has degree 1,
        // "f" has degree 0.
        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(a, c, "ac");
        Edge<String> e3 = graph.insert(a, d, "ad");
        Edge<String> e4 = graph.insert(b, c, "bc");
        Edge<String> e5 = graph.insert(b, e, "be");

        assertEquals(graph.from(e1), a);
        assertEquals(graph.from(e2), a);
        assertEquals(graph.from(e3), a);
        assertEquals(graph.from(e4), b);
        assertEquals(graph.from(e5), b);
    }

    /**
     * Checks to() vertices of an edge.
     */
    @Test
    public void testTo() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");
        Vertex<String> d = graph.insert("d");
        Vertex<String> e = graph.insert("e");
        Vertex<String> f = graph.insert("f");

        // "a" has degree 3,
        // "b" has degree 3,
        // "c" has degree 2,
        // "d" has degree 1,
        // "e" has degree 1,
        // "f" has degree 0.
        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(a, c, "ac");
        Edge<String> e3 = graph.insert(a, d, "ad");
        Edge<String> e4 = graph.insert(b, c, "bc");
        Edge<String> e5 = graph.insert(b, e, "be");

        assertEquals(graph.to(e1), b);
        assertEquals(graph.to(e2), c);
        assertEquals(graph.to(e3), d);
        assertEquals(graph.to(e4), c);
        assertEquals(graph.to(e5), e);
    }

    /**
     * Tests vertex label and get label.
     */
    @Test
    public void vertexLabel() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        graph.label(a, "cool");
        graph.label(b, 20);
        graph.label(c, null);

        assertEquals(graph.label(a), "cool");
        assertEquals(graph.label(b), 20);
        assertEquals(graph.label(c), null);
    }

    /**
     * Tests edge label and get label.
     */
    @Test
    public void edgeLabel() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, c, null);
        Edge<String> e3 = graph.insert(a, c, "yes");

        graph.label(e1, "cool");
        graph.label(e2, 20);
        graph.label(e3, null);

        assertEquals(graph.label(e1), "cool");
        assertEquals(graph.label(e2), 20);
        assertEquals(graph.label(e3), null);
    }

    /**
     * Tests clearLabels().
     */
    @Test
    public void testClearLabels() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, c, null);
        Edge<String> e3 = graph.insert(a, c, "yes");

        graph.label(a, "cool");
        graph.label(b, 20);
        graph.label(c, null);

        graph.label(e1, "cool");
        graph.label(e2, 20);
        graph.label(e3, null);

        assertEquals(graph.label(a), "cool");
        assertEquals(graph.label(b), 20);
        assertEquals(graph.label(c), null);

        assertEquals(graph.label(e1), "cool");
        assertEquals(graph.label(e2), 20);
        assertEquals(graph.label(e3), null);

        graph.clearLabels();

        assertEquals(graph.label(a), null);
        assertEquals(graph.label(b), null);
        assertEquals(graph.label(c), null);

        assertEquals(graph.label(e1), null);
        assertEquals(graph.label(e2), null);
        assertEquals(graph.label(e3), null);
    }

    // EXCEPTION TESTING

    /**
     * Inserts null vertex.
     */
    @Test (expected = PositionException.class)
    public void insertNullVertex() {
        graph.insert(null);
    }

    /**
     * Inserts edge with a bad "from" vertex.
     */
    @Test (expected = PositionException.class)
    public void insertBadFromEdge() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
    }

    /**
     * Inserts edge with a null "from" vertex.
     */
    @Test (expected = PositionException.class)
    public void insertNullFromEdge() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(null, b, "ab");
    }

    /**
     * Inserts edge with a bad "to" vertex.
     */
    @Test (expected = PositionException.class)
    public void insertBadToEdge() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
    }

    /**
     * Inserts edge with a null "to" vertex.
     */
    @Test (expected = PositionException.class)
    public void insertNullToEdge() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, null, "ab");
    }


    /**
     * Inserts edge with a bad "from" and "to" vertex.
     */
    @Test (expected = PositionException.class)
    public void insertBadFromToEdge() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
    }

    /**
     * Inserts self-loop.
     */
    @Test (expected = InsertionException.class)
    public void insertSelfLoop() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, a, "aa");
    }

    /**
     * Inserts duplicate edge.
     */
    @Test (expected = InsertionException.class)
    public void insertDuplicateEdge() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(a, b, "ahhh");
    }

    /**
     * Removes invalid vertex.
     */
    @Test (expected = PositionException.class)
    public void removeBadVertex() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph.insert("c");

        graph.remove(b);
    }

    /**
     * Removes null vertex.
     */
    @Test (expected = PositionException.class)
    public void removeNullVertex() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph.insert("a");
        Vertex<String> b = null;
        Vertex<String> c = graph.insert("c");

        graph.remove(b);
    }

    /**
     * Double removes vertex.
     */
    @Test (expected = PositionException.class)
    public void doubleRemoveVertex() {
        Graph<String, String> graph1 = createGraph();

        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph.insert("c");

        graph.remove(a);
        graph.remove(a);
    }

    /**
     * Removes vertex with incoming edges.
     */
    @Test (expected = RemovalException.class)
    public void removeVertexIncoming() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");

        graph.remove(b);
    }

    /**
     * Removes vertex with outgoing edges.
     */
    @Test (expected = RemovalException.class)
    public void removeVertexOutgoing() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");

        graph.remove(a);
    }

    /**
     * Removes vertex with incoming and outgoing edges.
     */
    @Test (expected = RemovalException.class)
    public void removeVertexIncomingOutgoing() {
        Vertex<String> a = graph.insert("a");
        Vertex<String> b = graph.insert("b");
        Vertex<String> c = graph.insert("c");

        Edge<String> e1 = graph.insert(a, b, "ab");
        Edge<String> e2 = graph.insert(b, c, "bc");

        graph.remove(b);
    }

    /**
     * Removes invalid edge.
     */
    @Test (expected = PositionException.class)
    public void removeBadEdge() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        Edge<String> e1 = graph1.insert(a, b, "ab");
        Edge<String> e2 = graph1.insert(b, c, "bc");

        graph.remove(e1);
    }

    /**
     * Removes null edge.
     */
    @Test (expected = PositionException.class)
    public void removeNullEdge() {
        Edge<String> e1 = null;
        graph.remove(e1);
    }

    /**
     * Calls outgoing() of an invalid vertex.
     */
    @Test (expected = PositionException.class)
    public void outgoingBadVertex() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        graph.outgoing(a);
    }

    /**
     * Calls outgoing() of a null vertex.
     */
    @Test (expected = PositionException.class)
    public void outgoingNullVertex() {
        Vertex<String> a = null;
        graph.outgoing(a);
    }

    /**
     * Calls incoming() of an invalid vertex.
     */
    @Test (expected = PositionException.class)
    public void incomingBadVertex() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        graph.incoming(a);
    }

    /**
     * Calls incoming() of a null vertex.
     */
    @Test (expected = PositionException.class)
    public void incomingNullVertex() {
        Vertex<String> a = null;
        graph.incoming(a);
    }

    /**
     * Calls from() of an invalid edge.
     */
    @Test (expected = PositionException.class)
    public void fromBadEdge() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        Edge<String> e1 = graph1.insert(a, b, "ab");
        Edge<String> e2 = graph1.insert(b, c, "bc");

        graph.from(e1);
    }

    /**
     * Calls from() of a null edge.
     */
    @Test (expected = PositionException.class)
    public void fromNullEdge() {
        Edge<String> e1 = null;
        graph.from(e1);
    }

    /**
     * Calls to() of an invalid edge.
     */
    @Test (expected = PositionException.class)
    public void toBadEdge() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        Edge<String> e1 = graph1.insert(a, b, "ab");
        Edge<String> e2 = graph1.insert(b, c, "bc");

        graph.to(e1);
    }

    /**
     * Calls to() of a null edge.
     */
    @Test (expected = PositionException.class)
    public void toNullEdge() {
        Edge<String> e1 = null;
        graph.to(e1);
    }

    /**
     * Label invalid vertex.
     */
    @Test (expected = PositionException.class)
    public void labelInvalidVertex() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        graph.label(a, "hello");
    }

    /**
     * Label null vertex.
     */
    @Test (expected = PositionException.class)
    public void labelNullVertex() {
        Vertex<String> a = null;
        graph.label(a, "hello");
    }

    /**
     * Label invalid edge.
     */
    @Test (expected = PositionException.class)
    public void labelInvalidEdge() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        Edge<String> e1 = graph1.insert(a, b, "ab");
        Edge<String> e2 = graph1.insert(b, c, "bc");

        graph.label(e1, "hello");
    }

    /**
     * Label null edge.
     */
    @Test (expected = PositionException.class)
    public void labelNullEdge() {
        Edge<String> e1 = null;
        graph.label(e1, "hello");
    }

    /**
     * Gets label of invalid vertex.
     */
    @Test (expected = PositionException.class)
    public void getLabelInvalidVertex() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        graph.label(a);
    }

    /**
     * Gets label of null vertex.
     */
    @Test (expected = PositionException.class)
    public void getLabelNullVertex() {
        Vertex<String> a = null;
        graph.label(a);
    }

    /**
     * Gets label of invalid edge.
     */
    @Test (expected = PositionException.class)
    public void getLabelInvalidEdge() {
        Graph<String, String> graph1 = createGraph();
        Vertex<String> a = graph1.insert("a");
        Vertex<String> b = graph1.insert("b");
        Vertex<String> c = graph1.insert("c");

        Edge<String> e1 = graph1.insert(a, b, "ab");
        Edge<String> e2 = graph1.insert(b, c, "bc");

        graph.label(e1);
    }

    /**
     * Gets label of null edge.
     */
    @Test (expected = PositionException.class)
    public void getLabelNullEdge() {
        Edge<String> e1 = null;
        graph.label(e1);
    }
}