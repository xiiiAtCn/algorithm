package dsa;

import dsa.interfaces.BinTree;
import dsa.interfaces.BinTreePosition;

public interface ComplBinTree extends BinTree {

    BinTreePosition addLast(Object object);

    Object delLast();

    BinTreePosition posOfNode(int i);

}
