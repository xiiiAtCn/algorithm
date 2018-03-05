package dsa;

import dsa.interfaces.Graph;
import dsa.interfaces.Stack;
import dsa.interfaces.Vertex;
/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/3 18:05
 * @description 
 * 
 */
 
public class DFSReachableComponent extends DFS {
    public DFSReachableComponent(Graph graph) {
        super(graph);
    }

    @Override
    protected Object visit(Vertex v, Object info) {
        ((Stack) info).push(v);
        return null;
    }

    @Override
    public Object algorithm(Vertex s, Object info) {
        reset(s);
        Stack S = new Stack_Array();
        traverse(s, info);
        return null;
    }
}
