package dsa;

import dsa.interfaces.Edge;
import dsa.interfaces.Graph;
import dsa.interfaces.Iterator;
import dsa.interfaces.Vertex;

public class DFS extends GraphTraverse {

    public DFS() {

    }

    public DFS(Graph graph) {
        super(graph);
    }

    //遍历过程中使用的计时器
    protected static int clock = 0;

    @Override
    protected Object visit(Vertex v, Object info) {
        return null;
    }

    @Override
    public Object algorithm(Vertex s, Object info) {
        return null;
    }

    //深度优先遍历算法
    @Override
    protected Object traverse(Vertex v, Object info) {  //从顶点v出发, 做深度优先查找
        if (UNDISCOVERED != v.getStatus()) {
            return null;    //跳过已访问过的顶点(针对非连通图)
        }
        v.setDStamp(clock++);
        v.setStatus(DISCOVERED);
        //访问当前节点
        visit(v, info);
        //检查顶点v
        for (Iterator it = v.outEdges(); it.hasNext(); ) {
            //通过边e=(v, u)
            Edge e = (Edge) it.getNext();
            //相连的每一顶点u
            Vertex u = (Vertex) e.getVPosInV(1).getElement();
            switch (u.getStatus()) {
                //u尚未被发现
                case UNDISCOVERED:
                    //e归类为树边(Tree)
                    e.setType(TREE);
                    //从u出发, 继续做深度优先查找
                    traverse(u, info);
                    break;
                //u已经被发现, 但对其访问尚未结束, 则
                case DISCOVERED:
                    //将e归类为"后向跨边"(Backward)
                    e.setType(BACKWARD);
                    break;
                //VISITED, 对u的访问已经结束
                default:
                    //相对于v, u被发现得更早
                    if (u.getDStamp() < v.getDStamp()) {
                        // 将e归类为"横跨边"
                        e.setType(CROSS);
                    } else {
                        //否则, 将e归类为"前向跨边"
                        e.setType(FORWARD);
                    }
            }
        }//至此, v的所有邻居都已经访问结束
        v.setFStamp(clock++);
        v.setStatus(VISITED);
        return null;//回溯
    }
}
