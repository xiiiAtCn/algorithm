package dsa;

import dsa.interfaces.*;

public abstract class BFS extends GraphTraverse {

    public BFS() {

    }

    public BFS(Graph graph) {
        super(graph);
    }

    @Override
    protected Object traverse(Vertex v, Object info) {
        if (UNDISCOVERED != v.getStatus()) {
            return null;
        }
        Queue Q = new Queue_List();
        v.setStatus(DISCOVERED);
        Q.enqueue(v);
        visit(v, null);
        while (!Q.isEmpty()) {
            Vertex vertex = (Vertex) Q.dequeue();
            for (Iterator it = vertex.outEdges(); it.hasNext(); ) {
                Edge e = (Edge) it.getNext();
                Vertex u = (Vertex) e.getVPosInV(1).getElement();
                if (UNDISCOVERED == u.getStatus()) {
                    e.setType(TREE);
                    u.setStatus(DISCOVERED);
                    Q.enqueue(u);
                    visit(u, vertex);
                } else {
                    e.setType(CROSS);
                }
            }
            vertex.setStatus(VISITED);
        }
        return null;
    }
}
