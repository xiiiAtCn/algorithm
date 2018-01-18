package dsa.interfaces;

public interface BinTreePosition<T> extends Position<T> {
    boolean hasParent();

    BinTreePosition<T> getParent();

    void setParent(BinTreePosition<T> parent);

    boolean isLeaf();

    boolean isLChild();

    boolean hasLChild();

    BinTreePosition<T> getLChild();

    void setLChild(BinTreePosition<T> lChild);

    boolean isRChild();

    boolean hasRChild();

    BinTreePosition<T> getRChild();

    void setRChild(BinTreePosition<T> rChild);

    int getSize();

    void updateSize();

    int getHeight();

    void updateHeight();

    int getDepth();

    void updateDepth();

    BinTreePosition<T> getPrev();

    BinTreePosition<T> getSucc();

    BinTreePosition<T> secede();

    BinTreePosition<T> attachL(BinTreePosition<T> c);

    BinTreePosition<T> attachR(BinTreePosition<T> c);

    Iterator<T> elementsPreOrder();

    Iterator<T> elementsInOrder();

    Iterator<T> elementsPostOrder();

    Iterator<T> elementsLevelOrder();
}
