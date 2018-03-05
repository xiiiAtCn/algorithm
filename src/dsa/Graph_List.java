package dsa;

import dsa.interfaces.*;

public class Graph_List implements Graph {

    //容器: 存放图中所有边
    protected List E;
    //容器: 存放图中所有顶点
    protected List V;

    public Graph_List() {
        E = new List_DLNode();
        V = new List_DLNode();
    }

    protected List getE() {
        return E;
    }

    protected List getV() {
        return V;
    }

    public int vNumber() {
        return V.getSize();
    }

    public int eNumber() {
        return E.getSize();
    }

    @Override
    public Iterator vertices() {
        return V.elements();
    }

    @Override
    public Iterator vPositions() {
        return V.positions();
    }

    @Override
    public Iterator edges() {
        return E.elements();
    }

    @Override
    public Iterator ePositions() {
        return E.positions();
    }

    @Override
    public boolean areAdjacent(Vertex u, Vertex v) {
        return null != edgeFromTo(u, v);
    }

    @Override
    public Edge edgeFromTo(Vertex u, Vertex v) {
        for (Iterator it = u.outEdges(); it.hasNext(); ) {
            Edge e = (Edge) it.getNext();
            if (v == e.getVPosInV(1).getElement()) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Vertex remove(Vertex v) {
        while (v.outDeg() > 0) {
            remove((Edge) (((Vertex_List) v).outEdges.first()).getElement());
        }
        while (0 > v.inDeg()) {
            remove((Edge) ((Vertex_List) v).inEdges.first().getElement());
        }
        return (Vertex) V.remove(v.getVPosInV());
    }

    @Override
    public Edge remove(Edge e) {
        ((Vertex_List) e.getVPosInV(0).getElement()).outEdges.remove(e.getEPosInI(0));
        ((Vertex_List) e.getVPosInV(1).getElement()).inEdges.remove(e.getEPosInI(1));
        return (Edge)E.remove(e.getEPosInE());
    }

    @Override
    public Position insert(Vertex v) {
        return V.insertLast(v);
    }

    @Override
    public Position insert(Edge e) {
        return E.insertLast(e);
    }
}
