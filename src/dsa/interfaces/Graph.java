package dsa.interfaces;

/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/2 18:16
 * @description 图
 *
 * 定义:
 *   1. 设G=(V, E)和G'=(V', E'), 如果V' ∈ V且E' ∈ E, 则称G'是G的一个子图(Sub-graph)
 *   2. 如果V' = V且E' = E, 则称G'是G的一个生成子图(Spanning subgraph)
 *   3. 若U ∈ V, 则在删除V\U中的顶点及其关联边之后所得到的G的子图, 称为G限制在U上的子图, 记作G|u = (U, E|u)
 *   4. 图中的一条通路或路径(Path), 就是由(不一定互异的)m + 1个顶点与m条边交替构成的一个序列ρ={v0, e1, v1, e2, v2, ..., em, vm},
 *   m ≥ 0, 而且ei = (v<sub>i-1</sub>, vi), 1 ≤ i ≤ m
 *   5. 长度m ≥ 1的路径, 若第一个顶点与最后一个顶点相同, 则称为环路(Cycle)
 *      1. 如果组成通路ρ的所有顶点各不相同, 则称之为简单通路(Simple path)
 *      2. 如果在组成环路的所有顶点中, 除v0 = vm外均各不相同, 则称之为简单环路(Simple cycle)
 *      3. 如果组成通路ρ的所有边都是有向边, 而且每一ei都是从v<sub>i-1</sub>指向vi, 1 ≤ i ≤ m, 则称ρ为有向通路(Directed path)
 *   6. 对于指定的顶点s, 从s可达的所有顶点所组成的集合, 称作s在G中对应的可达分量, 记作Vr(G, s)
 *   7. 若v ∈ Vr(G, s), 则有一条简单路径从顶点s通往v
 *   8. 图G = (V, E), 在顶点u, v ∈ V之间, 如果既存在一条从u到v的通路ρ(u, v), 也存在一条从v到u的通路ρ(v, u), 则称u和v是连通的, 记作u~v
 *   9. 有向图中相互连通的顶点也被称作是互相"强连通的"
 *   10. 有向图中, 由一组互相连通的顶点构成的极大集合, 称作一个强连通分量
 *   11. 连通关系"~"是顶点集上的一个等价关系
 *   12. 根据等价关系"~", 可以将顶点集V划分为若干等价类, 每一等价类都称作图G的一个连通分量(Connected component).
 *   由单个连通分量组成的图称为连通图(Connected graph), 有向连通图也可以称作"强连通图"(Strongly-connected graph)
 *   13. 若G中不含任何环路, 则称之为森林
 *   14. 连通的森林称作树
 *   15. 若G为由n个顶点与m条边组成一幅无向图
 *      1. 若G是连通的, 则 m ≥ n - 1
 *      2. 若G是一棵树, 则 m = n - 1
 *      3. 若G是森林, 则 m ≤ n - 1
 *   16. 若G的某一生成子图G'为一棵树, 则称G'为G的一棵生成树(Spanning tree)
 *   17. 若存在顶点r ∈ V, 使得对于任何顶点v ∈ V, 都有一条从r通往v的有向通路, 则称T为G的一棵(以r为根的)生成树.
 *   18. 若σ=(u0, u1, u2, ..., uk)是带权网络G中的一条路径, 则|σ| = ∑<sup>k</sup><sub>i=1</sub>w(u<sub>i-1</sub>, ui)称作带权路径σ的权重或长度.
 *   19. 从顶点s出发对图G = (V, E)做深度优先遍历之后, 我们把所有被访问到的顶点所组成的集合记作Vd(G, s), 把所有被标记为TREE的边所构成的集合记做Ed(G, s),
 *   记子图Td(G, s) = (Vd(G, s), Ed(G,s))
 *   20.通过深度优先遍历, 图G = (V, E)中任一顶点v所对应的可达分量都可以在o(|Vr(G, v)| + |E|Vr(G, v)|)时间内计算出来
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
