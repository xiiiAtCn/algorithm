package dsa.interfaces;

/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/2 18:16
 * @description 图
 *
 */

public interface Graph {
    int vNumber = 0;
    int eNumber = 0;

    //图中顶点迭代器
    Iterator vertices();

    //途中顶点位置迭代器
    Iterator vPositions();

    Iterator edges();

    Iterator ePositions();

    boolean areAdjacent(Vertex u, Vertex v);

    Edge edgeFromTo(Vertex u, Vertex v);

    Vertex remove(Vertex v);

    Edge remove(Edge e);

    Position insert(Vertex v);

    Position insert(Edge e);
}
