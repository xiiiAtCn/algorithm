package dsa;

import dsa.interfaces.Graph;
import dsa.interfaces.Vertex;

public class BFSDistance extends BFS {
    public BFSDistance(Graph graph) {
        super(graph);
    }

    @Override
    protected Object visit(Vertex v, Object info) {
        if (null == info) {
            v.setDistance(0);
        } else {
            v.setDistance(((Vertex) info).getDistance() + 1);
        }
        return null;
    }

    @Override
    public Object algorithm(Vertex s, Object info) {
        reset(s);
        traverse(s, info);
        return null;
    }
}
