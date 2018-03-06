package dsa;

import dsa.interfaces.Edge;
import dsa.interfaces.Graph;
import dsa.interfaces.Iterator;
import dsa.interfaces.Vertex;

/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/6 11:38
 * @description 1. 只要顶点v是从起点s可达的, 则从s通往节点v的最短路径必然存在, 且δ(s, v)必然唯一存在
 * 2. 相对于同一起点s, Vr(G, s)中所有顶点所对应的最短路径合起来将构成Vr(G, s)的一棵生成树, 称作最短路径生成树
 * 3. 单调性: 若从顶点s到v的一条最短路径为π=(u0=s, u1, u2, ..., uk=v), 则对于任何0 ≤ i ≤ k, (s, u1, u2, ..., ui)也是从顶点s到ui的一条最短路径
 * 4. 在所有边的权重为正数的前提下, 沿着任一路径π=(s, u1, u2, ..., uk), 从s到各顶点ui的最短路径必然是严格递增的, i = 1...n
 * 若有向带权图G中的所有顶点都是从顶点s可达的, 则可以根据s到每个顶点的最短路径长度, 将它们排成一个非降序列: {u0, u1, ..., u<sub>n-1</sub>}
 * 5. 从s到u1的最短路径由一条边(s, u1)组成, δ(s, u1) = |(s, u1)|;而且, 在从s发出的所有边中, 边(s, u1最短)
 * 6. 从s到u1的最短路径, 只有两种可能:
 * 1. 由一条边(s, u2)组成, δ(s, u2) = |(s, u2)|; 或者
 * 2. 由(s, u1)和(u1, u2)组成, δ(s, u2) = δ(s, u1) + |(u1, u2)|
 * 7. 从s到uk的最短路径, 由从s到某一ui的最短路径和(ui, uk)组成, 0 ≤ i ≤ k. δ(s, uk) = δ(s, ui) + |(ui, uk)|
 */

public class BestFDSijkstra extends BestFS {

    public BestFDSijkstra(Graph graph) {
        super(graph);
    }

    @Override
    protected void updateDistanceAfter(Vertex vertex) {
        for (Iterator it = vertex.outEdges(); it.hasNext(); ) {
            Edge e = (Edge) it.getNext();
            Vertex w = (Vertex) e.getVPosInV(1).getElement();
            int weight = (Integer) e.getInfo();
            if (w.getDistance() > vertex.getDistance() + weight) {
                w.setDistance(vertex.getDistance() + weight);
                w.setBFSParent(vertex);
            }
        }
    }
}
