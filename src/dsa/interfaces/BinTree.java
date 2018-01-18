package dsa.interfaces;

public interface BinTree<T> {
    BinTreePosition<T> getRoot();

    boolean isEmpty();

    int getSize();

    int getHeight();

    Iterator elementsPreOrder();

    Iterator elementsPostOrder();

    Iterator elementsLevelOrder();
}
