package dsa.interfaces;

public interface Edge {
    //未知边
    int UNKNOWN = 0;

    //树边
    int TREE = 1;

    //横跨边
    int CROSS = 2;

    //前向跨边
    int FORWARD = 3;

    //后向跨边
    int BACKWARD = 4;

    //返回当前边的信息(对于带权图, 也就是各边的权重)
    Object getInfo();

    //将当前边的信息更新为x, 并返回原先的信息
    Object setInfo(Object x);

    //取当前边在所属的图的边集E中的位置
    Position getEPosInE();

    //取v[i]在顶点集V中的位置(i = 0或1, 分别对应于起点, 终点)
    Position getVPosInV(int i);

    //当前边在其两个端点的关联边集I(v[i])中的位置
    Position getEPosInI(int i);

    //读取, 设置边的类别(针对遍历)
    int getType();
    int setType(int t);
}
