package dsa;

import dsa.interfaces.Edge;
import dsa.interfaces.Graph;
import dsa.interfaces.Position;

public class Edge_List implements Edge {

    //当前边中存放的数据元素
    protected Object info;

    //当前边在所属的图的边表中的位置
    protected Position ePosInE;

    //当前边两个端点在顶点表中的位置
    protected Position vPosInV[];

    //当前边在其两个端点的关联边表中的位置
    //约定: 第0(1)个顶点分别为尾(头)顶点
    //禁止头, 尾顶点相同的边
    protected Position ePosInI[];

    //(经过遍历之后)边被归入的类别
    protected int type;

    public Edge_List(Graph g, Vertex_List u, Vertex_List v, Object x) {
        info = x;
        ePosInE = g.insert(this);
        vPosInV = new DLNode[2];
        vPosInV[0] = u.getVPosInV();
        vPosInV[1] = v.getVPosInV();
        ePosInI = new DLNode[2];
        ePosInI[0] = u.outEdges.insertLast(this);
        ePosInI[1] = v.inEdges.insertLast(this);
        type = UNKNOWN;
    }


    @Override
    public Object getInfo() {
        return info;
    }

    @Override
    public Object setInfo(Object x) {
        Object bak = info;
        info = x;
        return bak;
    }

    @Override
    public Position getEPosInE() {
        return ePosInE;
    }

    @Override
    public Position getVPosInV(int i) {
        return vPosInV[i];
    }

    @Override
    public Position getEPosInI(int i) {
        return ePosInI[i];
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int setType(int t) {
        int bak = type;
        type = t;
        return bak;
    }
}
