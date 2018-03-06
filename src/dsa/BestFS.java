package dsa;

import dsa.interfaces.Graph;
import dsa.interfaces.Iterator;
import dsa.interfaces.Vertex;

public abstract class BestFS extends GraphTraverse {
    public BestFS() {
    }

    public BestFS(Graph graph) {
        super(graph);
    }

    protected abstract void updateDistanceAfter(Vertex vertex);

    protected Object traverse(Vertex s, Object info) {
        if (UNDISCOVERED != s.getStatus()) {
            return null;
        }
        s.setDistance(0);
        Vertex v;
        while (null != (v = bestVertex())) {
            visit(v, null);
            updateDistanceAfter(v);
        }
        return null;
    }

    protected Object visit(Vertex v, Object info) {
        v.setStatus(VISITED);
        return null;
    }

    @Override
    public Object algorithm(Vertex s, Object info) {
        reset(s);
        traverse(s, info);
        return null;
    }

    protected Vertex bestVertex() {
        int bestValue = Integer.MAX_VALUE;
        Vertex bestVertex = null;
        for (Iterator it = g.vertices(); it.hasNext(); ) {
            Vertex u = (Vertex) it.getNext();
            if (UNDISCOVERED == u.getStatus()) {
                if (bestValue > u.getDistance()) {
                    bestValue = u.getDistance();
                    bestVertex = u;
                }
            }
        }
        if (null != bestVertex && null != bestVertex.getBFSParent()) {
            g.edgeFromTo(bestVertex.getBFSParent(), bestVertex).setType(TREE);
        }
        return bestVertex;
    }
}
