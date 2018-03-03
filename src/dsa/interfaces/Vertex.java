package dsa.interfaces;

import java.beans.VetoableChangeListener;

public interface Vertex {

    //尚未被发现的顶点
    int DISCOVERED = 1;
    //已被发现的顶点
    int UNDISCOVERED = 0;
    //已访问过的顶点
    int VISITED = 2;

    //返回当前顶点的信息
    public Object getInfo();

    //返回节点的出度
    int outDeg();

    //返回节点的入度
    int inDeg();

    //返回当前节点所有关联边, 关联边位置的迭代器
    Iterator inEdges();
    Iterator inEdgePositions();
    Iterator outEdges();
    Iterator outEdgePositions();

    //取当前顶点所属的图的顶点集V中的位置
    Position getVPosInV();
    //读取, 设置顶点的状态
    int getStatus();
    int setStatus(int s);

    //读取, 设置顶点的时间标签(DFS)
    int getDStamp();
    int setDStamp(int s);
    int getFStamp();
    int setFStamp(int s);

    //读取, 设置顶点至起点的最短距离(BFS或BestFS)
    int getDistance();
    int setDistance(int s);

    //读取, 设置顶点在的DFS, BFS, BestFS或MST树中的父亲
    Vertex getBFSParent();
    Vertex setBFSParent(Vertex s);


























}
