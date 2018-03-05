package dsa;

import dsa.interfaces.Edge;
import dsa.interfaces.Graph;
import dsa.interfaces.Iterator;
import dsa.interfaces.Vertex;

public abstract class GraphTraverse {
    //尚未被发现的顶点
    final static int UNDISCOVERED = 0;
    //已被发现的节点
    final static int DISCOVERED = 1;
    //已访问过的顶点
    final static int VISITED = 2;
    //未知边
    final static int UNKNOWN = 0;
    //树边
    final static int TREE = 1;
    //横跨边
    final static int CROSS = 2;
    //前向跨边
    final static int FORWARD = 3;
    //后向跨边
    final static int BACKWARD = 4;

    protected Graph g;

    public GraphTraverse() {

    }

    public GraphTraverse(Graph g) {
        this.g = g;
    }
    //将g中各顶点的标志, 各边的分类复位(s为遍历起点), 此处的s并未使用
    protected void reset(Vertex s) {
        for (Iterator it = g.vertices(); it.hasNext(); ) {
            Vertex v = (Vertex) it.getNext();
            v.setStatus(UNDISCOVERED);
            v.setDistance(Integer.MAX_VALUE);
        }
        for (Iterator it = g.edges(); it.hasNext(); ) {
            ((Edge) it.getNext()).setType(UNKNOWN);
        }
    }

    //遍历过程中对顶点v的具体访问操作的模板: 取决于, 服务于具体的算法algorithm()
    protected abstract Object visit(Vertex v, Object info);

    //基于遍历操作实现的其他算法的模板: s为起始顶点, info向算法传递参数及保存算法的返回信息
    public abstract Object algorithm(Vertex s, Object info);

    //遍历算法模板
    //从顶点v出发遍历
    protected abstract Object traverse(Vertex v, Object info);

}
