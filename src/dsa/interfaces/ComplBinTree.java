package dsa.interfaces;

public interface ComplBinTree extends BinTree {

    BinTreePosition addLast(Object object);

    Object delLast();

    BinTreePosition posOfNode(int i);

}
