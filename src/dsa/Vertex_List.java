package dsa;

import dsa.interfaces.*;

public class Vertex_List implements Vertex {

    //当前顶点中存放的数据元素
    protected Object info;

    //当前顶点在所属的图的顶点表V中的位置
    protected Position vPosInV;

    //关联边表: 存放以当前顶点为尾的所有边(的位置)
    protected List outEdges;

    //关联边表: 存放以当前顶点为头的所有边(的位置)
    protected List inEdges;

    //(在遍历图等操作过程中)顶点的状态
    protected int status;

    //时间标签: DFS过程中该顶点被发现时的时刻
    protected int dStamp;

    //时间标签: DFS过程中该顶点被访问结束时的时刻
    protected int fStamp;

    //到指定起点的距离: BFS, Dijkstra等算法所确定该顶点到起点的距离
    protected int distance;

    //在最短距离树(BFS或BestFs)中的父亲
    protected Vertex bfsParent;

    public Vertex_List(Graph g, Object x) {
        info = x;
        vPosInV = g.insert(this);
        outEdges = new List_DLNode();
        inEdges = new List_DLNode();
        status = UNDISCOVERED;
        dStamp = fStamp = Integer.MAX_VALUE;
        distance = Integer.MAX_VALUE;
        bfsParent = null;
    }

    @Override
    public Object getInfo() {
        return info;
    }

    @Override
    public int outDeg() {
        return outEdges.getSize();
    }

    @Override
    public int inDeg() {
        return inEdges.getSize();
    }

    @Override
    public Iterator inEdges() {
        return outEdges.elements();
    }

    @Override
    public Iterator inEdgePositions() {
        return outEdges.positions();
    }

    @Override
    public Iterator outEdges() {
        return outEdges.elements();
    }

    @Override
    public Iterator outEdgePositions() {
        return outEdges.positions();
    }

    @Override
    public Position getVPosInV() {
        return vPosInV;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int setStatus(int s) {
        int ss = status;
        status = s;
        return ss;
    }

    @Override
    public int getDStamp() {
        return dStamp;
    }

    @Override
    public int setDStamp(int s) {
        int ss = dStamp;
        dStamp = s;
        return ss;
    }

    @Override
    public int getFStamp() {
        return fStamp;
    }

    @Override
    public int setFStamp(int s) {
        int ss = fStamp;
        fStamp = s;
        return ss;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public int setDistance(int s) {
        int bak = distance;
        distance = s;
        return bak;
    }

    @Override
    public Vertex getBFSParent() {
        return bfsParent;
    }

    @Override
    public Vertex setBFSParent(Vertex s) {
        Vertex bak = bfsParent;
        bfsParent = s;
        return bak;
    }
}
