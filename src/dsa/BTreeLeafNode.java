package dsa;

import dsa.interfaces.List;

/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/2 11:30
 * @description B树节点结构
 *
 */

public class BTreeLeafNode<K, V> {
    private K key;
    private V value;
    private BTreeLeafNode<K, V> leftParent;
    private BTreeLeafNode<K, V> rightParent;
    private BTreeLeafNode<K, V> leftSibling;
    private BTreeLeafNode<K, V> rightSibling;
    private List<BTreeLeafNode<K, V>> leftChildren;
    private List<BTreeLeafNode<K, V>> rightChildren;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BTreeLeafNode<K, V> getLeftParent() {
        return leftParent;
    }

    public void setLeftParent(BTreeLeafNode<K, V> leftParent) {
        this.leftParent = leftParent;
    }

    public BTreeLeafNode<K, V> getRightParent() {
        return rightParent;
    }

    public void setRightParent(BTreeLeafNode<K, V> rightParent) {
        this.rightParent = rightParent;
    }

    public BTreeLeafNode<K, V> getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(BTreeLeafNode<K, V> leftSibling) {
        this.leftSibling = leftSibling;
    }

    public BTreeLeafNode<K, V> getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(BTreeLeafNode<K, V> rightSibling) {
        this.rightSibling = rightSibling;
    }

    public List<BTreeLeafNode<K, V>> getLeftChildren() {
        return leftChildren;
    }

    public void setLeftChildren(List<BTreeLeafNode<K, V>> leftChildren) {
        this.leftChildren = leftChildren;
    }

    public List<BTreeLeafNode<K, V>> getRightChildren() {
        return rightChildren;
    }

    public void setRightChildren(List<BTreeLeafNode<K, V>> rightChildren) {
        this.rightChildren = rightChildren;
    }
}