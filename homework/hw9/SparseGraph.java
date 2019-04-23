package hw9;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

import java.util.ArrayList;
import java.util.List;

/**
    An implementation of a directed graph using incidence lists
    for sparse graphs where most things aren't connected.
    @param <V> Vertex element type.
    @param <E> Edge element type.
*/
public class SparseGraph<V, E> implements Graph<V, E> {

    // Class for a vertex of type V
    private final class VertexNode<V> implements Vertex<V> {
        V data;
        Graph<V, E> owner;
        List<Edge<E>> outgoing;
        List<Edge<E>> incoming;
        Object label;

        VertexNode(V v) {
            this.data = v;
            this.outgoing = new ArrayList<>();
            this.incoming = new ArrayList<>();
            this.label = null;
        }

        @Override
        public V get() {
            return this.data;
        }

        @Override
        public void put(V v) {
            this.data = v;
        }
    }

    //Class for an edge of type E
    private final class EdgeNode<E> implements Edge<E> {
        E data;
        Graph<V, E> owner;
        VertexNode<V> from;
        VertexNode<V> to;
        Object label;

        // Constructor for a new edge
        EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
            this.from = f;
            this.to = t;
            this.data = e;
            this.label = null;
        }

        @Override
        public E get() {
            return this.data;
        }

        @Override
        public void put(E e) {
            this.data = e;
        }
    }

    private List<Vertex<V>> vertices;
    private List<Edge<E>> edges;

    /** Constructor for instantiating a graph. */
    public SparseGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Checks vertex belongs to this graph
    private void checkOwner(VertexNode<V> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    // Checks edge belongs to this graph
    private void checkOwner(EdgeNode<E> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    // Converts the vertex back to a VertexNode to use internally
    private VertexNode<V> convert(Vertex<V> v) throws PositionException {
        try {
            VertexNode<V> gv = (VertexNode<V>) v;
            this.checkOwner(gv);
            return gv;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    // Converts and edge back to a EdgeNode to use internally
    private EdgeNode<E> convert(Edge<E> e) throws PositionException {
        try {
            EdgeNode<E> ge = (EdgeNode<E>) e;
            this.checkOwner(ge);
            return ge;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    @Override
    public Vertex<V> insert(V v) {
        Vertex<V> gv = new VertexNode<>(v);
        vertices.add(gv);
        return gv;
    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
            throws PositionException, InsertionException {
        VertexNode<V> f = convert(from);
        VertexNode<V> t = convert(to);
        if (f.get().equals(t.get())) {
            throw new InsertionException();
        }
        Edge<E> ge = new EdgeNode<>(f, t, e);

        for (Edge<E> out: f.outgoing) {
            EdgeNode<E> oute = convert(out);
            if (t.get().equals(oute.to.get())) {
                throw new InsertionException();
            }
        }

        return ge;
    }

    @Override
    public V remove(Vertex<V> v) throws PositionException,
            RemovalException {
        VertexNode<V> gv = convert(v);
        if (!gv.incoming.isEmpty() || !gv.outgoing.isEmpty()) {
            throw new RemovalException();
        }

        vertices.remove(gv);

        return gv.get();
    }

    @Override
    public E remove(Edge<E> e) throws PositionException {
        EdgeNode<E> ge = convert(e);

        ge.from.outgoing.remove(ge);
        ge.to.incoming.remove(ge);
        edges.remove(ge);
        
        return ge.get();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
        VertexNode<V> gv = convert(v);
        return gv.outgoing;
    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
        VertexNode<V> gv = convert(v);
        return gv.incoming;
    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {
        EdgeNode<E> ge = convert(e);
        return ge.from;
    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {
        EdgeNode<E> ge = convert(e);
        return ge.to;
    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {
        VertexNode<V> gv = convert(v);
        gv.label = l;
    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {
        EdgeNode<E> ge = convert(e);
        ge.label = l;
    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {
        VertexNode<V> gv = convert(v);
        return gv.label;
    }

    @Override
    public Object label(Edge<E> e) throws PositionException {
        EdgeNode<E> ge = convert(e);
        return ge.label;
    }

    @Override
    public void clearLabels() {
        for (Vertex<V> v: vertices) {
            VertexNode<V> gv = convert(v);
            gv.label = null;
        }
        for (Edge<E> e: edges) {
            EdgeNode<E> ge = convert(e);
            ge.label = null;
        }
    }

    private String vertexString(Vertex<V> v) {
        return "\"" + v.get() + "\"";
    }

    private String verticesToString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> v : this.vertices) {
            sb.append("  ").append(vertexString(v)).append("\n");
        }
        return sb.toString();
    }

    private String edgeString(Edge<E> e) {
        return String.format("%s -> %s [label=\"%s\"]",
                this.vertexString(this.from(e)),
                this.vertexString(this.to(e)),
                e.get());
    }

    private String edgesToString() {
        String edgs = "";
        for (Edge<E> e : this.edges) {
            edgs += "    " + this.edgeString(e) + ";\n";
        }
        return edgs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n")
                .append(this.verticesToString())
                .append(this.edgesToString())
                .append("}");
        return sb.toString();
    }
}
